package com.example.tccmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tccmobile.data.ReceitaResumo
import com.example.tccmobile.data.ReceitasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReceitasRapidasViewModel : ViewModel() {

    private val repositorio = ReceitasRepository()

    private val _receitas = MutableStateFlow<List<ReceitaResumo>>(repositorio.listarRapidas())
    val receitas: StateFlow<List<ReceitaResumo>> = _receitas.asStateFlow()
}