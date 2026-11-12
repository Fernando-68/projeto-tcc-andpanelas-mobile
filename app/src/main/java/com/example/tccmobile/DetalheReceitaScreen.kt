
package com.example.tccmobile

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.tccmobile.ui.theme.PlayfairDisplay
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest

private val FundoDetalhe = Color(0xFFFFFCF8)
private val MarromDetalhe = Color(0xFF38251A)
private val VerdeDetalhe = Color(0xFF72C5A8)
private val CinzaDetalhe = Color(0xFF9A887D)
private val BordaDetalhe = Color(0xFFEDE2D0)

@Composable
fun DetalheReceitaScreen(
    onVoltar: () -> Unit
) {

    var abaSelecionada by remember {
        mutableStateOf("Ingredientes")
    }

    var curtida by remember {
        mutableStateOf(false)
    }

    var salva by remember {
        mutableStateOf(false)
    }

    var seguindo by remember {
        mutableStateOf(false)
    }

    var comentario by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDetalhe)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        // CONTEÚDO ROLÁVEL

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            // IMAGEM PRINCIPAL

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {

                Image(
                    painter = painterResource(
                        R.drawable.frango_ao_limao_siciliano
                    ),
                    contentDescription = "Frango ao Limão Siciliano",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // VOLTAR

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(FundoDetalhe)
                        .clickable { onVoltar() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "‹",
                        color = MarromDetalhe,
                        fontSize = 34.sp
                    )
                }

                // CATEGORIA

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5B83D))
                        .padding(
                            horizontal = 14.dp,
                            vertical = 7.dp
                        )
                ) {
                    Text(
                        text = "ALMOÇO",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // TÍTULO

                Text(
                    text = "Frango ao Limão Siciliano",
                    color = MarromDetalhe,
                    fontFamily = PlayfairDisplay,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Uma receita deliciosa e perfumada de frango marinado em suco de limão siciliano com ervas frescas. Pronto em menos de 1 hora!",
                    color = CinzaDetalhe,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // AUTORA

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFEFD4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AC",
                            color = Color(0xFFFFB52E),
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Ana Costa",
                            color = MarromDetalhe,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = if (seguindo) "Seguindo" else "+ Seguir",
                            color = VerdeDetalhe,
                            fontSize = 13.sp,
                            modifier = Modifier.clickable {
                                seguindo = !seguindo
                            }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "⚑ Denunciar",
                        color = CinzaDetalhe,
                        fontSize = 11.sp,
                        modifier = Modifier.clickable {
                            // Ação de denúncia será integrada posteriormente.
                        }
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // INFORMAÇÕES

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    InformacaoReceita(
                        simbolo = "◷",
                        valor = "40 min",
                        legenda = "Tempo",
                        modifier = Modifier.weight(1f)
                    )

                    InformacaoReceita(
                        simbolo = "♨",
                        valor = "Fácil",
                        legenda = "Dificuldade",
                        modifier = Modifier.weight(1f)
                    )

                    InformacaoReceita(
                        simbolo = "♡",
                        valor = "Moderado",
                        legenda = "Custo",
                        modifier = Modifier.weight(1f)
                    )

                    InformacaoReceita(
                        simbolo = "♧",
                        valor = "4 porções",
                        legenda = "Porções",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // ETIQUETAS

                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    EtiquetaReceita("Frango")
                    EtiquetaReceita("Rápido")
                    EtiquetaReceita("Fresco")
                    EtiquetaReceita("Saudável")
                }

                Spacer(modifier = Modifier.height(18.dp))

                // AÇÕES

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    AcaoReceita(
                        texto = if (curtida) "343" else "342",
                        simbolo = if (curtida) "♥" else "♡",
                        modifier = Modifier.weight(1f),
                        selecionada = curtida,
                        onClick = { curtida = !curtida }
                    )

                    AcaoReceita(
                        texto = if (salva) "Salvo" else "Salvar",
                        simbolo = "▣",
                        modifier = Modifier.weight(1f),
                        selecionada = salva,
                        onClick = { salva = !salva }
                    )

                    Box(
                        modifier = Modifier
                            .weight(1.1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(13.dp))
                            .background(VerdeDetalhe)
                            .clickable {
                                // Adicionar receita será integrado à lista depois.
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+ Adicionar",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                // VÍDEO

                Text(
                    text = "Vídeo de passo a passo",
                    color = MarromDetalhe,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                VideoReceita()

                Spacer(modifier = Modifier.height(22.dp))

                // ABAS INGREDIENTES / PREPARO

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = BordaDetalhe,
                            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                ) {

                    AbaDetalheReceita(
                        texto = "Ingredientes",
                        selecionada = abaSelecionada == "Ingredientes",
                        modifier = Modifier.weight(1f)
                    ) {
                        abaSelecionada = "Ingredientes"
                    }

                    AbaDetalheReceita(
                        texto = "Modo De Preparo",
                        selecionada = abaSelecionada == "Modo de Preparo",
                        modifier = Modifier.weight(1f)
                    ) {
                        abaSelecionada = "Modo de Preparo"
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (abaSelecionada == "Ingredientes") {

                    IngredienteReceita("Filé de frango", "800g")
                    IngredienteReceita("Limão siciliano", "2 unidades")
                    IngredienteReceita("Alho", "4 dentes")
                    IngredienteReceita("Azeite extra virgem", "3 col. de sopa")
                    IngredienteReceita("Tomilho fresco", "3 ramos")
                    IngredienteReceita("Sal e pimenta-do-reino", "a gosto")
                    IngredienteReceita("Manteiga", "3 col. de sopa")

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(VerdeDetalhe)
                            .clickable {
                                // Adicionar todos os ingredientes à lista futuramente.
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Adicionar tudo à lista de compras",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                } else {

                    val etapas = listOf(
                        "Tempere os filés de frango com sal, pimenta e suco de limão siciliano.",
                        "Aqueça o azeite em uma frigideira e doure os dentes de alho.",
                        "Adicione o frango e cozinhe até dourar dos dois lados.",
                        "Acrescente o tomilho e o restante do limão.",
                        "Finalize com manteiga e cozinhe até formar um molho leve.",
                        "Sirva acompanhado de arroz ou legumes."
                    )

                    etapas.forEachIndexed { indice, etapa ->
                        EtapaPreparoReceita(
                            numero = indice + 1,
                            texto = etapa
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // COMENTÁRIOS

                Text(
                    text = "Comentários (4)",
                    color = MarromDetalhe,
                    fontFamily = PlayfairDisplay,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(VerdeDetalhe)
                            .clickable {
                                // Envio do comentário será implementado depois.
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "➤",
                            color = Color.White
                        )
                    }

                    androidx.compose.material3.OutlinedTextField(
                        value = comentario,
                        onValueChange = { comentario = it },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text("Escreva um comentário...")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ComentarioReceita(
                    iniciais = "PS",
                    usuario = "@pedrosouza",
                    texto = "Fiz essa receita ontem e ficou incrível! O frango ficou super suculento 🍋"
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // NAVEGAÇÃO INFERIOR FIXA

        NavegacaoInferiorHome()
    }
}

// INFORMAÇÕES DA RECEITA

@Composable
private fun InformacaoReceita(
    simbolo: String,
    valor: String,
    legenda: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .height(76.dp)
            .border(1.dp, BordaDetalhe, RoundedCornerShape(13.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = simbolo,
            color = VerdeDetalhe,
            fontSize = 19.sp
        )

        Text(
            text = valor,
            color = MarromDetalhe,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = legenda,
            color = CinzaDetalhe,
            fontSize = 10.sp
        )
    }
}

// ETIQUETAS

@Composable
private fun EtiquetaReceita(texto: String) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color(0xFFE8DDC8))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = texto,
            color = MarromDetalhe,
            fontSize = 11.sp
        )
    }
}

// BOTÕES DE AÇÃO

@Composable
private fun AcaoReceita(
    texto: String,
    simbolo: String,
    modifier: Modifier = Modifier,
    selecionada: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BordaDetalhe, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = simbolo,
            color = if (selecionada) VerdeDetalhe else CinzaDetalhe,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = texto,
            color = MarromDetalhe,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
    }
}

// ABAS DE CONTEÚDO

@Composable
private fun AbaDetalheReceita(
    texto: String,
    selecionada: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(44.dp)
            .background(
                if (selecionada) MarromDetalhe else FundoDetalhe
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = texto,
            color = if (selecionada) Color.White else CinzaDetalhe,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// INGREDIENTES

@Composable
private fun IngredienteReceita(
    nome: String,
    quantidade: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .border(1.dp, BordaDetalhe, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = nome,
            color = MarromDetalhe,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = quantidade,
            color = VerdeDetalhe,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ETAPAS DO PREPARO

@Composable
private fun EtapaPreparoReceita(
    numero: Int,
    texto: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(VerdeDetalhe),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = numero.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = texto,
            color = MarromDetalhe,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

// COMENTÁRIOS

@Composable
private fun ComentarioReceita(
    iniciais: String,
    usuario: String,
    texto: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFEBD0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = iniciais,
                color = Color(0xFFE66A25),
                fontSize = 14.sp
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .border(
                    1.dp,
                    BordaDetalhe,
                    RoundedCornerShape(14.dp)
                )
                .background(Color.White, RoundedCornerShape(14.dp))
                .padding(12.dp)
        ) {

            Text(
                text = usuario,
                color = VerdeDetalhe,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = texto,
                color = MarromDetalhe,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "✎     ▤",
                color = VerdeDetalhe,
                fontSize = 17.sp
            )
        }
    }
}

// PLAYER DO YOUTUBE


@Composable
private fun VideoReceita() {

    val contexto = LocalContext.current

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(13.dp)),

        factory = {

            WebView(contexto).apply {

                // CONFIGURAÇÃO DO PLAYER

                setBackgroundColor(android.graphics.Color.BLACK)

                webViewClient = object : WebViewClient() {

                    override fun onReceivedError(
                        view: WebView,
                        request: WebResourceRequest,
                        error: WebResourceError
                    ) {
                        super.onReceivedError(view, request, error)

                        Log.e(
                            "VideoReceita",
                            "Erro ao carregar: ${error.description}"
                        )
                    }
                }

                webChromeClient = WebChromeClient()

                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadsImagesAutomatically = true
                settings.mediaPlaybackRequiresUserGesture = true

                // PERMISSÃO PARA COOKIES DE TERCEIROS

                CookieManager.getInstance()
                    .setAcceptThirdPartyCookies(this, true)

                // CARREGAMENTO DO VÍDEO

                val html = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta name="viewport"
                              content="width=device-width,
                              initial-scale=1.0">

                        <style>
                            html, body {
                                margin: 0;
                                padding: 0;
                                width: 100%;
                                height: 100%;
                                background: #000;
                                overflow: hidden;
                            }

                            iframe {
                                width: 100%;
                                height: 100%;
                                border: 0;
                            }
                        </style>
                    </head>

                    <body>

                        <iframe
                            src="https://www.youtube-nocookie.com/embed/9zPc5lVEIQA?playsinline=1&rel=0"
                            title="Frango ao Limão Siciliano"
                            allow="accelerometer;
                                   autoplay;
                                   clipboard-write;
                                   encrypted-media;
                                   gyroscope;
                                   picture-in-picture;
                                   web-share"
                            allowfullscreen>
                        </iframe>

                    </body>
                    </html>
                """.trimIndent()

                loadDataWithBaseURL(
                    "https://www.youtube-nocookie.com",
                    html,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },

        update = {
            // Mantém o player durante as recomposições.
        }
    )
}