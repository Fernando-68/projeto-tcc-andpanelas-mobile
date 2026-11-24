package com.example.tccmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.ComunidadeResumo
import com.example.tccmobile.data.PerfilRepository
import com.example.tccmobile.data.PerfilUsuario
import com.example.tccmobile.data.ReceitaResumo
import com.example.tccmobile.data.ReceitasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AbaPerfil { RECEITAS, SALVOS, COMUNIDADES }

val MOTIVOS_DENUNCIA = listOf(
    "Conteúdo impróprio ou ofensivo",
    "Spam ou propaganda indesejada",
    "Não tem relação com culinária",
    "Violação de direitos autorais",
    "Outro motivo"
)

data class DenunciaPerfilState(
    val aberta: Boolean = false,
    val motivo: String = MOTIVOS_DENUNCIA.first(),
    val detalhes: String = "",
    val enviada: Boolean = false
)

data class PerfilUiState(
    val perfil: PerfilUsuario? = null,
    val ehMeuPerfil: Boolean = true,
    val abaSelecionada: AbaPerfil = AbaPerfil.RECEITAS,
    val receitas: List<ReceitaResumo> = emptyList(),
    val comunidades: List<ComunidadeResumo> = emptyList(),
    val denuncia: DenunciaPerfilState = DenunciaPerfilState()
)

class PerfilViewModel : ViewModel() {

    private val _estado = MutableStateFlow(PerfilUiState())
    val estado: StateFlow<PerfilUiState> = _estado.asStateFlow()

    fun carregar(usuarioId: Long?) {
        viewModelScope.launch {
            _estado.update {
                it.copy(
                    ehMeuPerfil = usuarioId == null,
                    receitas = ReceitasRepository().listarRapidas(),
                    comunidades = PerfilRepository.listarComunidades()
                )
            }
            if (usuarioId == null) {
                PerfilRepository.perfilProprio.collect { perfil ->
                    _estado.update { it.copy(perfil = perfil) }
                }
            } else {
                val perfil = PerfilRepository.buscarPerfilUsuario(usuarioId)
                _estado.update { it.copy(perfil = perfil) }
            }
        }
    }

    fun selecionarAba(aba: AbaPerfil) {
        _estado.update { it.copy(abaSelecionada = aba) }
    }

    fun alternarSeguir() {
        val perfil = _estado.value.perfil ?: return
        viewModelScope.launch {
            val seguindo = PerfilRepository.alternarSeguir(perfil.id)
            _estado.update { it.copy(perfil = perfil.copy(seguindoAgora = seguindo)) }
        }
    }

    fun abrirDenuncia() {
        _estado.update { it.copy(denuncia = it.denuncia.copy(aberta = true)) }
    }

    fun fecharDenuncia() {
        _estado.update { it.copy(denuncia = DenunciaPerfilState()) }
    }

    fun selecionarMotivo(motivo: String) {
        _estado.update { it.copy(denuncia = it.denuncia.copy(motivo = motivo)) }
    }

    fun alterarDetalhes(detalhes: String) {
        _estado.update { it.copy(denuncia = it.denuncia.copy(detalhes = detalhes)) }
    }

    fun enviarDenuncia() {
        val estado = _estado.value
        val perfil = estado.perfil ?: return
        viewModelScope.launch {
            PerfilRepository.enviarDenuncia(perfil.id, estado.denuncia.motivo, estado.denuncia.detalhes)
            _estado.update { it.copy(denuncia = DenunciaPerfilState(enviada = true)) }
        }
    }
}