package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)


        composeView.setContent {

            MaterialTheme {
                AndroidCompact2()
            }
        }
    }
}


@Composable
fun AndroidCompact2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 412.dp)
            .requiredHeight(height = 917.dp)
            .background(color = Color.Black)
    ) {
        Form(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 16.dp,
                    y = 43.dp))
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 380.dp)
            .requiredHeight(height = 756.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "CO-es-20251027-TRIFECTA-perspective_38b36818-20d6-48e4-be7d-320739504fc5_large 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-2.99).dp,
                    y = 0.dp)
                .fillMaxHeight()
                .requiredWidth(width = 412.dp))
        Box(
            modifier = Modifier
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Dirección",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Ingresa tu dirección",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 103.97.dp)
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Código",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Código de socio",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 207.93.dp)
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Nombre",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Tu nombre",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 311.9.dp)
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Apellido",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Tu apellido",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 415.86.dp)
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Email",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "ejemplo@email.com",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 519.83.dp)
                .requiredWidth(width = 380.dp)
                .requiredHeight(height = 84.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = 0.dp)
                    .requiredWidth(width = 372.dp)
                    .requiredHeight(height = 20.dp)
            ) {
                Text(
                    text = "Contraseña",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 27.98.dp)
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White.copy(alpha = 0.27f))
                    .border(border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
                        shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Mínimo 6 caracteres",
                    color = Color.White.copy(alpha = 0.55f),
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.Gray ,
            border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.27f)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 647.78.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .shadow(elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 380.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White,
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 154.88.dp,
                            y = 16.dp))
            }
        }
        Row(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 144.dp,
                    y = 735.12.dp)
                .requiredWidth(width = 92.dp)
                .requiredHeight(height = 20.dp)
        ) {
            Text(
                text = "Obtener ayuda",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp))
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun AndroidCompact2Preview() {
    AndroidCompact2(Modifier)
}
