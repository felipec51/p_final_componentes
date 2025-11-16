package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class sidebar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sidebar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)

        composeView.setContent {
            MaterialTheme {
                Component1()
            }
        }
    }
}


@Composable
fun Component1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()  // Ocupa toda la pantalla
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Sección superior (Logo y gestión)
            Column {
                // Logo y título
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 42.dp)
                            .requiredHeight(height = 35.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(color = Color(0xffe50914)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "R",
                            color = Color.White,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "RewindCodeFilm",
                            color = Color(0xffe50914),
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Text(
                            text = "Admin Panel",
                            color = Color(0xff99a1af),
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }
                }

                // Gestión
                Text(
                    text = "Gestión",
                    color = Color(0xff99a1af),
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Usuarios
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier.requiredSize(size = 16.dp)
                    )
                    Text(
                        text = "Usuarios",
                        color = Color(0xffd1d5dc),
                        style = TextStyle(fontSize = 14.sp)
                    )
                }

                // Películas
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier.requiredSize(size = 16.dp)
                    )
                    Text(
                        text = "Películas",
                        color = Color(0xffd1d5dc),
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }

            // Sección inferior (Configuración y Admin)
            Column {
                // Configuración
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier.requiredSize(size = 16.dp)
                    )
                    Text(
                        text = "Configuración",
                        color = Color(0xffd1d5dc),
                        style = TextStyle(fontSize = 14.sp)
                    )
                }

                // Admin info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = RoundedCornerShape(37596000.dp))
                            .background(color = Color(0xffe50914))
                    ) {
                        Text(
                            text = "AD",
                            color = Color.White,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Admin",
                            color = Color.White,
                            style = TextStyle(fontSize = 14.sp)
                        )
                        Text(
                            text = "admin@rewindcodefilm.com",
                            color = Color(0xff99a1af),
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Component1Preview() {
    Component1(Modifier)
}