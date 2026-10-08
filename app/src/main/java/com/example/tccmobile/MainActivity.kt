package com.example.tccmobile

import android.R.attr.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.TCCMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TCCMobileTheme {
                TCCMobileTheme {
                    LoginScreen()
                }

            }
        }
    }
    }

private val Fundo = Color(0xFFFFFCF8)
private val Marrom = Color(0xFF38251A)
private val CinzaTexto = Color(0xFF9A887D)
private val Verde = Color(0xFF6BC2A5)
private val VerdeEscuro = Color(0xFF57AE91)
private val Borda = Color(0xFFEDE2D0)
private val Amarelo = Color(0xFFE7A52E)
private val VermelhoErro = Color(0xFFB3261E)

@Composable
fun LoginScreen() {
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var senha by rememberSaveable {
        mutableStateOf("")
    }

    var senhaVisivel by rememberSaveable {
        mutableStateOf(false)
    }

    var tentouEntrar by rememberSaveable {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current


    // VALIDAÇÕES DE CAMPO

    val emailValido = email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS
                .matcher(email.trim())
                .matches()

    val senhaValida = senha.length >= 15
    val formularioValido = emailValido && senhaValida

    val mostrarErroEmail = tentouEntrar && !emailValido
    val mostrarErroSenha = tentouEntrar && !senhaValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        // HERO

        HeroArea()


        // FORMULÁRIO

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                    top = 28.dp,
                    bottom = 24.dp
                )
        ) {

            // TÍTULO

            Text(
                text = "Bem-vindo de volta!",
                color = Marrom,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Entre na sua conta para continuar",
                color = CinzaTexto,
                fontSize = 17.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // E-MAIL

            Text(
                text = "E-mail",
                color = Marrom,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = email,

                onValueChange = {
                    email = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        text = "seu@email",
                        color = CinzaTexto,
                        fontSize = 17.sp
                    )
                },

                singleLine = true,

                isError = mostrarErroEmail,

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),

                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(
                            androidx.compose.ui.focus.FocusDirection.Down
                        )
                    }
                ),

                shape = androidx.compose.foundation.shape.RoundedCornerShape(
                    18.dp
                ),

                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Borda,
                    focusedBorderColor = Verde,
                    errorBorderColor = VermelhoErro,
                    unfocusedContainerColor = Fundo,
                    focusedContainerColor = Fundo,
                    errorContainerColor = Fundo,
                    cursorColor = Verde
                ),

                supportingText = {
                    if (mostrarErroEmail) {
                        Text(
                            text = "Digite um E-mail válido.",
                            color = VermelhoErro
                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // SENHA

            Text(
                text = "Senha",
                color = Marrom,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = senha,

                onValueChange = {
                    senha = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        text = "Digite sua senha",
                        color = CinzaTexto,
                        fontSize = 17.sp
                    )
                },

                singleLine = true,

                isError = mostrarErroSenha,

                visualTransformation = if (senhaVisivel) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),

                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()

                        if (formularioValido) {
                            tentouEntrar = true
                        } else {
                            tentouEntrar = true
                        }
                    }
                ),

                trailingIcon = {
                    TextButton(
                        onClick = {
                            senhaVisivel = !senhaVisivel
                        }
                    ) {
                        Text(
                            text = if (senhaVisivel) {
                                "Ocultar"
                            } else {
                                "Mostrar"
                            },
                            color = Verde,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },

                shape = androidx.compose.foundation.shape.RoundedCornerShape(
                    18.dp
                ),

                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Borda,
                    focusedBorderColor = Verde,
                    errorBorderColor = VermelhoErro,
                    unfocusedContainerColor = Fundo,
                    focusedContainerColor = Fundo,
                    errorContainerColor = Fundo,
                    cursorColor = Verde
                ),

                supportingText = {
                    if (mostrarErroSenha) {
                        Text(
                            text = "A senha deve possuir pelo menos 15 caracteres.",
                            color = VermelhoErro
                        )
                    }
                }
            )

            // ESQUECI A SENHA

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        //futuramente navegar para recuperação de senha
                    }
                ) {
                    Text(
                        text = "Esqueci a senha",
                        color = Verde,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // BOTÃO ENTRAR

            Button(
                onClick = {
                    tentouEntrar = true

                    if (formularioValido) {
                        focusManager.clearFocus()

                        //futuramente chamada para o ViewModel
                        //ViewModel -> API
                    }
                },
                enabled = formularioValido,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = androidx.compose.foundation.shape.RoundedCornerShape(
                    18.dp
                ),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Verde,
                    disabledContentColor = Verde.copy(
                        alpha = 0.45f
                    ),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White.copy(
                        alpha = 0.75f
                    )
                )
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // DIVISOR

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Borda)
                )

                Text(
                    text = "OU CONTINUE COM",
                    color = CinzaTexto,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(
                        horizontal = 14.dp
                    )
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Borda)
                )
            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            // CADASTRO

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Não tem conta?",
                    color = CinzaTexto,
                    fontSize = 17.sp
                )

                TextButton(
                    onClick = {
                        //futuramente navegar para cadastro
                    }
                ) {
                    Text(
                        text = "Cadastre-se",
                        color = Verde,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// HERO DA TELA DE LOGIN

@Composable
fun HeroArea() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(228.dp)
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(
                id = R.drawable.hero_area
            ),
            contentDescription = "Pessoa cozinhando",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(
                        alpha = 0.38f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            // LOGO

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "&",
                    color = Color(0xff71c9c5),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "Panelas",
                    color = Color.White,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // FRASE

            Row {
                Text(
                    text = "Conecte-se pela ",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "cozinha",
                    color = Amarelo,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Fundo)
//            .systemBarsPadding()
//            .verticalScroll(rememberScrollState())
//    ) {
//        //AREA SUPERIOR / HERO
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(228.dp)
//        ) {
//            Image(
//                painter = painterResource(
//                    id = R.drawable.hero_area
//                ),
//                contentDescription = "Pessoa cozinhando",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//
//            //CAMADA ESCURA SOBRE A IMAGEM
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.38f))
//            )
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 24.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.Start
//            ) {
//                //LOGO
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "&",
//                        color = Color(0xff71c9c5),
//                        fontSize = 50.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily.Serif
//                    )
//
//                    Text(
//                        text = "Panelas",
//                        color = Color.White,
//                        fontSize = 48.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily.Serif
//                    )
//                }
//
//                Spacer(
//                    modifier = Modifier.height(20.dp)
//                )
//
//                //FRASE
//
//                Row {
//                    Text(
//                        text = "Conecte-se pela ",
//                        color = Color.White,
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily.Serif
//                    )
//
//                    Text(
//                        text = "cozinha.",
//                        color = Amarelo,
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily.Serif
//                    )
//                }
//            }
//        }
//    }
//
//    //CONTEÚDO DO LOGIN
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(
//                horizontal = 24.dp,
//                vertical = 28.dp
//            )
//    ) {
//        Text(
//            text = "Bem-vindo de volta!",
//            color = textoPrincipal,
//            fontSize = 27.sp,
//            fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily.Serif
//        )
//
//        Spacer(
//            modifier = Modifier.height(6.dp)
//        )
//
//        Text(
//            text = "Entre na sua conta para continuar",
//            color = textoSecundario,
//            fontSize = 17.sp
//        )
//
//        Spacer(
//            modifier = Modifier.height(32.dp)
//        )
//
//        //EMAIL
//
//        Text(
//            text = "E-mail",
//            color = textoPrincipal,
//            fontSize = 17.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//
//        Spacer(
//            modifier = Modifier.height(8.dp)
//        )
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email
//            ),
//            placeholder = {
//                Text(
//                    text = "seu@email.com",
//                    color = textoSecundario,
//                    fontSize = 17.sp
//                )
//            },
//            singleLine = true,
//            shape = RoundedCornerShape(18.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = borda,
//                focusedBorderColor = verde,
//                unfocusedContainerColor = fundo,
//                focusedContainerColor = fundo,
//                cursorColor = verde
//            )
//        )
//
//        Spacer(
//            modifier = Modifier.height(26.dp)
//        )
//
//        //SENHA
//
//        Text(
//            text = "Senha",
//            color = textoPrincipal,
//            fontSize = 17.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//
//        Spacer(
//            modifier = Modifier.height(8.dp)
//        )
//
//        OutlinedTextField(
//            value = senha,
//            onValueChange = { senha = it },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password
//            ),
//            visualTransformation = if (mostrarSenha) {
//                VisualTransformation.None
//            } else{
//                PasswordVisualTransformation()
//            },
//            singleLine = true
//        )
//    }
//
