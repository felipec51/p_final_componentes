package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
class CatalogoPeliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo_peliculas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {

            MaterialTheme {
                catalogopeli()
            }
        }
    }
}

@Composable
fun catalogopeli(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 420.dp)
            .requiredHeight(height = 691.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.99.dp, Alignment.Top),
            modifier = Modifier
                .requiredWidth(width = 150.dp)
                .requiredHeight(height = 48.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 24.dp)
            ) {
                Text(
                    text = "Catálogo de Películas",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-1.88).dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "7 películas encontradas",
                    color = Color(0xff99a1af),
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-2).dp)
                        .requiredWidth(width = 145.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-0.5).dp,
                    y = 71.04.dp)
                .requiredWidth(width = 425.dp)
                .requiredHeight(height = 619.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 9.98.dp,
                        y = 14.96.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_18),
                    contentDescription = "image 18",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-4).dp)
                        .requiredWidth(width = 194.dp)
                        .requiredHeight(height = 110.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 218.dp,
                        y = 15.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_13),
                    contentDescription = "image 13",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 115.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 10.dp,
                        y = 129.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_14),
                    contentDescription = "image 14",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-3).dp)
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(height = 113.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 218.dp,
                        y = 129.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_15),
                    contentDescription = "image 15",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-4).dp)
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(height = 113.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 0.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 10.dp,
                        y = 243.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 107.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vistaprevia9),
                    contentDescription = "vista previa 9",
                    modifier = Modifier
                        .fillMaxSize())
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 218.dp,
                        y = 243.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 107.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_16),
                    contentDescription = "image 16",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = (-2).dp,
                            y = (-4).dp)
                        .requiredWidth(width = 203.dp)
                        .requiredHeight(height = 115.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 10.dp,
                        y = 358.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vistaprevia14),
                    contentDescription = "vista previa 14",
                    modifier = Modifier
                        .fillMaxSize())
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 218.dp,
                        y = 358.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_17),
                    contentDescription = "image 17",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-4).dp)
                        .fillMaxWidth()
                        .requiredHeight(height = 113.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 10.dp,
                        y = 472.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_18),
                    contentDescription = "image 18",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-4).dp)
                        .requiredWidth(width = 194.dp)
                        .requiredHeight(height = 110.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 218.dp,
                        y = 472.dp)
                    .requiredWidth(width = 200.dp)
                    .requiredHeight(height = 106.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_19),
                    contentDescription = "image 19",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-3).dp)
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(height = 113.dp))
            }
        }
    }
}

@Preview(widthDp = 420, heightDp = 691)
@Composable
private fun ContainerPreview() {
    catalogopeli(Modifier)
}