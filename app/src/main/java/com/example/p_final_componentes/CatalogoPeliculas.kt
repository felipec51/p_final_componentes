package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class CatalogoPeliculas : AppCompatActivity() {

    private val URL_GET_PELICULAS = "http://192.168.2.4/androidComponentes/getPeliculas.php"

    private val peliculasState = mutableStateOf<List<Pelicula>>(emptyList())
    private val isLoadingState = mutableStateOf(true)
    private val errorState = mutableStateOf<String?>(null)
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo_peliculas)

        requestQueue = Volley.newRequestQueue(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarPeliculas()

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                CatalogoPeli(
                    peliculas = peliculasState.value,
                    isLoading = isLoadingState.value,
                    error = errorState.value,
                    onPeliculaClick = { pelicula ->
                        val intent = Intent(this@CatalogoPeliculas, PantallaUnida::class.java).apply {
                            putExtra("pelicula_id", pelicula.id_pelicula)
                        }
                        startActivity(intent)
                    },
                    onRetry = { cargarPeliculas() }
                )
            }
        }
    }

    private fun cargarPeliculas() {
        isLoadingState.value = true
        errorState.value = null

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

// COMPOSABLE REUTILIZABLE CON PARÁMETRO PARA MODO
@Composable
fun CatalogoPeli(
    peliculas: List<Pelicula>,
    isLoading: Boolean,
    error: String?,
    onPeliculaClick: (Pelicula) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    showHeader: Boolean = true,  // Nuevo parámetro
    modoUsuario: Boolean = true  // Nuevo parámetro: true = usuario, false = admin
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(16.dp)
    ) {
        Column {
            // Header (opcional)
            if (showHeader) {
                Text(
                    text = "Catálogo de Películas",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "${peliculas.size} películas encontradas",
                    color = Color(0xff99a1af),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Content
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = Color(0xffe50914))
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Cargando películas...", color = Color.White)
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
                                text = "Error al cargar películas",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = error,
                                color = Color(0xff99a1af),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                            Button(
                                onClick = onRetry,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xffe50914)
                                )
                            ) {
                                Text("Reintentar")
                            }
                        }
                    }
                }

                peliculas.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay películas disponibles",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(peliculas) { pelicula ->
                            PeliculaCard(
                                pelicula = pelicula,
                                onClick = { onPeliculaClick(pelicula) },
                                modoUsuario = modoUsuario
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PeliculaCard(
    pelicula: Pelicula,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modoUsuario: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        // Imagen de la película
        Box {
            AsyncImage(
                model = pelicula.poster_path,
                contentDescription = pelicula.titulo,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.fondo),
                error = painterResource(id = R.drawable.fondo),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Badge de admin (solo en modo admin)
            if (!modoUsuario) {
                Surface(
                    color = Color(0xffe50914).copy(alpha = 0.9f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "EDITAR",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Título
        Text(
            text = pelicula.titulo,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        // Año y calificación
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = pelicula.anio.toString(),
                color = Color(0xff99a1af),
                fontSize = 12.sp
            )
            Text(
                text = pelicula.calificacion,
                color = Color(0xffe50914),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Precio (solo en modo usuario)
        if (modoUsuario) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Alquiler: $${pelicula.precio_alquiler}",
                color = Color(0xff4ade80),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CatalogoPeliPreview() {
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

    CatalogoPeli(
        peliculas = peliculasEjemplo,
        isLoading = false,
        error = null,
        onPeliculaClick = {},
        onRetry = {},
        modoUsuario = true
    )
}