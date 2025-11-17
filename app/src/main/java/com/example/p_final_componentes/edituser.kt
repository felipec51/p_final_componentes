package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class edituser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edituser)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)

        composeView.setContent {
            MaterialTheme {
                FormEditarUsuario()
            }
        }
    }
}

@Composable
fun FormEditarUsuario(modifier: Modifier = Modifier) {
    // Estados para los campos
    var id by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xff141414),
        border = BorderStroke(1.12.dp, Color(0xff2f2f2f)),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 448.dp)
                .requiredHeight(height = 646.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 414.9.dp, y = 17.1.dp)
                    .requiredSize(size = 16.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(11.97.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.11.dp, y = 25.1.dp)
                    .requiredWidth(width = 398.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 18.dp)
                ) {
                    Text(
                        text = "Editar Usuario",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 136.99.dp, y = (-1.36).dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 40.dp)
                ) {
                    Text(
                        text = "Complete los datos del usuario para actualizar",
                        color = Color(0xff99a1af),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 3.29.dp, y = (-2).dp)
                            .requiredWidth(width = 392.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.11.dp, y = 111.05.dp)
                    .requiredWidth(width = 398.dp)
                    .requiredHeight(height = 426.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.98.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 346.dp)
                        .padding(top = 15.98.dp)
                ) {
                    TextField(
                        value = id,
                        onValueChange = { id = it },
                        label = {
                            Text(
                                text = "ID",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        },
                        placeholder = { Text("ID del usuario", color = Color.Gray) },
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.88.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    ) {
                        TextField(
                            value = username,
                            onValueChange = { username = it },
                            label = {
                                Text(
                                    text = "Username",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.em,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                            },
                            placeholder = { Text("username", color = Color.Gray) },
                            textStyle = TextStyle(fontSize = 16.sp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .requiredHeight(height = 50.dp)
                        )

                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = {
                                Text(
                                    text = "Nombre",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.em,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                            },
                            placeholder = { Text("nombre", color = Color.Gray) },
                            textStyle = TextStyle(fontSize = 16.sp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .requiredHeight(height = 50.dp)
                        )
                    }

                    TextField(
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = {
                            Text(
                                text = "Contraseña",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        },
                        placeholder = { Text("contraseña", color = Color.Gray) },
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    )

                    TextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = {
                            Text(
                                text = "Dirección",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        },
                        placeholder = { Text("Dirección", color = Color.Gray) },
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    )

                    TextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = {
                            Text(
                                text = "Teléfono",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        },
                        placeholder = { Text("teléfono", color = Color.Gray) },
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(
                                text = "Email",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        },
                        placeholder = { Text("email", color = Color.Gray) },
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 50.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Bottom),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 537.dp)
                    .requiredWidth(width = 398.dp)
                    .requiredHeight(height = 80.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 36.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xffe50914))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Guardar Cambios",
                        color = Color.White,
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 36.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xff2f2f2f))
                        .border(
                            border = BorderStroke(1.12.dp, Color(0xff2f2f2f)),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color.White,
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 448, heightDp = 646)
@Composable
private fun FormEditarUsuarioPreview() {
    FormEditarUsuario(Modifier)
}