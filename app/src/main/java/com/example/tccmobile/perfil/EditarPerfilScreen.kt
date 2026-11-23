package com.example.tccmobile.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.viewmodel.EditarPerfilViewModel

@Composable
fun EditarPerfilScreen(
    onVoltar: () -> Unit,
    onSalvo: () -> Unit,
    viewModel: EditarPerfilViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregar()
    }

    LaunchedEffect(estado.salvo) {
        if (estado.salvo) onSalvo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PerfilFundo)
    ) {
        CabecalhoPerfilEdicao(
            titulo = "Editar perfil",
            subtitulo = "Atualize as informações que aparecem no seu perfil",
            onVoltar = onVoltar
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(PerfilAvatarFundo)
                        .border(2.dp, PerfilVerde, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = iniciaisDoNome(estado.nome),
                        color = PerfilVerdeEscuro,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            CampoPerfil(
                rotulo = "Nome de exibição",
                valor = estado.nome,
                onValorAlterado = viewModel::alterarNome
            )

            CampoPerfil(
                rotulo = "Nome de usuário",
                valor = estado.usuario,
                onValorAlterado = viewModel::alterarUsuario
            )

            CampoPerfil(
                rotulo = "Bio",
                valor = estado.bio,
                onValorAlterado = viewModel::alterarBio,
                minLinhas = 3
            )

            Text(
                text = "Sua bio aparecerá abaixo do nome no seu perfil público.",
                color = PerfilCinza,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(onClick = onVoltar, modifier = Modifier.weight(1f)) {
                    Text(text = "Cancelar", color = PerfilCinza, fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = viewModel::salvar,
                    modifier = Modifier.weight(1f),
                    enabled = !estado.salvando,
                    colors = ButtonDefaults.buttonColors(containerColor = PerfilVerdeEscuro)
                ) {
                    Text(text = "Salvar", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}