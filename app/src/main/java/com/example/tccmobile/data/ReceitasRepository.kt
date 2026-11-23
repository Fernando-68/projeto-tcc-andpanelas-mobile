package com.example.tccmobile.data

import com.example.tccmobile.R

data class ReceitaResumo(
    val id: Long,
    val imagem: Int,
    val titulo: String,
    val tempo: String
)

class ReceitasRepository {

    fun listarRapidas(): List<ReceitaResumo> = listOf(
        ReceitaResumo(5L, R.drawable.frango_ao_limao, "Frango ao Limão", "25 min"),
        ReceitaResumo(3L, R.drawable.sopa_de_abobora, "Sopa de Abóbora", "30 min"),
        ReceitaResumo(4L, R.drawable.pesto_de_manjericao, "Macarrão ao Pesto", "15 min"),
        ReceitaResumo(6L, R.drawable.pao_de_queijo, "Pão de Queijo", "25 min")
    )
}