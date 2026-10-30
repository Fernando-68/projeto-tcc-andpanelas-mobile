package com.example.tccmobile.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "preferencias_usuario"
)

private val CHAVE_EMAIL = stringPreferencesKey("email_usuario")

class EmailDataStore(private val context: Context) {
    val emailSlavo: Flow<String> = context.dataStore.data.map { preferencias ->
        preferencias[CHAVE_EMAIL] ?: ""
    }

    suspend fun salvarEmail(email: String) {
        context.dataStore.edit { preferencias ->
            preferencias[CHAVE_EMAIL] = email
        }
    }
}