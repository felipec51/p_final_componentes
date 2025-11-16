package com.example.p_final_componentes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object ApiService {

    // CAMBIA ESTA URL SEGÚN TU CONFIGURACIÓN
    // Para emulador Android: "http://10.0.2.2/api/"
    // Para dispositivo real: "http://TU_IP_LOCAL/api/" (ejemplo: "http://192.168.1.100/api/")
    // Para servidor online: "http://tudominio.com/api/"
    private const val BASE_URL = "http://192.168.2.4/api/"

    // Función para hacer peticiones POST
    private suspend fun makePostRequest(endpoint: String, jsonData: JSONObject): ApiResponse {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL + endpoint)
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doOutput = true
                connection.doInput = true

                // Enviar datos
                val writer = OutputStreamWriter(connection.outputStream)
                writer.write(jsonData.toString())
                writer.flush()
                writer.close()

                // Leer respuesta
                val responseCode = connection.responseCode
                val reader = if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader(InputStreamReader(connection.inputStream))
                } else {
                    BufferedReader(InputStreamReader(connection.errorStream))
                }

                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                // Parsear respuesta JSON
                val jsonResponse = JSONObject(response.toString())

                ApiResponse(
                    success = jsonResponse.optBoolean("success", false),
                    message = jsonResponse.optString("message", ""),
                    data = jsonResponse
                )

            } catch (e: Exception) {
                ApiResponse(
                    success = false,
                    message = "Error de conexión: ${e.message}",
                    data = null
                )
            }
        }
    }

    // Función para hacer peticiones GET
    private suspend fun makeGetRequest(endpoint: String): ApiResponse {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL + endpoint)
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 10000

                val responseCode = connection.responseCode
                val reader = if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader(InputStreamReader(connection.inputStream))
                } else {
                    BufferedReader(InputStreamReader(connection.errorStream))
                }

                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val jsonResponse = JSONObject(response.toString())

                ApiResponse(
                    success = jsonResponse.optBoolean("success", false),
                    message = jsonResponse.optString("message", ""),
                    data = jsonResponse
                )

            } catch (e: Exception) {
                ApiResponse(
                    success = false,
                    message = "Error de conexión: ${e.message}",
                    data = null
                )
            }
        }
    }

    // Función para hacer peticiones DELETE
    private suspend fun makeDeleteRequest(endpoint: String, jsonData: JSONObject): ApiResponse {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL + endpoint)
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "DELETE"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doOutput = true
                connection.doInput = true

                val writer = OutputStreamWriter(connection.outputStream)
                writer.write(jsonData.toString())
                writer.flush()
                writer.close()

                val responseCode = connection.responseCode
                val reader = if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader(InputStreamReader(connection.inputStream))
                } else {
                    BufferedReader(InputStreamReader(connection.errorStream))
                }

                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val jsonResponse = JSONObject(response.toString())

                ApiResponse(
                    success = jsonResponse.optBoolean("success", false),
                    message = jsonResponse.optString("message", ""),
                    data = jsonResponse
                )

            } catch (e: Exception) {
                ApiResponse(
                    success = false,
                    message = "Error de conexión: ${e.message}",
                    data = null
                )
            }
        }
    }

    // LOGIN
    suspend fun login(email: String, password: String): ApiResponse {
        val jsonData = JSONObject().apply {
            put("email", email)
            put("password", password)
        }
        return makePostRequest("login.php", jsonData)
    }

    // REGISTER
    suspend fun register(
        username: String,
        nombre: String,
        email: String,
        password: String,
        direccion: String = "",
        telefono: String = ""
    ): ApiResponse {
        val jsonData = JSONObject().apply {
            put("username", username)
            put("nombre", nombre)
            put("email", email)
            put("password", password)
            put("direccion", direccion)
            put("telefono", telefono)
        }
        return makePostRequest("register.php", jsonData)
    }

    // GET ALL USERS
    suspend fun getUsers(): ApiResponse {
        return makeGetRequest("get_users.php")
    }

    // UPDATE USER
    suspend fun updateUser(
        idUsuario: Int,
        username: String,
        nombre: String,
        email: String,
        direccion: String,
        telefono: String,
        rolIdRol: Int,
        password: String? = null
    ): ApiResponse {
        val jsonData = JSONObject().apply {
            put("id_usuario", idUsuario)
            put("username", username)
            put("nombre", nombre)
            put("email", email)
            put("direccion", direccion)
            put("telefono", telefono)
            put("rol_id_rol", rolIdRol)
            if (!password.isNullOrEmpty()) {
                put("password", password)
            }
        }

        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL + "update_user.php")
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "PUT"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doOutput = true
                connection.doInput = true

                val writer = OutputStreamWriter(connection.outputStream)
                writer.write(jsonData.toString())
                writer.flush()
                writer.close()

                val responseCode = connection.responseCode
                val reader = if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader(InputStreamReader(connection.inputStream))
                } else {
                    BufferedReader(InputStreamReader(connection.errorStream))
                }

                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val jsonResponse = JSONObject(response.toString())

                ApiResponse(
                    success = jsonResponse.optBoolean("success", false),
                    message = jsonResponse.optString("message", ""),
                    data = jsonResponse
                )

            } catch (e: Exception) {
                ApiResponse(
                    success = false,
                    message = "Error de conexión: ${e.message}",
                    data = null
                )
            }
        }
    }

    // DELETE USER
    suspend fun deleteUser(idUsuario: Int): ApiResponse {
        val jsonData = JSONObject().apply {
            put("id_usuario", idUsuario)
        }
        return makeDeleteRequest("delete_user.php", jsonData)
    }
}

// Data class para manejar las respuestas
data class ApiResponse(
    val success: Boolean,
    val message: String,
    val data: JSONObject?
)