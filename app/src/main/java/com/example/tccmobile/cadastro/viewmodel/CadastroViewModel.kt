package com.example.tccmobile.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.ImagemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class FotoPerfilState(
    val uri: String? = null,
    val url: String = "",
    val enviando: Boolean = false,
    val erro: Boolean = false
)

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio = ImagemRepository()

    private val _foto = MutableStateFlow(FotoPerfilState())
    val foto: StateFlow<FotoPerfilState> = _foto.asStateFlow()

    fun selecionarFoto(uri: Uri) {
        _foto.value = FotoPerfilState(uri = uri.toString(), enviando = true)

        viewModelScope.launch {
            try {
                val bytes = withContext(Dispatchers.IO) {
                    getApplication<Application>().contentResolver.openInputStream(uri)?.use { it.readBytes() }
                } ?: throw IllegalStateException("Imagem indisponível")

                val url = repositorio.enviarFoto(bytes)
                _foto.update { it.copy(url = url, enviando = false) }
            } catch (e: Exception) {
                _foto.update { it.copy(enviando = false, erro = true) }
            }
        }
    }
}