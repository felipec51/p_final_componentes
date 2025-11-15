package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Login(
                    onLogin = { email, password ->
                        // Validación de admin
                        if (email == "admin@gmail.com" && password == "admin") {
                            val intent = Intent(this@login, catalogoadmin::class.java)
                            startActivity(intent)
                            finish() // Opcional: cierra el login para que no pueda volver con el botón atrás
                        }
                        // Validación de usuario normal (puedes agregar más validaciones aquí)
                        else if (email.isNotEmpty() && password.isNotEmpty()) {
                            val intent = Intent(this@login, menupeliculas::class.java)
                            startActivity(intent)
                            finish() // Opcional: cierra el login
                        }
                        // Si los campos están vacíos, no hace nada (puedes agregar un Toast aquí)
                    }
                )
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier, onLogin: (String, String) -> Unit) {
    // ESTADOS DE TEXTO
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(412.dp)
            .requiredHeight(917.dp)
            .background(Color.Black.copy(alpha = 0.54f))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight()
                .requiredWidth(467.dp)
        )

        IniciarSesionAndroid(
            correo = correo,
            contrasena = contrasena,
            onCorreoChange = { correo = it },
            onContrasenaChange = { contrasena = it },
            onLogin = onLogin,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-11).dp, y = 38.dp)
        )
    }
}

@Composable
fun IniciarSesionAndroid(
    correo: String,
    contrasena: String,
    onCorreoChange: (String) -> Unit,
    onContrasenaChange: (String) -> Unit,
    onLogin: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredWidth(436.dp)
            .requiredHeight(844.dp)
    ) {
        // FONDO NEGRO
        Box(
            modifier = Modifier
                .offset(x = 30.dp)
                .requiredWidth(395.dp)
                .requiredHeight(844.dp)
                .background(Color.Black.copy(alpha = 0.73f))
        )

        // TÍTULO
        Text(
            text = " iniciar sesion",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.offset(x = 54.dp, y = 53.dp)
        )

        // ----------- CAMPO CORREO ------------------------------------
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.offset(x = 36.dp, y = 117.dp)
        ) {
            Text(text = "Correo electrónico", color = Color.White)

            Box(
                modifier = Modifier
                    .requiredWidth(366.dp)
                    .requiredHeight(56.dp)
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            )

            TextField(
                value = correo,
                onValueChange = onCorreoChange,
                placeholder = { Text("tu@email.com") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                ),
                modifier = Modifier
                    .offset(y = (-56).dp)
                    .requiredWidth(366.dp)
            )
        }

        // ----------- CAMPO CONTRASEÑA ----------------------------
        Column(
            modifier = Modifier.offset(x = 36.dp, y = 214.dp)
        ) {
            Text(text = "Contraseña", color = Color.White)

            Box(
                modifier = Modifier
                    .requiredWidth(366.dp)
                    .requiredHeight(56.dp)
                    .offset(y = 22.dp)
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            )

            TextField(
                value = contrasena,
                onValueChange = onContrasenaChange,
                singleLine = true,
                placeholder = { Text("••••••") },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                ),
                modifier = Modifier
                    .offset(y = (-34).dp)
                    .requiredWidth(366.dp)
            )
        }

        // ----------- BOTÓN INICIAR SESIÓN ------------------------
        Box(
            modifier = Modifier
                .offset(x = 57.dp, y = 298.dp)
                .requiredWidth(318.dp)
                .requiredHeight(75.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(y = 12.dp)
                    .requiredWidth(318.dp)
                    .requiredHeight(50.dp)
                    .background(Color(0xffe50914), RoundedCornerShape(10.dp))
            )

            TextButton(
                onClick = { onLogin(correo, contrasena) },
                modifier = Modifier
                    .requiredWidth(318.dp)
                    .requiredHeight(75.dp)
            ) {
                Text(
                    "Iniciar Sesion",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .requiredWidth(width = 183.dp)
                        .requiredHeight(height = 75.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
        }

        // ¿Olvidaste contraseña?
        Text(
            text = "¿Olvidaste contraseña?",
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 15.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 124.dp, y = 393.dp)
                .requiredWidth(width = 191.dp)
                .requiredHeight(height = 23.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )

        // Recordarme
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 43.dp, y = 443.dp)
                .requiredWidth(width = 148.dp)
                .requiredHeight(height = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 18.dp)
                    .requiredHeight(height = 24.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = Color(0xffd9d9d9))
            )
            Text(
                text = "Recordarme",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.dp, y = 3.dp)
                    .requiredWidth(width = 121.dp)
                    .requiredHeight(height = 21.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }

        // ¿Primera vez en rewindCodeFilm?
        Text(
            text = "¿Primera vez en rewindCodeFilm?",
            color = Color.White,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 36.dp, y = 487.dp)
                .requiredWidth(width = 211.dp)
                .requiredHeight(height = 30.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )

        // Registrarse
        Text(
            text = "Registrarse",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 256.dp, y = 487.dp)
                .requiredWidth(width = 126.dp)
                .requiredHeight(height = 30.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )

        // Obtener ayuda
        Text(
            text = "Obtener ayuda",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp, y = 537.dp)
                .requiredWidth(width = 160.dp)
                .requiredHeight(height = 24.dp)
        )

        // Or Login with
        Column(
            verticalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp, y = 592.dp)
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
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 131.49.dp, y = 0.dp)
                        .requiredWidth(width = 95.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 9.dp)
                        .requiredWidth(width = 119.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 233.29.dp, y = 9.dp)
                        .requiredWidth(width = 118.dp)
                )
            }
        }

        // Iconos sociales
        Row(
            horizontalArrangement = Arrangement.spacedBy(39.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 147.dp, y = 663.dp)
                .requiredWidth(width = 168.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_ic),
                contentDescription = "facebook_ic",
                colorFilter = ColorFilter.tint(Color(0xff4092ff)),
                modifier = Modifier.requiredSize(size = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.google_ic),
                contentDescription = "google_ic",
                modifier = Modifier.requiredSize(size = 30.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(44.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.requiredSize(size = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.twitterx),
                    contentDescription = "TwitterX",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.requiredSize(size = 26.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun LoginPreview() {
    Login(Modifier) { _, _ -> }
}