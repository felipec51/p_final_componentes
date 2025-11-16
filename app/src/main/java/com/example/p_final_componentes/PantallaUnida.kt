package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class PantallaUnida : AppCompatActivity() {
    // IMPORTANTE: Cambia esta IP según tu configuración
    private val URL_GET_DETALLES = "http://192.168.2.4/androidComponentes/getDetallesPelicula.php"

    private val peliculaState = mutableStateOf<PeliculaDetalle?>(null)
    private val isLoadingState = mutableStateOf(true)
    private val errorState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_unida)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener el ID de la película desde el Intent
        val peliculaId = intent.getIntExtra("pelicula_id", -1)

        if (peliculaId != -1) {
            cargarDetallesPelicula(peliculaId)
        } else {
            errorState.value = "Error: No se recibió ID de película"
            isLoadingState.value = false
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                PantallaCompleta(
                    pelicula = peliculaState.value,
                    isLoading = isLoadingState.value,
                    error = errorState.value,
                    onRetry = { if (peliculaId != -1) cargarDetallesPelicula(peliculaId) }
                )
            }
        }
    }

    private fun cargarDetallesPelicula(id: Int) {
        isLoadingState.value = true
        errorState.value = null

        val url = "$URL_GET_DETALLES?id=$id"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val success = response.getBoolean("success")
                    if (success) {
                        val peliculaJson = response.getJSONObject("pelicula")
                        val actoresArray = response.getJSONArray("actores")
                        val generosArray = response.getJSONArray("generos")
                        val trailersArray = response.getJSONArray("trailers")

                        // Convertir arrays JSON a listas
                        val actores = mutableListOf<String>()
                        for (i in 0 until actoresArray.length()) {
                            actores.add(actoresArray.getString(i))
                        }

                        val generos = mutableListOf<String>()
                        for (i in 0 until generosArray.length()) {
                            generos.add(generosArray.getString(i))
                        }

                        val trailers = mutableListOf<String>()
                        for (i in 0 until trailersArray.length()) {
                            trailers.add(trailersArray.getString(i))
                        }

                        val pelicula = PeliculaDetalle(
                            id_pelicula = peliculaJson.getInt("id_pelicula"),
                            titulo = peliculaJson.getString("titulo"),
                            anio = peliculaJson.getInt("anio"),
                            duracion_min = peliculaJson.getInt("duracion_min"),
                            descripcion = peliculaJson.getString("descripcion"),
                            poster_path = peliculaJson.getString("poster_path"),
                            precio_alquiler = peliculaJson.getString("precio_alquiler"),
                            calificacion = peliculaJson.getString("calificacion"),
                            director_nombre = peliculaJson.getString("director_nombre"),
                            numerocopias = peliculaJson.getInt("numerocopias"),
                            estado = peliculaJson.getString("estado"),
                            actores = actores,
                            generos = generos,
                            trailers = trailers,
                            copias_disponibles = response.getInt("copias_disponibles")
                        )

                        peliculaState.value = pelicula
                        isLoadingState.value = false
                    } else {
                        val error = response.optString("error", "Error desconocido")
                        errorState.value = error
                        isLoadingState.value = false
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    errorState.value = "Error al procesar datos: ${e.message}"
                    isLoadingState.value = false
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            },
            { error ->
                val errorMsg = "Error de red: ${error.message ?: "Verifique la conexión"}"
                errorState.value = errorMsg
                isLoadingState.value = false
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}

@Composable
fun PantallaCompleta(
    pelicula: PeliculaDetalle?,
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = Color(0xffe50914))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Cargando detalles...", color = Color.White)
                    }
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "⚠️",
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = error,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            pelicula != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Pasar los datos a tus composables
                    Compra(pelicula = pelicula)
                    Informacio(pelicula = pelicula)
                    Trailer(trailers = pelicula.trailers)
                }
            }
        }
    }
}