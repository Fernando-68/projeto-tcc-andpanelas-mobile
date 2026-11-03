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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.PlayfairDisplay

private val FundoNovaSenha = Color(0xFFFFFCF8)
private val MarromNovaSenha = Color(0xFF38251A)
private val CinzaNovaSenha = Color(0xFF9A887D)
private val VerdeNovaSenha = Color(0xFF72C5A8)
private val BordaNovaSenha = Color(0xFFF0E5D2)
private val TextoNovaSenha = Color(0xFF66564B)

@Composable
fun NovaSenhaScreen(
    onVoltar: () -> Unit,
    onSalvarNovaSenha: (String, String) -> Unit
) {

    var novaSenha by rememberSaveable {
        mutableStateOf("")
    }

    var repetirNovaSenha by rememberSaveable {
        mutableStateOf("")
    }

    var novaSenhaVisivel by rememberSaveable {
        mutableStateOf(false)
    }

    var repetirSenhaVisivel by rememberSaveable {
        mutableStateOf(false)
    }

    var tentouSalvar by rememberSaveable {
        mutableStateOf(false)
    }

    val senhaValida = validarNovaSenha(novaSenha)

    val confirmacaoValida = repetirNovaSenha == novaSenha

    val mostrarErroNovaSenha = tentouSalvar && !senhaValida

    val mostrarErroConfirmacao = tentouSalvar && !confirmacaoValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoNovaSenha)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        // CABEÇALHO

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

            // BOTÃO VOLTAR

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(42.dp)
                    .border(
                        width = 1.dp,
                        color = BordaNovaSenha,
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

        IndicadorEtapasNovaSenha(
            etapaAtual = 3
        )

        Spacer(
            modifier = Modifier.height(48.dp)
        )

        // TÍTULO

        Text(
            text = "Crie uma nova senha",
            color = MarromNovaSenha,
            fontFamily = PlayfairDisplay,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // TEXTO EXPLICATIVO

        Text(
            text = "Crie uma senha segura com pelo menos 8 caracteres para garantir a segurança da sua conta.",
            color = TextoNovaSenha,
            fontSize = 15.sp,
            lineHeight = 23.sp
        )

        Spacer(
            modifier = Modifier.height(48.dp)
        )

        // CAMPO NOVA SENHA

        Text(
            text = "Nova Senha",
            color = MarromNovaSenha,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = novaSenha,

            onValueChange = {
                novaSenha = it
            },

            modifier = Modifier.fillMaxWidth(),

            placeholder = {
                Text(
                    text = "Crie sua nova senha",
                    color = CinzaNovaSenha,
                    fontSize = 17.sp
                )
            },

            singleLine = true,

            isError = mostrarErroNovaSenha,

            visualTransformation = if (novaSenhaVisivel) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            trailingIcon = {
                TextButton(
                    onClick = {
                        novaSenhaVisivel = !novaSenhaVisivel
                    }
                ) {
                    Text(
                        text = if (novaSenhaVisivel) {
                            "Ocultar"
                        } else {
                            "Mostrar"
                        },
                        color = VerdeNovaSenha,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },

            shape = RoundedCornerShape(18.dp),

            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (mostrarErroNovaSenha) {
                    Color(0xFFB3261E)
                } else {
                    BordaNovaSenha
                },
                focusedBorderColor = if (mostrarErroNovaSenha) {
                    Color(0xFFB3261E)
                } else {
                    VerdeNovaSenha
                },
                errorBorderColor = Color(0xFFB3261E),
                unfocusedContainerColor = FundoNovaSenha,
                focusedContainerColor = FundoNovaSenha,
                errorContainerColor = FundoNovaSenha,
                cursorColor = VerdeNovaSenha
            ),

            supportingText = {
                if (mostrarErroNovaSenha) {
                    Text(
                        text = "A nova senha deve possuir pelo menos 8 caracteres, incluindo maiúscula, minúscula, número e caractere especial.",
                        color = Color(0xffb3261e),
                        fontSize = 12.sp
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // CAMPO REPETIR NOVA SENHA

        Text(
            text = "Repita Nova Senha",
            color = MarromNovaSenha,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = repetirNovaSenha,

            onValueChange = {
                repetirNovaSenha = it
            },

            modifier = Modifier.fillMaxWidth(),

            placeholder = {
                Text(
                    text = "Confirme sua nova senha",
                    color = CinzaNovaSenha,
                    fontSize = 17.sp
                )
            },

            singleLine = true,
            isError = mostrarErroConfirmacao,

            visualTransformation = if (repetirSenhaVisivel) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            trailingIcon = {
                TextButton(
                    onClick = {
                        repetirSenhaVisivel = !repetirSenhaVisivel
                    }
                ) {
                    Text(
                        text = if (repetirSenhaVisivel) {
                            "Ocultar"
                        } else {
                            "Mostrar"
                        },
                        color = VerdeNovaSenha,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },

            shape = RoundedCornerShape(18.dp),

            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (mostrarErroConfirmacao) {
                    Color(0xFFB3261E)
                } else {
                    BordaNovaSenha
                },
                focusedBorderColor = if (mostrarErroConfirmacao) {
                    Color(0xFFB3261E)
                } else {
                    VerdeNovaSenha
                },
                errorBorderColor = Color(0xFFB3261E),
                unfocusedContainerColor = FundoNovaSenha,
                focusedContainerColor = FundoNovaSenha,
                errorContainerColor = FundoNovaSenha,
                cursorColor = VerdeNovaSenha
            ),

            supportingText = {
                if (mostrarErroConfirmacao) {
                    Text(
                        text = if (repetirNovaSenha.isBlank()) {
                            "Confirme sua nova senha."
                        } else {
                            "As senhas não coincidem."
                        },
                        color = Color(0xffb3261e),
                        fontSize = 12.sp
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        // BOTÃO SALVAR NOVA SENHA

        Button(
            onClick = {
                tentouSalvar = true

                if (senhaValida && confirmacaoValida) {
                    onSalvarNovaSenha(
                        novaSenha,
                        repetirNovaSenha
                    )
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeNovaSenha,
                contentColor = Color.White
            )
        ) {

            Text(
                text = "Salvar Nova Senha",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}


// INDICADOR DAS TRÊS ETAPAS

@Composable
private fun IndicadorEtapasNovaSenha(
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
                                VerdeNovaSenha
                            } else {
                                BordaNovaSenha
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
                    color = CinzaNovaSenha,
                    fontSize = 10.sp,
                    maxLines = 1
                )

                Text(
                    text = "✓",
                    color = VerdeNovaSenha,
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
                    color = CinzaNovaSenha,
                    fontSize = 10.sp,
                    maxLines = 1
                )

                Text(
                    text = "✓",
                    color = VerdeNovaSenha,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            // ETAPA 3

            Box(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "3. NOVA SENHA",
                    color = MarromNovaSenha,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = "●",
                    color = VerdeNovaSenha,
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}

// VALIDAÇÃO DA SENHA

private fun validarNovaSenha(senha: String): Boolean {

    val temMaiuscula = senha.any {
        it.isUpperCase()
    }

    val temMinuscula = senha.any {
        it.isLowerCase()
    }

    val temNumero = senha.any {
        it.isDigit()
    }

    val temEspecial = senha.any {
        !it.isLetterOrDigit() && !it.isWhitespace()
    }

    return senha.length >= 8 &&
            temMaiuscula &&
            temMinuscula &&
            temNumero &&
            temEspecial
}