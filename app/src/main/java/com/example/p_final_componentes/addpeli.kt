package com.example.p_final_componentes

import android.os.Bundle
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class addpeli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addpeli)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                FormAadirPelicula()
            }
        }
    }
}



@Composable
fun FormAadirPelicula(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xff141414),
        border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .shadow(elevation = 6.dp,
                shape = RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 563.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 369.dp,
                        y = 9.dp)
                    .requiredSize(size = 16.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .requiredSize(size = 16.dp))
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(11.97.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp,
                        y = 25.dp)
                    .requiredWidth(width = 344.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 18.dp)
                ) {
                    Text(
                        text = "Agregar Nueva Película",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 97.99.dp,
                                y = (-1.36).dp))
                }
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 398.dp)
                        .requiredHeight(height = 40.dp)
                ) {
                    Text(
                        text = "Complete los detalles de la película para agregar al catálogo ",
                        color = Color(0xff99a1af),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.43.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 3.dp,
                                y = (-1.97).dp)
                            .requiredWidth(width = 331.dp)
                            .requiredHeight(height = 42.dp))
                }
            }
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp,
                        y = 118.dp)
                    .requiredWidth(width = 348.dp)
                    .requiredHeight(height = 426.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.98.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 346.dp)
                        .padding(top = 15.983917236328125.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 50.dp)
                    ) {
                        Text(
                            text = "Título",
                            color = Color(0xffd1d5dc),
                            lineHeight = 1.em,
                            style = TextStyle(
                                fontSize = 14.sp))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 2.dp,
                                    y = 16.dp)
                                .requiredWidth(width = 340.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(color = Color(0xff2f2f2f)))
                    }
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 50.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 150.dp)
                                .requiredHeight(height = 50.dp)
                        ) {
                            Text(
                                text = "Año",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(
                                    fontSize = 14.sp))
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 1.dp,
                                        y = 18.dp)
                                    .requiredWidth(width = 149.dp)
                                    .requiredHeight(height = 36.dp)
                                    .clip(shape = RoundedCornerShape(6.dp))
                                    .background(color = Color(0xff2f2f2f)))
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 175.dp,
                                    y = 0.dp)
                                .requiredWidth(width = 165.dp)
                                .requiredHeight(height = 50.dp)
                        ) {
                            Text(
                                text = "Rating",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.em,
                                style = TextStyle(
                                    fontSize = 14.sp))
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 0.dp,
                                        y = 18.dp)
                                    .requiredWidth(width = 165.dp)
                                    .requiredHeight(height = 36.dp)
                                    .clip(shape = RoundedCornerShape(6.dp))
                                    .background(color = Color(0xff2f2f2f)))
                        }
                    }
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 348.dp)
                            .requiredHeight(height = 50.dp)
                    ) {
                        Text(
                            text = "Género",
                            color = Color(0xffd1d5dc),
                            lineHeight = 1.em,
                            style = TextStyle(
                                fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = (-7).dp))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = 15.dp)
                                .requiredWidth(width = 340.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(color = Color(0xff2f2f2f)))
                    }
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 348.dp)
                            .requiredHeight(height = 50.dp)
                    ) {
                        Text(
                            text = "Director",
                            color = Color(0xffd1d5dc),
                            lineHeight = 1.em,
                            style = TextStyle(
                                fontSize = 14.sp))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = (-1).dp,
                                    y = 20.dp)
                                .requiredWidth(width = 340.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(color = Color(0xff2f2f2f)))
                    }
                    Column(
                        modifier = Modifier
                            .requiredWidth(width = 348.dp)
                            .requiredHeight(height = 50.dp)
                    ) {
                        Text(
                            text = "URL del Póster",
                            color = Color(0xffd1d5dc),
                            lineHeight = 1.em,
                            style = TextStyle(
                                fontSize = 14.sp))
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 340.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(color = Color(0xff2f2f2f)))
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Bottom),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 80.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 36.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xffe50914))
                            .padding(horizontal = 16.dp,
                                vertical = 8.dp)
                    ) {
                        Text(
                            text = "Agregar Película",
                            color = Color.White,
                            lineHeight = 1.43.em,
                            style = TextStyle(
                                fontSize = 14.sp))
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 36.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xff2f2f2f))
                            .border(border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp,
                                vertical = 8.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            color = Color.White,
                            lineHeight = 1.43.em,
                            style = TextStyle(
                                fontSize = 14.sp))
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 390, heightDp = 563)
@Composable
private fun FormAadirPeliculaPreview() {
    FormAadirPelicula(Modifier)
}