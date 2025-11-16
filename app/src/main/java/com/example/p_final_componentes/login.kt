package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
import java.util.Hashtable

class login : AppCompatActivity() {

    // IMPORTANTE: Cambia esta IP según tu configuración
    // Para EMULADOR: "http://10.0.2.2/androidComponentes/login.php"
    // Para DISPOSITIVO REAL: "http://TU_IP_LOCAL/androidComponentes/login.php"
    private val URL_LOGIN = "http://192.168.2.4/androidComponentes/login.php"

    private val isLoadingState = mutableStateOf(false)
    private val errorMessageState = mutableStateOf<String?>(null)
    private lateinit var requestQueue: RequestQueue

    // Función que implementa la lógica de Volley
    private fun attemptLogin(email: String, password: String) {
        isLoadingState.value = true
        errorMessageState.value = null

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_LOGIN,
            // Listener de Éxito
            Response.Listener { response ->
                isLoadingState.value = false
                val res = response.trim() // Eliminar espacios en blanco

                when (res) {
                    "ERROR 1" -> {
                        errorMessageState.value = "Faltan campos (email/contraseña)."
                        Toast.makeText(this, "Debe llenar ambos campos.", Toast.LENGTH_LONG).show()
                    }
                    "ERROR 2" -> {
                        errorMessageState.value = "Email o contraseña incorrecta."
                        Toast.makeText(this, "Email o contraseña incorrecta.", Toast.LENGTH_LONG).show()
                    }
                    "1" -> { // Rol 1: Admin
                        Toast.makeText(this, "Bienvenido Admin!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@login, catalogoadmin::class.java)
                        intent.putExtra("user_email", email)
                        intent.putExtra("user_type", "admin")
                        startActivity(intent)
                        finish()
                    }
                    "2" -> { // Rol 2: Socio/Usuario normal
                        Toast.makeText(this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@login, menupeliculas::class.java)
                        intent.putExtra("user_email", email)
                        intent.putExtra("user_type", "socio")
                        startActivity(intent)
                        finish()
                    }
                    else -> {
                        // Respuesta inesperada (posible error SQL de PHP)
                        errorMessageState.value = "Respuesta inesperada: $res"
                        Toast.makeText(this, "Error del servidor: $res", Toast.LENGTH_LONG).show()
                    }
                }
            },
            // Listener de Error (Error de conexión, timeout, etc.)
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false
                val errorMsg = "Error de red: ${volleyError.message ?: "Verifique la conexión y la IP del servidor."}"
                errorMessageState.value = errorMsg
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        ) {
            // Envío de parámetros POST
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = Hashtable()
                parametros["user"] = email
                parametros["passw"] = password
                return parametros
            }
        }

        requestQueue.add(stringRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        requestQueue = Volley.newRequestQueue(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Login(
                    isLoading = isLoadingState.value,
                    errorMessage = errorMessageState.value,
                    onClearError = { errorMessageState.value = null },
                    onLogin = { email, password ->
                        attemptLogin(email, password)
                    }
                )
            }
        }
    }
}

@Composable
fun Login(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    onClearError: () -> Unit,
    onLogin: (String, String) -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    if (errorMessage != null) {
        LaunchedEffect(errorMessage) {
            onClearError()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.54f))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
        )

        IniciarSesionAndroid(
            correo = correo,
            contrasena = contrasena,
            onCorreoChange = { onClearError(); correo = it },
            onContrasenaChange = { onClearError(); contrasena = it },
            onLogin = { onLogin(correo, contrasena) },
            isLoading = isLoading,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f)
                .padding(vertical = 50.dp)
        )
    }
}

@Composable
fun IniciarSesionAndroid(
    correo: String,
    contrasena: String,
    onCorreoChange: (String) -> Unit,
    onContrasenaChange: (String) -> Unit,
    onLogin: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black.copy(alpha = 0.85f))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "INICIAR SESIÓN",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = "Correo electrónico",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 4.dp)
        )
        TextField(
            value = correo,
            onValueChange = onCorreoChange,
            placeholder = { Text("tu@email.com", color = Color.Gray) },
            singleLine = true,
            enabled = !isLoading,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                focusedContainerColor = Color.White.copy(alpha = 0.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xffe50914),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Contraseña",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 4.dp)
        )
        TextField(
            value = contrasena,
            onValueChange = onContrasenaChange,
            singleLine = true,
            placeholder = { Text("••••••", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            enabled = !isLoading,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                focusedContainerColor = Color.White.copy(alpha = 0.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xffe50914),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Button(
            onClick = onLogin,
            enabled = !isLoading && correo.isNotEmpty() && contrasena.isNotEmpty(),
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
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    "Iniciar Sesión",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        TextButton(onClick = { /* TODO: Implementar recuperar contraseña */ }) {
            Text(
                text = "¿Olvidaste contraseña?",
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                fontSize = 15.sp,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "¿Primera vez en rewindCodeFilm? ",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp)
            )
            TextButton(onClick = { /* TODO: Implementar registro */ }) {
                Text(
                    text = "Regístrate",
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(fontSize = 14.sp),
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = " O Iniciar Sesión con ",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp)
            )
            HorizontalDivider(
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(39.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_ic),
                contentDescription = "Facebook",
                colorFilter = ColorFilter.tint(Color(0xff4092ff)),
                modifier = Modifier.requiredSize(size = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.google_ic),
                contentDescription = "Google",
                modifier = Modifier.requiredSize(size = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.twitterx),
                contentDescription = "Twitter/X",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.requiredSize(size = 30.dp)
            )
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun LoginPreview() {
    Login(
        isLoading = false,
        errorMessage = null,
        onClearError = {},
        onLogin = { _, _ -> }
    )
}