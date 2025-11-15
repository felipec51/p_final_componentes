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
fun Informacio(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 1099.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
        ) {
            Text(
                text = "Happy Gilmore 2",
                color = Color.White,
                lineHeight = 1.33.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp,
                        y = 20.95.dp))
            Text(
                text = "2025",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp,
                        y = 71.02.dp))
            Text(
                text = "13+",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 75.86.dp,
                        y = 71.02.dp))
            Text(
                text = "Comedia, Deportes",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 122.21.dp,
                        y = 71.02.dp))
            Text(
                text = "El juego no ha terminado para Happy Gilmore. El legendario y explosivo golfista interpretado por Adam Sandler vuelve al green para cumplir el deseo de su hija. Con su característico estilo poco convencional y su temperamento único, Happy deberá enfrentarse a una nueva generación de jugadores y demostrar que aún tiene lo necesario para competir al más alto nivel.",
                color = Color(0xffd1d5dc),
                lineHeight = 1.63.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp,
                        y = 107.05.dp)
                    .requiredWidth(width = 341.dp))
            Text(
                text = "Elenco:",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.01.dp,
                        y = 270.65.dp))
            Text(
                text = "Adam Sandler, Julie Bowen, Christopher McDonald, Ben Stiller, Travis Kelce",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-23.49).dp,
                        y = 287.65.dp)
                    .requiredWidth(width = 292.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 342.68.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 756.dp)
        ) {
            Text(
                text = "Más info",
                color = Color.White,
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 3.dp)
                    .requiredWidth(width = 393.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-5).dp,
                        y = 45.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 23.995532989501953.dp,
                        end = 23.995555877685547.dp,
                        top = 23.9954833984375.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 24.dp)
                ) {
                    Text(
                        text = "Géneros",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.95).dp))
                    Text(
                        text = "Películas para reír y Deportes",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.63.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 35.09.dp))
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-5).dp,
                        y = 175.73.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 129.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 23.99953.dp,
                        end = 23.9947.dp,
                        top = 23.975.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 24.dp)
                ) {
                    Text(
                        text = "Esta película es...",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.95).dp))
                    Text(
                        text = "Comedia física, Irreverente, Deportes, Golf, Contra todo obstáculo, Película",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.63.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 35.09.dp)
                            .requiredWidth(width = 341.dp))
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-5).dp,
                        y = 329.2.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 23.993.dp,
                        end = 23.997.dp,
                        top = 23.975.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 24.dp)
                ) {
                    Text(
                        text = "Audio",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.95).dp))
                    Text(
                        text = "Inglés - Audio descriptivo, Inglés [Original], Español",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.63.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 35.09.dp))
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-5).dp,
                        y = 459.93.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 107.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 23.995532989501953.dp,
                        end = 23.9.dp,
                        top = 23.9.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 24.dp)
                ) {
                    Text(
                        text = "Subtítulos",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.95).dp))
                    Text(
                        text = "Inglés y Español",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.63.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 35.09.dp))
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-5).dp,
                        y = 590.66.dp)
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 152.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    .padding(start = 23.995532989501953.dp,
                        end = 23.995555877685547.dp,
                        top = 23.9954833984375.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 24.dp)
                ) {
                    Text(
                        text = "Elenco",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.95).dp))
                    Text(
                        text = "Adam Sandler, Christopher McDonald, Julie Bowen, Frances Bay, Carl Weathers, Allen Covert, Robert Smigel, Bob Barker, Richard Kiel, Dennis Dugan",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.63.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 35.99.dp)
                            .requiredWidth(width = 344.dp))
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 1099)
@Composable
private fun InformacioPreview() {
    Informacio(Modifier)
}