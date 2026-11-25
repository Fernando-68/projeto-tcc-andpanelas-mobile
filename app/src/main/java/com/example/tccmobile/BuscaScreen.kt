package com.example.tccmobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Surface
import com.example.tccmobile.home.NavegacaoInferiorHome
import com.example.tccmobile.ui.theme.components.AppBottomBar
import com.example.tccmobile.ui.theme.components.BottomBarItem

private val FundoBusca = Color(0xFFFFFCF8)
private val MarromBusca = Color(0xFF38251A)
private val CinzaBusca = Color(0xFF9A887D)
private val VerdeBusca = Color(0xFF72C5A8)
private val BordaBusca = Color(0xFFEDE2D0)

private data class PessoaBusca(
    val nome: String,
    val usuario: String,
    val iniciais: String,
    val cor: Color
)

private val pessoasDisponiveis = listOf(
    PessoaBusca(
        nome = "Ana Beatriz",
        usuario = "anabeatriz",
        iniciais = "AB",
        cor = Color(0xFF82CDBB)
    ),
    PessoaBusca(
        nome = "Carlos Eduardo",
        usuario = "cadu.cozinha",
        iniciais = "CE",
        cor = Color(0xFF6D1D32)
    ),
    PessoaBusca(
        nome = "Mariana Lopes",
        usuario = "marilopes",
        iniciais = "ML",
        cor = Color(0xFF925B2E)
    ),
    PessoaBusca(
        nome = "Rafael Souza",
        usuario = "rafa.souza",
        iniciais = "RS",
        cor = Color(0xFF6D1D32)
    ),
    PessoaBusca(
        nome = "Juliana Martins",
        usuario = "ju.martins",
        iniciais = "JM",
        cor = Color(0xFFB779A8)
    ),
    PessoaBusca(
        nome = "Pedro Henrique",
        usuario = "pedro.cozinha",
        iniciais = "PH",
        cor = Color(0xFF547C9D)
    ),
    PessoaBusca(
        nome = "Camila Oliveira",
        usuario = "camilaoliveira",
        iniciais = "CO",
        cor = Color(0xFFD28A55)
    ),
    PessoaBusca(
        nome = "Lucas Ferreira",
        usuario = "lucasferreira",
        iniciais = "LF",
        cor = Color(0xFF648B69)
    ),
    PessoaBusca(
        nome = "Beatriz Costa",
        usuario = "bia.nacozinha",
        iniciais = "BC",
        cor = Color(0xFFAA806A)
    ),
    PessoaBusca(
        nome = "Gabriel Almeida",
        usuario = "gabrielalmeida",
        iniciais = "GA",
        cor = Color(0xFF7770A5)
    )
)

private data class IngredienteBuscaSelecionado(
    val nome: String,
    val quantidade: String
)

private data class ComunidadeBusca(
    val nome: String,
    val descricao: String,
    val membros: String,
    val emoji: String,
    val cor: Color,
    val categoria: String
)

private val comunidadesDisponiveis = listOf(

    ComunidadeBusca(
        nome = "Confeitaria para Todos",
        descricao = "Compartilhe receitas, técnicas e dicas de confeitaria.",
        membros = "2.450",
        emoji = "🍰",
        cor = Color(0xFFFFE7D9),
        categoria = "Confeitaria"
    ),

    ComunidadeBusca(
        nome = "Vida Saudável",
        descricao = "Receitas equilibradas e alimentação saudável.",
        membros = "1.820",
        emoji = "🥗",
        cor = Color(0xFFE2EEE7),
        categoria = "Saudável"
    ),

    ComunidadeBusca(
        nome = "Amantes do Churrasco",
        descricao = "Cortes, temperos e técnicas para um bom churrasco.",
        membros = "3.120",
        emoji = "🔥",
        cor = Color(0xFFFFE0D5),
        categoria = "Churrasco"
    ),

    ComunidadeBusca(
        nome = "Mundo das Massas",
        descricao = "Massas artesanais, molhos e receitas italianas.",
        membros = "1.540",
        emoji = "🍝",
        cor = Color(0xFFFFF0D5),
        categoria = "Massas"
    ),

    ComunidadeBusca(
        nome = "Comida Caseira",
        descricao = "O sabor e o carinho das receitas de família.",
        membros = "4.300",
        emoji = "🍲",
        cor = Color(0xFFE8E3F5),
        categoria = "Caseira"
    ),

    ComunidadeBusca(
        nome = "Receitas Econômicas",
        descricao = "Ideias deliciosas gastando pouco.",
        membros = "2.760",
        emoji = "🥘",
        cor = Color(0xFFE0F2EC),
        categoria = "Econômica"
    ),

    ComunidadeBusca(
        nome = "Doces e Sobremesas",
        descricao = "Bolos, tortas, doces e sobremesas especiais.",
        membros = "1.980",
        emoji = "🧁",
        cor = Color(0xFFF4E7EF),
        categoria = "Confeitaria"
    ),

    ComunidadeBusca(
        nome = "Cozinha Vegana",
        descricao = "Descubra o universo da culinária vegetal.",
        membros = "1.250",
        emoji = "🌱",
        cor = Color(0xFFE2EEE7),
        categoria = "Vegana"
    )
)

@Composable
fun BuscaScreen(
    onAbrirDetalheReceita: (Long) -> Unit,
    onAbrirInicio: () -> Unit,
    onAbrirComunidades: () -> Unit,
    onAbrirGeladeira: () -> Unit
) {

    var categoriaSelecionada by remember {
        mutableStateOf("Pessoas")
    }

    var textoPesquisa by remember {
        mutableStateOf("")
    }

    var pessoasSeguidas by remember {
        mutableStateOf(setOf("anabeatriz", "rafa.souza"))
    }

    var quantidadeIngrediente by remember {
        mutableStateOf("1")
    }

    var meusIngredientes by remember {
        mutableStateOf(
            listOf(
                IngredienteBuscaSelecionado(
                    nome = "queijo",
                    quantidade = "1"
                )
            )
        )
    }

    var mostrarSugestoesIngredientes by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoBusca)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        // CABEÇALHO

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Buscar Receita",
                    color = MarromBusca,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Diga o que tem e sugerimos receitas compatíveis",
                    color = CinzaBusca,
                    fontSize = 12.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BordaBusca)
        )

        // CONTEÚDO ROLÁVEL

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            // CAMPO DE PESQUISA

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(Color.White)
                    .border(
                        1.dp,
                        BordaBusca,
                        RoundedCornerShape(13.dp)
                    )
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.lupa),
                    contentDescription = "Pesquisar",
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                BasicTextField(
                    value = textoPesquisa,
                    onValueChange = {
                        textoPesquisa = it
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                ) { campoInterno ->

                    Box {
                        if (textoPesquisa.isEmpty()) {
                            Text(
                                text = "Buscar pessoas, receitas, ingredientes...",
                                color = CinzaBusca,
                                fontSize = 14.sp
                            )
                        }

                        campoInterno()
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // CATEGORIAS

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                listOf(
                    "Pessoas",
                    "Receitas",
                    "Ingredientes",
                    "Comunidades"
                ).forEach { categoria ->

                    val selecionada =
                        categoriaSelecionada == categoria

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                if (selecionada)
                                    MarromBusca
                                else
                                    Color.Transparent
                            )
                            .clickable {
                                categoriaSelecionada = categoria
                                textoPesquisa = ""
                            }
                            .padding(
                                horizontal = 16.dp,
                                vertical = 10.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = categoria,
                            color = if (selecionada)
                                Color.White
                            else
                                CinzaBusca,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CONTEÚDO DE CADA CATEGORIA

            when (categoriaSelecionada) {

                "Pessoas" -> {

                    PessoasBusca(
                        textoPesquisa = textoPesquisa,
                        pessoasSeguidas = pessoasSeguidas,
                        onAlternarSeguir = { usuario ->

                            pessoasSeguidas =
                                if (usuario in pessoasSeguidas) {
                                    pessoasSeguidas - usuario
                                } else {
                                    pessoasSeguidas + usuario
                                }
                        }
                    )
                }

                "Receitas" -> {

                    ReceitasBusca(
                        textoPesquisa = textoPesquisa,
                        onPesquisaChange = {
                            textoPesquisa = it
                        },
                        onAbrirDetalheReceita = {
                            onAbrirDetalheReceita(1L)
                        }
                    )
                }

                "Ingredientes" -> {

                    IngredientesBusca(
                        nomeIngrediente = textoPesquisa,
                        onNomeIngredienteChange = {
                            textoPesquisa = it
                        },
                        quantidade = quantidadeIngrediente,
                        onQuantidadeChange = {
                            quantidadeIngrediente = it
                        },
                        ingredientes = meusIngredientes,
                        onAdicionarIngrediente = { nome, qtd ->

                            val nomeFormatado = nome.trim().lowercase()

                            if (
                                nomeFormatado.isNotEmpty() &&
                                meusIngredientes.none {
                                    it.nome.equals(nomeFormatado, ignoreCase = true)
                                }
                            ) {
                                meusIngredientes = meusIngredientes + IngredienteBuscaSelecionado(
                                    nome = nomeFormatado,
                                    quantidade = qtd
                                )

                                textoPesquisa = ""
                                quantidadeIngrediente = "1"
                                mostrarSugestoesIngredientes = false
                            }
                        },
                        onRemoverIngrediente = { ingrediente ->

                            meusIngredientes = meusIngredientes.filter {
                                !it.nome.equals(ingrediente, ignoreCase = true)
                            }
                        },
                        onLimparTudo = {
                            meusIngredientes = emptyList()
                            mostrarSugestoesIngredientes = false
                        },
                        onBuscarReceitas = {
                            mostrarSugestoesIngredientes = true
                        },
                        mostrarSugestoes = mostrarSugestoesIngredientes
                    )
                }

                "Comunidades" -> {

                    ComunidadesBusca(
                        textoPesquisa = textoPesquisa
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // NAVEGAÇÃO INFERIOR FIXA

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


@Composable
private fun ReceitasBusca(
    textoPesquisa: String,
    onPesquisaChange: (String) -> Unit,
    onAbrirDetalheReceita: () -> Unit
) {
    if (textoPesquisa.isBlank()) {
        ConteudoExplorarReceitasBusca(
            textoPesquisa = textoPesquisa,
            onPesquisaChange = onPesquisaChange
        )
        return
    }

    val textoNormalizado = textoPesquisa.trim().lowercase()

    val termosReceita = listOf(
        "frango ao limão siciliano",
        "frango",
        "limão",
        "siciliano",
        "almoço",
        "ana costa"
    )

    val encontrouReceita = termosReceita.any {
        it.contains(textoNormalizado) ||
                textoNormalizado.contains(it)
    }

    var ordenarPor by remember {
        mutableStateOf("Tempo")
    }

    var mostrarOrdenacao by remember {
        mutableStateOf(false)
    }

    var avaliacaoChefinho by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {

        if (encontrouReceita) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "1 resultado encontrado",
                    color = CinzaBusca,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        mostrarOrdenacao = true
                    }
                ) {
                    Text(
                        text = "Ordenar por: ",
                        color = CinzaBusca,
                        fontSize = 12.sp
                    )

                    Text(
                        text = ordenarPor,
                        color = Color(0xFF16A65A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            CartaoDicaChefinhoBusca(
                avaliacao = avaliacaoChefinho,
                onAvaliar = {
                    avaliacaoChefinho = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            CartaoResultadoReceitaBusca(
                onClick = onAbrirDetalheReceita
            )

        } else {

            CartaoNenhumResultadoBusca(
                onVoltar = {
                    onPesquisaChange("")
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            DicaChefinhoCompactaBusca()
        }

        Spacer(modifier = Modifier.height(18.dp))

        CartaoGeladeiraBusca()
    }

    if (mostrarOrdenacao) {
        Dialog(
            onDismissRequest = {
                mostrarOrdenacao = false
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 12.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ordenar por",
                            color = MarromBusca,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "Fechar",
                            color = CinzaBusca,
                            fontSize = 12.sp,
                            modifier = Modifier.clickable {
                                mostrarOrdenacao = false
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(BordaBusca)
                    )

                    val opcoesOrdenacao = listOf(
                        "Relevância",
                        "Tempo",
                        "Custo",
                        "Dificuldade",
                        "De A - Z",
                        "Nº de Porções Crescente",
                        "Nº de Porções Decrescente"
                    )

                    opcoesOrdenacao.forEach { opcao ->

                        Text(
                            text = opcao,
                            color = if (ordenarPor == opcao)
                                Color(0xFF16A65A)
                            else
                                MarromBusca,
                            fontSize = 14.sp,
                            fontWeight = if (ordenarPor == opcao)
                                FontWeight.SemiBold
                            else
                                FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    ordenarPor = opcao
                                    mostrarOrdenacao = false
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun ConteudoExplorarReceitasBusca(
    textoPesquisa: String,
    onPesquisaChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        // EM ALTA AGORA

        Text(
            text = "Em alta agora 🔥",
            color = MarromBusca,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChipReceitaBusca("frango") {
                onPesquisaChange("frango")
            }

            ChipReceitaBusca("bolo") {
                onPesquisaChange("bolo")
            }

            ChipReceitaBusca("vegano") {
                onPesquisaChange("vegano")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChipReceitaBusca("rápido") {
                onPesquisaChange("rápido")
            }

            ChipReceitaBusca("massa") {
                onPesquisaChange("massa")
            }

            ChipReceitaBusca("sopa") {
                onPesquisaChange("sopa")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // EXPLORAR POR CATEGORIA

        Text(
            text = "Explorar por categoria",
            color = MarromBusca,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            CategoriaReceitaBusca(
                emoji = "☕",
                nome = "Café da manhã",
                cor = Color(0xFFFFECD5),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Café da manhã")
            }

            CategoriaReceitaBusca(
                emoji = "🍲",
                nome = "Almoço",
                cor = Color(0xFFE0F2EC),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Almoço")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            CategoriaReceitaBusca(
                emoji = "🌙",
                nome = "Jantar",
                cor = Color(0xFFECE4E1),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Jantar")
            }

            CategoriaReceitaBusca(
                emoji = "🍰",
                nome = "Sobremesa",
                cor = Color(0xFFFFDF8C),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Sobremesa")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            CategoriaReceitaBusca(
                emoji = "🌱",
                nome = "Vegano",
                cor = Color(0xFFE2EEE7),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Vegano")
            }

            CategoriaReceitaBusca(
                emoji = "⚡",
                nome = "Rápido",
                cor = Color(0xFFFFE0E2),
                modifier = Modifier.weight(1f)
            ) {
                onPesquisaChange("Rápido")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BUSCAR PELA GELADEIRA

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0F3EF))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(Color(0xFF3985F5)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.box),
                    contentDescription = "Ícone da geladeira",
                    modifier = Modifier
                        .size(25.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Buscar pela Geladeira",
                    color = Color(0xFF14665D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Diga tudo que tem em casa e sugerimos receitas.",
                    color = Color(0xFF679B91),
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
private fun ChipReceitaBusca(
    nome: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .border(
                1.dp,
                BordaBusca,
                CircleShape
            )
            .clickable { onClick() }
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    Color(0xFF14665D),
                    CircleShape
                )
        )

        Text(
            text = nome,
            color = MarromBusca,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun CategoriaReceitaBusca(
    emoji: String,
    nome: String,
    cor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(cor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = emoji,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = nome,
            color = MarromBusca,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
private fun PessoasBusca(
    textoPesquisa: String,
    pessoasSeguidas: Set<String>,
    onAlternarSeguir: (String) -> Unit
) {

    val pessoasFiltradas = pessoasDisponiveis.filter { pessoa ->

        pessoa.nome.contains(textoPesquisa, ignoreCase = true) ||
                pessoa.usuario.contains(textoPesquisa, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = if (textoPesquisa.isEmpty())
                "Sugestões para você"
            else
                "Resultados da pesquisa",
            color = MarromBusca,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (pessoasFiltradas.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhuma pessoa encontrada.",
                    color = CinzaBusca,
                    fontSize = 14.sp
                )
            }

        } else {

            pessoasFiltradas.forEach { pessoa ->

                PessoaBuscaItem(
                    pessoa = pessoa,
                    seguindo = pessoa.usuario in pessoasSeguidas,
                    onClick = {
                        onAlternarSeguir(pessoa.usuario)
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun PessoaBuscaItem(
    pessoa: PessoaBusca,
    seguindo: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // FOTO DE PERFIL

        Box(
            modifier = Modifier
                .size(52.dp)
                .background(pessoa.cor, CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = pessoa.iniciais,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // NOME E USUÁRIO

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = pessoa.nome,
                color = MarromBusca,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "@${pessoa.usuario}",
                color = CinzaBusca,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // BOTÃO SEGUIR

        Box(
            modifier = Modifier
                .width(112.dp)
                .height(38.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(
                    if (seguindo)
                        Color(0xFFF4EEE6)
                    else
                        VerdeBusca
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = if (seguindo) "Seguindo" else "Seguir",
                color = if (seguindo)
                    MarromBusca
                else
                    Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun TextoCategoriaBusca(
    titulo: String,
    descricao: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = titulo,
            color = MarromBusca,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = descricao,
            color = CinzaBusca,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
    }
}


@Composable
private fun IngredientesBusca(
    nomeIngrediente: String,
    onNomeIngredienteChange: (String) -> Unit,
    quantidade: String,
    onQuantidadeChange: (String) -> Unit,
    ingredientes: List<IngredienteBuscaSelecionado>,
    onAdicionarIngrediente: (String, String) -> Unit,
    onRemoverIngrediente: (String) -> Unit,
    onLimparTudo: () -> Unit,
    onBuscarReceitas: () -> Unit,
    mostrarSugestoes: Boolean
) {

    val ingredientesRapidos = listOf(
        "frango",
        "ovo",
        "queijo",
        "tomate",
        "cebola",
        "alho",
        "arroz",
        "feijão"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        // TÍTULO

        Text(
            text = "Quais ingredientes você procura na receita?",
            color = MarromBusca,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO DE QUANTIDADE E BOTÃO ADICIONAR

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .border(
                        1.dp,
                        BordaBusca,
                        RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                BasicTextField(
                    value = quantidade,
                    onValueChange = {
                        onQuantidadeChange(
                            it.filter { caractere ->
                                caractere.isDigit()
                            }.take(3)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                ) { campoInterno ->

                    Box {
                        if (quantidade.isEmpty()) {
                            Text(
                                text = "Qtd.",
                                color = CinzaBusca,
                                fontSize = 14.sp
                            )
                        }

                        campoInterno()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(VerdeBusca)
                    .clickable {
                        onAdicionarIngrediente(
                            nomeIngrediente,
                            quantidade.ifBlank { "1" }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ADIÇÃO RÁPIDA

        Text(
            text = "Toque para adicionar rapidamente:",
            color = CinzaBusca,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        ingredientesRapidos.chunked(4).forEach { linha ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                linha.forEach { ingrediente ->

                    val jaAdicionado = ingredientes.any {
                        it.nome.equals(
                            ingrediente,
                            ignoreCase = true
                        )
                    }

                    ChipIngredienteBusca(
                        nome = ingrediente,
                        adicionado = jaAdicionado,
                        modifier = Modifier.weight(1f)
                    ) {

                        if (!jaAdicionado) {
                            onAdicionarIngrediente(
                                ingrediente,
                                quantidade.ifBlank { "1" }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // MEUS INGREDIENTES

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Meus Ingredientes (${ingredientes.size})",
                color = Color(0xFF263447),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            if (ingredientes.isNotEmpty()) {

                Text(
                    text = "Limpar tudo",
                    color = CinzaBusca,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        onLimparTudo()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (ingredientes.isEmpty()) {

            Text(
                text = "Nenhum ingrediente adicionado ainda.",
                color = CinzaBusca,
                fontSize = 13.sp
            )

        } else {

            ingredientes.chunked(3).forEach { linha ->

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    linha.forEach { ingrediente ->

                        ChipMeuIngredienteBusca(
                            ingrediente = ingrediente,
                            onRemover = {
                                onRemoverIngrediente(
                                    ingrediente.nome
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // BOTÃO O QUE POSSO FAZER?

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (ingredientes.isEmpty())
                        CinzaBusca.copy(alpha = 0.35f)
                    else
                        VerdeBusca
                )
                .clickable(
                    enabled = ingredientes.isNotEmpty()
                ) {
                    onBuscarReceitas()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "O que posso fazer?",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (mostrarSugestoes) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Suas sugestões de receitas aparecerão aqui quando conectarmos o catálogo de receitas.",
                color = CinzaBusca,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // BUSCAR PELA GELADEIRA

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0F3EF))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(Color(0xFF3985F5)),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.box),
                    contentDescription = "Ícone da geladeira",
                    modifier = Modifier
                        .size(20.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Buscar pela Geladeira",
                    color = Color(0xFF14665D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Diga tudo que tem em casa e sugerimos receitas.",
                    color = Color(0xFF679B91),
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}


@Composable
private fun ChipIngredienteBusca(
    nome: String,
    adicionado: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .border(
                1.dp,
                BordaBusca,
                CircleShape
            )
            .clickable(
                enabled = !adicionado
            ) {
                onClick()
            }
            .padding(
                horizontal = 8.dp,
                vertical = 9.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (adicionado) "✓" else "+",
            color = if (adicionado)
                VerdeBusca
            else
                CinzaBusca,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = nome,
            color = MarromBusca,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
private fun ChipMeuIngredienteBusca(
    ingrediente: IngredienteBuscaSelecionado,
    onRemover: () -> Unit
) {

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color(0xFFE0F2EC))
            .border(
                1.dp,
                Color(0xFFB8E2D5),
                CircleShape
            )
            .clickable {
                onRemover()
            }
            .padding(
                horizontal = 12.dp,
                vertical = 7.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if ((ingrediente.quantidade.toIntOrNull() ?: 1) > 1) {
                "${ingrediente.quantidade}x ${ingrediente.nome}"
            } else {
                ingrediente.nome
            },
            color = Color(0xFF559C86),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            text = "⊗",
            color = Color(0xFF559C86),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun ComunidadesBusca(
    textoPesquisa: String
) {

    var comunidadesParticipando by remember {
        mutableStateOf(
            setOf("Confeitaria para Todos", "Comida Caseira")
        )
    }

    val comunidadesFiltradas = comunidadesDisponiveis.filter {
        it.nome.contains(textoPesquisa, ignoreCase = true) ||
                it.descricao.contains(textoPesquisa, ignoreCase = true) ||
                it.categoria.contains(textoPesquisa, ignoreCase = true)
    }

    val minhasComunidades = comunidadesDisponiveis.filter {
        it.nome in comunidadesParticipando
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        // EM DESTAQUE

        Text(
            text = "Em destaque",
            color = MarromBusca,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        val destaque = comunidadesDisponiveis.first()

        CartaoComunidadeBusca(
            comunidade = destaque,
            participando = destaque.nome in comunidadesParticipando,
            destaque = true,
            onClick = {
                comunidadesParticipando =
                    if (destaque.nome in comunidadesParticipando) {
                        comunidadesParticipando - destaque.nome
                    } else {
                        comunidadesParticipando + destaque.nome
                    }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // EXPLORAR COMUNIDADES

        Text(
            text = if (textoPesquisa.isBlank())
                "Explorar comunidades"
            else
                "Resultados da pesquisa",
            color = MarromBusca,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (comunidadesFiltradas.isEmpty()) {

            Text(
                text = "Nenhuma comunidade encontrada.",
                color = CinzaBusca,
                fontSize = 13.sp
            )

        } else {

            comunidadesFiltradas
                .filter { it.nome != destaque.nome }
                .forEach { comunidade ->

                    CartaoComunidadeBusca(
                        comunidade = comunidade,
                        participando = comunidade.nome in comunidadesParticipando,
                        destaque = false,
                        onClick = {
                            comunidadesParticipando =
                                if (comunidade.nome in comunidadesParticipando) {
                                    comunidadesParticipando - comunidade.nome
                                } else {
                                    comunidadesParticipando + comunidade.nome
                                }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // MINHAS COMUNIDADES

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Minhas comunidades",
                color = MarromBusca,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "(${minhasComunidades.size})",
                color = CinzaBusca,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (minhasComunidades.isEmpty()) {

            Text(
                text = "Você ainda não participa de nenhuma comunidade.",
                color = CinzaBusca,
                fontSize = 13.sp
            )

        } else {

            minhasComunidades.forEach { comunidade ->

                CartaoComunidadeBusca(
                    comunidade = comunidade,
                    participando = true,
                    destaque = false,
                    onClick = {
                        comunidadesParticipando =
                            comunidadesParticipando - comunidade.nome
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Composable
private fun CartaoComunidadeBusca(
    comunidade: ComunidadeBusca,
    participando: Boolean,
    destaque: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .border(
                1.dp,
                BordaBusca,
                RoundedCornerShape(15.dp)
            )
            .padding(13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(if (destaque) 58.dp else 50.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(comunidade.cor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = comunidade.emoji,
                fontSize = if (destaque) 28.sp else 24.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = comunidade.nome,
                color = MarromBusca,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comunidade.descricao,
                color = CinzaBusca,
                fontSize = 11.sp,
                lineHeight = 15.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "${comunidade.membros} membros",
                color = Color(0xFF72A995),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(9.dp))
                .background(
                    if (participando)
                        Color(0xFFF2EEE8)
                    else
                        VerdeBusca
                )
                .clickable {
                    onClick()
                }
                .padding(
                    horizontal = 11.dp,
                    vertical = 9.dp
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = if (participando)
                    "Participando"
                else
                    "Participar",
                color = if (participando)
                    MarromBusca
                else
                    Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
private fun CartaoDicaChefinhoBusca(
    avaliacao: String?,
    onAvaliar: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFFAEBCB))
            .padding(12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.chefinho),
                    contentDescription = "Chefinho",
                    modifier = Modifier.size(38.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "Dica do Chefinho",
                    color = Color(0xFF63442F),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "O chefinho teve uma ideia, o que você acha?",
                    color = Color(0xFF94745C),
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (avaliacao == "Gostei")
                            Color(0xFF72C5A8)
                        else
                            Color(0xFF91CBAA)
                    )
                    .clickable {
                        onAvaliar("Gostei")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (avaliacao == "Gostei")
                        "✓ Gostei"
                    else
                        "Gostei",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE8425E))
                    .clickable {
                        onAvaliar("Não gostei")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (avaliacao == "Não gostei")
                        "✓ Não gostei"
                    else
                        "Não gostei",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CartaoResultadoReceitaBusca(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .border(
                1.dp,
                BordaBusca,
                RoundedCornerShape(15.dp)
            )
            .clickable {
                onClick()
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {

            Image(
                painter = painterResource(
                    R.drawable.frango_ao_limao_siciliano
                ),
                contentDescription = "Frango ao Limão Siciliano",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF72C5A8))
                    .padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    )
            ) {
                Text(
                    text = "83% compatível",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF72C5A8))
                    .padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    )
            ) {
                Text(
                    text = "Fácil",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF69432D))
                        .border(
                            1.dp,
                            Color.White,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AC",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Ana Costa",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Column(
            modifier = Modifier.padding(11.dp)
        ) {

            Text(
                text = "Frango ao Limão Siciliano",
                color = MarromBusca,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "◷ 40min",
                    color = CinzaBusca,
                    fontSize = 11.sp
                )

                Text(
                    text = "♧ 4 porções",
                    color = CinzaBusca,
                    fontSize = 11.sp
                )
            }
        }
    }
}


@Composable
private fun CartaoNenhumResultadoBusca(
    onVoltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFE7D9C3),
                shape = RoundedCornerShape(16.dp)
            )
            .heightIn(min = 175.dp)
            .padding(
                horizontal = 22.dp,
                vertical = 25.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Nenhum item Encontrado",
            color = Color(0xFF63442F),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "De acordo com a sua pesquisa não conseguimos encontrar uma receita compatível!",
            color = Color(0xFF747E90),
            fontSize = 14.sp,
            lineHeight = 19.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(17.dp))

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(VerdeBusca)
                .clickable {
                    onVoltar()
                }
                .padding(
                    horizontal = 20.dp,
                    vertical = 9.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "← voltar",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun DicaChefinhoCompactaBusca() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFFAEBCB))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.chefinho),
                contentDescription = "Chefinho",
                modifier = Modifier.size(38.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = "Dica do Chefinho",
                color = Color(0xFF63442F),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Otimize suas compras e reduza o desperdício com ajuda da IA →",
                color = Color(0xFF94745C),
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun CartaoGeladeiraBusca() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFE0F3EF))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF3985F5)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.box),
                contentDescription = "Ícone da geladeira",
                modifier = Modifier
                    .size(20.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(11.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Buscar pela Geladeira",
                color = Color(0xFF14665D),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Diga tudo que tem em casa e sugerimos receitas.",
                color = Color(0xFF679B91),
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}
