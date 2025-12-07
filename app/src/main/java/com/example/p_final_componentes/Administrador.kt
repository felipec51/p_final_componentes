package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class Administrador : ComponentActivity() {

    private fun navigateToCRUD() {
        try {
            val intent = Intent(this@Administrador, AdminCRUDActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al navegar: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("Administrador", "Error al navegar: ${e.message}", e)
        }
    }

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

        setContent {
            MaterialTheme {
                admincompleto(
                    onNavigateToadminuser = { navigateToAdminUser() },
                    onNavigateToadminPeli = { navigateToAdminPeli() },
                    onNavigateToAddPeli = { navigateToAddPeli() },
                    onNavigateToReportes = { navigateToReportes() },
                    onNavigateToCRUD = { navigateToCRUD() }
                )
            }
        }
    }
}

@Composable
fun admincompleto(
    modifier: Modifier = Modifier,
    onNavigateToadminuser: () -> Unit,
    onNavigateToadminPeli: () -> Unit,
    onNavigateToAddPeli: () -> Unit,
    onNavigateToReportes: () -> Unit,
    onNavigateToCRUD: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botones superiores en fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xff474747))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = onNavigateToadminPeli
                    )
            ) {
                Text(
                    text = "Admin Películas",
                    color = Color.White,
                    style = TextStyle(fontSize = 13.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xffe50914))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = onNavigateToAddPeli
                    )
            ) {
                Text(
                    text = "Agregar Película",
                    color = Color.White,
                    style = TextStyle(fontSize = 13.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xff474747))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                        onClick = onNavigateToadminuser
                    )
            ) {
                Text(
                    text = "Admin Users",
                    color = Color.White,
                    style = TextStyle(fontSize = 13.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Botón de Reportes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
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
                    onClick = onNavigateToReportes
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
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "MÓDULO DE REPORTES",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        // Botón de CRUD Catálogo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xff6a1b9a),
                            Color(0xff9c27b0)
                        )
                    )
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.White.copy(alpha = 0.5f)),
                    onClick = onNavigateToCRUD
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
                    contentDescription = "CRUD Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "GESTIONAR CATÁLOGO",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        // Estadísticas
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "ESTADÍSTICAS DEL SISTEMA",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            // Card de estadísticas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xff1f1f1f))
                    .padding(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatRow("Total Películas", "21", Color(0xffe50914))
                    StatRow("Total Usuarios", "5", Color(0xff00b8d4))
                    StatRow("Películas Alquiladas", "15", Color(0xffffab00))
                    StatRow("Ingresos del Mes", "$850,000", Color(0xff76ff03))
                }
            }
        }

        // Catálogo de películas
        Catalogopeli()
    }
}

@Composable
fun StatRow(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color(0xffb0b0b0),
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(widthDp = 390, heightDp = 950)
@Composable
private fun MainContentPreview() {
    admincompleto(
        onNavigateToadminuser = {},
        onNavigateToadminPeli = {},
        onNavigateToAddPeli = {},
        onNavigateToReportes = {},
        onNavigateToCRUD = {}
    )
}