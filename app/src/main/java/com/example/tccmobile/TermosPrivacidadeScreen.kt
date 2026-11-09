package com.example.tccmobile

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

private val FundoTermos = Color(0xfffffcf8)
private val MarromTermos = Color(0xff38251a)
private val VerdeTermos = Color(0xff72c5a8)

@Composable
fun TermosPrivacidadeScreen(
    onvoltar: () -> Unit
) {
    val context = LocalContext.current

    var paginas by remember {
        mutableStateOf<List<Bitmap>>(emptyList())
    }

    var carregando by remember { mutableStateOf(true) }

    var erroCarregamento by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            paginas = withContext(Dispatchers.IO) {
                val arquivoPdf = File(
                    context.cacheDir,
                    "Termos_de_Uso_e_Privacidade_Panelas.pdf"
                )

                context.assets.open(
                    "Termos_de_Uso_e_Privacidade_Panelas.pdf"
                ).use { entrada ->

                    FileOutputStream(arquivoPdf).use { saida ->
                        entrada.copyTo(saida)
                    }
                }

                val descritor = ParcelFileDescriptor.open(
                    arquivoPdf,
                    ParcelFileDescriptor.MODE_READ_ONLY
                )

                val renderer = PdfRenderer(descritor)

                val imagens = mutableStateListOf<Bitmap>()

                try {
                    for (indice in 0 until renderer.pageCount) {

                        coroutineContext.ensureActive()

                        val pagina = renderer.openPage(indice)

                        try {
                            val largura = 1200
                            val altura = (
                                    largura.toFloat() *
                                    pagina.height / pagina.width
                                    ).toInt()

                            val bitmap = Bitmap.createBitmap(
                                largura,
                                altura,
                                Bitmap.Config.ARGB_8888
                            )

                            pagina.render(
                                bitmap,
                                null,
                                null,
                                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                            )

                            imagens.add(bitmap)
                        } finally {
                            pagina.close()
                        }
                    }
                } finally {
                    renderer.close()
                    descritor.close()
                }

                imagens
            }
        } catch (e: Exception) {
            erroCarregamento = true
        } finally {
            carregando = false
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(FundoTermos)
    ) {

        // CABEÇALHO
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<",
                color = MarromTermos,
                fontSize = 32.sp,
                modifier = Modifier.clickable{ onvoltar() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Termos de Uso e Privacidade",
                color = MarromTermos,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        when {
            carregando -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = VerdeTermos
                    )
                }
            }

            erroCarregamento -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Não foi possível carregar o documento. Verifique se o arquivo PDF está na pasta assets.",
                        color = MarromTermos,
                        fontSize = 16.sp
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(paginas) { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Página do documento de termos",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                )
                        )
                    }
                }
            }
        }
    }
}