package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Hashtable
// 💡 NUEVAS IMPORTACIONES PARA SCROLL
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


// 1. ** Activity Principal de Registro **
class RegistroActivityFelipe : AppCompatActivity() {

    // 2. URL del servidor para el registro
    private val URL_REGISTRO = "http://192.168.20.35/androidComponentes/registro.php"

    private val isLoadingState = mutableStateOf(false)
    private val errorMessageState = mutableStateOf<String?>(null)
    private lateinit var requestQueue: RequestQueue

    // Función que implementa la lógica de Volley para el registro
    private fun attemptRegister(
        username: String,
        password: String,
        nombre: String,
        direccion: String,
        telefono: String,
        email: String,
        preguntaSeguridad: String,
        respuestaSeguridad: String
    ) {
        isLoadingState.value = true
        errorMessageState.value = null

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_REGISTRO,
            // Listener de Éxito
            Response.Listener { response ->
                isLoadingState.value = false
                Log.d("RegistroActivity", "Respuesta PHP: $response")

                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    if (success) {
                        Toast.makeText(this, "✅ Registro exitoso. Ahora inicia sesión.", Toast.LENGTH_LONG).show()
                        // Navegar de vuelta a la pantalla de Login
                        val intent = Intent(this@RegistroActivityFelipe, login::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        errorMessageState.value = message
                        Toast.makeText(this, "❌ Error al registrar: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error de parseo JSON o respuesta inesperada: $response"
                    errorMessageState.value = errorMsg
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("RegistroActivity", "Error de parseo: ${e.message}", e)
                }
            },
            // Listener de Error (Error de conexión, timeout, etc.)
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false
                val errorMsg = "Error de red: ${volleyError.message ?: "Verifique la conexión Wi-Fi y la IP."}"
                errorMessageState.value = errorMsg
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("RegistroActivity", "Error Volley: ${volleyError.message}")
            }
        ) {
            // Envío de parámetros POST
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = Hashtable()
                // Las claves deben coincidir con las esperadas en registro.php
                parametros["username"] = username
                parametros["password"] = password
                parametros["nombre"] = nombre
                parametros["direccion"] = direccion
                parametros["telefono"] = telefono
                parametros["email"] = email
                // PARÁMETROS NUEVOS ENVIADOS
                parametros["pregunta_seguridad"] = preguntaSeguridad
                parametros["respuesta_seguridad"] = respuestaSeguridad
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestQueue = Volley.newRequestQueue(this)

        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                RegisterScreen(
                    isLoading = isLoadingState.value,
                    errorMessage = errorMessageState.value,
                    onClearError = { errorMessageState.value = null },
                    // Se actualiza la llamada con los nuevos 2 campos
                    onRegister = { user, pass, nom, dir, tel, mail, preg, resp ->
                        attemptRegister(user, pass, nom, dir, tel, mail, preg, resp)
                    },
                    onNavigateToLogin = { finish() }
                )
            }
        }
    }
}

// 3. ** Composable de la Pantalla de Registro **
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    onClearError: () -> Unit,
    onRegister: (String, String, String, String, String, String, String, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    // ESTADOS DE TEXTO para todos los campos
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    // ESTADOS NUEVOS para Pregunta de Seguridad
    var respuestaSeguridad by remember { mutableStateOf("") }

    val preguntas = listOf(
        "¿Cuál es el nombre de tu primera mascota?",
        "¿En qué ciudad naciste?"
    )
    var preguntaSeleccionada by remember { mutableStateOf(preguntas.first()) }
    var expanded by remember { mutableStateOf(false) }


    // Validación simple para habilitar el botón
    val isFormValid = username.isNotEmpty() && password.isNotEmpty() && nombre.isNotEmpty() && email.isNotEmpty() && respuestaSeguridad.isNotEmpty()

    // Manejo de errores (similar al Login)
    if (errorMessage != null) {
        LaunchedEffect(errorMessage) {
            onClearError()
        }
    }

    // 💡 Estado de Scroll
    val scrollState = rememberScrollState()

    // Fondo y estructura
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.54f))
    ) {
        //
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Centramos el formulario de registro en la pantalla
        Column(
            // 💡 AÑADIMOS EL MODIFICADOR DE SCROLL A LA COLUMNA
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black.copy(alpha = 0.85f))
                .padding(24.dp)
                .verticalScroll(scrollState), // <--- ¡AQUÍ ESTÁ EL CAMBIO!
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TÍTULO
            Text(
                text = "REGÍSTRATE",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // ---------- CAMPOS DEL FORMULARIO ----------

            // Nombre Completo
            InputField(
                label = "Nombre Completo",
                value = nombre,
                onValueChange = { onClearError(); nombre = it }
            )

            // Username
            InputField(
                label = "Nombre de Usuario",
                value = username,
                onValueChange = { onClearError(); username = it }
            )

            // Email
            InputField(
                label = "Correo Electrónico",
                value = email,
                onValueChange = { onClearError(); email = it }
            )

            // Contraseña
            InputField(
                label = "Contraseña",
                value = password,
                onValueChange = { onClearError(); password = it },
                isPassword = true
            )

            // Dirección
            InputField(
                label = "Dirección (Opcional)",
                value = direccion,
                onValueChange = { onClearError(); direccion = it }
            )

            // Teléfono
            InputField(
                label = "Teléfono (Opcional)",
                value = telefono,
                onValueChange = { onClearError(); telefono = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // -----------------------------------------------------------------
            // CAMPOS DE SEGURIDAD
            // -----------------------------------------------------------------

            // SELECTOR DE PREGUNTA
            Text("Pregunta de Seguridad", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White.copy(alpha = 0.1f),
                        contentColor = Color.White
                    )
                ) {
                    Text(preguntaSeleccionada, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Seleccionar Pregunta")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.85f).background(Color(0xff2a2a2a))
                ) {
                    preguntas.forEach { pregunta ->
                        DropdownMenuItem(
                            text = { Text(pregunta, color = Color.White) },
                            onClick = {
                                preguntaSeleccionada = pregunta
                                expanded = false
                            }
                        )
                    }
                }
            }

            // CAMPO RESPUESTA
            InputField(
                label = "Respuesta de Seguridad",
                value = respuestaSeguridad,
                onValueChange = { onClearError(); respuestaSeguridad = it }
            )
            // -----------------------------------------------------------------

            Spacer(modifier = Modifier.height(16.dp))

            // ----------- BOTÓN REGISTRARSE ------------------------
            Button(
                onClick = {
                    // Se pasan los nuevos campos de seguridad
                    onRegister(
                        username,
                        password,
                        nombre,
                        direccion,
                        telefono,
                        email,
                        preguntaSeleccionada,
                        respuestaSeguridad
                    )
                },
                enabled = !isLoading && isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffe50914),
                    disabledContainerColor = Color(0x80e50914)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        "Crear Cuenta",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ya tienes cuenta? / Iniciar Sesión
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "¿Ya tienes una cuenta? ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                TextButton(onClick = onNavigateToLogin) {
                    Text(
                        text = "Inicia Sesión",
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

// 4. Componente de Campo de Entrada Reutilizable (Se mantiene igual)
@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        Text(text = label, color = Color.White, modifier = Modifier.padding(bottom = 4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                focusedContainerColor = Color.White.copy(alpha = 0.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xffe50914),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        isLoading = false,
        errorMessage = null,
        onClearError = {},
        onRegister = { _, _, _, _, _, _, _, _ -> },
        onNavigateToLogin = {}
    )
}