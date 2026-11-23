
package com.example.tccmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.home.NavegacaoInferiorHome

@Composable
fun ComunidadesScreen(
    onAbrirInicio: () -> Unit,
    onAbrirBusca: () -> Unit,
    onAbrirGeladeira: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFCF8))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(28.dp)
        ) {
            Text(
                text = "Comunidades",
                color = Color(0xFF38251A),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Encontre pessoas e compartilhe suas experiências culinárias.",
                color = Color(0xFF9A887D),
                fontSize = 15.sp
            )
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


