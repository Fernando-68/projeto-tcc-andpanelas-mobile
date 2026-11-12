package com.example.tccmobile

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.tccmobile.ui.theme.PlayfairDisplay
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import com.example.tccmobile.navigation.Routes

private val FundoHome = Color(0xFFFFFCF8)
private val MarromHome = Color(0xFF38251A)
private val CinzaHome = Color(0xFF9A887D)
private val VerdeHome = Color(0xFF72C5A8)
private val BordaHome = Color(0xFFEDE2D0)

@Composable
fun HomeScreen(
    onAbrirDetalheReceita: () -> Unit
) {

    val abaSelecionada = remember {
        mutableStateOf("Para você")
    }

    val pesquisaAberta = remember {
        mutableStateOf(false)
    }

    val textoPesquisa = remember {
        mutableStateOf("")
    }

    val usuariosSeguidos = remember {
        mutableStateOf(setOf("anabeatriz", "rafa.souza"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoHome)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // CABEÇALHO FIXO

        CabecalhoHome()

        // CONTEÚDO COM ROLAGEM VERTICAL

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // CATEGORIAS

            CategoriasHome()

            Spacer(modifier = Modifier.height(38.dp))

            // PERFIS

            PerfisHome(
                onPesquisaClick = {
                    pesquisaAberta.value = true
                    textoPesquisa.value = ""
                }
            )

            // PESQUISA DE USUÁRIOS

            if (pesquisaAberta.value) {

                Spacer(modifier = Modifier.height(30.dp))

                PainelPesquisaUsuariosHome(
                    textoPesquisa = textoPesquisa.value,
                    onTextoPesquisaChange = {
                        textoPesquisa.value = it
                    },

                    usuariosSeguidos = usuariosSeguidos.value,
                    onAlternarSeguir = { usuario ->

                        usuariosSeguidos.value =
                            if (usuario in usuariosSeguidos.value) {
                                usuariosSeguidos.value - usuario
                            }else {
                                usuariosSeguidos.value + usuario
                            }
                    },
                    onFechar = {
                        pesquisaAberta.value = false
                        textoPesquisa.value = ""
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ABAS DE RECEITAS

            AbasHome(
                abaSelecionada = abaSelecionada.value,
                onAbaSelecionada = { novaAba ->
                    abaSelecionada.value = novaAba
                }
            )

            when (abaSelecionada.value) {

                "Seguindo" -> {

                    Spacer(modifier = Modifier.height(16.dp))

                    ReceitaSeguindoHome(

                       onAbrirReceita = onAbrirDetalheReceita
                    )
                }

                "Populares" -> {

                    Spacer(modifier = Modifier.height(30.dp))

                    ReceitasPopularesHome()
                }

                "Receitas da semana" -> {

                    Spacer(modifier = Modifier.height(24.dp))

                    ReceitasDaSemanaHome()
                }

                else -> {

                    Spacer(modifier = Modifier.height(34.dp))

                    ReceitaDoDiaHome()

                    Spacer(modifier = Modifier.height(28.dp))

                    ReceitasRapidasHome()
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

        }
            // NAVEGAÇÃO INFERIOR FIXA

            NavegacaoInferiorHome()
    }
}

// CABEÇALHO

@Composable
private fun CabecalhoHome() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(FundoHome)
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(R.drawable.logo_alternativa),
            contentDescription = "Logo &Panelas",
            modifier = Modifier.width(145.dp),
            contentScale = ContentScale.Fit
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        color = Color(0xFFEAF5EF),
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = VerdeHome,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AR",
                    color = VerdeHome,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(1.dp)
            .background(BordaHome)
    )
}

// CATEGORIAS HORIZONTAIS

@Composable
private fun CategoriasHome() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        CategoriaItem(
            imagem = R.drawable.floco_neve,
            texto = "Geladeira",
            corIcone = Color(0xff64a5f5)
        )

        CategoriaItem (
            imagem = R.drawable.chef_hat,
            texto = "Chefia",
            corIcone = CinzaHome
        )

        CategoriaItem(
            imagem = R.drawable.list,
            texto = "Lista",
            corIcone = CinzaHome
        )

        CategoriaItem(
            imagem = R.drawable.users,
            texto = "Comunidade",
            corIcone = VerdeHome
        )
    }
}

@Composable
private fun CategoriaItem(
    imagem: Int,
    texto: String,
    corIcone: Color
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .clip(CircleShape)
            .border(1.5.dp, BordaHome, CircleShape)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {

        Image(
            painter = painterResource(imagem),
            contentDescription = null,
            modifier = Modifier.size(19.dp)
        )

        Text(
            text = texto,
            color = MarromHome,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// PERFIS / STORIES

@Composable
private fun PerfisHome(
    onPesquisaClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        PerfilItem(
            nome = "Ana",
            iniciais = "AC",
            cor = Color(0xff82c4ac)
        )

        PerfilItem(
            nome = "Beatriz",
            iniciais = "BA",
            cor = Color(0xff7ed321)
        )

        PerfilItem(
            nome = "Mariana",
            iniciais = "ML",
            cor = Color(0xff925b2e)
        )

        PerfilItem(
            nome = "Carlos",
            iniciais = "CF",
            cor = Color(0xFFF44336)
        )
    }
}

@Composable
private fun PerfilItem(
    nome: String,
    iniciais: String,
    cor: Color,
    pesquisa: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .then(
                    if (pesquisa) {
                        Modifier
                            .border(1.5.dp, BordaHome, CircleShape)
                            .padding(5.dp)
                    } else {
                        Modifier.background(cor, CircleShape)
                    }
                )
                .then(
                    if (pesquisa) {
                        Modifier.clickable {
                            onClick()
                        }
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (pesquisa) {
                Image(
                    painter = painterResource(R.drawable.search_alter),
                    contentDescription = "Pesquisar perfis",
                    modifier = Modifier.size(55.dp)
                )

            } else {
                Text(
                    text = iniciais,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Text(
            text = nome,
            color = MarromHome,
            fontSize = 15.sp
        )
    }
}

// ABAS DE RECEITAS

@Composable
private fun AbasHome(
    abaSelecionada: String,
    onAbaSelecionada: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AbaHome(
            texto = "Para você",
            selecionada = abaSelecionada == "Para você",
            onClick = {
                onAbaSelecionada("Para você")
            }
        )

        AbaHome(
            texto = "Seguindo",
            selecionada = abaSelecionada == "Seguindo",
            onClick = {
                onAbaSelecionada("Seguindo")
            }
        )

        AbaHome(
            texto = "Populares",
            selecionada = abaSelecionada == "Populares",
            onClick = {
                onAbaSelecionada("Populares")
            }
        )

        AbaHome(
            texto = "Receitas da semana",
            selecionada = abaSelecionada == "Receitas da semana",
            onClick = {
                onAbaSelecionada("Receitas da semana")
            }
        )
    }
}

@Composable
private fun AbaHome(
    texto: String,
    selecionada: Boolean = false,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .height(46.dp)
            .clip(CircleShape)
            .background(
                if (selecionada) VerdeHome else FundoHome
            )
            .border(
                width = if (selecionada) 0.dp else 1.5.dp,
                color = BordaHome,
                shape = CircleShape
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 25.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = texto,
            color = if (selecionada) Color.White else MarromHome,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// RECEITA DA ABA SEGUINDO


@Composable
private fun ReceitaSeguindoHome(
    onAbrirReceita: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .clickable { onAbrirReceita() }
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaHome,
                shape = RoundedCornerShape(22.dp)
            )
    ) {

        // IMAGEM E ELEMENTOS SOBREPOSTOS

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {

            // IMAGEM DA RECEITA

            Image(
                painter = painterResource(
                    R.drawable.frango_ao_limao_siciliano
                ),
                contentDescription = "Frango ao Limão Siciliano",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // CATEGORIA DA RECEITA

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerdeHome)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
            ) {

                Text(
                    text = "ALMOÇO",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // AUTOR DA RECEITA

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {

                // INICIAIS DO AUTOR

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            VerdeHome,
                            CircleShape
                        )
                        .border(
                            1.5.dp,
                            Color.White,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "AC",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // NOME DO AUTOR

                Text(
                    text = "Ana Costa",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // INFORMAÇÕES DA RECEITA

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                )
        ) {

            Text(
                text = "Frango ao Limão Siciliano",
                color = MarromHome,
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

                Text(
                    text = "◷",
                    color = VerdeHome,
                    fontSize = 19.sp
                )

                Text(
                    text = "40 min",
                    color = CinzaHome,
                    fontSize = 14.sp
                )

                Text(
                    text = "♨",
                    color = VerdeHome,
                    fontSize = 17.sp
                )

                Text(
                    text = "Fácil",
                    color = CinzaHome,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun ReceitasPopularesHome() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // TÍTULO E VER TODAS

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Populares da Semana",
                color = MarromHome,
                fontFamily = PlayfairDisplay,
                fontSize = 23.sp,
                modifier = Modifier.clickable {
                    // Navegação será implementada posteriormente.
                }
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // PRIMEIRA LINHA DE RECEITAS

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            ReceitaPopularCardHome(
                modifier = Modifier.weight(1f),
                imagem = R.drawable.frango_ao_limao,
                nome = "Frango ao Limão",
                tempo = "30 min",
                porcoes = "2 porções"
            )

            ReceitaPopularCardHome(
                modifier = Modifier.weight(1f),
                imagem = R.drawable.sopa_de_abobora,
                nome = "Sopa de Abóbora",
                tempo = "40 min",
                porcoes = "4 porções"
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // SEGUNDA LINHA DE RECEITAS

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            ReceitaPopularCardHome(
                modifier = Modifier.weight(1f),
                imagem = R.drawable.pesto_de_manjericao,
                nome = "Macarrão ao Pesto",
                tempo = "15 min",
                porcoes = "2 porções"
            )

            ReceitaPopularCardHome(
                modifier = Modifier.weight(1f),
                imagem = R.drawable.pao_de_queijo,
                nome = "Pão de Queijo",
                tempo = "25 min",
                porcoes = "12 unidades"
            )
        }
    }
}

@Composable
private fun ReceitasDaSemanaHome() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
    ) {

        // TÍTULO DO RANKING

        Text(
            text = "Pódio de Receitas \uD83C\uDFC6",
            color = MarromHome,
            fontFamily = PlayfairDisplay,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(22.dp))

        // PRIMEIRO LUGAR

         ReceitaCampeaHome()

        Spacer(modifier = Modifier.height(14.dp))

        // SEGUNDO LUGAR

        ReceitaRankingCompactaHome(
            imagem = R.drawable.moqueca_baiana,
            posicao = "#2 LUGAR · PRATA",
            corPosicao = Color(0xffb0b0b0),
            nome = "Moqueca Baiana Aromática",
            tempo = "50 min",
            dificuldade = "Médio"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // TERCEIRO LUGAR

        ReceitaRankingCompactaHome(
            imagem = R.drawable.picanha_na_brasa_com_alho,
            posicao = "#3 LUGAR · BRONZE",
            corPosicao = Color(0xffcd7f32),
            nome = "Picanha na Brasa com Alho",
            tempo = "35 min",
            dificuldade = "Fácil"
        )
    }
}

@Composable
private fun ReceitaCampeaHome() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(
                width = 2.dp,
                color = Color(0xfff0d45d),
                shape = RoundedCornerShape(24.dp)
            )
    ) {

        // IMAGEM E CLASSIFICAÇÃO

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp)
        ) {

            Image(painter = painterResource(
                R.drawable.feijoada_completa_tradicional
            ),
                contentDescription = "Feijoada completa Tradicional",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // IDENTIFICAÇÃO DO PRIMEIRO LUGAR

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(Color(0xfffff4cc))
                    .border(
                        width = 1.5.dp,
                        color = Color(0xffffc928),
                        shape = CircleShape
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
            ) {

                Text(
                    text = "#1 LUGAR · CAMPEÃ DA SEMANA",
                    color = Color(0xff94501c),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // INFORMAÇÕES DA RECEITA

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {

            // AUTOR

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box(modifier = Modifier
                    .size(32.dp)
                    .background(
                        Color(0xfffff1c2),
                        CircleShape
                    ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "CF",
                        color = Color(0xff94501c),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "por Carlos Ferreira",
                    color = CinzaHome,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // NOME DA RECEITA

            Text(
                text = "Feijoada Completa Tradicional",
                color = MarromHome,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                lineHeight = 22.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(7.dp))

            // DESCRIÇÃO

            Text(
                text = "O prato brasileiro mais famoso, preparado lentamente com carnes selecionadas e temperos especiais.",
                color = CinzaHome,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // TEMPO E DIFICULDADE

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "◷",
                    color = CinzaHome,
                    fontSize = 17.sp
                )

                Text(
                    text = "180 min",
                    color = CinzaHome,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "▥",
                    color = CinzaHome,
                    fontSize = 17.sp
                )

                Text(
                    text = "Médio",
                    color = CinzaHome,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // BOTÃO VER RECEITA

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(VerdeHome)
                    .clickable {
                        // Navegação para a receita será implementada depois.
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Ver Receita Completa",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@Composable
private fun ReceitaRankingCompactaHome(
    imagem: Int,
    posicao: String,
    corPosicao: Color,
    nome: String,
    tempo: String,
    dificuldade: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaHome,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        // IMAGEM E POSIÇÃO NO RANKING

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {

            Image(
                painter = painterResource(imagem),
                contentDescription = nome,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 5.dp
                    )
            ) {

                Text(
                    text = posicao,
                    color = corPosicao,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // INFORMAÇÕES

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 11.dp
                )
        ) {

            Text(
                text = nome,
                color = MarromHome,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                lineHeight = 19.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = tempo,
                    color = CinzaHome,
                    fontSize = 12.sp
                )

                Text(
                    text = "·",
                    color = CinzaHome,
                    fontSize = 12.sp
                )

                Text(
                    text = dificuldade,
                    color = CinzaHome,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
private fun ReceitaPopularCardHome(
    modifier: Modifier = Modifier,
    imagem: Int,
    nome: String,
    tempo: String,
    porcoes: String
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaHome,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        // IMAGEM

        Image(
            painter = painterResource(imagem),
            contentDescription = nome,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp),
            contentScale = ContentScale.Crop
        )

        // INFORMAÇÕES

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 9.dp
                )
        ) {
            Text(
                text = nome,
                color = MarromHome,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                lineHeight = 19.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = tempo,
                    color = CinzaHome,
                    fontSize = 12.sp
                )

                Text(
                    text = ".",
                    color = CinzaHome,
                    fontSize = 12.sp
                )

                Text(
                    text = porcoes,
                    color = CinzaHome,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


// RECEITA DO DIA

@Composable
private fun ReceitaDoDiaHome() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .height(230.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {

        Image(
            painter = painterResource(R.drawable.receita_do_dia),
            contentDescription = "Feijoada completa tradicional",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.32f))
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xffffc928))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Receita do Dia",
                color = MarromHome,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 20.dp,
                    end = 16.dp,
                    bottom = 18.dp
                )
        ) {
            Text(
                text = "Feijoada Completa Tradicional",
                color = Color.White,
                fontFamily = PlayfairDisplay,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "por Beatriz Alvez",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ReceitasRapidasHome() {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Rápidas e Gostosas",
                color = MarromHome,
                fontFamily = PlayfairDisplay,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ver todas",
                color = VerdeHome,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    // Navegação será ligada posteriormente.
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ReceitaCardHome(
                imagem = R.drawable.frango_ao_limao,
                nome = "Frango ao Limão",
                tempo = "25 min"
            )

            ReceitaCardHome(
                imagem = R.drawable.sopa_de_abobora,
                nome = "Sopa de Abóbora",
                tempo = "30 min"
            )

            ReceitaCardHome(
                imagem = R.drawable.pesto_de_manjericao,
                nome = "Macarrão ao Pesto",
                tempo = "15 min"
            )
        }
    }
}


@Composable
private fun ReceitaCardHome(
    imagem: Int,
    nome: String,
    tempo: String
) {

    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .widthIn(
                min = 170.dp,
                max = 230.dp
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 1.5.dp,
                color = BordaHome,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        Image(
            painter = painterResource(imagem),
            contentDescription = nome,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 9.dp
                )
        ) {

            Text(
                text = nome,
                color = MarromHome,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = tempo,
                color = CinzaHome,
                fontSize = 14.sp
            )
        }
    }
}


// NAVEGAÇÃO INFERIOR


@Composable
fun NavegacaoInferiorHome() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(FundoHome)
    ) {

        // LINHA DIVISÓRIA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BordaHome)
        )

        // ÁREA DA NAVEGAÇÃO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
        ) {

            // QUATRO ITENS DISTRIBUÍDOS IGUALMENTE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
                    .offset(y = (-8).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // INÍCIO
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ItemNavegacaoHome(
                        imagem = R.drawable.home,
                        texto = "Início",
                        selecionado = true
                    )
                }

                // BUSCAR
                Box(
                    modifier = Modifier.weight(1f)
                        .offset(x = (-12).dp),
                    contentAlignment = Alignment.Center
                ) {
                    ItemNavegacaoHome(
                        imagem = R.drawable.lupa,
                        texto = "Buscar"
                    )
                }

                // COMUNIDADES
                Box(
                    modifier = Modifier.weight(1f)
                        .offset(x = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ItemNavegacaoHome(
                        imagem = R.drawable.users_alter,
                        texto = "Comunidades"
                    )
                }

                // GELADEIRA
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ItemNavegacaoHome(
                        imagem = R.drawable.geladeira,
                        texto = "Geladeira"
                    )
                }
            }

            // BOTÃO CENTRAL DE ADIÇÃO
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-34).dp)
                    .size(46.dp)
                    .background(
                        VerdeHome,
                        CircleShape
                    )
                    .clickable {
                        // Ação de adicionar será implementada depois.
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}


@Composable
private fun ItemNavegacaoHome(
    imagem: Int,
    texto: String,
    selecionado: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imagem),
            contentDescription = texto,
            modifier = Modifier.size(20.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = texto,
            color = if (selecionado) VerdeHome else CinzaHome,
            fontSize = 11.sp,
            fontWeight = if (selecionado) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

private data class UsuarioPesquisaHome(
    val nome: String,
    val usuario: String,
    val iniciais: String,
    val cor: Color
)

private val usuariosDisponiveisHome = listOf(

    UsuarioPesquisaHome(
        nome = "Ana Beatriz",
        usuario = "anabeatriz",
        iniciais = "AB",
        cor = Color(0xFF82CDBB)
    ),

    UsuarioPesquisaHome(
        nome = "Carlos Eduardo",
        usuario = "cadu.cozinha",
        iniciais = "CE",
        cor = Color(0xFF6D1D32)
    ),

    UsuarioPesquisaHome(
        nome = "Mariana Lopes",
        usuario = "marilopes",
        iniciais = "ML",
        cor = Color(0xFF82CDBB)
    ),

    UsuarioPesquisaHome(
        nome = "Rafael Souza",
        usuario = "rafa.souza",
        iniciais = "RS",
        cor = Color(0xFF6D1D32)
    )
)

@Composable
private fun PainelPesquisaUsuariosHome(
    textoPesquisa: String,
    onTextoPesquisaChange: (String) -> Unit,
    usuariosSeguidos: Set<String>,
    onAlternarSeguir: (String) -> Unit,
    onFechar: () -> Unit
) {

    val usuariosFiltrados = usuariosDisponiveisHome.filter { usuario ->

        usuario.nome.contains(textoPesquisa, ignoreCase = true) ||
                usuario.usuario.contains(textoPesquisa, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(28.dp)
            )
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFF7EFDF))
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            )
    ) {

        // TÍTULO E BOTÃO FECHAR

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Buscar pessoas",
                color = Color(0xFF6D1D32),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFECE0C9))
                    .clickable {
                        onFechar()
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Fechar pesquisa",
                    tint = Color(0xFF6D1D32),
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // CAMPO DE PESQUISA

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(
                    width = 1.5.dp,
                    color = Color(0xFFE5D7BD),
                    shape = CircleShape
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.lupa),
                contentDescription = "Pesquisar",
                modifier = Modifier.size(23.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            BasicTextField(
                value = textoPesquisa,
                onValueChange = onTextoPesquisaChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color(0xFF6D1D32),
                    fontSize = 15.sp
                ),
                decorationBox = { campoInterno ->

                    Box {

                        if (textoPesquisa.isEmpty()) {

                            Text(
                                text = "Buscar por nome ou @usuário",
                                color = Color(0xFF9A817B),
                                fontSize = 15.sp
                            )
                        }

                        campoInterno()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // RESULTADOS DA PESQUISA

        if (usuariosFiltrados.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Nenhuma pessoa encontrada.",
                    color = Color(0xFF9A817B),
                    fontSize = 14.sp
                )
            }

        } else {

            usuariosFiltrados.forEachIndexed { indice, usuario ->

                UsuarioPesquisaItemHome(
                    usuario = usuario,
                    seguindo = usuario.usuario in usuariosSeguidos,
                    onAlternarSeguir = {
                        onAlternarSeguir(usuario.usuario)
                    }
                )

                if (indice != usuariosFiltrados.lastIndex) {

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE5D7BD))
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

@Composable
private fun UsuarioPesquisaItemHome(
    usuario: UsuarioPesquisaHome,
    seguindo: Boolean,
    onAlternarSeguir: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        // FOTO OU INICIAIS

        Box(
            modifier = Modifier
                .size(54.dp)
                .background(
                    color = usuario.cor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = usuario.iniciais,
                color = if (usuario.cor == Color(0xFF82CDBB)) {
                    Color(0xFF6D1D32)
                } else {
                    Color.White
                },
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // NOME E @USUÁRIO

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = usuario.nome,
                color = Color(0xFF6D1D32),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "@${usuario.usuario}",
                color = Color(0xFF9A817B),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // BOTÃO SEGUIR / DEIXAR DE SEGUIR

        Box(
            modifier = Modifier
                .width(116.dp)
                .height(42.dp)
                .clip(CircleShape)
                .then(
                    if (seguindo) {
                        Modifier.border(
                            width = 1.5.dp,
                            color = Color(0xFF6D1D32),
                            shape = CircleShape
                        )
                    } else {
                        Modifier.background(Color(0xFF6D1D32))
                    }
                )
                .clickable {
                    onAlternarSeguir()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = if (seguindo) {
                    "Deixar de seguir"
                } else {
                    "Seguir"
                },
                color = if (seguindo) {
                    Color(0xFF6D1D32)
                } else {
                    Color.White
                },
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}