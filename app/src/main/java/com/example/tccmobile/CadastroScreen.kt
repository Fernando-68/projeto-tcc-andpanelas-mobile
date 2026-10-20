package com.example.tccmobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.PlayfairDisplay

private val FundoCadastro = Color(0xfffffcf8)
private val MarromCadastro = Color(0xff38251a)
private val CinzaCadastro = Color(0xff9aa887)
private val VerdeCadastro = Color(0xff72c5a8)
private val BordaCadastro = Color(0xfff0e5d2)

@Composable
fun CadastroScreen (
    onVoltarLogin: () -> Unit
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var usuario by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var dataNascimento by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var senhaVisivel by rememberSaveable { mutableStateOf(false) }
    var aceitouTermos by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoCadastro)
            .verticalScroll(rememberScrollState())
    ) {
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
                    .padding(start = 24.dp, bottom = 20.dp)
                    .width(190.dp)
                    .height(55.dp)
                    .align(Alignment.BottomStart)
            )
        }

        // CONTEÚDO DO CADASTRO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 28.dp)
        ) {
            Text(
                text = "Crie sua conta",
                color = MarromCadastro,
                fontFamily = PlayfairDisplay,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Junte-se à maior comunidade de culinária",
                color = CinzaCadastro,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(26.dp))


            // FOTO DE PERFIL
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // CÍRCULO TRACEJADO COM A CÂMERA CENTRALIZADA
                Box(
                    modifier = Modifier.size(88.dp),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.foundation.Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        drawCircle(
                            color = CinzaCadastro,
                            radius = size.minDimension / 2 - 2.dp.toPx(),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(
                                width = 1.5.dp.toPx(),
                                pathEffect = androidx.compose.ui.graphics.PathEffect
                                    .dashPathEffect(
                                        floatArrayOf(
                                            7.dp.toPx(),
                                            5.dp.toPx()
                                        )
                                    )
                            )
                        )
                    }

                    Image(
                        painter = painterResource(
                            id = R.drawable.camera
                        ),
                        contentDescription = "Adicionar foto de perfil",
                        modifier = Modifier.size(36.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // TEXTOS AO LADO DA CÂMERA
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Foto de perfil",
                        color = MarromCadastro,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Adicione sua melhor foto",
                        color = CinzaCadastro,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Carregar foto",
                        color = VerdeCadastro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            // Ação de carregar foto será implementada depois.
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            //NOME COMPLETO
            CampoCadastro(
                rotulo = "Nome de usuário",
                valor = usuario,
                aoAlterar = { usuario = it },
                placeholder = "@seuusuario"
            )

            Spacer(modifier = Modifier.height(18.dp))

            //EMAIL
            CampoCadastro(
                rotulo = "E-mail",
                valor = email,
                aoAlterar = { email = it },
                placeholder = "seu@email.com"
            )

            Spacer(modifier = Modifier.height(18.dp))

            //DATA DE NASCIMENTO
            CampoCadastro(
                rotulo = "Data de nascimento",
                valor = dataNascimento,
                aoAlterar = { dataNascimento = it },
                placeholder = "DD/MM/AAAA"
            )

            Spacer(modifier = Modifier.height(18.dp))

            //SENHA
            Text(
                text = "Senha",
                color = MarromCadastro,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                placeholder = {
                    Text(
                        text = "Crie uma senha forte",
                        color = CinzaCadastro,
                        fontSize = 16.sp
                    )
                },
                singleLine = true,
                visualTransformation = if (senhaVisivel) {
                    androidx.compose.ui.text.input.VisualTransformation.None
                } else {
                    androidx.compose.ui.text.input.PasswordVisualTransformation()
                },
                trailingIcon = {
                    Text(
                        text = if (senhaVisivel) "Ocultar" else "Mostrar",
                        color = VerdeCadastro,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            senhaVisivel = !senhaVisivel
                        }
                    )
                },
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = BordaCadastro,
                    focusedBorderColor = VerdeCadastro,
                    unfocusedContainerColor = FundoCadastro,
                    focusedContainerColor = FundoCadastro,
                    focusedTextColor = MarromCadastro,
                    unfocusedTextColor = MarromCadastro,
                    cursorColor = VerdeCadastro
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            //ACEITOU OS TERMOS
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = aceitouTermos,
                    onCheckedChange = {
                        aceitouTermos = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = VerdeCadastro,
                        uncheckedColor = BordaCadastro,
                        checkmarkColor = Color.White
                    )
                )

                Text(
                    text = "Li e concordo com os Termos de Uso e Privacidade.",
                    color = CinzaCadastro,
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            //BOTÃO CRIAR CONTA
            Button(
                onClick = {
                    //A validação será implementada depois.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeCadastro
                )
            ) {
                Text(
                    text = "Criar conta",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            //VOLTAR PARA O LOGIN
            Text(
                text = "Já tem uma conta? Entrar",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onVoltarLogin()
                    }
                    .padding(vertical = 12.dp),
                color = VerdeCadastro,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

    @Composable
    private fun CampoCadastro(
        rotulo: String,
        valor: String,
        aoAlterar: (String) -> Unit,
        placeholder: String
    ) {
        Column {
            Text(
                text = rotulo,
                color = MarromCadastro,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = valor,
                onValueChange = aoAlterar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                placeholder = {
                    Text(
                        text = placeholder,
                        color = CinzaCadastro,
                        fontSize = 16.sp
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = BordaCadastro,
                    focusedBorderColor = VerdeCadastro,
                    unfocusedContainerColor = FundoCadastro,
                    focusedContainerColor = FundoCadastro,
                    focusedTextColor = MarromCadastro,
                    unfocusedTextColor = MarromCadastro,
                    cursorColor = VerdeCadastro
                )
            )
        }
    }