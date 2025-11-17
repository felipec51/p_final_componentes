package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

// Modelo de datos para la información del usuario
data class Usuario(
    val id: Int,
    val username: String,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val fechaCreacion: String,
    val nombreRol: String,
    val rolIdRol: Int
)

class AdminUsuarios : AppCompatActivity() {

    // 1. CONFIGURACIÓN DE URLS
    private val URL_BASE = "http://192.168.20.35/androidComponentes/"
    private val URL_OBTENER_USUARIOS = URL_BASE + "obtener_usuarios.php"
    private val URL_ELIMINAR_USUARIO = URL_BASE + "eliminar.php"

    private lateinit var requestQueue: RequestQueue
    private val listadoUsuarios = mutableStateListOf<Usuario>()
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestQueue = Volley.newRequestQueue(this)
        fetchUsuarios() // Iniciar la carga de datos

        try {
            setContent {
                MaterialTheme {
                    AdminUserView(
                        usuarios = listadoUsuarios,
                        isLoading = isLoading.value,
                        onUpdateClick = { usuario ->
                            Toast.makeText(this, "Editar: ${usuario.username}", Toast.LENGTH_SHORT).show()
                        },
                        onDeleteClick = { usuario ->
                            // Llama a la función de eliminación
                            deleteUsuario(usuario)
                        }
                    )
                }
            }
            Log.d("AdminUsuarios", "✅ setContent ejecutado exitosamente")
        } catch (e: Exception) {
            Log.e("AdminUsuarios", "❌ ERROR al inicializar UI: ${e.message}", e)
        }
    }

    private fun fetchUsuarios() {
        isLoading.value = true
        listadoUsuarios.clear()

        val stringRequest = StringRequest(
            Request.Method.GET, URL_OBTENER_USUARIOS,
            { response ->
                isLoading.value = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")

                    if (success) {
                        val usuariosArray = jsonResponse.getJSONArray("usuarios")
                        listadoUsuarios.addAll(parseUsuarios(usuariosArray))
                    } else {
                        val message = jsonResponse.getString("message")
                        // Muestra el mensaje de error de PHP (ej. "Método no permitido")
                        Toast.makeText(this, "Error al cargar: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    // Esto atrapa errores si la respuesta no es un JSON válido
                    val errorMsg = "Error de parseo JSON al obtener. ¿El script PHP tiene errores de sintaxis?"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("AdminUsuarios", errorMsg, e)
                }
            },
            { volleyError ->
                isLoading.value = false
                val errorMsg = "Error de red al obtener: ${volleyError.message ?: "Verifique la conexión Wi-Fi y la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("AdminUsuarios", "Error Volley Obtener: ${volleyError.message}")
            })

        requestQueue.add(stringRequest)
    }

    // FUNCIÓN CLAVE PARA LA ELIMINACIÓN Y EL REFRESCO
    // FUNCIÓN CLAVE PARA LA ELIMINACIÓN Y EL REFRESCO
    private fun deleteUsuario(usuario: Usuario) {
        isLoading.value = true

        // 1. AÑADIDO: DEBUG LOG para confirmar el ID antes de enviar
        Log.d("DELETE_USER", "Intentando eliminar el usuario ID: ${usuario.id}, Username: ${usuario.username}")

        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, URL_ELIMINAR_USUARIO,
            { response ->
                isLoading.value = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                    if (success) {
                        // REFRESCAR: Llama a fetchUsuarios para recargar la lista
                        fetchUsuarios()
                    }
                } catch (e: Exception) {
                    // Si el servidor envía un error HTML o una respuesta vacía
                    val errorMsg = "Error al procesar la respuesta de eliminación. (Server issue)"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("AdminUsuarios", errorMsg, e)
                }
            },
            { volleyError ->
                isLoading.value = false
                val errorMsg = "Error de red al eliminar: ${volleyError.message ?: "Verifique la conexión Wi-Fi o la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("AdminUsuarios", "Error Volley Eliminar: ${volleyError.message}")
            }) {
            // Envío del parámetro POST
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                // ESTA LÍNEA es la que garantiza que el ID se envía:
                parametros["id_usuario"] = usuario.id.toString()
                return parametros
            }

        }
        requestQueue.add(stringRequest)
    }

    private fun parseUsuarios(jsonArray: JSONArray): List<Usuario> {
        val list = mutableListOf<Usuario>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            list.add(
                Usuario(
                    id = obj.getInt("id_usuario"),
                    username = obj.getString("username"),
                    nombre = obj.getString("nombre"),
                    direccion = obj.optString("direccion", "N/A"),
                    telefono = obj.optString("telefono", "N/A"),
                    email = obj.getString("email"),
                    fechaCreacion = obj.getString("fecha_creacion"),
                    nombreRol = obj.getString("nombre_rol"),
                    rolIdRol = obj.getInt("rol_id_rol")
                )
            )
        }
        return list
    }
}

// ... (Resto de Composables: AdminHeader, AdminUserView, UserRow, Preview) ...

@Composable
fun AdminHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 85.dp)
            .background(color = Color(0xff141414))
    ) {
        Text(
            text = "ADMINISTRACIÓN DE USUARIOS",
            color = Color.White,
            lineHeight = 1.5.em,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 10.dp)
        )
    }
}

@Composable
fun AdminUserView(
    usuarios: List<Usuario>,
    isLoading: Boolean,
    onUpdateClick: (Usuario) -> Unit,
    onDeleteClick: (Usuario) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        AdminHeader()

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xffe50914))
            }
        } else if (usuarios.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay usuarios registrados.", color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(usuarios, key = { it.id }) { usuario ->
                    UserRow(
                        usuario = usuario,
                        onUpdateClick = onUpdateClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}

@Composable
fun UserRow(
    usuario: Usuario,
    onUpdateClick: (Usuario) -> Unit,
    onDeleteClick: (Usuario) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF222222))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "**${usuario.username}** (${usuario.nombreRol})",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nombre: ${usuario.nombre}",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                text = "Email: ${usuario.email}",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { onUpdateClick(usuario) },
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF007bff))
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Editar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(
                onClick = { onDeleteClick(usuario) },
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xffe50914))
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewAdminView() {
    val dummyUsers = listOf(
        Usuario(1, "admin", "Administrador", "Sede", "0000", "admin@local.com", "2025-11-01", "admin", 1),
        Usuario(2, "felipec51", "Felipe Murillo", "Cll 123", "30012", "felipe@ex.com", "2025-11-15", "socio", 2),
    )
    AdminUserView(
        usuarios = dummyUsers,
        isLoading = false,
        onUpdateClick = {},
        onDeleteClick = {}
    )
}