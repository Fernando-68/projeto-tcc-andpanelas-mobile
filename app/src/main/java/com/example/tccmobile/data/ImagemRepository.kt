package com.example.tccmobile.data

import com.example.tccmobile.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ImagemRepository {

    suspend fun enviarFoto(bytes: ByteArray): String = withContext(Dispatchers.IO) {
        val limite = "limite${System.currentTimeMillis()}"
        val conexao = URL("https://api.cloudinary.com/v1_1/${BuildConfig.CLOUDINARY_CLOUD_NAME}/image/upload")
            .openConnection() as HttpURLConnection

        conexao.requestMethod = "POST"
        conexao.doOutput = true
        conexao.setRequestProperty("Content-Type", "multipart/form-data; boundary=$limite")

        conexao.outputStream.use { saida ->
            saida.write("--$limite\r\nContent-Disposition: form-data; name=\"upload_preset\"\r\n\r\n${BuildConfig.CLOUDINARY_PRESET}\r\n".toByteArray())
            saida.write("--$limite\r\nContent-Disposition: form-data; name=\"file\"; filename=\"foto.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n".toByteArray())
            saida.write(bytes)
            saida.write("\r\n--$limite--\r\n".toByteArray())
        }

        JSONObject(conexao.inputStream.bufferedReader().readText()).getString("secure_url")
    }
}