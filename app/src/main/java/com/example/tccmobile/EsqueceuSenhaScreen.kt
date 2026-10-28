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

private val FundoEsqueceu = Color(0xfffffcf8)
private val MarromEsqueceu = Color(0xff38251a)
private val VerdeEsqueceu = Color(0xff72c5a8)
private val BordaEsqueceu = Color(0xfff0e5d2)
private val TextoEsqueceu = Color(0xff66564b)
private val CinzaEsqueceu = Color(0xff9aa887)

@Composable
fun EsqueceuSenhaScreen(
    onVoltarLogin: () -> Unit,
    onEnviarCodigo: (String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(VerdeEsqueceu, RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "1. IDENTIFICAÇÃO",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MarromEsqueceu
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(BordaEsqueceu, RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "2. CÓDIGO ENVIADO",
                    fontSize = 10.sp,
                    color = CinzaEsqueceu
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(BordaEsqueceu, RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "3. NOVA SENHA",
                    fontSize = 10.sp,
                    color = CinzaEsqueceu
                )
            }
        }

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

                Spacer(modifier = Modifier.height(8.dp))

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
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = BordaEsqueceu,
                    focusedBorderColor = VerdeEsqueceu,
                    unfocusedContainerColor = FundoEsqueceu,
                    focusedContainerColor = FundoEsqueceu,
                    focusedTextColor = MarromEsqueceu,
                    unfocusedTextColor = MarromEsqueceu,
                    cursorColor = VerdeEsqueceu
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            // BOTÃO ENVIAR CÓDIGO
            Button(
                onClick = {
                    // A navegação para a próxima etapa será ligada depois
                    onEnviarCodigo(email.trim())
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