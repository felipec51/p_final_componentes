package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme // ¡Importante para MaterialTheme.shapes.medium!
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class main2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {

            MaterialTheme {
                Login()
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 412.dp)
            .requiredHeight(height = 917.dp)
            .background(color = Color.Black.copy(alpha = 0.54f))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "CO-es-20251027-TRIFECTA-perspective_38b36818-20d6-48e4-be7d-320739504fc5_large 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 16.5.dp,
                    y = 0.dp)
                .fillMaxHeight()
                .requiredWidth(width = 467.dp))
        IniciarSesionAndroid(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = (-11).dp,
                    y = 38.dp))
    }
}

@Composable
fun IniciarSesionAndroid(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 436.dp)
            .requiredHeight(height = 844.dp)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 30.dp,
                    y = 0.dp)
                .requiredWidth(width = 395.dp)
                .requiredHeight(height = 844.dp)
                .background(color = Color.Black.copy(alpha = 0.73f)))
        Text(
            text = " iniciar sesion",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 54.dp,
                    y = 53.dp)
                .requiredWidth(width = 309.dp)
                .requiredHeight(height = 41.dp)
                .wrapContentHeight(align = Alignment.CenterVertically))
        Column(
            verticalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 36.dp,
                    y = 117.dp)
                .requiredWidth(width = 366.dp)
                .requiredHeight(height = 78.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 14.dp)
            ) {
                Text(
                    text = "Correo electrónico",
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = (-1.24).dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 366.dp)
                        .requiredHeight(height = 56.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White.copy(alpha = 0.1f))
                        .border(border = BorderStroke(1.1204500198364258.dp, Color.White.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(16.dp))
                        .padding(start = 48.dp,
                            end = 12.dp,
                            top = 4.dp,
                            bottom = 4.dp)
                ) {
                    Text(
                        text = "tu@email.com",
                        color = Color.White.copy(alpha = 0.4f),
                        style = TextStyle(
                            fontSize = 14.sp))
                }
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 15.98.dp,
                            y = 18.dp)
                        .requiredSize(size = 20.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 36.dp,
                    y = 214.97.dp)
                .requiredWidth(width = 366.dp)
                .requiredHeight(height = 78.dp)
        ) {
            Column(
                modifier = Modifier
                    .requiredWidth(width = 366.dp)
                    .requiredHeight(height = 78.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 14.dp)
                ) {
                    Text(
                        text = "Contraseña",
                        color = Color.White.copy(alpha = 0.9f),
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-1.24).dp))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 21.99.dp)
                    .requiredWidth(width = 366.dp)
                    .requiredHeight(height = 56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 366.dp)
                        .requiredHeight(height = 56.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White.copy(alpha = 0.1f))
                        .border(border = BorderStroke(1.1204500198364258.dp, Color.White.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(16.dp))
                        .padding(start = 48.dp,
                            end = 12.dp,
                            top = 4.dp,
                            bottom = 4.dp)
                ) {
                    Text(
                        text = "••••••••",
                        color = Color.White.copy(alpha = 0.4f),
                        style = TextStyle(
                            fontSize = 14.sp))
                }
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 15.98.dp,
                            y = 18.dp)
                        .requiredSize(size = 20.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 57.33.dp,
                    y = 298.dp)
                .requiredWidth(width = 318.dp)
                .requiredHeight(height = 75.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 12.dp)
                    .requiredWidth(width = 318.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = Color(0xffe50914)))
            Text(
                text = "Iniciar Sesion",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 68.64.dp,
                        y = 0.dp)
                    .requiredWidth(width = 183.dp)
                    .requiredHeight(height = 75.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))

        }
        Text(
            text = "¿Olvidaste contraseña?",
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 15.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 124.dp,
                    y = 393.dp)
                .requiredWidth(width = 191.dp)
                .requiredHeight(height = 23.dp)
                .wrapContentHeight(align = Alignment.CenterVertically))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 43.dp,
                    y = 443.dp)
                .requiredWidth(width = 148.dp)
                .requiredHeight(height = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 18.dp)
                    .requiredHeight(height = 24.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = Color(0xffd9d9d9)))
            Text(
                text = "Recordarme",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.dp,
                        y = 3.dp)
                    .requiredWidth(width = 121.dp)
                    .requiredHeight(height = 21.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        Text(
            text = "¿Primera vez en rewindCodeFilm?",
            color = Color.White,
            style = TextStyle(
                fontSize = 14.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 36.dp,
                    y = 487.dp)
                .requiredWidth(width = 211.dp)
                .requiredHeight(height = 30.dp)
                .wrapContentHeight(align = Alignment.CenterVertically))
        Text(
            text = "Registrarse",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 256.dp,
                    y = 487.dp)
                .requiredWidth(width = 126.dp)
                .requiredHeight(height = 30.dp)
                .wrapContentHeight(align = Alignment.CenterVertically))
        Text(
            text = "Obtener ayuda",
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp,
                    y = 537.dp)
                .requiredWidth(width = 160.dp)
                .requiredHeight(height = 24.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp,
                    y = 592.dp)
                .requiredWidth(width = 351.dp)
                .requiredHeight(height = 17.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 351.dp)
                    .requiredHeight(height = 17.dp)
            ) {
                Text(
                    text = "Or Login with",
                    color = Color.Gray,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 131.49.dp,
                            y = 0.dp)
                        .requiredWidth(width = 95.dp))
                Divider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = 9.dp)
                        .requiredWidth(width = 119.dp))
                Divider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 233.29.dp,
                            y = 9.dp)
                        .requiredWidth(width = 118.dp))
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(39.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 147.dp,
                    y = 663.dp)
                .requiredWidth(width = 168.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_ic),
                contentDescription = "facebook_ic",
                colorFilter = ColorFilter.tint(Color(0xff4092ff)),
                modifier = Modifier
                    .requiredSize(size = 30.dp))
            Image(
                painter = painterResource(id = R.drawable.google_ic),
                contentDescription = "google_ic",
                modifier = Modifier
                    .requiredSize(size = 30.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(44.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredSize(size = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.twitterx),
                    contentDescription = "TwitterX",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .requiredSize(size = 26.dp))
            }
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun LoginPreview() {
    Login(Modifier)
}