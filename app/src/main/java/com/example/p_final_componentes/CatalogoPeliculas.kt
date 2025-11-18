package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.ripple.rememberRipple // Necesario para la corrección del error
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember // Necesario para la corrección del error
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
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
                Catalogopeli()
            }
        }
    }
}

@Composable
fun Catalogopeli(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // FUNCIÓN DE NAVEGACIÓN A LA ACTIVIDAD 'comprar' QUE ACEPTA UN ID
    val navigateToComprar: (Int) -> Unit = { movieId ->
        val intent = Intent(context, comprar::class.java)
        // **PASANDO EL ID DE LA PELÍCULA**
        intent.putExtra("MOVIE_ID", movieId)
        context.startActivity(intent)
    }

    Box(
        modifier = modifier
            .requiredWidth(width = 367.dp)
            .requiredHeight(height = 691.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.99.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 6.dp, y = 0.dp)
                .requiredWidth(width = 233.dp)
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
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = (-1.88).dp)
                )
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
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = (-1.99).dp)
                        .requiredWidth(width = 192.dp)
                )
            }
        }

        // --- Todas las imágenes son clickeables, usan el fix de clickable y pasan el ID ---

        // Imagen 1 (ID 1)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 186.dp, y = 80.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(1) } // **ID 1**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.stranger),
                contentDescription = "image_13",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 2 (ID 2)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 80.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(2) } // **ID 2**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.thecrown),
                contentDescription = "image_18",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 3 (ID 3)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 189.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(3) } // **ID 3**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_13),
                contentDescription = "image 14",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 4 (ID 4)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 186.dp, y = 189.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(4) } // **ID 4**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.mission),
                contentDescription = "image_15",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 5 (ID 5)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 299.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(5) } // **ID 5**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.bad),
                contentDescription = "vista previa 9",
                modifier = Modifier
                    .requiredWidth(width = 176.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 6 (ID 6)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 186.dp, y = 299.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(6) } // **ID 6**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.vistaprevia9),
                contentDescription = "image_16",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 7 (ID 7)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 409.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(7) } // **ID 7**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.laprimera),
                contentDescription = "vista previa 14",
                modifier = Modifier
                    .requiredWidth(width = 176.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 8 (ID 8)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 186.dp, y = 409.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(8) } // **ID 8**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.vistaprevia14),
                contentDescription = "image_17",
                modifier = Modifier
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }

        // Imagen 9 (ID 9) - Mantenido aunque solo pediste IDs del 1 al 8
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 518.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(17) }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_18),
                contentDescription = "image_18",
                modifier = Modifier
                    .requiredWidth(width = 177.dp)
                    .requiredHeight(height = 100.dp)
            )
        }

        // Imagen 10 (ID 10) - Mantenido aunque solo pediste IDs del 1 al 8
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 186.dp, y = 518.dp)
                .requiredWidth(width = 176.dp)
                .requiredHeight(height = 102.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { navigateToComprar(10) } // **ID 10**
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_19),
                contentDescription = "image_19",
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .offset(x = 0.dp, y = (-3).dp)
                    .requiredWidth(width = 175.dp)
                    .requiredHeight(height = 99.dp)
            )
        }
    }
}

@Preview(widthDp = 367, heightDp = 691)
@Composable
private fun ContainerPreview() {
    Catalogopeli()
}