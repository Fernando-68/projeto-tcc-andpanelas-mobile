
package com.example.tccmobile.comunidades

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.R
import com.example.tccmobile.home.NavegacaoInferiorHome

private val FundoCriarComunidade = Color(0xFFFFFCF8)
private val MarromCriarComunidade = Color(0xFF38251A)
private val VerdeCriarComunidade = Color(0xFF72C5A8)
private val BordaCriarComunidade = Color(0xFFEDE2D0)
private val TextoCriarComunidade = Color(0xFF9A887D)

@Composable
fun CriarComunidadeScreen(
    onVoltar: () -> Unit,
    onCriarComunidade: (String, String, String) -> Unit,
    onAbrirInicio: () -> Unit,
    onAbrirBusca: () -> Unit,
    onAbrirComunidades: () -> Unit,
    onAbrirGeladeira: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var categoriaSelecionada by remember {
        mutableStateOf("Confeitaria & Doces")
    }

    var categoriaPersonalizada by remember {
        mutableStateOf("")
    }

    var mostrarCategoriaPersonalizada by remember {
        mutableStateOf(false)
    }

    var mostrarErro by remember {
        mutableStateOf(false)
    }

    val categorias = listOf(
        "Confeitaria & Doces",
        "Culinária Brasileira",
        "Vegano & Vegetariano",
        "Fitness & Saudável",
        "Massas & Risotos",
        "Bebidas & Drinks",
        "Churrasco"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoCriarComunidade)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            // CABEÇALHO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(
                            Color(0xFFF5EEE5),
                            CircleShape
                        )
                        .clickable {
                            onVoltar()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "‹",
                        color = MarromCriarComunidade,
                        fontSize = 27.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Criar Nova Comunidade",
                        color = MarromCriarComunidade,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Reúna pessoas apaixonadas pelas mesmas receitas",
                        color = TextoCriarComunidade,
                        fontSize = 10.sp
                    )
                }
            }

            DividerCriarComunidade()

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(14.dp))

                // IDENTIDADE VISUAL
                Text(
                    text = "Identidade Visual",
                    color = MarromCriarComunidade,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Personalize o visual para torná-la atraente.",
                    color = TextoCriarComunidade,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // CAPA DA COMUNIDADE
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp)
                        .border(
                            1.dp,
                            BordaCriarComunidade,
                            RoundedCornerShape(10.dp)
                        )
                        .background(
                            Color.White,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.icone_imagem
                            ),
                            contentDescription = "Adicionar imagem da capa",
                            modifier = Modifier.size(32.dp)
                        )

                        Text(
                            text = "Capa da comunidade (Recomendado 1200x400)",
                            color = MarromCriarComunidade,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "Toque para adicionar uma imagem",
                            color = TextoCriarComunidade,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ÍCONE DA COMUNIDADE
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .border(
                                1.dp,
                                BordaCriarComunidade,
                                RoundedCornerShape(12.dp)
                            )
                            .background(
                                Color.White,
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            color = TextoCriarComunidade,
                            fontSize = 24.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Ícone da Comunidade",
                            color = MarromCriarComunidade,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "Quadrado min. 400x400px",
                            color = TextoCriarComunidade,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                DividerCriarComunidade()

                Spacer(modifier = Modifier.height(16.dp))

                // INFORMAÇÕES BÁSICAS
                Text(
                    text = "Informações Básicas",
                    color = MarromCriarComunidade,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                CampoTituloCriarComunidade(
                    "Nome da comunidade *",
                    "${nome.length}/60"
                )

                CampoTextoCriarComunidade(
                    valor = nome,
                    aoAlterar = {
                        nome = it.take(60)
                        mostrarErro = false
                    },
                    placeholder = "Ex: Panificação Artesanal, Massas..."
                )

                Spacer(modifier = Modifier.height(12.dp))

                CampoTituloCriarComunidade(
                    "Descrição *",
                    "${descricao.length}/300"
                )

                CampoTextoCriarComunidade(
                    valor = descricao,
                    aoAlterar = {
                        descricao = it.take(300)
                        mostrarErro = false
                    },
                    placeholder = "Conte sobre o que é essa comunidade, suas regras e quem é bem-vindo...",
                    altura = 88.dp,
                    multiline = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CATEGORIAS
                CampoTituloCriarComunidade(
                    "Categoria principal *"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categorias.chunked(2).forEach { grupo ->

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            grupo.forEach { categoria ->

                                CategoriaCriarComunidade(
                                    texto = categoria,
                                    selecionada = categoriaSelecionada == categoria,
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        categoriaSelecionada = categoria
                                        mostrarCategoriaPersonalizada = false
                                    }
                                )
                            }

                            if (grupo.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (mostrarCategoriaPersonalizada) {

                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            CampoTextoCriarComunidade(
                                valor = categoriaPersonalizada,
                                aoAlterar = {
                                    categoriaPersonalizada = it
                                },
                                placeholder = "Outra categoria"
                            )
                        }

                    } else {

                        Text(
                            text = "Outra categoria",
                            color = TextoCriarComunidade,
                            fontSize = 11.sp,
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    1.dp,
                                    BordaCriarComunidade,
                                    RoundedCornerShape(9.dp)
                                )
                                .background(
                                    Color.White,
                                    RoundedCornerShape(9.dp)
                                )
                                .clickable {
                                    mostrarCategoriaPersonalizada = true
                                    categoriaSelecionada = ""
                                }
                                .padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                VerdeCriarComunidade,
                                RoundedCornerShape(9.dp)
                            )
                            .clickable {
                                if (categoriaPersonalizada.isNotBlank()) {
                                    categoriaSelecionada = categoriaPersonalizada
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                if (mostrarErro) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Preencha o nome, a descrição e selecione uma categoria.",
                        color = Color(0xFFD94343),
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // BOTÕES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .border(
                                1.dp,
                                BordaCriarComunidade,
                                RoundedCornerShape(9.dp)
                            )
                            .background(
                                Color.White,
                                RoundedCornerShape(9.dp)
                            )
                            .clickable {
                                onVoltar()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cancelar",
                            color = TextoCriarComunidade,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .height(44.dp)
                            .background(
                                VerdeCriarComunidade,
                                RoundedCornerShape(9.dp)
                            )
                            .clickable {

                                val categoriaFinal =
                                    if (categoriaSelecionada.isNotBlank()) {
                                        categoriaSelecionada
                                    } else {
                                        categoriaPersonalizada
                                    }

                                if (
                                    nome.isNotBlank() &&
                                    descricao.isNotBlank() &&
                                    categoriaFinal.isNotBlank()
                                ) {
                                    mostrarErro = false

                                    onCriarComunidade(
                                        nome.trim(),
                                        descricao.trim(),
                                        categoriaFinal
                                    )

                                } else {
                                    mostrarErro = true
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Criar Comunidade",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // NAVEGAÇÃO INFERIOR
        NavegacaoInferiorHome(
            telaSelecionada = "Comunidades",
            onInicioClick = onAbrirInicio,
            onBuscarClick = onAbrirBusca,
            onComunidadesClick = onAbrirComunidades,
            onGeladeiraClick = onAbrirGeladeira
        )
    }
}

@Composable
private fun DividerCriarComunidade() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(BordaCriarComunidade)
    )
}

@Composable
private fun CampoTituloCriarComunidade(
    titulo: String,
    contador: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = titulo,
            color = MarromCriarComunidade,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )

        if (contador != null) {
            Text(
                text = contador,
                color = TextoCriarComunidade,
                fontSize = 9.sp
            )
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CampoTextoCriarComunidade(
    valor: String,
    aoAlterar: (String) -> Unit,
    placeholder: String,
    altura: Dp = 42.dp,
    multiline: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(altura)
            .border(
                1.dp,
                BordaCriarComunidade,
                RoundedCornerShape(8.dp)
            )
            .background(
                Color.White,
                RoundedCornerShape(8.dp)
            )
            .padding(
                horizontal = 11.dp,
                vertical = 9.dp
            ),
        contentAlignment = if (multiline) {
            Alignment.TopStart
        } else {
            Alignment.CenterStart
        }
    ) {
        BasicTextField(
            value = valor,
            onValueChange = aoAlterar,
            modifier = Modifier.fillMaxWidth(),
            singleLine = !multiline,
            textStyle = TextStyle(
                color = MarromCriarComunidade,
                fontSize = 11.sp
            ),
            decorationBox = { innerTextField ->

                Box {
                    if (valor.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = TextoCriarComunidade,
                            fontSize = 10.sp
                        )
                    }

                    innerTextField()
                }
            }
        )
    }
}

@Composable
private fun CategoriaCriarComunidade(
    texto: String,
    selecionada: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                1.dp,
                if (selecionada) {
                    VerdeCriarComunidade
                } else {
                    BordaCriarComunidade
                },
                RoundedCornerShape(20.dp)
            )
            .background(
                if (selecionada) {
                    Color(0xFFE7F5EF)
                } else {
                    Color.White
                },
                RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 9.dp,
                vertical = 8.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = if (selecionada) {
                Color(0xFF459B7E)
            } else {
                MarromCriarComunidade
            },
            fontSize = 9.sp,
            textAlign = TextAlign.Center
        )
    }
}