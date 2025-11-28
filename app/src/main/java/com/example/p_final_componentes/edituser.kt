package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource // Importante para el FIX
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap

class edituser : AppCompatActivity() {

    private val URL_BASE = "http://192.168.20.35/androidComponentes/"
    private val URL_ACTUALIZAR_USUARIO = URL_BASE + "editar_usuario.php"

    private lateinit var requestQueue: RequestQueue
    private var usuarioOriginal: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edituser) // Asegura que el layout tenga un ComposeView con ID 'render'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Volley
        requestQueue = Volley.newRequestQueue(this)


        @Suppress("DEPRECATION")
        usuarioOriginal = intent.getSerializableExtra("usuario_data") as? Usuario

        val composeView = findViewById<ComposeView>(R.id.render)

        composeView.setContent {
            MaterialTheme {
                FormEditarUsuario(
                    usuarioToEdit = usuarioOriginal,
                    // Lógica para el botón GUARDAR: llamar a la función de red
                    onSaveClick = { id, user, name, password, address, phone, mail ->
                        updateUsuario(id, user, name, password, address, phone, mail)
                    },
                    // Lógica para el botón CANCELAR: cerrar la Activity
                    onCancelClick = {
                        finish()
                    }
                )
            }
        }
    }

    //  FUNCIÓN DE ACTUALIZACIÓN CON VOLLEY
    private fun updateUsuario(
        id: String,
        username: String,
        nombre: String,
        password: String,
        direccion: String,
        telefono: String,
        email: String
    ) {
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, URL_ACTUALIZAR_USUARIO,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                    if (success) {
                        // Si la actualización fue exitosa, cerramos la Activity
                        finish()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error al procesar la respuesta del servidor. (JSON o Server issue)"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("edituser", errorMsg, e)
                }
            },
            { volleyError ->
                val errorMsg = "Error de red al actualizar: ${volleyError.message ?: "Verifique la conexión Wi-Fi o la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("edituser", "Error Volley Actualizar: ${volleyError.message}")
            }) {

            // Envío de parámetros POST
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                // Las claves deben coincidir exactamente con el script PHP: editar_usuario.php
                parametros["id_usuario"] = id
                parametros["username"] = username
                parametros["nombre"] = nombre
                parametros["direccion"] = direccion
                parametros["telefono"] = telefono
                parametros["email"] = email

                // Solo enviamos la contraseña si el campo no está vacío
                if (password.isNotEmpty()) {
                    parametros["password"] = password
                }
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    readOnly: Boolean = false,
    isPasswordField: Boolean = false,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White.copy(alpha = 0.7f)) },
        placeholder = { Text(placeholder) },
        readOnly = readOnly,
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF282828),
            unfocusedContainerColor = Color(0xFF282828),
            focusedLabelColor = Color(0xFFE50914),
            unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
            focusedIndicatorColor = Color(0xFFE50914),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
            cursorColor = Color(0xFFE50914),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}
@Composable
fun FormEditarUsuario(
    usuarioToEdit: Usuario?,
    onSaveClick: (id: String, username: String, nombre: String, password: String, direccion: String, telefono: String, email: String) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estados
    var id by remember { mutableStateOf(usuarioToEdit?.id?.toString() ?: "") }
    var username by remember { mutableStateOf(usuarioToEdit?.username ?: "") }
    var nombre by remember { mutableStateOf(usuarioToEdit?.nombre ?: "") }
    var contrasena by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf(usuarioToEdit?.direccion ?: "") }
    var telefono by remember { mutableStateOf(usuarioToEdit?.telefono ?: "") }
    var email by remember { mutableStateOf(usuarioToEdit?.email ?: "") }

    Surface(
        color = Color(0xff141218),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .shadow(6.dp, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff141218))
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),   // 🔥 SCROLL AGREGADO
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Título
            Text(
                text = "Editar Usuario",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Campos
            Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {

                CustomTextField(
                    label = "ID Usuario (No Editable)",
                    value = id,
                    onValueChange = {},
                    placeholder = "ID",
                    readOnly = true
                )

                CustomTextField(
                    label = "Username",
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Ingrese nuevo username"
                )

                CustomTextField(
                    label = "Nombre Completo",
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = "Ingrese nuevo nombre"
                )

                CustomTextField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Ingrese nuevo email"
                )

                CustomTextField(
                    label = "Dirección",
                    value = direccion,
                    onValueChange = { direccion = it },
                    placeholder = "Ingrese nueva dirección"
                )

                CustomTextField(
                    label = "Teléfono",
                    value = telefono,
                    onValueChange = { telefono = it },
                    placeholder = "Ingrese nuevo teléfono"
                )

                CustomTextField(
                    label = "Nueva Contraseña (Opcional)",
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    placeholder = "********",
                    isPasswordField = true,
                    modifier = Modifier.padding(bottom = 20.dp) // más aire 🔥
                )
            }

            // BOTONES
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),  // 🔥 MÁS SEPARADOS
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                // Guardar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)            // 🔥 botón más grande
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xffe50914))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onSaveClick(
                                id, username, nombre, contrasena,
                                direccion, telefono, email
                            )
                        }
                        .padding(vertical = 10.dp)
                ) {
                    Text("Guardar Cambios", color = Color.White, fontSize = 15.sp)
                }

                // Cancelar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xff2f2f2f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onCancelClick() }
                        .padding(vertical = 10.dp)
                ) {
                    Text("Cancelar", color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}



@Preview(widthDp = 448, heightDp = 746)
@Composable
private fun FormEditarUsuarioPreview() {
    val dummyUser = Usuario().apply {
        id = 101
        username = "jdoe"
        nombre = "John Doe"
        direccion = "Av. Siempre Viva 123"
        telefono = "555-1234"
        email = "john.doe@example.com"
    }
    MaterialTheme {
        FormEditarUsuario(
            usuarioToEdit = dummyUser,
            onSaveClick = { _,_,_,_,_,_,_ -> }, // Mock para Preview
            onCancelClick = {} // Mock para Preview
        )
    }
}