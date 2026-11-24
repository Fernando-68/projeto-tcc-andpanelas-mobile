package com.example.tccmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.PerfilRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EditarPerfilUiState(
    val nome: String = "",
    val usuario: String = "",
    val bio: String = "",
    val salvando: Boolean = false,
    val salvo: Boolean = false
)

class EditarPerfilViewModel : ViewModel() {

    private val _estado = MutableStateFlow(EditarPerfilUiState())
    val estado: StateFlow<EditarPerfilUiState> = _estado.asStateFlow()

    fun carregar() {
        val perfil = PerfilRepository.perfilProprio.value
        _estado.update { it.copy(nome = perfil.nome, usuario = perfil.usuario, bio = perfil.bio) }
    }

    fun alterarNome(nome: String) {
        _estado.update { it.copy(nome = nome) }
    }

    fun alterarUsuario(usuario: String) {
        _estado.update { it.copy(usuario = usuario) }
    }

    fun alterarBio(bio: String) {
        _estado.update { it.copy(bio = bio) }
    }

    fun salvar() {
        val estado = _estado.value
        if (estado.nome.isBlank() || estado.usuario.isBlank()) return
        viewModelScope.launch {
            _estado.update { it.copy(salvando = true) }
            PerfilRepository.salvarPerfil(estado.nome.trim(), estado.usuario.trim(), estado.bio.trim())
            _estado.update { it.copy(salvando = false, salvo = true) }
        }
    }
}