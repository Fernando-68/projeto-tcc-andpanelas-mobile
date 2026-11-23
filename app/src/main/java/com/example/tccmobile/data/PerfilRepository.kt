package com.example.tccmobile.data

import com.example.tccmobile.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PerfilUsuario(
    val id: Long,
    val nome: String,
    val usuario: String,
    val bio: String,
    val email: String,
    val dataNascimento: String,
    val totalReceitas: Int,
    val seguidores: String,
    val seguindo: Int,
    val seguindoAgora: Boolean = false
)

data class ComunidadeResumo(
    val id: Long,
    val imagem: Int,
    val nome: String,
    val membros: String
)

object PerfilRepository {

    private val _perfilProprio = MutableStateFlow(
        PerfilUsuario(
            id = 1L,
            nome = "Ana Costa",
            usuario = "anacosta",
            bio = "Chef caseira apaixonada por comida brasileira. Compartilhando receitas simples e deliciosas.",
            email = "ana.costa@email.com",
            dataNascimento = "20/01/2009",
            totalReceitas = 47,
            seguidores = "1.2k",
            seguindo = 389
        )
    )
    val perfilProprio: StateFlow<PerfilUsuario> = _perfilProprio.asStateFlow()

    private val perfisOutros = mapOf(
        2L to PerfilUsuario(
            id = 2L,
            nome = "Pedro Souza",
            usuario = "pedrosouza",
            bio = "Amante de doces e confeitaria. Sempre testando receitas novas.",
            email = "pedro.souza@email.com",
            dataNascimento = "10/03/1995",
            totalReceitas = 23,
            seguidores = "845",
            seguindo = 120
        )
    )

    private val seguidos = mutableSetOf<Long>()

    suspend fun buscarPerfilUsuario(usuarioId: Long): PerfilUsuario {
        delay(300)
        val perfil = perfisOutros[usuarioId] ?: perfisOutros.values.first()
        return perfil.copy(seguindoAgora = perfil.id in seguidos)
    }

    suspend fun alternarSeguir(usuarioId: Long): Boolean {
        delay(200)
        if (!seguidos.remove(usuarioId)) seguidos.add(usuarioId)
        return usuarioId in seguidos
    }

    suspend fun enviarDenuncia(usuarioId: Long, motivo: String, detalhes: String) {
        delay(500)
    }

    suspend fun salvarPerfil(nome: String, usuario: String, bio: String) {
        delay(500)
        _perfilProprio.update { it.copy(nome = nome, usuario = usuario, bio = bio) }
    }

    suspend fun salvarDataNascimento(dataNascimento: String) {
        delay(500)
        _perfilProprio.update { it.copy(dataNascimento = dataNascimento) }
    }

    fun listarComunidades(): List<ComunidadeResumo> = listOf(
        ComunidadeResumo(1L, R.drawable.feijoada_completa_tradicional, "Bolos & Confeitaria", "28.7k"),
        ComunidadeResumo(2L, R.drawable.moqueca_baiana, "Cozinha Brasileira", "45.6k")
    )
}