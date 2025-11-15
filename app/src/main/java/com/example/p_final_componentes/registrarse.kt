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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

    // ESTADOS DE LOS CAMPOS
    var direccion by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var actor by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(390.dp)
            .requiredHeight(844.dp)
    ) {

        // Fondo oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.73f))
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 46.dp, bottom = 709.dp)
        ) {
            Text(
                text = "Dirección",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.offset(x = 8.dp)
            )

            // Caja gris
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )
            TextField(
                value = direccion,
                onValueChange = { direccion = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Direccion",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }

// --------------------------
//          NOMBRE
// --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 149.dp, bottom = 605.dp)
        ) {

            Text(
                "Nombre",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.offset(8.dp)
            )

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = nombre,
                onValueChange = { nombre = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Nombre",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }

        // --------------------------
        //     ACTOR FAVORITO
        // --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 253.dp, bottom = 502.dp)
        ) {

            Text("Actor favorito", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = actor,
                onValueChange = { actor = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Actor favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }


        // --------------------------
        //    DIRECTOR FAVORITO
        // --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 357.dp, bottom = 398.dp)
        ) {

            Text("Director favorito", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 35.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = director,
                onValueChange = { director = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Director favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }


        // --------------------------
        //       CONTRASEÑA
        // --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 461.dp, bottom = 294.dp)
        ) {

            Text("Contraseña", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Ingresa tu Contraseña",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }


        // --------------------------
        //          EMAIL
        // --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 565.dp, bottom = 190.dp)
        ) {

            Text("Email", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = email,
                onValueChange = { email = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Email",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }


        // --------------------------
        //        GÉNERO
        // --------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 7.dp, top = 669.dp, bottom = 86.dp)
        ) {

            Text("Género", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(8.dp))

            Box(
                modifier = Modifier
                    .offset(x = 2.38.dp, y = 34.dp)
                    .requiredWidth(353.dp)
                    .requiredHeight(43.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff5b5b5b).copy(alpha = 0.66f))
            )

            TextField(
                value = genero,
                onValueChange = { genero = it },

                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),          // placeholder ocupa todo el espacio del campo
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "Genero favorito",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 18.sp         // placeholder más grande
                        )
                    }
                },

                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp                 // texto al escribir también más grande
                ),

                singleLine = true,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),

                modifier = Modifier
                    .offset(x = 16.dp, y = 30.dp)
                    .requiredWidth(330.dp)
                    .requiredHeight(55.dp)           // altura del campo
            )
        }

        // --------------------------
        //         BOTÓN
        // --------------------------
        Box(
            modifier = Modifier
                .offset(x = 6.63.dp, y = 776.05.dp)
                .requiredWidth(358.dp)
                .requiredHeight(45.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xffe50914))
                .clickable {
                    // ACCIÓN DEL BOTÓN
                    println("REGISTRAR: $nombre, $email, $direccion")
                }
        ) {
            Text(
                text = "Registrar",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}
@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun FormPreview() {
    Form(Modifier)
}