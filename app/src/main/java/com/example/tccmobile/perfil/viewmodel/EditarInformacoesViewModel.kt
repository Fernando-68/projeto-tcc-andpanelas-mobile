package com.example.tccmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.PerfilRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EditarInformacoesUiState(
    val email: String = "",
    val dataNascimento: String = "",
    val editandoData: Boolean = false,
    val salvando: Boolean = false
)

class EditarInformacoesViewModel : ViewModel() {

    private val _estado = MutableStateFlow(EditarInformacoesUiState())
    val estado: StateFlow<EditarInformacoesUiState> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            PerfilRepository.perfilProprio.collect { perfil ->
                _estado.update { it.copy(email = perfil.email, dataNascimento = perfil.dataNascimento) }
            }
        }
    }

    fun iniciarEdicaoData() {
        _estado.update { it.copy(editandoData = true) }
    }

    fun alterarData(dataNascimento: String) {
        _estado.update { it.copy(dataNascimento = dataNascimento) }
    }

    fun salvarData() {
        val dataNascimento = _estado.value.dataNascimento
        viewModelScope.launch {
            _estado.update { it.copy(salvando = true) }
            PerfilRepository.salvarDataNascimento(dataNascimento.trim())
            _estado.update { it.copy(salvando = false, editandoData = false) }
        }
    }
}