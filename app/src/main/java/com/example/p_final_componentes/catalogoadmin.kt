package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class catalogoadmin : AppCompatActivity() {

    private val URL_GET_PELICULAS = "http://192.168.2.4/androidComponentes/getPeliculas.php"

    private val peliculasState = mutableStateOf<List<Pelicula>>(emptyList())
    private val isLoadingState = mutableStateOf(true)
    private val errorState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogoadmin2)
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
                MainScreenWithSidebar(
                    peliculas = peliculasState.value,
                    isLoading = isLoadingState.value,
                    error = errorState.value,
                    onRetry = { cargarPeliculas() },
                    onPeliculaClick = { pelicula ->
                        // Navegar a editar película
                        val intent = Intent(this@catalogoadmin, editpeli::class.java).apply {
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
fun MainScreenWithSidebar(
    peliculas: List<Pelicula>,
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit,
    onPeliculaClick: (Pelicula) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSidebar by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        // Contenido principal
        AppPreview(
            peliculas = peliculas,
            isLoading = isLoading,
            error = error,
            onRetry = onRetry,
            onPeliculaClick = onPeliculaClick,
            onMenuClick = { showSidebar = !showSidebar }
        )

        // Sidebar con animación y fondo oscuro
        AnimatedVisibility(
            visible = showSidebar,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Fondo oscuro semi-transparente
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { showSidebar = false }
                )

                // Sidebar que ocupa ancho limitado
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(280.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Component1()
                }
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 389.dp)
            .requiredHeight(height = 365.dp)
            .background(color = Color(0xff141414))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(width = 370.dp)
                .requiredHeight(height = 91.dp)
                .padding(start = 24.dp, end = 9.0.dp)
        ) {
            // ICONO CON CLICKABLE
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 16.dp)
                    .requiredHeight(height = 28.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable { onMenuClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_3),
                    contentDescription = "Menu Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .requiredSize(size = 0.dp)
                    .background(color = Color(0xff2f2f2f)))
            Box(
                modifier = Modifier
                    .requiredWidth(width = 211.dp)
                    .requiredHeight(height = 42.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 104.63.dp, y = 0.dp)
                        .requiredWidth(width = 106.dp)
                        .requiredHeight(height = 42.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(width = 106.dp)
                            .requiredHeight(height = 42.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color(0xff2f2f2f))
                            .border(border = BorderStroke(0.9014080166816711.dp, Color(0xff2f2f2f)),
                                shape = RoundedCornerShape(10.dp))
                            .padding(start = 36.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = "Buscar",
                            color = Color(0xff6a7282).copy(alpha = 0.5f),
                            style = TextStyle(fontSize = 16.sp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.icon_1n),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 12.dp, y = 12.9.dp)
                            .requiredSize(size = 16.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 1.49.dp)
                        .requiredWidth(width = 89.dp)
                        .requiredHeight(height = 39.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xff2f2f2f))
                        .border(border = BorderStroke(0.9014080166816711.dp, Color(0xff2f2f2f)),
                            shape = RoundedCornerShape(10.dp))
                        .padding(start = 11.99999713897705.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 39.dp)
                            .requiredHeight(height = 21.dp)
                    ) {
                        Text(
                            text = "Todos",
                            color = Color.White,
                            lineHeight = 1.5.em,
                            style = TextStyle(fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = (-0.2).dp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.icon_1ad),
                        contentDescription = "Icon",
                        alpha = 0.5f,
                        modifier = Modifier.requiredSize(size = 16.dp))
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 92.dp)
                    .requiredHeight(height = 42.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xffe50914))
                    .padding(start = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconad),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .requiredWidth(width = 9.dp)
                        .requiredHeight(height = 16.dp))
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 66.dp)
                        .requiredHeight(height = 42.dp)
                ) {
                    Text(
                        text = "Agregar Película",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp, y = (-0.2).dp)
                            .requiredWidth(width = 51.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 115.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 250.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xff141414),
                border = BorderStroke(0.9014080166816711.dp, Color(0xff2f2f2f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 11.1.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 322.dp)
                        .requiredHeight(height = 104.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 25.dp, y = 18.dp)
                            .requiredWidth(width = 273.dp)
                            .requiredHeight(height = 74.dp)
                    ) {
                        Text(
                            text = "Total peliculas",
                            color = Color(0xff99a1af),
                            lineHeight = 1.43.em,
                            style = TextStyle(fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = (-0.2).dp))
                        Text(
                            text = "7",
                            color = Color.White,
                            lineHeight = 1.5.em,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = 20.8.dp))
                        Text(
                            text = "en el catalogo",
                            color = Color(0xff6a7282),
                            lineHeight = 1.33.em,
                            style = TextStyle(fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = 49.9.dp))
                    }
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 249.58.dp, y = 24.9.dp)
                            .requiredSize(size = 48.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.filmicon),
                                contentDescription = "FilmIcon 1",
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 0.42.dp, y = 0.1.dp)
                                    .requiredSize(size = 24.dp))
                        }
                    }
                }
            }
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xff141414),
                border = BorderStroke(0.9014080166816711.dp, Color(0xff2f2f2f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 129.1.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 322.dp)
                        .requiredHeight(height = 104.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 25.dp, y = 18.dp)
                            .requiredWidth(width = 273.dp)
                            .requiredHeight(height = 74.dp)
                    ) {
                        Text(
                            text = "Rating Promedio",
                            color = Color(0xff99a1af),
                            lineHeight = 1.43.em,
                            style = TextStyle(fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = (-0.2).dp))
                        Text(
                            text = "8.3",
                            color = Color.White,
                            lineHeight = 1.5.em,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = 20.8.dp))
                        Text(
                            text = "De todas las películas",
                            color = Color(0xff6a7282),
                            lineHeight = 1.33.em,
                            style = TextStyle(fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp, y = 49.9.dp))
                    }
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 249.58.dp, y = 24.9.dp)
                            .requiredSize(size = 48.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.staricon),
                            contentDescription = "StarIcon",
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AppPreview(
    peliculas: List<Pelicula>,
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit,
    onPeliculaClick: (Pelicula) -> Unit,
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        App(Modifier, onMenuClick)

        // Catálogo en modo admin
        CatalogoPeli(
            peliculas = peliculas,
            isLoading = isLoading,
            error = error,
            onPeliculaClick = onPeliculaClick,
            onRetry = onRetry,
            showHeader = false,  // Sin header porque ya tiene el App()
            modoUsuario = false  // Modo admin
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 412, heightDp = 250)
@Composable
fun Admipeliculaprevie() {
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

    MainScreenWithSidebar(
        peliculas = peliculasEjemplo,
        isLoading = false,
        error = null,
        onRetry = {},
        onPeliculaClick = {}
    )
}