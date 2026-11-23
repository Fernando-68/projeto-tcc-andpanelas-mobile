package com.example.tccmobile.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.R

internal val PerfilFundo = Color(0xFFFFFCF8)
internal val PerfilMarrom = Color(0xFF38251A)
internal val PerfilCinza = Color(0xFF9A887D)
internal val PerfilBorda = Color(0xFFEDE2D0)
internal val PerfilVerde = Color(0xFF72C5A8)
internal val PerfilVerdeEscuro = Color(0xFF5FA08C)
internal val PerfilVermelho = Color(0xFFB3261E)
internal val PerfilCapa = Color(0xFFD9D9D9)
internal val PerfilAvatarFundo = Color(0xFFE3F1EC)

internal fun iniciaisDoNome(nome: String): String =
    nome.split(" ").filter { it.isNotBlank() }.take(2).joinToString("") { it.first().uppercase() }

@Composable
internal fun CabecalhoPerfilEdicao(titulo: String, subtitulo: String, onVoltar: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = "Voltar",
            modifier = Modifier
                .size(20.dp)
                .clickable { onVoltar() }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = titulo, color = PerfilMarrom, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitulo, color = PerfilCinza, fontSize = 13.sp)
        }
    }
}

@Composable
internal fun CampoPerfil(
    rotulo: String,
    valor: String,
    onValorAlterado: (String) -> Unit,
    minLinhas: Int = 1
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = rotulo, color = PerfilMarrom, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = valor,
            onValueChange = onValorAlterado,
            modifier = Modifier.fillMaxWidth(),
            minLines = minLinhas,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PerfilVerde,
                unfocusedBorderColor = PerfilBorda,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Composable
internal fun CampoLeitura(rotulo: String, valor: String, acao: String, onAcao: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = rotulo, color = PerfilMarrom, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, PerfilBorda, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valor,
                color = PerfilMarrom,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = acao,
                color = PerfilVerdeEscuro,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onAcao() }
            )
        }
    }
}