import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReceitaMiniCard(
    modifier: Modifier = Modifier,
    receitaId: Long,
    imagem: Int,
    titulo: String,
    tempo: String,
    onClick: (Long) -> Unit
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick(receitaId) }
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaMini,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        Image(
            painter = painterResource(imagem),
            contentDescription = titulo,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {

            Text(
                text = titulo,
                color = MarromMini,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = tempo,
                color = CinzaMini,
                fontSize = 14.sp
            )
        }
    }
}