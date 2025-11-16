package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class masinformacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_masinformacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

@Composable
fun Informacio(pelicula: PeliculaDetalle? = null, modifier: Modifier = Modifier) {
    if (pelicula == null) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Cargando...", color = Color.White)
        }
        return
    }

    val generosTexto = pelicula.generos.joinToString(", ")
    val actoresTexto = pelicula.actores.joinToString(", ")
    val duracionTexto = "${pelicula.duracion_min / 60}h ${pelicula.duracion_min % 60}min"

    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 1099.dp)
    ) {
        // Sección principal
        Box(
            modifier = Modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
        ) {
            Text(
                text = pelicula.titulo,
                color = Color.White,
                lineHeight = 1.33.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 20.95.dp)
            )

            Text(
                text = pelicula.anio.toString(),
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 71.02.dp)
            )

            Text(
                text = pelicula.calificacion,
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 75.86.dp, y = 71.02.dp)
            )

            Text(
                text = generosTexto,
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 122.21.dp, y = 71.02.dp)
            )

            Text(
                text = pelicula.descripcion,
                color = Color(0xffd1d5dc),
                lineHeight = 1.63.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 107.05.dp)
                    .requiredWidth(width = 341.dp)
            )

            Text(
                text = "Elenco:",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.01.dp, y = 270.65.dp)
            )

            Text(
                text = actoresTexto,
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-23.49).dp, y = 287.65.dp)
                    .requiredWidth(width = 292.dp)
            )
        }

        // Sección "Más info"
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 342.68.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 756.dp)
        ) {
            Text(
                text = "Más info",
                color = Color.White,
                lineHeight = 1.4.em,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 3.dp)
            )

            // Géneros
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 45.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(
                    text = "Géneros",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = generosTexto,
                    color = Color(0xffd1d5dc),
                    lineHeight = 1.63.em,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            // Duración
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 175.73.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(
                    text = "Duración",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = duracionTexto,
                    color = Color(0xffd1d5dc),
                    lineHeight = 1.63.em,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            // Director
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 306.46.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(
                    text = "Director",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = pelicula.director_nombre,
                    color = Color(0xffd1d5dc),
                    lineHeight = 1.63.em,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            // Elenco completo
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 437.19.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 152.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(
                    text = "Elenco",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = actoresTexto,
                    color = Color(0xffd1d5dc),
                    lineHeight = 1.63.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.requiredWidth(width = 344.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 1099)
@Composable
private fun InformacioPreview() {
    val peliculaEjemplo = PeliculaDetalle(
        id_pelicula = 1,
        titulo = "Stranger Things",
        anio = 2025,
        duracion_min = 2400,
        descripcion = "Fuerza maligna desciende sobre un pueblo...",
        poster_path = "",
        precio_alquiler = "15000",
        calificacion = "16+",
        director_nombre = "The Duffer Brothers",
        numerocopias = 5,
        estado = "disponible",
        generos = listOf("Sci-fi", "Terror", "Drama"),
        actores = listOf("Winona Ryder", "David Harbour", "Millie Bobby Brown"),
        trailers = emptyList(),
        copias_disponibles = 3
    )
    Informacio(pelicula = peliculaEjemplo)
}