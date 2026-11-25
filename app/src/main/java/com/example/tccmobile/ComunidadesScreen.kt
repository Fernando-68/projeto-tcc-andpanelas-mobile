
package com.example.tccmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.components.AppBottomBar
import com.example.tccmobile.ui.theme.components.BottomBarItem

@Composable
fun ComunidadesScreen(
    onAbrirInicio: () -> Unit,
    onAbrirBusca: () -> Unit,
    onAbrirGeladeira: () -> Unit,
    onAbrirComunidades: () -> Unit
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

        AppBottomBar(
            selected = BottomBarItem.Buscar,
            onItemClick = { item ->
                when (item) {
                    BottomBarItem.Inicio -> onAbrirInicio()
                    BottomBarItem.Buscar -> {}
                    BottomBarItem.Comunidades -> onAbrirComunidades()
                    BottomBarItem.Geladeira -> onAbrirGeladeira()
                    BottomBarItem.ChefinhoIA -> {}
                }
            },
            onAddClick = {}
        )
    }
}


