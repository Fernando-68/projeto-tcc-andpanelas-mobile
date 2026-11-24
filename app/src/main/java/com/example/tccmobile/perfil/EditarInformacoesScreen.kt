package com.example.tccmobile.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.viewmodel.EditarInformacoesViewModel

@Composable
fun EditarInformacoesScreen(
    onVoltar: () -> Unit,
    onConfirmarAlteracao: (String) -> Unit,
    viewModel: EditarInformacoesViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PerfilFundo)
    ) {
        CabecalhoPerfilEdicao(
            titulo = "Editar informações",
            subtitulo = "Altere seus dados de acesso e informações pessoais",
            onVoltar = onVoltar
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Dados de acesso", color = PerfilMarrom, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)

            CampoLeitura(
                rotulo = "E-mail",
                valor = estado.email,
                acao = "Alterar",
                onAcao = { onConfirmarAlteracao(estado.email) }
            )

            CampoLeitura(
                rotulo = "Senha",
                valor = "••••••••••••",
                acao = "Alterar",
                onAcao = { onConfirmarAlteracao(estado.email) }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(PerfilBorda)
            )

            Text(text = "Informações pessoais", color = PerfilMarrom, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)

            if (estado.editandoData) {
                CampoPerfil(
                    rotulo = "Data de nascimento",
                    valor = estado.dataNascimento,
                    onValorAlterado = viewModel::alterarData
                )
                Button(
                    onClick = viewModel::salvarData,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !estado.salvando,
                    colors = ButtonDefaults.buttonColors(containerColor = PerfilVerdeEscuro)
                ) {
                    Text(text = "Salvar data", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            } else {
                CampoLeitura(
                    rotulo = "Data de nascimento",
                    valor = estado.dataNascimento,
                    acao = "Alterar",
                    onAcao = viewModel::iniciarEdicaoData
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF7F4EE))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Sua data de nascimento não aparece no seu perfil público. Usamos apenas para privacidade e verificação da conta.",
                    color = PerfilMarrom,
                    fontSize = 13.sp
                )
            }

            Text(
                text = "Para alterar e-mail ou senha, enviaremos um código de confirmação para o e-mail atual antes de concluir a mudança.",
                color = PerfilCinza,
                fontSize = 12.sp
            )
        }
    }
}