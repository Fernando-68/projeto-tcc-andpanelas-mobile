package com.example.tccmobile

import android.os.Bundle
import android.view.Window
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.TCCMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.statusBarColor = Color(0xfffffcf8)
//        window.navigationBarColor = Color(0xfffffcf8)
        //enableEdgeToEdge()
        setContent {
            TCCMobileTheme {
                TCCMobileTheme {
                    LoginScreen()
                }

            }
        }
    }
    }

@Composable
fun LoginScreen() {
    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var mostrarSenha by remember {
        mutableStateOf(false)
    }

    val fundo = Color(0xfffffcf8)
    val textoPrincipal = Color(0xff38251a)
    val textoSecundario = Color(0xff9a887d)
    val verde = Color(0xff6bc2a5)
    val borda = Color(0xffede2d0)
    val amarelo = Color(0xffe7a52e)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        //AREA SUPERIOR / HERO

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(228.dp)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.hero_area
                ),
                contentDescription = "Pessoa cozinhando",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            //CAMADA ESCURA SOBRE A IMAGEM

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.38f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                //LOGO

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "&",
                        color = Color(0xff71c9c5),
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Text(
                        text = "Panelas",
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                //FRASE

                Row {
                    Text(
                        text = "Conecte-se pela ",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Text(
                        text = "cozinha.",
                        color = amarelo,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }

    //CONTEÚDO DO LOGIN

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 28.dp
            )
    ) {
        Text(
            text = "Bem-vindo de volta!",
            color = textoPrincipal,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Entre na sua conta para continuar",
            color = textoSecundario,
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        //EMAIL

        Text(
            text = "E-mail",
            color = textoPrincipal,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            placeholder = {
                Text(
                    text = "seu@email.com",
                    color = textoSecundario,
                    fontSize = 17.sp
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = borda,
                focusedBorderColor = verde,
                unfocusedContainerColor = fundo,
                focusedContainerColor = fundo,
                cursorColor = verde
            )
        )

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        //SENHA

        Text(
            text = "Senha",
            color = textoPrincipal,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            },
            singleLine = true
        )
    }

}