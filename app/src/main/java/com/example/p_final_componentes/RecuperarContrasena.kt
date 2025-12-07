package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
// Importaciones de Material3
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.Hashtable

class RecuperarContrasena : AppCompatActivity() {

    private val URL_RECUPERACION = "${ApiConfig.BASE_URL}/recuperar_contrasena.php"
    private val isLoadingState = mutableStateOf(false)
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestQueue = Volley.newRequestQueue(this)

        val composeView = ComposeView(this)
        setContentView(composeView)

        composeView.setContent {
            MaterialTheme {
                RecuperacionForm(
                    isLoading = isLoadingState.value,
                    onRecuperar = ::attemptRecuperacion,
                    onNavigateBack = { finish() }
                )
            }
        }
    }

    private fun attemptRecuperacion(usuario: String, pregunta: String, respuesta: String) {
        isLoadingState.value = true

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_RECUPERACION,
            Response.Listener { response ->
                isLoadingState.value = false
                val res = response.trim()

                when (res) {
                    "SUCCESS" -> {
                        Toast.makeText(this, "✅ Contraseña restablecida a 'temporal123'. Por favor, inicie sesión y cámbiela.", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    "ERROR_USER" -> {
                        Toast.makeText(this, "❌ Usuario no encontrado o pregunta incorrecta.", Toast.LENGTH_LONG).show()
                    }
                    "ERROR_ANSWER" -> {
                        Toast.makeText(this, "❌ Respuesta de seguridad incorrecta.", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this, "Error inesperado: $res", Toast.LENGTH_LONG).show()
                    }
                }
            },
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false
                val errorMsg = "Error de red: Verifique la conexión o el servidor."
                Log.e("Recuperacion", "Error Volley: ${volleyError.message}")
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        ) {
            // Envío de parámetros POST
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = Hashtable()
                parametros["usuario"] = usuario
                parametros["pregunta"] = pregunta
                parametros["respuesta"] = respuesta
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperacionForm(
    isLoading: Boolean,
    onRecuperar: (String, String, String) -> Unit,
    onNavigateBack: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var respuesta by remember { mutableStateOf("") }

    val preguntas = listOf(
        "¿Cuál es el nombre de tu primera mascota?",
        "¿En qué ciudad naciste?"
    )
    var preguntaSeleccionada by remember { mutableStateOf(preguntas.first()) }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black.copy(alpha = 0.85f))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TÍTULO
            Text(
                text = "RECUPERAR ACCESO",
                color = Color(0xffe50914),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp, top = 8.dp)
            )

            Text(
                text = "Ingrese su nombre de usuario/correo y responda la pregunta de seguridad para restablecer su contraseña a 'temporal123'.",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            //CAMPO USUARIO/CORREO
            Text("Usuario/Correo", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                placeholder = { Text("tu@email.com o nombre de usuario", color = Color.Gray) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                    focusedContainerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color(0xffe50914),
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // SELECTOR DE PREGUNTA
            Text("Pregunta de Seguridad", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
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

            Spacer(Modifier.height(16.dp))

            //  CAMPO RESPUESTA
            Text("Respuesta", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
            TextField(
                value = respuesta,
                onValueChange = { respuesta = it },
                placeholder = { Text("Respuesta a la pregunta", color = Color.Gray) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                    focusedContainerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color(0xffe50914),
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )


            Button(
                onClick = { onRecuperar(usuario, preguntaSeleccionada, respuesta) },
                enabled = !isLoading && usuario.isNotEmpty() && respuesta.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffe50914),
                    disabledContainerColor = Color(0x80e50914)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Validar y Restablecer", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Botón de Volver
            TextButton(onClick = onNavigateBack) {
                Text(
                    text = "Cancelar y volver al Login",
                    color = Color.Gray,
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                    fontSize = 15.sp,
                )
            }
        }
    }
}