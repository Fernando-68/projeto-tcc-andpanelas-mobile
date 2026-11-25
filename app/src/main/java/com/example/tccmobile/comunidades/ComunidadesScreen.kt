package com.example.tccmobile.comunidades

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.Text
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.home.NavegacaoInferiorHome
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tccmobile.R

private val FundoComunidades = Color(0xFFFFFCF8)
private val MarromComunidades = Color(0xFF38251A)
private val VerdeComunidades = Color(0xFF72C5A8)
private val BordaComunidades = Color(0xFFEDE2D0)
private val TextoSecundarioComunidades = Color(0xFF9A887D)

@Composable
fun ComunidadesScreen(
    onAbrirInicio: () -> Unit,
    onAbrirBusca: () -> Unit,
    onAbrirGeladeira: () -> Unit,
    onCriarComunidade: () -> Unit = {},
    onAbrirComunidade: () -> Unit = {}
) {
    var textoPesquisa by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoComunidades)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            // CABEÇALHO
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Comunidades",
                    color = MarromComunidades,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .background(VerdeComunidades, RoundedCornerShape(20.dp))
                        .clickable { onCriarComunidade() }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Criar",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // PESQUISA
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(
                        1.dp,
                        BordaComunidades,
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⌕",
                    color = TextoSecundarioComunidades,
                    fontSize = 23.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = textoPesquisa,
                    onValueChange = { textoPesquisa = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = TextStyle(
                        color = MarromComunidades,
                        fontSize = 13.sp
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            if (textoPesquisa.isEmpty()) {
                                Text(
                                    text = "Pesquisar comunidade pelo nome...",
                                    color = TextoSecundarioComunidades,
                                    fontSize = 12.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // DESTAQUE DA SEMANA
            TituloSecaoComunidades("Destaque da semana")

            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { onAbrirComunidade() }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.card_em_alta
                    ),
                    contentDescription = "Destaque da semana",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Camada escura para melhorar a leitura dos textos
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.42f))
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "🔥 Em alta",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Cozinha Brasileira",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "45,6k membros  •  8,9k receitas",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            // MINHAS COMUNIDADES
            TituloSecaoComunidades("Minhas comunidades")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ComunidadeMiniatura(
                    imagem = R.drawable.bolos_e_conf,
                    nome = "Bolos & Confeitaria",
                    onClick = onAbrirComunidade
                )

                ComunidadeMiniatura(
                    imagem = R.drawable.cozinha_bras,
                    nome = "Cozinha Brasileira",
                    onClick = onAbrirComunidade
                )

                ComunidadeMiniatura(
                    imagem = R.drawable.veganos_criat,
                    nome = "Veganos Criativos",
                    onClick = onAbrirComunidade
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // EXPLORAR COMUNIDADES
            TituloSecaoComunidades("Explorar comunidades")

            Spacer(modifier = Modifier.height(10.dp))

            ComunidadeListaItem(
                emoji = "🌱",
                imagem = R.drawable.icon_veganos,
                nome = "Veganos Criativos",
                informacoes = "12,4k membros • 3,2k receitas",
                participando = false,
                onClick = onAbrirComunidade
            )

            ComunidadeListaItem(
                emoji = "🧁",
                imagem = R.drawable.icon_bolinho_confeitaria,
                nome = "Bolos & Confeitaria",
                informacoes = "28,7k membros • 1,8k receitas",
                participando = true,
                onClick = onAbrirComunidade
            )

            ComunidadeListaItem(
                emoji = "🍲",
                imagem = R.drawable.icon_cozinha_brasileira,
                nome = "Cozinha Brasileira",
                informacoes = "45,6k membros • 8,9k receitas",
                participando = true,
                onClick = onAbrirComunidade
            )

            ComunidadeListaItem(
                emoji = "🥑",
                nome = "Fitness & Saúde",
                informacoes = "8,2k membros • 1,1k receitas",
                participando = false,
                onClick = onAbrirComunidade
            )

            ComunidadeListaItem(
                emoji = "🍝",
                nome = "Massas & Risotos",
                informacoes = "6,5k membros • 920 receitas",
                participando = false,
                onClick = onAbrirComunidade
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // NAVEGAÇÃO INFERIOR FIXA
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
private fun TituloSecaoComunidades(texto: String) {
    Text(
        text = texto,
        color = MarromComunidades,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
}


@Composable
private fun ComunidadeMiniatura(
    imagem: Int,
    nome: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(88.dp)
            .border(
                1.dp,
                BordaComunidades,
                RoundedCornerShape(12.dp)
            )
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagem),
            contentDescription = nome,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(9.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = nome,
            color = MarromComunidades,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2
        )
    }
}



@Composable
private fun ComunidadeListaItem(
    emoji: String,
    nome: String,
    informacoes: String,
    participando: Boolean,
    onClick: () -> Unit,
    imagem: Int? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 9.dp)
            .border(
                1.dp,
                BordaComunidades,
                RoundedCornerShape(12.dp)
            )
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFE3F5EC)),
            contentAlignment = Alignment.Center
        ) {
            if (imagem != null) {
                Image(
                    painter = painterResource(id = imagem),
                    contentDescription = nome,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = emoji,
                    fontSize = 21.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = nome,
                color = MarromComunidades,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = informacoes,
                color = TextoSecundarioComunidades,
                fontSize = 9.sp
            )
        }

        Box(
            modifier = Modifier
                .background(
                    if (participando) Color(0xFFE3F5EC)
                    else VerdeComunidades,
                    RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                text = if (participando) "✓ Participando" else "+ Entrar",
                color = if (participando) Color(0xFF459B7E) else Color.White,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}