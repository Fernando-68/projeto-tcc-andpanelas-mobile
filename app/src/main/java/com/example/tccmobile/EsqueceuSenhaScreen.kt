package com.example.tccmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.PlayfairDisplay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin

private val FundoEsqueceu = Color(0xfffffcf8)
private val MarromEsqueceu = Color(0xff38251a)
private val VerdeEsqueceu = Color(0xff72c5a8)
private val BordaEsqueceu = Color(0xfff0e5d2)
private val TextoEsqueceu = Color(0xff66564b)
private val CinzaEsqueceu = Color(0xff9aa887)

private val VermelhoEsqueceu = Color(0xffD9534F)

@Composable
fun EsqueceuSenhaScreen(
    onVoltarLogin: () -> Unit,
    onEnviarCodigo: (String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }

    var tentouEnviar by rememberSaveable {
        mutableStateOf(false)
    }

//    val emailValido = validarEmail(email)
//
//    val emailComErro = tentouEnviar && !emailValido
//
//    val context = LocalContext.current
//
//    val emailDataStore = remember {
//        EmailDataStore(context)
//    }
//
//    val emailSalvo = emailDataStore.emailSalvo.collectAsState(
//        initial = ""
//    )
//
//    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoEsqueceu)
            .padding(horizontal = 12.dp)
    ) {
        // CABEÇALHO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(34.dp)
                    .border(
                        width = 1.dp,
                        color = BordaEsqueceu,
                        shape = CircleShape
                    )
                    .clickable {
                        onVoltarLogin()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = "Voltar para o Login",
                    modifier = Modifier.size(16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Image(
                painter = painterResource(
                    id = R.drawable.logo_alternativa
                ),
                contentDescription = "Logo &Panelas",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(110.dp)
                    .height(36.dp)
            )
        }

        // INDICADOR DE ETAPAS
        IndicadorEtapas(
            etapaAtual = 1
        )

        // CONTEÚDO PRINCIPAL
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Esqueceu a senha?",
                color = MarromEsqueceu,
                fontFamily = PlayfairDisplay,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sem problema! Informe seu e-mail cadastrado e enviaremos um código de verificação para redefinir sua senha.",
                color = TextoEsqueceu,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(18.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // AVISO INFORMATIVO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xffe7f4ee),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = VerdeEsqueceu,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "📬",
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Verifique sua caixa de entrada após o envio — o código chega em segundos!",
                    color = TextoEsqueceu,
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // CAMPO EMAIL
            Text(
                text = "E-mail cadastrado",
                color = MarromEsqueceu,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                textStyle = TextStyle(
                    color = MarromEsqueceu,
                    fontSize = 13.sp
                ),
                placeholder = {
                    Text(
                        text = "seu@email.com",
                        color = CinzaEsqueceu,
                        fontSize = 13.sp
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                shape = RoundedCornerShape(10.dp),
                isError = emailComErro,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = if (emailComErro) {
                        VermelhoEsqueceu
                    } else {
                        BordaEsqueceu
                    },
                    focusedBorderColor = if (emailComErro) {
                        VermelhoEsqueceu
                    } else {
                        VerdeEsqueceu
                    },
                    unfocusedContainerColor = FundoEsqueceu,
                    focusedContainerColor = FundoEsqueceu,
                    focusedTextColor = MarromEsqueceu,
                    unfocusedTextColor = MarromEsqueceu,
                    cursorColor = VerdeEsqueceu
                )
            )
            if (emailComErro) {
                Text(
                    text = "Digite um e-mail válido, usando letras minúsculas e números antes de @gmail.com.",
                    color = VermelhoEsqueceu,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // BOTÃO ENVIAR CÓDIGO
            Button(
                onClick = {
                    tentouEnviar = true
                    // A navegação para a próxima etapa será ligada depois
                    if (emailValido) {
                        onEnviarCodigo(email.trim())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeEsqueceu
                )
            ) {
                Text(
                    text = "Enviar código",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(17.dp))

            // VOLTAR AO LOGIN
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lembrou a senha? ",
                    color = CinzaEsqueceu,
                    fontSize = 12.sp
                )

                Text(
                    text = "Entrar",
                    color = VerdeEsqueceu,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onVoltarLogin()
                    }
                )
            }
        }
    }
}



@Composable
private fun IndicadorEtapas(
    etapaAtual: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // BARRAS SUPERIORES
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { indice ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(3.dp)
                        .background(
                            color = if (indice + 1 <= etapaAtual) {
                                VerdeEsqueceu
                            } else {
                                BordaEsqueceu
                            },
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                if (indice < 2) {
                    Spacer(modifier = Modifier.width(7.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // NOMES DAS ETAPAS E INDICADORES
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // PRIMEIRA ETAPA
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "1. IDENTIFICAÇÃO",
                    fontSize = 8.sp,
                    fontWeight = if (etapaAtual == 1) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    color = if (etapaAtual == 1) {
                        MarromEsqueceu
                    } else {
                        CinzaEsqueceu
                    },
                    maxLines = 1
                )

                if (etapaAtual >= 1) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        IndicadorSeparacao(
                            etapaConcluida = etapaAtual > 1,
                            etapaEmAndamento = etapaAtual == 1
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(7.dp))

            // SEGUNDA ETAPA
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "2. CÓDIGO ENVIADO",
                    fontSize = 8.sp,
                    fontWeight = if (etapaAtual == 2) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    color = if (etapaAtual == 2) {
                        MarromEsqueceu
                    } else {
                        CinzaEsqueceu
                    },
                    maxLines = 1
                )

                if (etapaAtual >= 2) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        IndicadorSeparacao(
                            etapaConcluida = etapaAtual > 2,
                            etapaEmAndamento = etapaAtual == 2
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(7.dp))

            // TERCEIRA ETAPA
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "3. NOVA SENHA",
                    fontSize = 8.sp,
                    fontWeight = if (etapaAtual == 3) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    color = if (etapaAtual == 3) {
                        MarromEsqueceu
                    } else {
                        CinzaEsqueceu
                    },
                    maxLines = 1
                )
            }
        }
    }
}


@Composable
private fun IndicadorSeparacao(
    etapaConcluida: Boolean,
    etapaEmAndamento: Boolean
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(9.dp),
        contentAlignment = Alignment.Center
    ) {

        // ETAPA CONCLUÍDA: DESENHA UM CHECK
        if (etapaConcluida) {

            Canvas(
                modifier = Modifier.size(9.dp)
            ) {
                val caminho = Path().apply {
                    moveTo(
                        size.width * 0.15f,
                        size.height * 0.50f
                    )

                    lineTo(
                        size.width * 0.40f,
                        size.height * 0.75f
                    )

                    lineTo(
                        size.width * 0.85f,
                        size.height * 0.25f
                    )
                }

                drawPath(
                    path = caminho,
                    color = VerdeEsqueceu,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            // ETAPA EM ANDAMENTO: DESENHA UM PONTO
        } else if (etapaEmAndamento) {

            Box(
                modifier = Modifier
                    .size(5.dp)
                    .background(
                        color = VerdeEsqueceu,
                        shape = CircleShape
                    )
            )
        }
    }
}



private fun validarEmail(email: String): Boolean {

    val emailLimpo = email.trim()

    val padraoEmail = Regex("^[a-z]+[a-z0-9]*@gmail\\.com$")

    if (!padraoEmail.matches(emailLimpo)) {
        return false
    }

    val usuario = emailLimpo.substringBefore("@")

    val quantidadeLetras = usuario.count {
        it in 'a'..'z'
    }

    val quantidadeNumeros = usuario.count {
        it in '0'..'9'
    }

    if (quantidadeLetras == 0) {
        return false
    }

    if (quantidadeLetras == 1 && quantidadeNumeros > 0) {
        return false
    }

    return true
}
