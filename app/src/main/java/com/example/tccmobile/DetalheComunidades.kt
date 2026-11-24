
package com.example.tccmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.home.NavegacaoInferiorHome

private val FundoComunidade = Color(0xFFFFFCF8)
private val MarromComunidade = Color(0xFF38251A)
private val VerdeComunidade = Color(0xFF72C5A8)
private val BordaComunidade = Color(0xFFEDE2D0)
private val TextoSecundarioComunidade = Color(0xFF9A887D)

private data class MembroComunidade(
    val nome: String,
    val iniciais: String,
    val funcao: String,
    val receitas: Int,
    val cor: Color,
    val moderador: Boolean = false
)

private data class ReceitaComunidade(
    val nome: String,
    val autor: String,
    val iniciaisAutor: String,
    val tempo: String,
    val porcoes: String,
    val categoria: String,
    val curtidas: Int,
    val comentarios: Int,
    val nivel: String,
    val emoji: String,
    val cor: Color
)

@Composable
fun DetalheComunidadeScreen(
    nomeComunidade: String,
    onVoltar: () -> Unit,
    onDenunciar: () -> Unit,
    onAbrirInicio: () -> Unit,
    onAbrirBusca: () -> Unit,
    onAbrirGeladeira: () -> Unit,
    onEditarComunidade: () -> Unit = {},
    onCompartilhar: () -> Unit = {}
) {
    var abaSelecionada by remember { mutableStateOf("Receitas") }
    var participando by remember { mutableStateOf(false) }
    var menuAberto by remember { mutableStateOf(false) }

    val membros = remember {
        listOf(
            MembroComunidade(
                "Mariana Lima", "ML", "Moderadora", 89,
                Color(0xFFF8D9D8), true
            ),
            MembroComunidade(
                "Ana Costa", "AC", "Membro", 47,
                Color(0xFFC8E8DF)
            ),
            MembroComunidade(
                "Pedro Souza", "PS", "Membro", 31,
                Color(0xFFFBE8C2)
            ),
            MembroComunidade(
                "Beatriz Alves", "BA", "Membro", 65,
                Color(0xFFD1E6EA)
            ),
            MembroComunidade(
                "Carlos Ferreira", "CF", "Membro", 22,
                Color(0xFFE5E5E5)
            )
        )
    }

    val receitas = remember {
        listOf(
            ReceitaComunidade(
                "Pão de Queijo Mineiro (Vegano)",
                "Mariana Lima", "ML", "30 min",
                "15 porções", "Econômico", 482, 19,
                "Fácil", "🥖", Color(0xFFE9C18C)
            ),
            ReceitaComunidade(
                "Risoto de Cogumelos Porcini",
                "Mariana Lima", "ML", "45 min",
                "2 porções", "Premium", 312, 14,
                "Médio", "🍚", Color(0xFFD9D5C2)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoComunidade)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 18.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF596B3E),
                                    Color(0xFF34452D),
                                    Color(0xFF202B1D)
                                )
                            )
                        )
                ) {
                    Text(
                        text = "🥬  🥑  🥦  🍋  🥒  🥑",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 35.dp),
                        fontSize = 35.sp
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.12f),
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.65f)
                                    )
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 18.dp)
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF8F5EF))
                            .clickable { onVoltar() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("‹", color = MarromComunidade, fontSize = 32.sp)
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 18.dp, end = 16.dp)
                    ) {
                        Box {
                            Text(
                                text = "⋮",
                                color = Color.White,
                                fontSize = 28.sp,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { menuAberto = true }
                                    .padding(horizontal = 10.dp)
                            )

                            DropdownMenu(
                                expanded = menuAberto,
                                onDismissRequest = { menuAberto = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Denunciar comunidade") },
                                    onClick = {
                                        menuAberto = false
                                        onDenunciar()
                                    }
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 22.dp, end = 18.dp, bottom = 18.dp)
                    ) {
                        Text(
                            text = nomeComunidade,
                            color = Color.White,
                            fontSize = 29.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "♟ 12.4k membros   •   ▫ 3.2k receitas",
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 18.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Receitas plant-based deliciosas para mostrar que vegano não come só salada! 🌱",
                        color = TextoSecundarioComunidade,
                        fontSize = 14.sp,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            "AC" to Color(0xFFC8E8DF),
                            "PS" to Color(0xFFFBE8C2),
                            "ML" to Color(0xFFF8D9D8),
                            "CF" to Color(0xFFE5E5E5)
                        ).forEach { (iniciais, cor) ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 0.dp)
                                    .size(25.dp)
                                    .clip(CircleShape)
                                    .background(cor)
                                    .border(1.dp, Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    iniciais,
                                    color = MarromComunidade,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "+12.4k outros membros",
                            color = TextoSecundarioComunidade,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { participando = !participando },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            shape = RoundedCornerShape(9.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = VerdeComunidade
                            )
                        ) {
                            Text(
                                text = if (participando) {
                                    "Participando da comunidade"
                                } else {
                                    "Participar da comunidade"
                                },
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        OutlinedButton(
                            onClick = onCompartilhar,
                            modifier = Modifier.size(44.dp),
                            shape = RoundedCornerShape(9.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp, BordaComunidade
                            )
                        ) {
                            Text("⇧", color = TextoSecundarioComunidade, fontSize = 23.sp)
                        }
                    }
                }

                HorizontalDivider(color = BordaComunidade, thickness = 1.dp)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("Receitas", "Membros").forEach { aba ->
                        val selecionada = abaSelecionada == aba

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(
                                    if (selecionada) MarromComunidade
                                    else Color.White
                                )
                                .border(
                                    1.dp,
                                    if (selecionada) MarromComunidade
                                    else BordaComunidade,
                                    RoundedCornerShape(9.dp)
                                )
                                .clickable { abaSelecionada = aba },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = aba,
                                color = if (selecionada) Color.White
                                else TextoSecundarioComunidade,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                HorizontalDivider(color = BordaComunidade)
            }

            if (abaSelecionada == "Receitas") {
                items(receitas) { receita ->
                    ReceitaComunidadeCard(receita)
                }

                item {
                    Text(
                        text = "Editar comunidade",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEditarComunidade() }
                            .padding(vertical = 22.dp),
                        color = Color(0xFFFF9800),
                        fontSize = 14.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                items(membros) { membro ->
                    MembroComunidadeItem(membro)
                }
            }
        }

        NavegacaoInferiorHome(
            telaSelecionada = "Comunidades",
            onInicioClick = onAbrirInicio,
            onBuscarClick = onAbrirBusca,
            onComunidadesClick = {},
            onGeladeiraClick = onAbrirGeladeira
        )
    }
}

@Composable
private fun ReceitaComunidadeCard(receita: ReceitaComunidade) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 9.dp),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(receita.cor, receita.cor.copy(alpha = 0.65f))
                        )
                    )
            ) {
                Text(
                    text = receita.emoji,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 76.sp
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(CircleShape)
                        .background(VerdeComunidade)
                        .padding(horizontal = 15.dp, vertical = 6.dp)
                ) {
                    Text(receita.nivel, color = Color.White, fontSize = 11.sp)
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(27.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE91E4D)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            receita.iniciaisAutor,
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        receita.autor,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = receita.nome,
                    color = MarromComunidade,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "◷ ${receita.tempo}     ♙ ${receita.porcoes}     $ ${receita.categoria}",
                    color = TextoSecundarioComunidade,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFF2EEE9))
                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("♡ ${receita.curtidas}", color = Color(0xFFE91E4D), fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(18.dp))
                    Text("◯ ${receita.comentarios}", color = TextoSecundarioComunidade, fontSize = 13.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("♧", color = TextoSecundarioComunidade, fontSize = 21.sp)
                }
            }
        }
    }
}

@Composable
private fun MembroComunidadeItem(membro: MembroComunidade) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 5.dp),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, BordaComunidade)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(membro.cor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    membro.iniciais,
                    color = MarromComunidade,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    membro.nome,
                    color = MarromComunidade,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "${membro.funcao} · ${membro.receitas} receitas",
                    color = TextoSecundarioComunidade,
                    fontSize = 11.sp
                )
            }

            if (membro.moderador) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFE58A00))
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        "MOD",
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}