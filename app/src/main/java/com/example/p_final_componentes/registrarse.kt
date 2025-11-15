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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
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
                Form()
            }
        }
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 844.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.73f)))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 46.15771484375.dp,
                    bottom = 709.7569961547852.dp)
        ) {
            Text(
                text = "Dirección",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 34.84.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Ingresa tu dirección",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 149.97802734375.dp,
                    bottom = 605.9366836547852.dp)
        ) {
            Text(
                text = "Nombre",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 35.02.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Tu nombre",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 253.798828125.dp,
                    bottom = 502.11588287353516.dp)
        ) {
            Text(
                text = "Actor favorito ",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp)
                    .requiredWidth(width = 99.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 35.2.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Actor favorito",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 357.61962890625.dp,
                    bottom = 398.29508209228516.dp)
        ) {
            Text(
                text = "Director favorito ",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp)
                    .requiredWidth(width = 99.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 35.38.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Director favorito",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 461.43994140625.dp,
                    bottom = 294.47476959228516.dp)
        ) {
            Text(
                text = "Contraseña",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 34.56.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Ingresa tu Contraseña",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 565.2607421875.dp,
                    bottom = 190.65396881103516.dp)
        ) {
            Text(
                text = "Email",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 34.74.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Ingresa tu email",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.627848148345947.dp,
                    end = 7.574697017669678.dp,
                    top = 669.0810546875.dp,
                    bottom = 86.83365631103516.dp)
        ) {
            Text(
                text = "Genero",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 8.dp,
                        y = (-0.01).dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.38.dp,
                        y = 34.92.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 43.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff5b5b5b).copy(alpha = 0.66f)))
            Text(
                text = "Genero favorito",
                color = Color.White.copy(alpha = 0.55f),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 46.97.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 6.63.dp,
                    y = 776.05.dp)
                .requiredWidth(width = 358.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = Color(0xffe50914)))
            Text(
                text = "Registrar ",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun FormPreview() {
    Form(Modifier)
}