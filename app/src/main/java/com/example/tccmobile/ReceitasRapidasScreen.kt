package com.example.tccmobile

import ReceitaMiniCard
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.ui.components.ReceitaMiniCard
import com.example.tccmobile.ui.theme.PlayfairDisplay
import com.example.tccmobile.viewmodel.ReceitasRapidasViewModel

private val FundoRapidas = Color(0xFFFFFCF8)
private val MarromRapidas = Color(0xFF38251A)
private val BordaRapidas = Color(0xFFEDE2D0)
private val VerdeRapidas = Color(0xFF72C5A8)

@Composable
fun ReceitasRapidasScreen(
    onVoltar: () -> Unit,
    onAbrirReceita: (Long) -> Unit,
    viewModel: ReceitasRapidasViewModel = viewModel()
) {

    val receitas by viewModel.receitas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoRapidas)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(BordaRapidas)
                    .clickable { onVoltar() },
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "Voltar",
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = "Rápidas e Gostosas",
                color = MarromRapidas,
                fontFamily = PlayfairDisplay,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            items(receitas, key = { it.id }) { receita ->

                ReceitaMiniCard(
                    modifier = Modifier.fillMaxWidth(),
                    receitaId = receita.id,
                    imagem = receita.imagem,
                    titulo = receita.titulo,
                    tempo = receita.tempo,
                    onClick = onAbrirReceita
                )
            }
        }
    }
}