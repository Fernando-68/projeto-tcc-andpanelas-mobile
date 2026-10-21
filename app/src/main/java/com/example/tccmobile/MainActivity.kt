package com.example.tccmobile

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.tccmobile.ui.theme.PlayfairDisplay
import com.example.tccmobile.ui.theme.TCCMobileTheme
import com.example.tccmobile.LoginScreen
import com.example.tccmobile.CadastroScreen
import com.example.tccmobile.navigation.Routes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TCCMobileTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.LOGIN
                ) {
                    composable(
                        route = Routes.LOGIN
                    ) {
                        LoginScreen(
                            onCadastrarClick = {
                                navController.navigate(Routes.CADASTRO)
                            }
                        )
                    }

                    composable(
                        route = Routes.CADASTRO
                    ) {
                        CadastroScreen(
                            onVoltarLogin = {
                                navController.popBackStack()
                            }
                        )
                    }
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
fun LoginScreen(
    onCadastrarClick: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }

    var senha by rememberSaveable { mutableStateOf("") }

    var senhaVisivel by rememberSaveable { mutableStateOf(false) }

    var tentouEntrar by rememberSaveable { mutableStateOf(false) }

    var emailFoiValidado by rememberSaveable { mutableStateOf(false) }

    var senhaFoiValidada by rememberSaveable { mutableStateOf(false) }

    var mensagemResultado by rememberSaveable { mutableStateOf("") }

    val emailFocusRequester = remember {
        FocusRequester()
    }

    val senhaFocusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current


    // VALIDAÇÕES DE CAMPO

    val emailValido= validarEmail(email)
    val senhaValida = validarSenha(senha)


    val formularioValido = emailValido && senhaValida

    val mostrarErroEmail =
        email.isNotBlank() && (emailFoiValidado || tentouEntrar) && !emailValido

    val mostrarErroSenha =
        senha.isNotBlank() && (senhaFoiValidada || tentouEntrar) && !senhaValida


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
                    mensagemResultado = ""
                },

                modifier = Modifier.fillMaxWidth()
                    .focusRequester(emailFocusRequester)
                    .onFocusChanged { foco ->
                        if (!foco.isFocused) {
                            emailFoiValidado = true
                        }
                    },

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
                       emailFoiValidado = true

                        if (emailValido) {
                            senhaFocusRequester.requestFocus()
                        } else {
                            emailFocusRequester.requestFocus()
                        }
                    }
                ),

                shape = RoundedCornerShape(18.dp),
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
                            text = "E-mail inválido. Use apenas letras minúsculas e números antes de @gmail.com, seguindo as regras de cadastro.",
                            color = VermelhoErro,
                            modifier = Modifier.padding(top = 5.dp)
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

                modifier = Modifier.fillMaxWidth()
                    .focusRequester(senhaFocusRequester)
                    .onFocusChanged { foco ->
                        if (!foco.isFocused) {
                            senhaFoiValidada = true
                        }
                    },

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
                            text = "A senha deve possuir pelo menos 12 caracteres, uma letra maiúscula, um número e um caractere especial.",
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
                    emailFoiValidado = true
                    senhaFoiValidada = true
                    mensagemResultado = ""

                    when {

                        !emailValido -> {
                            emailFocusRequester.requestFocus()
                        }

                        !senhaValida -> {
                            senhaFocusRequester.requestFocus()
                        }
                        else -> {
                            focusManager.clearFocus()

                            //os campos passaram pelas validações
                            mensagemResultado = "Dados preenchidos corretamente"

                            //futuramente chamada para o ViewModel
                            //ViewModel -> API
                        }
                    }
                },

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
                    TextButton(
                        onClick = onCadastrarClick
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
}

// HERO DA TELA DE LOGIN


@Composable
fun HeroArea() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(228.dp)
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.hero_area),
            contentDescription = "Pessoa cozinhando",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Escurecimento da imagem
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.38f))
        )

        // Logo e frase na ordem do protótipo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            // Logo original
            Image(
                painter = painterResource(
                    id = R.drawable.logo_panelas
                ),
                contentDescription = "Logo &Panelas",
                modifier = Modifier
                    .width(270.dp)
                    .height(70.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Frase com as cores do protótipo
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Conecte-se pela ")
                    }

                    withStyle(
                        SpanStyle(
                            color = Amarelo,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("cozinha.")
                    }
                },
                fontFamily = PlayfairDisplay,
                fontSize = 27.sp,
                lineHeight = 34.sp
            )
        }
    }
}

    private fun validarEmail(email: String): Boolean {
        val emailNormalizado = email.trim()

        // Aceita somente letras minúsculas e números antes de @gmail.com
        val padraoEmail = Regex(
            "^[a-z]+[a-z0-9]*@gmail\\.com$"
        )

        if (!padraoEmail.matches(emailNormalizado)) {
            return false
        }

        val nomeUsuario = emailNormalizado.substringBefore("@")

        val quantidadeLetras = nomeUsuario.count { it in 'a'..'z' }

        val quantidadeNumeros = nomeUsuario.count { it in '0'..'9' }

        // Precisa ter pelo menos uma letra
        if (quantidadeLetras == 0) {
            return false
        }

        // Não permite somente uma letra seguida de números
        if (quantidadeLetras == 1 && quantidadeNumeros > 0) {
            return false
        }

        // Se houver números, exige pelo menos 4 letras
        // OU uma combinação de letras e números com pelo menos 2 letras
        if (quantidadeNumeros > 0 && quantidadeLetras < 2) {
            return false
        }

        return true
    }

private fun validarSenha(senha: String): Boolean {
    val temMaiuscula = senha.any { it.isUpperCase() }
    val temNumero = senha.any { it.isDigit() }
    val temEspecial = senha.any {
        !it.isLetterOrDigit() && !it.isWhitespace()
    }

    return senha.length >= 12 &&
            temMaiuscula &&
            temNumero &&
            temEspecial
}