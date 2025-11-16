package com.example.p_final_componentes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import androidx.compose.foundation.clickable

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Form()
            }
        }
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    // ESTADOS DE LOS CAMPOS
    var direccion by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var actor by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // URL de tu servidor PHP
    val apiUrl = "http://192.168.2.4/androidComponentes/registrar_usuario.php"

    Box(
        modifier = modifier
            .requiredWidth(390.dp)
            .requiredHeight(844.dp)
    ) {
        // Fondo oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.73f))
        )

        // DIRECCIÓN
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 46.dp, bottom = 709.dp)
        ) {
            Text(
                text = "Dirección",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.offset(x = 8.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Direccion",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // NOMBRE
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 149.dp, bottom = 605.dp)
        ) {
            Text(
                "Nombre",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.offset(8.dp)
            )
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Nombre",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // ACTOR FAVORITO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 253.dp, bottom = 502.dp)
        ) {
            Text("Actor favorito", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = actor,
                onValueChange = { actor = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Actor favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // DIRECTOR FAVORITO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 357.dp, bottom = 398.dp)
        ) {
            Text("Director favorito", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = director,
                onValueChange = { director = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Director favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // CONTRASEÑA
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 461.dp, bottom = 294.dp)
        ) {
            Text("Contraseña", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Ingresa tu Contraseña",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // EMAIL
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 565.dp, bottom = 190.dp)
        ) {
            Text("Email", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Email",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // GÉNERO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 669.dp, bottom = 86.dp)
        ) {
            Text("Género", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))
            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = genero,
                onValueChange = { genero = it },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Genero favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)
            )
        }

        // BOTÓN REGISTRAR - CORREGIDO
        Box(
            modifier = Modifier
                .offset(x = 6.63.dp, y = 776.05.dp)
                .requiredWidth(358.dp)
                .requiredHeight(45.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xffe50914))
                .clickable(enabled = !isLoading) {
                    when {
                        nombre.isBlank() -> {
                            Toast.makeText(context, "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
                        }
                        email.isBlank() -> {
                            Toast.makeText(context, "El email es obligatorio", Toast.LENGTH_SHORT).show()
                        }
                        contrasena.isBlank() -> {
                            Toast.makeText(context, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show()
                        }
                        contrasena.length < 6 -> {
                            Toast.makeText(context, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            isLoading = true
                            scope.launch {
                                val resultado = registrarUsuario(
                                    apiUrl = apiUrl,
                                    nombre = nombre,
                                    direccion = direccion,
                                    email = email,
                                    contrasena = contrasena,
                                    actor = actor,
                                    director = director,
                                    genero = genero
                                )

                                isLoading = false

                                if (resultado.success) {
                                    Toast.makeText(context, resultado.message, Toast.LENGTH_LONG).show()
                                    // Limpiar campos después del registro exitoso
                                    nombre = ""
                                    direccion = ""
                                    email = ""
                                    contrasena = ""
                                    actor = ""
                                    director = ""
                                    genero = ""
                                } else {
                                    Toast.makeText(context, resultado.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Text(
                    text = "Registrar",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Data class para el resultado
data class RegistroResultado(
    val success: Boolean,
    val message: String,
    val userId: Int? = null
)

// Función suspendida para hacer la petición HTTP
suspend fun registrarUsuario(
    apiUrl: String,
    nombre: String,
    direccion: String,
    email: String,
    contrasena: String,
    actor: String,
    director: String,
    genero: String
): RegistroResultado = withContext(Dispatchers.IO) {
    try {
        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        connection.doOutput = true
        connection.connectTimeout = 10000
        connection.readTimeout = 10000

        // Crear JSON con los datos
        val jsonData = JSONObject().apply {
            put("nombre", nombre)
            put("direccion", direccion)
            put("email", email)
            put("contrasena", contrasena)
            put("actor", actor)
            put("director", director)
            put("genero", genero)
        }

        // Enviar datos
        val writer = OutputStreamWriter(connection.outputStream)
        writer.write(jsonData.toString())
        writer.flush()
        writer.close()

        // Leer respuesta
        val responseCode = connection.responseCode

        val inputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream
        } else {
            connection.errorStream
        }

        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            response.append(line)
        }
        reader.close()

        // Log para debug
        android.util.Log.d("REGISTRO", "Response: $response")

        // Parsear respuesta JSON
        val jsonResponse = JSONObject(response.toString())
        val success = jsonResponse.optBoolean("success", false)
        val message = jsonResponse.optString("message", "Error desconocido")
        val userId = if (jsonResponse.has("user_id")) jsonResponse.getInt("user_id") else null

        connection.disconnect()

        RegistroResultado(success, message, userId)

    } catch (e: Exception) {
        e.printStackTrace()
        android.util.Log.e("REGISTRO", "Error: ${e.message}", e)
        RegistroResultado(false, "Error de conexión: ${e.message}")
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun FormPreview() {
    Form(Modifier)
}