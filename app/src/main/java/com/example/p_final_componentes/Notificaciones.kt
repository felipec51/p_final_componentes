package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

    private val BASE_URL = "http://192.168.20.35/androidComponentes"
    private val URL_NOTIFICACIONES = "$BASE_URL/obtener_notificaciones.php"

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
                    url = URL_NOTIFICACIONES,
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
    url: String,
    onPeliculaClick: (Pelicula) -> Unit,
    onVolver: () -> Unit
) {
    val context = LocalContext.current
    var notificaciones by remember { mutableStateOf<List<Pelicula>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                isLoading = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.optBoolean("success")

                    if (success) {
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
        onPeliculaClick = onPeliculaClick
    )
}

// 2. COMPONENTE VISUAL (Sin lógica de Volley, listo para Preview)
@Composable
fun NotificacionesContent(
    isLoading: Boolean,
    errorMessage: String?,
    notificaciones: List<Pelicula>,
    onPeliculaClick: (Pelicula) -> Unit
) {
    val colorBackground = Color(0xff18181b)
    val colorCard = Color(0xff27272a)
    val colorPrimaryPink = Color(0xfff6395f)
    val colorTextSecondary = Color(0xff71717b)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(24.dp)
    ) {
        // --- Encabezado ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = colorPrimaryPink,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Disponibles ahora",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // --- Contenido Dinámico ---
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = colorPrimaryPink)
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = Color.Red)
            }
        } else if (notificaciones.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "No hay películas disponibles",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Te avisaremos cuando se liberen copias.",
                        color = colorTextSecondary,
                        fontSize = 14.sp
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(notificaciones) { pelicula ->
                    NotificationCard(
                        pelicula = pelicula,
                        colorCard = colorCard,
                        colorAccent = colorPrimaryPink,
                        colorTextSec = colorTextSecondary,
                        onClick = { onPeliculaClick(pelicula) }
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
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorCard),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "¡Ya disponible!",
                        color = colorAccent,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = pelicula.titulo,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Precio: $${pelicula.precio_alquiler} | Copias libres: ${pelicula.copias_disponibles}",
                        color = colorTextSec,
                        fontSize = 14.sp
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

// 3. LA PREVIEW (Datos falsos para ver el diseño)
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewNotificacionesList() {
    // Creamos datos falsos simulando la respuesta de la BD
    val p1 = Pelicula()
    p1.titulo = "Misión Imposible: Sentencia Mortal"
    p1.precio_alquiler = 13000.0
    p1.copias_disponibles = 2

    val p2 = Pelicula()
    p2.titulo = "Oppenheimer"
    p2.precio_alquiler = 15000.0
    p2.copias_disponibles = 5

    val listaFalsa = listOf(p1, p2)

    MaterialTheme {
        NotificacionesContent(
            isLoading = false,
            errorMessage = null,
            notificaciones = listaFalsa,
            onPeliculaClick = {}
        )
    }
}