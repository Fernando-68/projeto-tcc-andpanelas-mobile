
package com.example.tccmobile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FundoDenuncia = Color(0xFFFFFCF8)
private val MarromDenuncia = Color(0xFF38251A)
private val VerdeDenuncia = Color(0xFF72C5A8)
private val BordaDenuncia = Color(0xFFEDE2D0)
private val TextoSecundarioDenuncia = Color(0xFF9A887D)
private val VermelhoDenuncia = Color(0xFFD90020)

@Composable
fun DenunciarComunidadeScreen(
    nomeComunidade: String,
    onCancelar: () -> Unit,
    onEnviarDenuncia: (String, String) -> Unit
) {
    var motivoSelecionado by remember { mutableStateOf("") }
    var detalhes by remember { mutableStateOf("") }
    var mostrarErro by remember { mutableStateOf(false) }

    val motivos = listOf(
        "Conteúdo impróprio ou ofensivo",
        "Spam ou propaganda indesejada",
        "Não tem relação com culinária",
        "Violação de direitos autorais",
        "Outro motivo"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDenuncia)
            .padding(16.dp)
    ) {

        // CABEÇALHO
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Denunciar Comunidade",
                color = MarromDenuncia,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        Color(0xFFF5EEE5),
                        CircleShape
                    )
                    .clickable { onCancelar() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "×",
                    color = MarromDenuncia,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = nomeComunidade,
            color = TextoSecundarioDenuncia,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // MOTIVOS
        Text(
            text = "QUAL O MOTIVO DA DENÚNCIA?",
            color = MarromDenuncia,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        motivos.forEach { motivo ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        motivoSelecionado = motivo
                        mostrarErro = false
                    }
                    .padding(vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .border(
                            width = 1.dp,
                            color = if (motivoSelecionado == motivo) {
                                VerdeDenuncia
                            } else {
                                BordaDenuncia
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (motivoSelecionado == motivo) {
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .background(
                                    VerdeDenuncia,
                                    CircleShape
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = motivo,
                    color = MarromDenuncia,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // DETALHES ADICIONAIS
        Text(
            text = "Detalhes adicionais (opcional)",
            color = MarromDenuncia,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .border(
                    1.dp,
                    BordaDenuncia,
                    RoundedCornerShape(9.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(9.dp)
                )
                .padding(10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            BasicTextField(
                value = detalhes,
                onValueChange = { detalhes = it.take(500) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = MarromDenuncia,
                    fontSize = 11.sp
                ),
                decorationBox = { innerTextField ->
                    Box {
                        if (detalhes.isEmpty()) {
                            Text(
                                text = "Descreva aqui mais informações sobre a denúncia...",
                                color = TextoSecundarioDenuncia,
                                fontSize = 10.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${detalhes.length}/500",
            color = TextoSecundarioDenuncia,
            fontSize = 9.sp,
            modifier = Modifier.align(Alignment.End)
        )

        if (mostrarErro) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Selecione um motivo para continuar.",
                color = VermelhoDenuncia,
                fontSize = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Nossa equipe analisará esta denúncia para garantir que as diretrizes da comunidade sejam respeitadas.",
            color = TextoSecundarioDenuncia,
            fontSize = 10.sp,
            lineHeight = 15.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // BOTÕES
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .border(
                        1.dp,
                        BordaDenuncia,
                        RoundedCornerShape(9.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(9.dp)
                    )
                    .clickable { onCancelar() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cancelar",
                    color = TextoSecundarioDenuncia,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .weight(1.3f)
                    .height(44.dp)
                    .background(
                        VermelhoDenuncia,
                        RoundedCornerShape(9.dp)
                    )
                    .clickable {
                        if (motivoSelecionado.isNotBlank()) {
                            mostrarErro = false
                            onEnviarDenuncia(
                                motivoSelecionado,
                                detalhes.trim()
                            )
                        } else {
                            mostrarErro = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Enviar denúncia",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

