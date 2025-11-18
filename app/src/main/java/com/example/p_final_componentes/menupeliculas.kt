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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Menupeliculasrender()
            }
        }
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
                .offset(x = 9.dp,
                    y = 14.dp))
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
                .offset(
                    x = 8.dp,
                    y = 0.dp
                )
                .requiredWidth(width = 395.dp)
                .requiredHeight(height = 250.dp)
        ) {
            Text(
                text = "RewindCodeFilm",
                color = Color(0xffe50914),
                lineHeight = 1.13.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .requiredWidth(width = 198.dp)
            )
            Text(
                text = "Solo en RewindCodeFilm",
                color = Color.White,
                lineHeight = 0.75.em,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 34.dp
                    )
                    .requiredWidth(width = 395.dp)
            )
            Text(
                text = "En RewindCodeFilm encontraras titulos originales, \nincreibles que no estan en ninguna otra otra parte. \nPeliculas especiales pensados exclusicamentes para ti.",
                color = Color.White,
                lineHeight = 1.69.em,
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 6.dp,
                        y = 118.dp
                    )
                    .requiredWidth(width = 366.dp)
            )
        }
    }
}

@Composable
fun Menupeliculasrender(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MenuAndroid(Modifier)
        Catalogopeli()
    }}
@Preview(showBackground = true, showSystemUi = true,widthDp = 412, heightDp = 250)

@Composable
fun Menupeliculaprevie() {
    Menupeliculasrender()
}
