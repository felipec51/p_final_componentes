package com.example.p_final_componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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


@Composable
fun MasInformacionDetalle(pelicula: Pelicula, modifier: Modifier = Modifier) {
    // Ajustamos la altura del contenedor principal para que se ajuste al contenido completo
    // Si este componente está dentro de un Column scrollable (como en comprar.kt), no necesita altura fija
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // --- Bloque Superior (Sinopsis, Elenco, Director) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Altura mínima ajustada para acomodar Director
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                .padding(24.dp)
        ) {
            // Título Dinámico
            Text(
                text = pelicula.getTitulo() ?: "Título no disponible",
                color = Color.White,
                lineHeight = 1.33.em,
                style = MaterialTheme.typography.headlineSmall,
            )
            // Información corta: Año, Clasificación, Género Principal
            Box(modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)) {
                val shortInfo = buildString {
                    append(pelicula.getAnio_lanzamiento().toString())
                    append(" | ")
                    append(pelicula.getClasificacion() ?: "N/A")
                    append(" | ")
                    append(pelicula.getGeneros_detalle()?.split(",")?.getOrNull(0)?.trim() ?: "Género")
                }
                Text(
                    text = shortInfo,
                    color = Color(0xffd1d5dc),
                    lineHeight = 1.43.em,
                    style = TextStyle(fontSize = 14.sp),
                )
            }

            // Descripción/Sinopsis Dinámica
            Text(
                text = pelicula.getDescripcion() ?: "Sinopsis no disponible.",
                color = Color(0xffd1d5dc),
                lineHeight = 1.63.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 15.dp)
            )

            // Etiqueta Elenco
            Text(
                text = "Elenco:",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
            )
            // Elenco Dinámico
            Text(
                text = pelicula.getElenco() ?: "Elenco no disponible.",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 15.dp)
            )

            // 🚨 Etiqueta Director 🚨
            Text(
                text = "Director:",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
            )
            // 🚨 Director Dinámico 🚨
            Text(
                // Asume que la clase Pelicula ahora tiene getDirector_nombre()
                text = pelicula.getDirector_nombre() ?: "Director no disponible.",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp)
            )
        }

        // --- Bloque Inferior (Más info: Géneros e Idioma) ---
        Text(
            text = "Más info",
            color = Color.White,
            lineHeight = 1.4.em,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
        )

        // Géneros Detalle - Dinámico
        InfoBlock(
            title = "Géneros",
            detail = pelicula.getGeneros_detalle() ?: "Géneros no disponibles"
        )

        // Idioma - Dinámico
        InfoBlock(
            title = "Idioma",
            // Se usa el campo idioma original de la tabla pelicula
            detail = pelicula.getIdioma() ?: "N/A",
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}

// Composable Reutilizable para los bloques de "Más info"
@Composable
fun InfoBlock(title: String, detail: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
            .padding(24.dp)
    ) {
        // Título del bloque (e.g., Géneros)
        Text(
            text = title,
            color = Color.White,
            lineHeight = 1.5.em,
            style = TextStyle(fontSize = 16.sp)
        )
        // Detalle dinámico
        Text(
            text = detail,
            color = Color(0xffd1d5dc),
            lineHeight = 1.63.em,
            style = TextStyle(fontSize = 14.sp)
        )
    }
}


@Preview(widthDp = 393, heightDp = 900)
@Composable
private fun MasInformacionDetallePreview() {
    val mockPelicula = Pelicula().apply {
        setTitulo("Stranger Things")
        setDescripcion("Fuerza maligna desciende sobre un pequeño pueblo de Indiana en los 80. Un grupo de niños intenta resolver el misterio de la desaparición de su amigo.")
        setAnio_lanzamiento(2025)
        setClasificacion("16+")
        setIdioma("Inglés")
        setElenco("Winona Ryder, David Harbour, Millie Bobby Brown, Finn Wolfhard")
        setGeneros_detalle("Sci-fi, Terror, Drama")
        setDirector_nombre("The Duffer Brothers") // 🚨 Director mock
    }
    MasInformacionDetalle(pelicula = mockPelicula)
}