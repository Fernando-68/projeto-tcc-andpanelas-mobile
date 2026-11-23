package com.example.tccmobile.navigation

object Routes {
    const val LOGIN = "login"
    const val CADASTRO = "cadastro"
    const val VERIFICACAO_CODIGO = "verificacao-codigo/{email}"
    const val ESQUECEU_SENHA = "esqueceu-senha"

    const val CODIGO_RECUPERACAO = "codigo-recuperacao/{email}"

    const val NOVA_SENHA = "nova-senha"

    const val HOME = "home"

    const val TERMOS_PRIVACIDADE = "termos-privacidade"

    const val DETALHE_RECEITA = "detalhe-receita/{receitaId}"

    fun detalheReceita(receitaId: Long) = "detalhe-receita/$receitaId"

    const val BUSCA = "busca"

    const val RECEITAS_RAPIDAS = "receitas-rapidas"

    const val COMUNIDADES = "comunidades"

    const val GELADEIRA = "geladeira"
}

