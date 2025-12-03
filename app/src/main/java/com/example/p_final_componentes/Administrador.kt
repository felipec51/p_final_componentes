package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class Administrador : ComponentActivity() {

    private fun navigateToAdminUser() {
        try {
            val intent = Intent(this@Administrador, AdminUsuarios::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("Administrador", "Error al navegar: ${e.message}", e)
        }
    }

    private fun navigateToAdminPeli() {
        try {
            val intent = Intent(this@Administrador, AdminPeliculas::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("Administrador", "Error al navegar: ${e.message}", e)
        }
    }

    private fun navigateToAddPeli() {
        try {
            val intent = Intent(this@Administrador, addpeli::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("Administrador", "Error al navegar: ${e.message}", e)
        }
    }

    private fun navigateToReportes() {
        try {
            val intent = Intent(this@Administrador, ReportesActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("Administrador", "Error al navegar: ${e.message}", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogoadmin2)
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                admincompleto(
                    onNavigateToadminuser = { navigateToAdminUser() },
                    onNavigateToadminPeli = { navigateToAdminPeli() },
                    onNavigateToAddPeli = { navigateToAddPeli() },
                    onNavigateToReportes = { navigateToReportes() }
                )
            }
        }
    }
}

@Composable
fun MainAdmin(
    modifier: Modifier = Modifier,
    onNavigateToadminuser: () -> Unit,
    onNavigateToadminPeli: () -> Unit,
    onNavigateToAddPeli: () -> Unit,
    onNavigateToReportes: () -> Unit
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 389.dp)
            .requiredHeight(height = 450.dp)
            .background(color = Color(0xff141414))
    ) {
        // Primera fila de botones
        Row(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 19.dp, y = 43.dp)
                .fillMaxWidth()
                .padding(end = 19.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Admin Películas
            Box(
                modifier = Modifier
                    .weight(1f)
                    .requiredHeight(height = 33.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xff474747))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = { onNavigateToadminPeli() }
                    )
            ) {
                Text(
                    text = "Admin Películas",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Agregar Película
            Box(
                modifier = Modifier
                    .weight(1f)
                    .requiredHeight(height = 33.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xffe50914))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = { onNavigateToAddPeli() }
                    )
            ) {
                Text(
                    text = "Agregar Película",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Admin Users
            Box(
                modifier = Modifier
                    .weight(1f)
                    .requiredHeight(height = 33.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xff474747))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = { onNavigateToadminuser() }
                    )
            ) {
                Text(
                    text = "Admin Users",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Botón de Reportes (segunda fila)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 19.dp, y = 84.dp)
                .fillMaxWidth()
                .padding(end = 19.dp)
                .requiredHeight(height = 40.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xffe50914),
                            Color(0xffff6b35)
                        )
                    )
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                    onClick = { onNavigateToReportes() }
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.filmicon),
                    contentDescription = "Reportes Icon",
                    modifier = Modifier.requiredSize(size = 20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MÓDULO DE REPORTES",
                    color = Color.White,
                    lineHeight = 1.5.em,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        // Estadísticas
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 140.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 250.dp)
        ) {
            // Primera tarjeta - Total películas
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 3.dp, y = 8.dp)
                    .requiredWidth(width = 322.dp)
                    .requiredHeight(height = 102.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color(0xff3a3a3a).copy(alpha = 0.28f))
            )
            Text(
                text = "Total películas",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 23.dp, y = 26.9.dp)
            )
            Spacer(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 23.dp, y = 27.1.dp)
                    .requiredWidth(width = 273.dp)
                    .requiredHeight(height = 74.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.filmicon),
                contentDescription = "FilmIcon 1",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 260.dp, y = 46.1.dp)
                    .requiredSize(size = 24.dp)
            )
            Text(
                text = "7",
                color = Color.White,
                lineHeight = 1.5.em,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 23.dp, y = 47.9.dp)
            )
            Text(
                text = "en el catalogo",
                color = Color(0xff6a7282),
                lineHeight = 1.33.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 23.dp, y = 77.dp)
            )

            // Segunda tarjeta - Géneros disponibles
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 3.dp, y = 126.dp)
                    .requiredWidth(width = 322.dp)
                    .requiredHeight(height = 102.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color(0xff3a3a3a).copy(alpha = 0.28f))
            )
            Text(
                text = "Generos disponibles",
                color = Color(0xff99a1af),
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 143.9.dp)
            )
            Text(
                text = "8",
                color = Color.White,
                lineHeight = 1.5.em,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 164.9.dp)
            )

            Text(
                text = "En el catalogo",
                color = Color(0xff6a7282),
                lineHeight = 1.33.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 194.dp)
            )
        }
    }
}

@Composable
fun admincompleto(
    modifier: Modifier = Modifier,
    onNavigateToadminuser: () -> Unit,
    onNavigateToadminPeli: () -> Unit,
    onNavigateToAddPeli: () -> Unit,
    onNavigateToReportes: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainAdmin(
            onNavigateToadminuser = onNavigateToadminuser,
            onNavigateToadminPeli = onNavigateToadminPeli,
            onNavigateToAddPeli = onNavigateToAddPeli,
            onNavigateToReportes = onNavigateToReportes
        )
        Catalogopeli()
    }
}

@Preview(widthDp = 390, heightDp = 950)
@Composable
private fun MainContentPreview() {
    admincompleto(
        onNavigateToadminuser = {},
        onNavigateToadminPeli = {},
        onNavigateToAddPeli = {},
        onNavigateToReportes = {}
    )
}