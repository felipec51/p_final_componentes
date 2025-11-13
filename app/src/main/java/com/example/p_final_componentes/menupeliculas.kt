package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menupeliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menupeliculas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)


        composeView.setContent {

            MaterialTheme {
                MenuAndroid()
            }
        }
    }
}

@Composable
fun MenuAndroid(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 412.dp)
            .requiredHeight(height = 917.dp)
            .background(color = Color(0xff131313))
    ) {
        InicioPeliculas(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 9.dp,
                    y = 14.dp))
    }
}

@Composable
fun InicioPeliculas(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 403.dp)
            .requiredHeight(height = 849.dp)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 8.dp,
                    y = 0.dp)
                .requiredWidth(width = 395.dp)
                .requiredHeight(height = 226.dp)
        ) {
            Text(
                text = "RewindCodeFilm",
                color = Color(0xffe50914),
                lineHeight = 1.13.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .requiredWidth(width = 198.dp))
            Text(
                text = "Solo en RewindCodeFilm",
                color = Color.White,
                lineHeight = 0.75.em,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 34.dp)
                    .requiredWidth(width = 395.dp))
            Text(
                text = "En RewindCodeFilm encontraras titulos originales, \nincreibles que no estan en ninguna otra otra parte. \nPeliculas especiales pensados exclusicamentes para ti.",
                color = Color.White,
                lineHeight = 1.69.em,
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp,
                        y = 118.dp)
                    .requiredWidth(width = 366.dp))
        }
        Text(
            text = "Destacadas semana",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 14.dp,
                    y = 248.dp)
                .requiredWidth(width = 183.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 317.dp)
                .requiredWidth(width = 384.dp)
                .requiredHeight(height = 106.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_35),
                contentDescription = "Rectangle 35",
                modifier = Modifier
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
            Image(
                painter = painterResource(id = R.drawable.rectangle_36),
                contentDescription = "Rectangle 36",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 198.dp,
                        y = 0.dp)
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
        }
        Text(
            text = "Favoritos del publico",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 8.dp,
                    y = 455.dp)
                .requiredWidth(width = 173.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 527.dp)
                .requiredWidth(width = 384.dp)
                .requiredHeight(height = 106.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_32),
                contentDescription = "Rectangle 32",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 198.dp,
                        y = 0.dp)
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
            Image(
                painter = painterResource(id = R.drawable.rectangle_33),
                contentDescription = "Rectangle 33",
                modifier = Modifier
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
        }
        Text(
            text = "Peliculas de comedia",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 8.dp,
                    y = 665.dp)
                .requiredWidth(width = 186.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 743.dp)
                .requiredWidth(width = 384.dp)
                .requiredHeight(height = 106.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_33),
                contentDescription = "Rectangle 33",
                modifier = Modifier
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
            Image(
                painter = painterResource(id = R.drawable.rectangle_34),
                contentDescription = "Rectangle 34",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 198.dp,
                        y = 0.dp)
                    .requiredWidth(width = 186.dp)
                    .requiredHeight(height = 106.dp))
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun MenuAndroidPreview() {
    MenuAndroid(Modifier)
}