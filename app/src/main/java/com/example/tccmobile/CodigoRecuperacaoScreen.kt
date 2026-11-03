
package com.example.tccmobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.PlayfairDisplay
import kotlinx.coroutines.delay

private val FundoCodigo = Color(0xFFFFFCF8)
private val MarromCodigo = Color(0xFF38251A)
private val CinzaCodigo = Color(0xFF9A887D)
private val VerdeCodigo = Color(0xFF72C5A8)
private val BordaCodigo = Color(0xFFF0E5D2)
private val TextoCodigo = Color(0xFF66564B)
private val VermelhoCodigo = Color(0xFFD9534F)

private const val CODIGO_CORRETO = "000000"

@Composable
fun CodigoRecuperacaoScreen(
    email: String,
    onVoltar: () -> Unit,
    onVerificarCodigo: (String) -> Unit,
    onReenviarCodigo: () -> Unit
) {

    var codigo by rememberSaveable {
        mutableStateOf("")
    }

    var campoEmFoco by remember {
        mutableStateOf(false)
    }

    var mensagemErro by rememberSaveable {
        mutableStateOf("")
    }

    var mensagemSucesso by rememberSaveable {
        mutableStateOf("")
    }

    var contagemRegressiva by rememberSaveable {
        mutableStateOf(0)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    // CONTAGEM REGRESSIVA DO REENVIO

    LaunchedEffect(contagemRegressiva) {

        if (contagemRegressiva > 0) {

            delay(1000)

            contagemRegressiva--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoCodigo)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        // CABEÇALHO

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {

            // BOTÃO VOLTAR

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(42.dp)
                    .border(
                        width = 1.dp,
                        color = BordaCodigo,
                        shape = CircleShape
                    )
                    .clickable {
                        onVoltar()
                    },
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "Voltar",
                    modifier = Modifier.size(19.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // LOGOTIPO

            Image(
                painter = painterResource(
                    id = R.drawable.logo_alternativa
                ),
                contentDescription = "Logo &Panelas",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(150.dp)
                    .height(48.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // INDICADOR DE ETAPAS

        IndicadorEtapasCodigo(
            etapaAtual = 2
        )

        Spacer(
            modifier = Modifier.height(44.dp)
        )

        // CONTEÚDO PRINCIPAL

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // TÍTULO

            Text(
                text = "Código enviado!",
                color = MarromCodigo,
                fontFamily = PlayfairDisplay,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // MENSAGEM COM E-MAIL

            Text(
                text = buildAnnotatedString {

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append(
                            "Enviamos um código de 6 dígitos para "
                        )
                    }

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(email)
                    }

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append(
                            ". Insira-o abaixo para continuar:"
                        )
                    }
                },
                color = TextoCodigo,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // CAMPOS DO CÓDIGO

            BasicTextField(
                value = codigo,

                onValueChange = { novoCodigo ->

                    codigo = novoCodigo
                        .filter { it.isDigit() }
                        .take(6)

                    // Limpa as mensagens ao alterar o código

                    mensagemErro = ""
                    mensagemSucesso = ""
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { foco ->
                        campoEmFoco = foco.isFocused
                    },

                singleLine = true,

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color.Transparent
                ),

                cursorBrush = SolidColor(Color.Transparent),

                decorationBox = { innerTextField ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        repeat(6) { indice ->

                            val digitoAtual =
                                codigo.getOrNull(indice)?.toString() ?: ""

                            val caixaAtiva =
                                indice == codigo.length && campoEmFoco

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(58.dp)
                                    .padding(horizontal = 2.dp)
                                    .border(
                                        width = if (caixaAtiva) {
                                            2.dp
                                        } else {
                                            1.dp
                                        },
                                        color = if (caixaAtiva) {
                                            VerdeCodigo
                                        } else {
                                            BordaCodigo
                                        },
                                        shape = RoundedCornerShape(13.dp)
                                    )
                                    .clickable(
                                        interactionSource = remember {
                                            MutableInteractionSource()
                                        },
                                        indication = null
                                    ) {
                                        focusRequester.requestFocus()
                                    },
                                contentAlignment = Alignment.Center
                            ) {

                                if (digitoAtual.isNotEmpty()) {

                                    Text(
                                        text = digitoAtual,
                                        color = MarromCodigo,
                                        fontSize = 23.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                } else if (caixaAtiva) {

                                    Box(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(25.dp)
                                            .background(VerdeCodigo)
                                    )
                                }
                            }
                        }

                        // CAMPO REAL DE DIGITAÇÃO

                        Box(
                            modifier = Modifier.size(1.dp)
                        ) {
                            innerTextField()
                        }
                    }
                }
            )

            // MENSAGENS DE VALIDAÇÃO

            if (mensagemErro.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = mensagemErro,
                    color = VermelhoCodigo,
                    fontSize = 12.sp
                )

            } else if (mensagemSucesso.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = mensagemSucesso,
                    color = VerdeCodigo,
                    fontSize = 12.sp
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // BOTÃO VERIFICAR CÓDIGO

            Button(
                onClick = {

                    when {

                        codigo.isEmpty() -> {

                            mensagemErro =
                                "Informe o código recebido."
                        }

                        codigo.length < 6 -> {

                            mensagemErro =
                                "Digite os 6 dígitos do código."
                        }

                        codigo != CODIGO_CORRETO -> {

                            mensagemErro =
                                "Código inválido. Verifique e tente novamente."
                        }

                        else -> {

                            mensagemErro = ""
                            mensagemSucesso = ""

                            onVerificarCodigo(codigo)
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeCodigo,
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Verificar código",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(
                modifier = Modifier.height(38.dp)
            )

            // REENVIO DO CÓDIGO

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Não recebeu?",
                    color = CinzaCodigo,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // LINK DE REENVIO

                Text(
                    text = if (contagemRegressiva > 0) {
                        "Reenviar em ${contagemRegressiva}s"
                    } else {
                        "Reenviar código"
                    },

                    color = if (contagemRegressiva > 0) {
                        CinzaCodigo
                    } else {
                        VerdeCodigo
                    },

                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,

                    modifier = Modifier.clickable(
                        enabled = contagemRegressiva == 0
                    ) {

                        if (contagemRegressiva == 0) {

                            codigo = ""
                            mensagemErro = ""
                            mensagemSucesso =
                                "Código reenviado com sucesso!"

                            contagemRegressiva = 30

                            onReenviarCodigo()
                        }
                    }
                )
            }
        }
    }
}


// INDICADOR DAS TRÊS ETAPAS

@Composable
private fun IndicadorEtapasCodigo(
    etapaAtual: Int
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // BARRAS SUPERIORES

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            repeat(3) { indice ->

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(
                            color = if (indice + 1 <= etapaAtual) {
                                VerdeCodigo
                            } else {
                                BordaCodigo
                            },
                            shape = RoundedCornerShape(5.dp)
                        )
                )
            }
        }

        Spacer(
            modifier = Modifier.height(9.dp)
        )

        // NOMES DAS ETAPAS

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ETAPA 1

            Box(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "1. IDENTIFICAÇÃO",
                    color = CinzaCodigo,
                    fontSize = 10.sp,
                    maxLines = 1
                )

                Text(
                    text = "✓",
                    color = VerdeCodigo,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            // ETAPA 2

            Box(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "2. CÓDIGO ENVIADO",
                    color = MarromCodigo,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = "●",
                    color = VerdeCodigo,
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            // ETAPA 3

            Text(
                text = "3. NOVA SENHA",
                color = CinzaCodigo,
                fontSize = 10.sp,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
        }
    }
}