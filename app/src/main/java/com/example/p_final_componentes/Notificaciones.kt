package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Notificaciones : ComponentActivity() {

    // Asegúrate de que la IP sea correcta
    private val BASE_URL = "http://192.168.20.35/androidComponentes"
    private val URL_NOTIFICACIONES = "$BASE_URL/obtener_notificaciones.php"
    private val URL_ELIMINAR = "$BASE_URL/eliminar_de_lista.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val usuario = Sesion.usuarioActual

        if (usuario == null) {
            Toast.makeText(this, "Debes iniciar sesión", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            MaterialTheme {
                NotificacionesScreen(
                    userId = usuario.id,
                    urlObtener = URL_NOTIFICACIONES,
                    urlEliminar = URL_ELIMINAR, // Pasamos la nueva URL
                    onPeliculaClick = { pelicula ->
                        val intent = Intent(this, Rentar::class.java)
                        intent.putExtra("PELICULA_OBJ", pelicula)
                        startActivity(intent)
                    },
                    onVolver = { finish() }
                )
            }
        }
    }
}

@Composable
fun NotificacionesScreen(
    userId: Int,
    urlObtener: String,
    urlEliminar: String,
    onPeliculaClick: (Pelicula) -> Unit,
    onVolver: () -> Unit
) {
    val context = LocalContext.current
    var notificaciones by remember { mutableStateOf<List<Pelicula>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Función para eliminar película (Lógica Volley)
    fun eliminarPelicula(pelicula: Pelicula) {
        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(
            Method.POST, urlEliminar,
            Response.Listener { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    if (jsonResponse.optBoolean("success")) {
                        Toast.makeText(context, "Eliminada de la lista", Toast.LENGTH_SHORT).show()
                        // Actualizamos la lista local filtrando la película eliminada
                        notificaciones = notificaciones.filter { it.id_pelicula != pelicula.id_pelicula }
                    } else {
                        Toast.makeText(context, "Error: ${jsonResponse.optString("message")}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error en respuesta", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id_usuario"] = userId.toString()
                params["id_pelicula"] = pelicula.id_pelicula.toString()
                return params
            }
        }
        queue.add(request)
    }

    // Cargar datos iniciales
    LaunchedEffect(Unit) {
        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(
            Method.POST, urlObtener,
            Response.Listener { response ->
                isLoading = false
                try {
                    val jsonResponse = JSONObject(response)
                    if (jsonResponse.optBoolean("success")) {
                        val jsonArray = jsonResponse.getJSONArray("data")
                        val listaTemp = mutableListOf<Pelicula>()
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            val pelicula = Pelicula()
                            pelicula.id_pelicula = obj.getInt("id_pelicula")
                            pelicula.titulo = obj.getString("titulo")
                            pelicula.duracion = obj.optInt("duracion", 0)
                            pelicula.anio_lanzamiento = obj.optInt("anio_lanzamiento", 0)
                            pelicula.precio_alquiler = obj.optDouble("precio_alquiler", 0.0)
                            pelicula.copias_disponibles = obj.optInt("copias_disponibles", 1)
                            listaTemp.add(pelicula)
                        }
                        notificaciones = listaTemp
                    } else {
                        notificaciones = emptyList()
                    }
                } catch (e: Exception) {
                    errorMessage = "Error procesando datos."
                }
            },
            Response.ErrorListener {
                isLoading = false
                errorMessage = "Error de conexión."
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id_usuario"] = userId.toString()
                return params
            }
        }
        queue.add(request)
    }

    NotificacionesContent(
        isLoading = isLoading,
        errorMessage = errorMessage,
        notificaciones = notificaciones,
        onPeliculaClick = onPeliculaClick,
        onEliminarClick = { pelicula -> eliminarPelicula(pelicula) } // Conectamos la acción
    )
}

@Composable
fun NotificacionesContent(
    isLoading: Boolean,
    errorMessage: String?,
    notificaciones: List<Pelicula>,
    onPeliculaClick: (Pelicula) -> Unit,
    onEliminarClick: (Pelicula) -> Unit // Nuevo parámetro
) {
    val colorBackground = Color(0xff18181b)
    val colorCard = Color(0xff27272a)
    val colorPrimaryPink = Color(0xfff6395f)
    val colorTextSecondary = Color(0xff71717b)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
                .offset(x = 0.dp, y = 17.dp)
        ) {
            Icon(Icons.Default.Notifications, null, tint = colorPrimaryPink, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(8.dp))

            Text("Disponibles ahora", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = colorPrimaryPink)
            }
        } else if (errorMessage != null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = Color.Red)
            }
        } else if (notificaciones.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("No hay películas disponibles", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Te avisaremos cuando se liberen copias.", color = colorTextSecondary, fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(notificaciones) { pelicula ->
                    NotificationCard(
                        pelicula = pelicula,
                        colorCard = colorCard,
                        colorAccent = colorPrimaryPink,
                        colorTextSec = colorTextSecondary,
                        onClick = { onPeliculaClick(pelicula) },
                        onEliminar = { onEliminarClick(pelicula) } // Pasamos evento
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    pelicula: Pelicula,
    colorCard: Color,
    colorAccent: Color,
    colorTextSec: Color,
    onClick: () -> Unit,
    onEliminar: () -> Unit // Nuevo parámetro
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorCard),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top // Alineamos arriba para que el icono quede bien
            ) {
                // Info Película
                Column(modifier = Modifier.weight(1f)) {
                    Text("¡Ya disponible!", color = colorAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(pelicula.titulo, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Precio: $${pelicula.precio_alquiler} | Copias: ${pelicula.copias_disponibles}", color = colorTextSec, fontSize = 14.sp)
                }

                // Botón Eliminar
                IconButton(onClick = onEliminar) {
                    Icon(
                        imageVector = Icons.Default.Delete, // Icono de basura
                        contentDescription = "Eliminar de lista",
                        tint = Color.Gray // Color gris discreto
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = colorAccent),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("RENTAR AHORA", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Preview actualizado
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewNotificacionesList() {
    val p1 = Pelicula().apply { titulo = "Matrix"; precio_alquiler = 12000.0; copias_disponibles = 2 }
    MaterialTheme {
        NotificacionesContent(
            isLoading = false,
            errorMessage = null,
            notificaciones = listOf(p1),
            onPeliculaClick = {},
            onEliminarClick = {}
        )
    }
}