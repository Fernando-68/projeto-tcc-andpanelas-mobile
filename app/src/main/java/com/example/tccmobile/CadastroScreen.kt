package com.example.tccmobile

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle
import java.util.Locale
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
    onVoltarLogin: () -> Unit,
    onCadastroConcluido: (String) -> Unit
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var usuario by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var dataNascimento by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var senhaVisivel by rememberSaveable { mutableStateOf(false) }
    var aceitouTermos by rememberSaveable { mutableStateOf(false) }

    var tentouCriarConta by rememberSaveable {
        mutableStateOf(false)
    }

    val erroNome = if (tentouCriarConta &&
        !validarNomeCompleto(nome)
        ) {
        "Informe um nome válido, entre 2 e 100 caracteres."
    } else {
        null
    }

    val erroUsuario = if (tentouCriarConta &&
        !validarUsuario(usuario)
        ) {
        "Use letras, números, ponto ou _ (underline), sem pontos consecutivos"
    } else {
        null
    }

    val erroEmail = if (tentouCriarConta &&
        !validarEmailCadastro(email)
        ) {
        "Informe um Email válido, terminado em gmail.com."
    } else {
        null
    }

    val erroDataNascimento = if (tentouCriarConta &&
        !validarDataNascimento(dataNascimento)
        ) {
        "Informe uma data válida e tenha pelo menos 10 anos."
    } else {
        null
    }

    val erroSenha = if (tentouCriarConta &&
        !validarSenhaCadastro(senha)
        ) {
        "Use 12 caracteres ou mais, com letra maiúscula. número e símbolo."
    } else {
        null
    }

    val erroTermos = tentouCriarConta && !aceitouTermos

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
                rotulo = "Nome Completo",
                valor = nome,
                aoAlterar = { nome = it },
                placeholder = "Seu nome completo",
                erro =  erroNome
            )

            Spacer(modifier = Modifier.height(18.dp))

            // NOME USUÁRIO
            CampoCadastro(
                rotulo = "Nome de usuário",
                valor = usuario,
                aoAlterar = { usuario = it },
                placeholder = "@seuusuario",
                erro = erroUsuario

            )

            Spacer(modifier = Modifier.height(18.dp))

            //EMAIL
            CampoCadastro(
                rotulo = "E-mail",
                valor = email,
                aoAlterar = { email = it },
                placeholder = "seu@email.com",
                erro = erroEmail
            )

            Spacer(modifier = Modifier.height(18.dp))

            //DATA DE NASCIMENTO
            CampoCadastro(
                rotulo = "Data de nascimento",
                valor = dataNascimento,
                aoAlterar = { dataNascimento = it },
                placeholder = "DD/MM/AAAA",
                erro = erroDataNascimento
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
                isError = erroSenha != null,
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
                    unfocusedBorderColor = if (erroSenha != null) {
                        Color(0xffb3261e)
                    } else {
                        BordaCadastro
                    },
                    focusedBorderColor = if (erroSenha != null) {
                        Color(0xffb3261e)
                    } else {
                        VerdeCadastro
                    },
                    unfocusedContainerColor = FundoCadastro,
                    focusedContainerColor = FundoCadastro,
                    focusedTextColor = MarromCadastro,
                    unfocusedTextColor = MarromCadastro,
                    cursorColor = VerdeCadastro,
                    errorBorderColor = Color(0xffb3261e),
                    errorCursorColor = Color(0xffb3261e)
                ),
            )

            if (erroSenha != null) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = erroSenha,
                    color = Color(0xffb3261e),
                    fontSize = 12.sp
                )
            }

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

            if (erroTermos) {
                Text(
                    text = "Você precisa aceitar os Termos de Uso e Privacidade.",
                    color = Color(0xffb3261e),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            //BOTÃO CRIAR CONTA
            Button(
                onClick = {
                    tentouCriarConta = true

                    val formularioValido =
                        validarNomeCompleto(nome) &&
                                validarUsuario(usuario) &&
                                validarEmailCadastro(email) &&
                                validarDataNascimento(dataNascimento) &&
                                validarSenhaCadastro(senha) &&
                                aceitouTermos

                    if (formularioValido) {
                        onCadastroConcluido(email.trim())
                        // Futuramente enviar os dados para a API
                    }
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

private fun validarNomeCompleto(nome: String): Boolean {
    val nomeLimpo = nome.trim()

    val padrao = Regex(
        "^[\\p{L}]+(?:[ '\\u002D][\\p{L}]+)*$"
    )

    return nomeLimpo.length in 2..100 &&
            padrao.matches(nomeLimpo)
}

private fun validarUsuario(usuario: String): Boolean{
    if (usuario.length !in 1..30) return false

    val padrao = Regex("[a-zA-Z0-9._]+$")

    if (!padrao.matches(usuario)) return false
    if (usuario.startsWith(".") || usuario.endsWith(".")) {
        return false
    }
    if (".." in usuario) return false

    val reservados = setOf(
        "admin",
        "administrador",
        "suporte",
        "&panelas",
        "oficial"
    )

    return usuario.lowercase(Locale.ROOT) !in reservados
}

private fun validarEmailCadastro(email: String): Boolean {
    val emailNormalizado = email.trim()

    val padraoEmail = Regex(
        "^[a-z]+[a-z0-9]*@gmail\\.com$"
    )

    if (!padraoEmail.matches(emailNormalizado)) {
        return false
    }

    val nomeUsuario = emailNormalizado.substringBefore("@")
    val letras = nomeUsuario.count { it in 'a'..'z' }
    val numeros = nomeUsuario.count { it in '0'..'9' }

    if (letras == 0) return false
    if (letras == 1 && numeros > 0) return false
    if (numeros > 0 && letras < 2) return false

    return true
}

private fun validarSenhaCadastro(senha: String): Boolean {
    val temMaiuscula = senha.any { it.isUpperCase() }
    val temNumero = senha.any { it.isDigit() }
    val temEspecial = senha.any {
        !it.isLetterOrDigit() && !it.isWhitespace()
    }

    val senhasComuns = setOf(
        "123456",
        "123456789",
        "password",
        "password123",
        "&panelas123",
        "qwerty123"
    )

    return senha.length >= 12 &&
            temMaiuscula &&
            temNumero &&
            temEspecial &&
            senha.lowercase(Locale.ROOT) !in senhasComuns
}

private fun validarDataNascimento(data: String): Boolean {
    return try {
        val formato = DateTimeFormatter
            .ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT)

        val nascimento = LocalDate.parse(data, formato)
        val hoje = LocalDate.now()

        if (nascimento.isAfter(hoje)) {
            return false
        }

        Period.between(nascimento, hoje).years >=10
    } catch (e: Exception) {
        false
    }
}

    @Composable
    private fun CampoCadastro(
        rotulo: String,
        valor: String,
        aoAlterar: (String) -> Unit,
        placeholder: String,
        erro: String? = null
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
                isError = erro != null,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = if (erro != null) {
                        Color(0xffb3261e)
                    } else {
                        BordaCadastro
                    },
                    focusedBorderColor = if (erro != null) {
                        Color(0xffb3261e)
                    } else {
                        VerdeCadastro
                    },
                    unfocusedContainerColor = FundoCadastro,
                    focusedContainerColor = FundoCadastro,
                    focusedTextColor = MarromCadastro,
                    unfocusedTextColor = MarromCadastro,
                    cursorColor = VerdeCadastro,
                    errorBorderColor = Color(0xffb3261e),
                    errorCursorColor = Color(0xffb3261e)
                )
            )

            if (erro != null) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = erro,
                    color = Color(0xffb3261e),
                    fontSize = 12.sp
                )
            }
        }
    }