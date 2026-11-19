package com.example.tccmobile.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tccmobile.R

private val MarromCard = Color(0xFF38251A)
private val CinzaCard = Color(0xFF9A887D)
private val VerdeCard = Color(0xFF72C5A8)
private val BordaCard = Color(0xFFEDE2D0)

@Composable
fun ReceitaCard(
    receitaId: Long,
    imagem: Int,
    titulo: String,
    categoria: String,
    tempo: String,
    dificuldade: String,
    autorNome: String,
    autorFoto: String,
    onClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable { onClick(receitaId) }
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaCard,
                shape = RoundedCornerShape(22.dp)
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {

            Image(
                painter = painterResource(imagem),
                contentDescription = titulo,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerdeCard)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = categoria,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                AsyncImage(
                    model = autorFoto,
                    contentDescription = autorNome,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.users),
                    error = painterResource(R.drawable.users)
                )

                Text(
                    text = autorNome,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                text = titulo,
                color = MarromCard,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(painter = painterResource(R.drawable.icon_relogio), modifier = Modifier.width(20.dp), contentDescription = "Relógio")
                Text(text = tempo, color = CinzaCard, fontSize = 14.sp)
                Image(painter = painterResource(R.drawable.icon_talheres), modifier = Modifier.width(20.dp), contentDescription = "Dificuldade")
                Text(text = dificuldade, color = CinzaCard, fontSize = 14.sp)
            }
        }
    }
}