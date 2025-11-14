package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.ContentScale


class comprar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comprar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {

            MaterialTheme {
                Compra()
            }
        }
    }
}

@Composable
fun Compra(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 803.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 803.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff1a1a1a))
        ) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 46.73.dp,
                        y = 43.dp)
                    .requiredWidth(width = 300.dp)
                    .requiredHeight(height = 450.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .shadow(elevation = 50.dp,
                        shape = RoundedCornerShape(10.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 450.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imagewithfallback),
                        contentDescription = "image 20",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(568.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.01.dp,
                        y = 498.01.dp)
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 255.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 255.dp)
                        .padding(top = 15.95.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(7.99.dp, Alignment.Top),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 67.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 36.dp)
                        ) {
                            Text(
                                text = "Happy Gilmore 2",
                                color = Color.White,
                                lineHeight = 1.2.em,
                                style = TextStyle(
                                    fontSize = 30.sp),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 0.dp,
                                        y = (-3.1).dp))
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 23.dp)
                        ) {
                            Text(
                                text = "2025",
                                color = Color(0xff99a1af),
                                lineHeight = 1.43.em,
                                style = TextStyle(
                                    fontSize = 14.sp),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 13.dp,
                                        y = (-1.26).dp))
                            Text(
                                text = "1h 53min",
                                color = Color(0xff99a1af),
                                lineHeight = 1.43.em,
                                style = TextStyle(
                                    fontSize = 14.sp),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 117.dp,
                                        y = 0.dp))
                            Text(
                                text = "13+",
                                color = Color.White,
                                lineHeight = 1.33.em,
                                style = TextStyle(
                                    fontSize = 13.sp),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 77.15.dp,
                                        y = 0.dp))
                        }
                    }
                    Snackbar(
                        containerColor = Color(0xffe7000b),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Comprar película",
                            color = Color.White,
                            lineHeight = 1.56.em,
                            style = TextStyle(
                                fontSize = 18.sp),
                            modifier = Modifier
                                .fillMaxWidth())
                    }
                }
                NumeroDeCopias(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 1.dp,
                            y = 108.02.dp))
                Text(
                    text = "Disponible para compra",
                    color = Color(0xff99a1af),
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = 180.dp))
            }
        }
        Text(
            text = "RewindCodeFilm",
            color = Color(0xffe50914),
            lineHeight = 1.35.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 9.99.dp,
                    y = 7.5.dp)
                .requiredWidth(width = 204.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 319.01.dp,
                    y = 12.dp)
                .requiredWidth(width = 58.dp)
                .requiredHeight(height = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_1n),
                contentDescription = "Icon",
                modifier = Modifier
                    .requiredSize(size = 20.dp))
            Image(
                painter = painterResource(id = R.drawable.iconn),
                contentDescription = "Icon",
                modifier = Modifier
                    .requiredSize(size = 20.dp))
        }
    }
}

@Composable
fun NumeroDeCopias(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.15f),
        border = BorderStroke(0.841.dp, Color.White.copy(alpha = 0.2f)),
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 241.dp)
                .requiredHeight(height = 67.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.83.dp,
                        y = 12.83.dp)
                    .requiredWidth(width = 91.dp)
                    .requiredHeight(height = 41.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .requiredSize(size = 16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 41.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 67.dp)
                            .requiredHeight(height = 17.dp)
                    ) {
                        Text(
                            text = "Disponibles",
                            color = Color.White.copy(alpha = 0.6f),
                            lineHeight = 1.33.em,
                            style = TextStyle(
                                fontSize = 13.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = (-1.84).dp))
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 17.33.dp)
                            .requiredWidth(width = 67.dp)
                            .requiredHeight(height = 24.dp)
                    ) {
                        Text(
                            text = "3",
                            color = Color.White,
                            lineHeight = 1.5.em,
                            style = TextStyle(
                                fontSize = 16.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = (-2.16).dp))
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 136.46.dp,
                        y = 12.83.dp)
                    .requiredWidth(width = 91.dp)
                    .requiredHeight(height = 41.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .requiredSize(size = 16.dp))
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 67.dp)
                        .requiredHeight(height = 41.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 67.dp)
                            .requiredHeight(height = 17.dp)
                    ) {
                        Text(
                            text = "Total copias",
                            color = Color.White.copy(alpha = 0.6f),
                            lineHeight = 1.33.em,
                            style = TextStyle(
                                fontSize = 13.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = (-1.84).dp))
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 17.33.dp)
                            .requiredWidth(width = 67.dp)
                            .requiredHeight(height = 24.dp)
                    ) {
                        Text(
                            text = "5",
                            color = Color.White,
                            lineHeight = 1.5.em,
                            style = TextStyle(
                                fontSize = 16.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 0.dp,
                                    y = (-2.16).dp))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 119.47.dp,
                        y = 17.49.dp)
                    .requiredWidth(width = 1.dp)
                    .requiredHeight(height = 32.dp)
                    .background(color = Color.White.copy(alpha = 0.2f)))
        }
    }
}

@Preview(widthDp = 393, heightDp = 803)
@Composable
private fun CompraPreview() {
    Compra(Modifier)
}