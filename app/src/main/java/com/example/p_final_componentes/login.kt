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
import org.json.JSONObject

class login : AppCompatActivity() {

    private val URL_LOGIN = "${ApiConfig.BASE_URL}/login.php"
    private val isLoadingState = mutableStateOf(false)
    private val errorMessageState = mutableStateOf<String?>(null)
    private lateinit var requestQueue: RequestQueue

    private fun navigateToRegistro() {
        Log.d("LoginActivity", "Navegando a la pantalla de Registro")
        try {
            val intent = Intent(this@login, RegistroActivityFelipe::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("LoginActivity", " ERROR al abrir RegistroActivityFelipe: ${e.message}", e)
            Toast.makeText(this, "Error: ${e.message}. Verifique que la clase exista.", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToPassword() {
        Log.d("LoginActivity", "Navegando a la pantalla de Recuperar Contraseña")
        try {
            val intent = Intent(this@login, RecuperarContrasena::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("LoginActivity", "ERROR al abrir RecuperarContrasena: ${e.message}", e)
            Toast.makeText(this, "Error: ${e.message}. Verifique que la clase exista.", Toast.LENGTH_LONG).show()
        }
    }

    private fun attemptLogin(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            errorMessageState.value = "Debe ingresar email y contraseña."
            return
        }

        isLoadingState.value = true
        errorMessageState.value = null

        val stringRequest = object : StringRequest(
            Method.POST, URL_LOGIN,
            Response.Listener { response ->
                isLoadingState.value = false
                Log.d("LOGIN", "Respuesta: $response")

                try {
                    // 1. Convertimos la respuesta a JSON
                    val json = JSONObject(response)
                    val success = json.optBoolean("success")

                    if (success) {
                        // 2. Extraemos los datos del usuario del JSON
                        val userJson = json.getJSONObject("usuario")
                        val rol = json.getString("rol")

                        // 3. Creamos el objeto Usuario de Java
                        // Asegúrate de que el constructor de Usuario.java coincida o usa setters
                        val usuarioLogueado = Usuario()
                        usuarioLogueado.id = userJson.getInt("id")
                        usuarioLogueado.username = userJson.getString("username")
                        usuarioLogueado.nombre = userJson.getString("nombre")
                        usuarioLogueado.email = userJson.getString("email")
                        usuarioLogueado.rolIdRol = userJson.getInt("rolIdRol")
                        // Puedes setear el resto de campos si los necesitas (direccion, telefono)

                        // 4. GUARDAMOS EN LA SESIÓN GLOBAL
                        Sesion.usuarioActual = usuarioLogueado

                        Toast.makeText(this, "Bienvenido ${usuarioLogueado.nombre}", Toast.LENGTH_SHORT).show()

                        // 5. Navegación basada en Rol
                        if (rol == "1") {
                            val intent = Intent(this@login, Administrador::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent = Intent(this@login, menupeliculas::class.java)
                            startActivity(intent)
                            finish()
                        }

                    } else {
                        val msg = json.optString("message", "Error de credenciales")
                        errorMessageState.value = msg
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                    }

                } catch (e: Exception) {
                    Log.e("LOGIN", "Error parsing JSON", e)
                    // Fallback por si el PHP devuelve un error no JSON o falla el parseo
                    errorMessageState.value = "Error en el servidor: $response"
                }
            },
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false
                val errorMsg = "Error de red: ${volleyError.message}"
                errorMessageState.value = errorMsg
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["user"] = email
                params["passw"] = password
                return params
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
                    },
                    onNavigateToRegister = {
                        navigateToRegistro()
                    },
                    onNavigateToPass = {
                        navigateToPassword()
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
    onLogin: (String, String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToPass:() -> Unit
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
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToPass = onNavigateToPass,
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
    onNavigateToRegister: () -> Unit,
    onNavigateToPass: () -> Unit,
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

        Text(text = "Username o correo", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
        TextField(
            value = correo,
            onValueChange = onCorreoChange,
            placeholder = { Text("tu@email.com", color = Color.Gray) },
            singleLine = true,
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Campo Contraseña
        Text(text = "Contraseña", color = Color.White, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))
        TextField(
            value = contrasena,
            onValueChange = onContrasenaChange,
            singleLine = true,
            placeholder = { Text("••••••", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        // BOTÓN DE INICIAR SESIÓN
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
                .padding(bottom = 16.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(
                    "INICIAR SESIÓN",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Recuperar Contraseña (Recordatorio)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "¿Olvidaste la contraseña? ",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp)
            )

            TextButton(
                onClick = onNavigateToPass
            )
            {
                Text(
                    text = "Recuperar",
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(fontSize = 14.sp),
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Registrarse
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "¿Primera vez en rewindCodeFilm? ",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp)
            )

            TextButton(
                onClick = onNavigateToRegister
            )
            {
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

        // Separador social
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.weight(1f))
            Text(
                text = " O Iniciar Sesión con ",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp)
            )
            HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.weight(1f))
        }

        // Iconos sociales
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
        onLogin = { _, _ -> },
        onNavigateToRegister = {},
        onNavigateToPass = {}
    )
}