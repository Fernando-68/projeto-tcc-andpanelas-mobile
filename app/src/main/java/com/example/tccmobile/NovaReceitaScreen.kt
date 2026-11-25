
package com.example.tccmobile.receita

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FundoReceita = Color(0xFFFFFCF8)
private val MarromReceita = Color(0xFF38251A)
private val VerdeReceita = Color(0xFF75C5A4)
private val CinzaReceita = Color(0xFF948176)
private val BordaReceita = Color(0xFFEDE0D6)
private val LaranjaReceita = Color(0xFFF5A832)

@Composable
fun NovaReceitaScreen(
    onVoltar: () -> Unit,
    onProximo: () -> Unit
) {

    var nome by remember { mutableStateOf("Bolo Formigueiro Caseiro") }
    var descricao by remember {
        mutableStateOf(
            "Uma receita clássica, fofinha e muito fácil de fazer no café da tarde..."
        )
    }

    var categoriaSelecionada by remember { mutableStateOf("Almoço") }
    var dificuldade by remember { mutableStateOf("Fácil") }
    var custo by remember { mutableStateOf("Econômico") }
    var tempo by remember { mutableStateOf("45") }
    var porcoes by remember { mutableStateOf("8") }

    val categorias = listOf(
        "Bolos",
        "Almoço",
        "Café",
        "Sobremesas",
        "Sopas",
        "Lanches"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoReceita)
            .statusBarsPadding()
    ) {

        // CABEÇALHO

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 22.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "×",
                fontSize = 36.sp,
                color = MarromReceita,
                modifier = Modifier.clickable {
                    onVoltar()
                }
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Nova Receita",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = MarromReceita
                )

                Text(
                    text = "Etapa 1 de 3",
                    fontSize = 17.sp,
                    color = CinzaReceita
                )
            }

            Button(
                onClick = onProximo,
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeReceita
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Próximo",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        HorizontalDivider(color = BordaReceita)

        // CONTEÚDO DA TELA

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 27.dp)
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            // PROGRESSO

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .background(
                                color = if (index == 0) {
                                    VerdeReceita
                                } else {
                                    Color(0xFFFFE5BD)
                                },
                                shape = RoundedCornerShape(20.dp)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(27.dp))

            Text(
                text = "Informações da receita",
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF514033)
            )

            Spacer(modifier = Modifier.height(25.dp))

            // FOTO E VÍDEO

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                CaixaMidia(
                    simbolo = "◎",
                    texto = "Toque para tirar foto",
                    modifier = Modifier.weight(1f)
                )

                CaixaMidia(
                    simbolo = "▻",
                    texto = "Adicionar vídeo",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // NOME

            TextoCampo("Nome da receita", obrigatorio = true)

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(13.dp),
                colors = coresCampo()
            )

            Spacer(modifier = Modifier.height(22.dp))

            // DESCRIÇÃO

            TextoCampo("Descrição")

            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp),
                shape = RoundedCornerShape(13.dp),
                colors = coresCampo()
            )

            Spacer(modifier = Modifier.height(23.dp))

            // CATEGORIA

            TextoCampo("Categoria")

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                categorias.chunked(4).forEach { grupo ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        grupo.forEach { categoria ->
                            val selecionada =
                                categoriaSelecionada == categoria

                            Surface(
                                modifier = Modifier.clickable {
                                    categoriaSelecionada = categoria
                                },
                                shape = RoundedCornerShape(30.dp),
                                color = if (selecionada) {
                                    LaranjaReceita
                                } else {
                                    Color.White
                                },
                                border = if (selecionada) null else BorderStroke(
                                    1.5.dp,
                                    BordaReceita
                                )
                            ) {
                                Text(
                                    text = categoria,
                                    modifier = Modifier.padding(
                                        horizontal = 17.dp,
                                        vertical = 9.dp
                                    ),
                                    color = if (selecionada) {
                                        Color.White
                                    } else {
                                        MarromReceita
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // DIFICULDADE E CUSTO

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextoCampo("Dificuldade")

                    OutlinedTextField(
                        value = dificuldade,
                        onValueChange = { dificuldade = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(13.dp),
                        colors = coresCampo()
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    TextoCampo("Custo")

                    OutlinedTextField(
                        value = custo,
                        onValueChange = { custo = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(13.dp),
                        colors = coresCampo()
                    )
                }
            }

            Spacer(modifier = Modifier.height(23.dp))

            // TEMPO E PORÇÕES

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextoCampo("Tempo (min)")

                    OutlinedTextField(
                        value = tempo,
                        onValueChange = { tempo = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = RoundedCornerShape(13.dp),
                        colors = coresCampo()
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    TextoCampo("Porções")

                    OutlinedTextField(
                        value = porcoes,
                        onValueChange = { porcoes = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = RoundedCornerShape(13.dp),
                        colors = coresCampo()
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // BOTÃO PRÓXIMO

            Button(
                onClick = onProximo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeReceita
                )
            ) {
                Text(
                    text = "Próximo: Ingredientes  →",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
        }

        // BARRA INFERIOR

        HorizontalDivider(color = BordaReceita)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ItemMenuReceita("⌂", "Início")
            ItemMenuReceita("⌕", "Buscar")

            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(
                        VerdeReceita,
                        RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            ItemMenuReceita("♧", "Comunidades")
            ItemMenuReceita("▣", "Geladeira")
        }
    }
}

@Composable
private fun CaixaMidia(
    simbolo: String,
    texto: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(150.dp)
            .background(
                FundoReceita,
                RoundedCornerShape(15.dp)
            )
            .then(
                Modifier.padding(2.dp)
            )
            .background(
                Color(0xFFFFFCF8),
                RoundedCornerShape(15.dp)
            )
            .clickable {
                // Seleção de foto ou vídeo será implementada depois.
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = simbolo,
            fontSize = 35.sp,
            color = CinzaReceita
        )

        Text(
            text = texto,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = CinzaReceita
        )
    }
}

@Composable
private fun TextoCampo(
    texto: String,
    obrigatorio: Boolean = false
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = texto,
            color = MarromReceita,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        if (obrigatorio) {
            Text(
                text = " *",
                color = Color.Red,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun coresCampo() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = CinzaReceita,
    unfocusedBorderColor = BordaReceita,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedTextColor = MarromReceita,
    unfocusedTextColor = MarromReceita,
    cursorColor = VerdeReceita
)

@Composable
private fun ItemMenuReceita(
    simbolo: String,
    texto: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = simbolo,
            fontSize = 27.sp,
            color = CinzaReceita
        )
        Text(
            text = texto,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = CinzaReceita
        )
    }
}
