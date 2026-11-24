package com.example.tccmobile.perfil

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.R
import com.example.tccmobile.viewmodel.AbaPerfil
import com.example.tccmobile.viewmodel.DenunciaPerfilState
import com.example.tccmobile.viewmodel.MOTIVOS_DENUNCIA
import com.example.tccmobile.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(
    usuarioId: Long?,
    onEditarPerfil: () -> Unit,
    onAbrirConfiguracoes: () -> Unit,
    onAbrirReceita: (Long) -> Unit,
    viewModel: PerfilViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(usuarioId) {
        viewModel.carregar(usuarioId)
    }

    val perfil = estado.perfil ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PerfilFundo)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (estado.ehMeuPerfil) "Meu Perfil" else "Entre Panelas",
                color = PerfilMarrom,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            if (estado.ehMeuPerfil) {
                Text(
                    text = "⚙",
                    color = PerfilCinza,
                    fontSize = 22.sp,
                    modifier = Modifier.clickable { onAbrirConfiguracoes() }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(PerfilCapa)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp)
                    .offset(y = 44.dp)
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(PerfilAvatarFundo)
                    .border(2.dp, PerfilVerde, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = iniciaisDoNome(perfil.nome),
                    color = PerfilVerdeEscuro,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            if (estado.ehMeuPerfil) {
                BotaoEditarPerfil(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp)
                        .offset(y = 28.dp),
                    onClick = onEditarPerfil
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 56.dp)
        ) {
            Text(text = perfil.nome, color = PerfilMarrom, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = "@${perfil.usuario}", color = PerfilCinza, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = perfil.bio, color = PerfilMarrom, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Estatistica(valor = perfil.totalReceitas.toString(), rotulo = "Receitas")
                Estatistica(valor = perfil.seguidores, rotulo = "Seguidores")
                Estatistica(valor = perfil.seguindo.toString(), rotulo = "Seguindo")
            }
            if (!estado.ehMeuPerfil) {
                Spacer(modifier = Modifier.height(14.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                    Text(
                        text = "⚑ Denunciar",
                        color = PerfilMarrom,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { viewModel.abrirDenuncia() }
                    )
                    Text(
                        text = if (perfil.seguindoAgora) "- Seguindo" else "+ Seguir",
                        color = PerfilVerde,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { viewModel.alternarSeguir() }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            AbaPerfil.entries.forEach { aba ->
                val selecionada = estado.abaSelecionada == aba
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(if (selecionada) PerfilMarrom else Color.Transparent)
                        .clickable { viewModel.selecionarAba(aba) }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = rotuloAba(aba),
                        color = if (selecionada) Color.White else PerfilCinza,
                        fontSize = 14.sp,
                        fontWeight = if (selecionada) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (estado.abaSelecionada) {
                AbaPerfil.COMUNIDADES -> estado.comunidades.forEach { comunidade ->
                    ItemPerfilLista(
                        imagem = comunidade.imagem,
                        titulo = comunidade.nome,
                        subtitulo = "${comunidade.membros} membros",
                        onClick = {}
                    )
                }
                else -> estado.receitas.forEach { receita ->
                    ItemPerfilLista(
                        imagem = receita.imagem,
                        titulo = receita.titulo,
                        subtitulo = receita.tempo,
                        onClick = { onAbrirReceita(receita.id) }
                    )
                }
            }
        }
    }

    if (estado.denuncia.aberta) {
        DialogoDenunciaPerfil(
            denuncia = estado.denuncia,
            onSelecionarMotivo = viewModel::selecionarMotivo,
            onAlterarDetalhes = viewModel::alterarDetalhes,
            onCancelar = viewModel::fecharDenuncia,
            onEnviar = viewModel::enviarDenuncia
        )
    }

    if (estado.denuncia.enviada) {
        DialogoDenunciaEnviada(onEntendi = viewModel::fecharDenuncia)
    }
}

private fun rotuloAba(aba: AbaPerfil): String = when (aba) {
    AbaPerfil.RECEITAS -> "Receitas"
    AbaPerfil.SALVOS -> "Salvos"
    AbaPerfil.COMUNIDADES -> "Comunidades"
}

@Composable
private fun Estatistica(valor: String, rotulo: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(text = valor, color = PerfilMarrom, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = rotulo, color = PerfilCinza, fontSize = 14.sp)
    }
}

@Composable
private fun BotaoEditarPerfil(modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .border(1.dp, PerfilBorda, RoundedCornerShape(50))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.lapis_editar),
            contentDescription = "Editar perfil",
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Editar perfil", color = PerfilMarrom, fontSize = 14.sp)
    }
}

@Composable
private fun ItemPerfilLista(imagem: Int, titulo: String, subtitulo: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, PerfilBorda, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imagem),
            contentDescription = titulo,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = titulo, color = PerfilMarrom, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(text = subtitulo, color = PerfilCinza, fontSize = 13.sp)
        }
    }
}

@Composable
private fun DialogoDenunciaPerfil(
    denuncia: DenunciaPerfilState,
    onSelecionarMotivo: (String) -> Unit,
    onAlterarDetalhes: (String) -> Unit,
    onCancelar: () -> Unit,
    onEnviar: () -> Unit
) {
    Dialog(onDismissRequest = onCancelar) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Denunciar Perfil", color = PerfilMarrom, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "QUAL O MOTIVO DA DENÚNCIA?", color = PerfilCinza, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            MOTIVOS_DENUNCIA.forEach { motivo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelecionarMotivo(motivo) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = denuncia.motivo == motivo,
                        onClick = { onSelecionarMotivo(motivo) },
                        colors = RadioButtonDefaults.colors(selectedColor = PerfilVerde)
                    )
                    Text(text = motivo, color = PerfilMarrom, fontSize = 14.sp)
                }
            }
            OutlinedTextField(
                value = denuncia.detalhes,
                onValueChange = onAlterarDetalhes,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Escreva aqui mais informações sobre a denúncia...", color = PerfilCinza, fontSize = 13.sp) },
                minLines = 3,
                shape = RoundedCornerShape(14.dp)
            )
            Text(
                text = "Nossa equipe analisará esta denúncia dentro das próximas 24 horas para garantir que as diretrizes da comunidade sejam mantidas.",
                color = PerfilCinza,
                fontSize = 12.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(onClick = onCancelar, modifier = Modifier.weight(1f)) {
                    Text(text = "Cancelar", color = PerfilCinza)
                }
                Button(
                    onClick = onEnviar,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = PerfilVermelho)
                ) {
                    Text(text = "Enviar denúncia", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun DialogoDenunciaEnviada(onEntendi: () -> Unit) {
    Dialog(onDismissRequest = onEntendi) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(PerfilAvatarFundo),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✓", color = PerfilVerdeEscuro, fontSize = 24.sp)
            }
            Text(text = "Denúncia Enviada!", color = PerfilMarrom, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Obrigado por nos ajudar a manter a comunidade do Panelas limpa e segura para todos. Analisaremos seu reporte em breve.",
                color = PerfilCinza,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onEntendi,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PerfilVerde)
            ) {
                Text(text = "Entendi", color = Color.White)
            }
        }
    }
}