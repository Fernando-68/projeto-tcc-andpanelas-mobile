package com.example.tccmobile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.PlayfairDisplay

private val FundoVerificacao = Color(0xfffffcf8)
private val MarromVerificacao = Color(0xff38251a)
private val VerdeVerificacao = Color(0xff72c5a8)
private val BordaVerificacao = Color(0xfff0e5d2)
private val CinzaVerificacao = Color(0xff9aa887)

@Composable
fun VerificacaoCodigoScreen(
    email: String,
    onVerificarClick: (String) -> Unit,
    onReenviarCodigo: () -> Unit
) {
    var codigo by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoVerificacao)
    ) {
        // CABEÇALHO COM IMAGEM E LOGO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.hero_area
                ),
                contentDescription = "Cozinha do &Panelas",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.38f))
            )

            Image(
                painter = painterResource(
                    id = R.drawable.logo_panelas
                ),
                contentDescription = "Logo &Panelas",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
                    .width(150.dp)
                    .height(45.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            // IDENTIFICAÇÃO DA VERIFICAÇÃO
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xffe4f3ec),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
            ) {
                Text(
                    text =  "♙  Verificação de Segurança",
                    color = VerdeVerificacao,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enviamos um código de 6 digitos para",
                color = Color(0xff66564b),
                fontSize = 12.sp
            )
            Text(
                text = email,
                color = MarromVerificacao,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(20.dp))

            // CAMPO DE CÓDIGO COM SEIS DIGITOS
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = codigo,
                    onValueChange = { novoCodigo ->
                        if (
                            novoCodigo.length <= 6 &&
                            novoCodigo.all { it.isDigit() }
                        ) {
                            codigo = novoCodigo
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Transparent,
                        fontSize = 1.sp
                    ),
                    decorationBox = { innerTextField ->
                        Box{
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {
                                repeat(6) { indice ->
                                    val selecionado =
                                        indice == codigo.length

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(42.dp)
                                            .padding(horizontal = 2.dp)
                                            .border(
                                                width = if (selecionado) {
                                                    1.5.dp
                                                } else {
                                                    1.dp
                                                },
                                                color = if (selecionado) {
                                                    VerdeVerificacao
                                                } else {
                                                    BordaVerificacao
                                                },
                                                shape = RoundedCornerShape(10.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = codigo.getOrNull(indice)
                                                ?.toString() ?: "",
                                            color = MarromVerificacao,
                                            fontSize = 18.sp
                                        )
                                    }
                                }
                            }

                            // MANTÉM O CAMPO REAL ATIVO PARA DIGITAÇÃO
                            Box(
                                modifier = Modifier
                                    .size(1.dp)
                                    .align(Alignment.CenterStart)
                            ) {
                                innerTextField()
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // BOTÃO DE VERIFICAÇÃO
            Button(
                onClick = {
                    onVerificarClick(codigo)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeVerificacao
                )
            ) {
                Text(
                    text = "Verificar código",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Não recebeu?",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xff9a8a7e),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Reenviar código",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{
                        onReenviarCodigo()
                    },
                color = VerdeVerificacao,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}