package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class menupeliculas : AppCompatActivity() {

    private val URL_GET_PELICULAS = "http://192.168.2.4/androidComponentes/getPeliculas.php"

    private val peliculasState = mutableStateOf<List<Pelicula>>(emptyList())
    private val isLoadingState = mutableStateOf(true)
    private val errorState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menupeliculas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar películas
        cargarPeliculas()

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Menupeliculasrender(
                    peliculas = peliculasState.value,
                    isLoading = isLoadingState.value,
                    error = errorState.value,
                    onRetry = { cargarPeliculas() },
                    onPeliculaClick = { pelicula ->
                        // Navegar a pantalla de compra (usuario)
                        val intent = Intent(this@menupeliculas, PantallaUnida::class.java).apply {
                            putExtra("pelicula_id", pelicula.id_pelicula)
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }

    private fun cargarPeliculas() {
        isLoadingState.value = true
        errorState.value = null

        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, URL_GET_PELICULAS, null,
            { response ->
                try {
                    val success = response.getBoolean("success")
                    if (success) {
                        val peliculasArray = response.getJSONArray("peliculas")
                        val listaPeliculas = mutableListOf<Pelicula>()

                        for (i in 0 until peliculasArray.length()) {
                            val peliculaJson = peliculasArray.getJSONObject(i)
                            val pelicula = Pelicula(
                                id_pelicula = peliculaJson.getInt("id_pelicula"),
                                titulo = peliculaJson.getString("titulo"),
                                anio = peliculaJson.getInt("anio"),
                                duracion_min = peliculaJson.getInt("duracion_min"),
                                descripcion = peliculaJson.getString("descripcion"),
                                poster_path = peliculaJson.getString("poster_path"),
                                precio_alquiler = peliculaJson.getString("precio_alquiler"),
                                calificacion = peliculaJson.getString("calificacion"),
                                director_nombre = peliculaJson.getString("director_nombre")
                            )
                            listaPeliculas.add(pelicula)
                        }

                        peliculasState.value = listaPeliculas
                        isLoadingState.value = false
                    } else {
                        errorState.value = response.optString("error", "Error desconocido")
                        isLoadingState.value = false
                    }
                } catch (e: Exception) {
                    errorState.value = "Error: ${e.message}"
                    isLoadingState.value = false
                }
            },
            { error ->
                errorState.value = "Error de red: ${error.message}"
                isLoadingState.value = false
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}

@Composable
fun MenuAndroid(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 412.dp)
            .requiredHeight(height = 250.dp)
            .background(color = Color(0xff131313))
    ) {
        InicioPeliculas(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 9.dp, y = 14.dp)
        )
    }
}

@Composable
fun InicioPeliculas(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 403.dp)
            .requiredHeight(height = 250.dp)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 8.dp, y = 0.dp)
                .requiredWidth(width = 395.dp)
                .requiredHeight(height = 250.dp)
        ) {
            Text(
                text = "RewindCodeFilm",
                color = Color(0xffe50914),
                lineHeight = 1.13.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.requiredWidth(width = 198.dp)
            )
            Text(
                text = "Solo en RewindCodeFilm",
                color = Color.White,
                lineHeight = 0.75.em,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 34.dp)
                    .requiredWidth(width = 395.dp)
            )
            Text(
                text = "En RewindCodeFilm encontraras titulos originales, \nincreibles que no estan en ninguna otra otra parte. \nPeliculas especiales pensados exclusicamentes para ti.",
                color = Color.White,
                lineHeight = 1.69.em,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp, y = 118.dp)
                    .requiredWidth(width = 366.dp)
            )
        }
    }
}

@Composable
fun Menupeliculasrender(
    peliculas: List<Pelicula>,
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit,
    onPeliculaClick: (Pelicula) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MenuAndroid(Modifier)

        // Catálogo en modo usuario
        CatalogoPeli(
            peliculas = peliculas,
            isLoading = isLoading,
            error = error,
            onPeliculaClick = onPeliculaClick,
            onRetry = onRetry,
            showHeader = false,  // Sin header porque ya tiene MenuAndroid()
            modoUsuario = true   // Modo usuario (muestra precios y permite comprar)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 412, heightDp = 250)
@Composable
fun Menupeliculaprevie() {
    val peliculasEjemplo = listOf(
        Pelicula(
            id_pelicula = 1,
            titulo = "Stranger Things",
            anio = 2025,
            duracion_min = 2400,
            descripcion = "Fuerza maligna...",
            poster_path = "./imgs/fondestringer.png",
            precio_alquiler = "15000",
            calificacion = "16+",
            director_nombre = "The Duffer Brothers"
        )
    )

    Menupeliculasrender(
        peliculas = peliculasEjemplo,
        isLoading = false,
        error = null,
        onRetry = {},
        onPeliculaClick = {}
    )
}