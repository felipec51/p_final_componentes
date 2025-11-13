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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pelinodispo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pelinodispo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)


        composeView.setContent {

            MaterialTheme {
                pelinodispo()
            }
        }
    }

    @Composable
    fun MovieDetailActivityNODisponible(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 425.dp)
                .requiredHeight(height = 3140.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xff141414))
                    .padding(
                        start = 15.986080169677734.dp,
                        end = 15.98605728149414.dp,
                        top = 31.98857307434082.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 909.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xff1a1a1a))
                ) {
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 46.73.dp,
                                y = 24.dp
                            )
                            .requiredWidth(width = 300.dp)
                            .requiredHeight(height = 450.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.White)
                            .shadow(
                                elevation = 50.dp,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 450.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.imagewithfallback),
                                contentDescription = "ImageWithFallback",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                            Image(
                                painter = painterResource(id = R.drawable.image20),
                                contentDescription = "image 20",
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = (-9.71).dp,
                                        y = (-109.98).dp
                                    )
                                    .requiredWidth(width = 320.dp)
                                    .requiredHeight(height = 568.dp)
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 24.01.dp,
                                y = 498.01.dp
                            )
                            .requiredWidth(width = 345.dp)
                            .requiredHeight(height = 344.dp)
                            .padding(top = 15.986083984375.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Top),
                            modifier = Modifier
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 332.dp)
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
                                            fontSize = 30.sp
                                        ),
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = (-3.1).dp
                                            )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(height = 23.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(
                                            4.dp,
                                            Alignment.CenterHorizontally
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 59.87.dp,
                                                y = 0.dp
                                            )
                                            .requiredWidth(width = 40.dp)
                                            .requiredHeight(height = 23.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .border(
                                                border = BorderStroke(
                                                    1.0504200458526611.dp,
                                                    Color(0xff4a5565)
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(
                                                horizontal = 8.dp,
                                                vertical = 2.dp
                                            )
                                    ) {
                                        Text(
                                            text = "13+",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 13.sp
                                            )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 1.69.dp
                                            )
                                            .requiredWidth(width = 30.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "2025",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-0.95).dp
                                                )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 42.18.dp,
                                                y = 1.69.dp
                                            )
                                            .requiredWidth(width = 6.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "•",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-0.95).dp
                                                )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 111.43.dp,
                                                y = 1.69.dp
                                            )
                                            .requiredWidth(width = 6.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "•",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-0.95).dp
                                                )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 129.12.dp,
                                                y = 1.69.dp
                                            )
                                            .requiredWidth(width = 58.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "1h 53min",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-0.95).dp
                                                )
                                        )
                                    }
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 249.dp)
                            ) {
                                NumeroDeCopias()
                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = Color.White.copy(alpha = 0.15f),
                                    border = BorderStroke(
                                        0.8403360247612.dp,
                                        Color.White.copy(alpha = 0.2f)
                                    ),
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(16.dp))
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 207.dp)
                                            .requiredHeight(height = 95.dp)
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                8.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 12.83.dp,
                                                    y = 12.83.dp
                                                )
                                                .requiredWidth(width = 182.dp)
                                                .requiredHeight(height = 24.dp)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icon),
                                                contentDescription = "Icon",
                                                modifier = Modifier
                                                    .requiredSize(size = 16.dp)
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .requiredWidth(width = 51.dp)
                                                    .requiredHeight(height = 24.dp)
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .requiredWidth(width = 9.dp)
                                                        .requiredHeight(height = 24.dp)
                                                ) {
                                                    Text(
                                                        text = "3",
                                                        color = Color.White,
                                                        lineHeight = 1.5.em,
                                                        style = TextStyle(
                                                            fontSize = 16.sp
                                                        ),
                                                        modifier = Modifier
                                                            .align(alignment = Alignment.TopStart)
                                                            .offset(
                                                                x = 0.dp,
                                                                y = (-2.16).dp
                                                            )
                                                    )
                                                }
                                                Box(
                                                    modifier = Modifier
                                                        .align(alignment = Alignment.TopStart)
                                                        .offset(
                                                            x = 16.62.dp,
                                                            y = 5.04.dp
                                                        )
                                                        .requiredWidth(width = 35.dp)
                                                        .requiredHeight(height = 17.dp)
                                                ) {
                                                    Text(
                                                        text = "en fila",
                                                        color = Color.White.copy(alpha = 0.6f),
                                                        lineHeight = 1.33.em,
                                                        style = TextStyle(
                                                            fontSize = 13.sp
                                                        ),
                                                        modifier = Modifier
                                                            .align(alignment = Alignment.TopStart)
                                                            .offset(
                                                                x = 0.dp,
                                                                y = (-1.84).dp
                                                            )
                                                    )
                                                }
                                            }
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                8.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 12.83.dp,
                                                    y = 44.83.dp
                                                )
                                                .requiredWidth(width = 182.dp)
                                                .requiredHeight(height = 37.dp)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icon),
                                                contentDescription = "Icon",
                                                modifier = Modifier
                                                    .requiredSize(size = 16.dp)
                                            )
                                            Column(
                                                modifier = Modifier
                                                    .requiredWidth(width = 158.dp)
                                                    .requiredHeight(height = 37.dp)
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .requiredHeight(height = 17.dp)
                                                ) {
                                                    Text(
                                                        text = "Disponible aprox.",
                                                        color = Color.White.copy(alpha = 0.6f),
                                                        lineHeight = 1.33.em,
                                                        style = TextStyle(
                                                            fontSize = 13.sp
                                                        ),
                                                        modifier = Modifier
                                                            .align(alignment = Alignment.TopStart)
                                                            .offset(
                                                                x = 0.dp,
                                                                y = (-1.84).dp
                                                            )
                                                    )
                                                }
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .requiredHeight(height = 20.dp)
                                                ) {
                                                    Text(
                                                        text = "15 de noviembre de 2025",
                                                        color = Color.White,
                                                        lineHeight = 1.43.em,
                                                        style = TextStyle(
                                                            fontSize = 14.sp
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(height = 20.dp)
                                ) {
                                    Text(
                                        text = "No disponible para compra",
                                        color = Color(0xff99a1af),
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = (-0.95).dp
                                            )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(height = 36.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 87.dp)
                                            .requiredHeight(height = 36.dp)
                                    ) {
                                        Text(
                                            text = "$14.99",
                                            color = Color.White,
                                            lineHeight = 1.2.em,
                                            style = TextStyle(
                                                fontSize = 30.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-3.1).dp
                                                )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 95.36.dp,
                                                y = 14.71.dp
                                            )
                                            .requiredWidth(width = 27.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "USD",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-0.95).dp
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Snackbar(
                        containerColor = Color(0xffe9e9e9),
                        contentColor = Color.Black,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopCenter)
                            .offset(
                                x = 0.dp,
                                y = 856.01.dp
                            )
                    ) {
                        Text(
                            text = "Añadirme a la fila",
                            color = Color.Black,
                            lineHeight = 1.56.em,
                            style = TextStyle(
                                fontSize = 18.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 24.01.dp,
                                y = 909.01.dp
                            )
                            .requiredWidth(width = 345.dp)
                            .requiredHeight(height = 48.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 2249.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 393.dp)
                            .requiredHeight(height = 347.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                    ) {
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 24.dp,
                                    y = 24.dp
                                )
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 32.dp)
                        ) {
                            Text(
                                text = "Happy Gilmore 2",
                                color = Color.White,
                                lineHeight = 1.33.em,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-3.05).dp
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 24.dp,
                                    y = 71.97.dp
                                )
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 30.dp)
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "2025",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 38.18.dp,
                                        y = 0.dp
                                    )
                                    .requiredWidth(width = 6.dp)
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "•",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 51.86.dp,
                                        y = 0.dp
                                    )
                                    .requiredWidth(width = 25.dp)
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "13+",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 84.53.dp,
                                        y = 0.dp
                                    )
                                    .requiredWidth(width = 6.dp)
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "•",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 98.21.dp,
                                        y = 0.dp
                                    )
                                    .requiredWidth(width = 118.dp)
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "Comedia, Deportes",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.95).dp
                                        )
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 24.dp,
                                    y = 107.95.dp
                                )
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 159.dp)
                        ) {
                            Text(
                                text = "El juego no ha terminado para Happy Gilmore. El legendario y explosivo golfista interpretado por Adam Sandler vuelve al green para cumplir el deseo de su hija. Con su característico estilo poco convencional y su temperamento único, Happy deberá enfrentarse a una nueva generación de jugadores y demostrar que aún tiene lo necesario para competir al más alto nivel.",
                                color = Color(0xffd1d5dc),
                                lineHeight = 1.63.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-0.9).dp
                                    )
                                    .requiredWidth(width = 341.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 27.01.dp,
                                    y = 270.65.dp
                                )
                                .requiredWidth(width = 47.dp)
                                .requiredHeight(height = 18.dp)
                        ) {
                            Text(
                                text = "Elenco:",
                                color = Color(0xff99a1af),
                                lineHeight = 1.43.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 24.dp,
                                    y = 283.17.dp
                                )
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 40.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 3.02.dp,
                                        y = 9.48.dp
                                    )
                                    .requiredWidth(width = 292.dp)
                                    .requiredHeight(height = 38.dp)
                            ) {
                                Text(
                                    text = "Adam Sandler, Julie Bowen, Christopher McDonald, Ben Stiller, Travis Kelce",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopCenter)
                                        .offset(
                                            x = 0.02.dp,
                                            y = (-5).dp
                                        )
                                        .requiredWidth(width = 292.dp)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 371.14.dp
                            )
                            .requiredWidth(width = 393.dp)
                            .requiredHeight(height = 881.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 28.dp)
                        ) {
                            Text(
                                text = "Más info",
                                color = Color.White,
                                lineHeight = 1.4.em,
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 52.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 107.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.9954833984375.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Formatos",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 23.dp)
                            ) {
                                Text(
                                    text = "VHS, DVD",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.9).dp
                                        )
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 182.72.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 107.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.9954833984375.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Géneros",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 23.dp)
                            ) {
                                Text(
                                    text = "Películas para reír y Deportes",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.9).dp
                                        )
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 313.45.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 129.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.99560546875.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Esta película es...",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 45.dp)
                            ) {
                                Text(
                                    text = "Comedia física, Irreverente, Deportes, Golf, Contra todo obstáculo, Película",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.9).dp
                                        )
                                        .requiredWidth(width = 341.dp)
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 466.93.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 107.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.99560546875.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Audio",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 23.dp)
                            ) {
                                Text(
                                    text = "Inglés - Audio descriptivo, Inglés [Original], Español",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.9).dp
                                        )
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 597.66.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 107.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.9954833984375.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Subtítulos",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 23.dp)
                            ) {
                                Text(
                                    text = "Inglés y Español",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-0.9).dp
                                        )
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 728.38.dp
                                )
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 152.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xff5e5e5e).copy(alpha = 0.35f))
                                .padding(
                                    start = 23.995532989501953.dp,
                                    end = 23.995555877685547.dp,
                                    top = 23.9954833984375.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Elenco",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.95).dp
                                        )
                                )
                            }
                            Snackbar(
                                contentColor = Color(0xffd1d5dc)
                            ) {
                                Text(
                                    text = "Adam Sandler, Christopher McDonald, Julie Bowen, Frances Bay, Carl Weathers, Allen Covert, Robert Smigel, Bob Barker, Richard Kiel, Dennis Dugan",
                                    color = Color(0xffd1d5dc),
                                    lineHeight = 1.63.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Top),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 1307.74.dp
                            )
                            .requiredWidth(width = 393.dp)
                            .requiredHeight(height = 900.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 32.dp)
                        ) {
                            Text(
                                text = "Tráileres y más",
                                color = Color.White,
                                lineHeight = 1.33.em,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-3.05).dp
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 852.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 393.dp)
                                    .requiredHeight(height = 273.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = Color(0xff1d1d1d))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 393.dp)
                                        .requiredHeight(height = 221.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.image21),
                                            contentDescription = "image 21",
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = (-10.99).dp,
                                                    y = (-4.7).dp
                                                )
                                                .requiredWidth(width = 412.dp)
                                                .requiredHeight(height = 231.dp)
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                            .background(color = Color.Black.copy(alpha = 0.4f))
                                            .padding(end = 0.0000152587890625.dp)
                                    ) {
                                        IconButton(
                                            onClick = { },
                                            modifier = Modifier
                                                .clip(shape = RoundedCornerShape(35246200.dp))
                                                .background(color = Color.White.copy(alpha = 0.9f))
                                                .padding(start = 3.971904754638672.dp)
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .requiredSize(size = 48.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.icon),
                                                    contentDescription = "Icon",
                                                    modifier = Modifier
                                                        .requiredSize(size = 24.dp)
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 345.64.dp,
                                                y = 188.02.dp
                                            )
                                            .requiredWidth(width = 40.dp)
                                            .requiredHeight(height = 25.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color.Black.copy(alpha = 0.8f))
                                    ) {
                                        Text(
                                            text = "2:34",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 13.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.99.dp,
                                                    y = 2.99.dp
                                                )
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 15.99.dp,
                                            y = 237.3.dp
                                        )
                                        .requiredWidth(width = 361.dp)
                                        .requiredHeight(height = 20.dp)
                                ) {
                                    Text(
                                        text = "Happy Gilmore 2: Tráiler oficial",
                                        color = Color.White,
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = (-0.95).dp
                                            )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = 289.26.dp
                                    )
                                    .requiredWidth(width = 393.dp)
                                    .requiredHeight(height = 273.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = Color(0xff1d1d1d))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 393.dp)
                                        .requiredHeight(height = 221.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.image22),
                                            contentDescription = "image 22",
                                            modifier = Modifier
                                                .align(alignment = Alignment.Center)
                                                .offset(
                                                    x = (-0.21).dp,
                                                    y = (-0.11).dp
                                                )
                                                .requiredWidth(width = 399.dp)
                                                .requiredHeight(height = 225.dp)
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                            .background(color = Color.Black.copy(alpha = 0.4f))
                                            .padding(end = 0.0000152587890625.dp)
                                    ) {
                                        IconButton(
                                            onClick = { },
                                            modifier = Modifier
                                                .clip(shape = RoundedCornerShape(35246200.dp))
                                                .background(color = Color.White.copy(alpha = 0.9f))
                                                .padding(start = 3.971904754638672.dp)
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .requiredSize(size = 48.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.icon),
                                                    contentDescription = "Icon",
                                                    modifier = Modifier
                                                        .requiredSize(size = 24.dp)
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 345.64.dp,
                                                y = 188.03.dp
                                            )
                                            .requiredWidth(width = 40.dp)
                                            .requiredHeight(height = 25.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color.Black.copy(alpha = 0.8f))
                                    ) {
                                        Text(
                                            text = "3:12",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 13.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.99.dp,
                                                    y = 2.99.dp
                                                )
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 15.99.dp,
                                            y = 237.3.dp
                                        )
                                        .requiredWidth(width = 361.dp)
                                        .requiredHeight(height = 20.dp)
                                ) {
                                    Text(
                                        text = "Detrás de cámaras",
                                        color = Color.White,
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = (-0.95).dp
                                            )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = 578.52.dp
                                    )
                                    .requiredWidth(width = 393.dp)
                                    .requiredHeight(height = 273.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = Color(0xff1d1d1d))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 393.dp)
                                        .requiredHeight(height = 221.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.image23),
                                            contentDescription = "image 23",
                                            modifier = Modifier
                                                .align(alignment = Alignment.Center)
                                                .offset(
                                                    x = (-2.21).dp,
                                                    y = (-1.87).dp
                                                )
                                                .requiredWidth(width = 403.dp)
                                                .requiredHeight(height = 226.dp)
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredWidth(width = 393.dp)
                                            .requiredHeight(height = 221.dp)
                                            .background(color = Color.Black.copy(alpha = 0.4f))
                                            .padding(end = 0.0000152587890625.dp)
                                    ) {
                                        IconButton(
                                            onClick = { },
                                            modifier = Modifier
                                                .clip(shape = RoundedCornerShape(35246200.dp))
                                                .background(color = Color.White.copy(alpha = 0.9f))
                                                .padding(start = 3.971904754638672.dp)
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .requiredSize(size = 48.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.icon),
                                                    contentDescription = "Icon",
                                                    modifier = Modifier
                                                        .requiredSize(size = 24.dp)
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 345.64.dp,
                                                y = 188.03.dp
                                            )
                                            .requiredWidth(width = 40.dp)
                                            .requiredHeight(height = 25.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color.Black.copy(alpha = 0.8f))
                                    ) {
                                        Text(
                                            text = "1:58",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 13.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.99.dp,
                                                    y = 2.99.dp
                                                )
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 15.99.dp,
                                            y = 237.3.dp
                                        )
                                        .requiredWidth(width = 361.dp)
                                        .requiredHeight(height = 20.dp)
                                ) {
                                    Text(
                                        text = "Momentos graciosos del rodaje",
                                        color = Color.White,
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = (-0.95).dp
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(209.51.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color.Black,
                            1f to Color.Black,
                            start = Offset(212.71f, 0f),
                            end = Offset(212.71f, 55.98f)
                        )
                    )
                    .padding(
                        start = 15.986080169677734.dp,
                        end = 15.986061096191406.dp
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 80.dp)
                        .requiredHeight(height = 32.dp)
                ) {
                    Text(
                        text = "RewindCodeFilm",
                        color = Color(0xffe50914),
                        lineHeight = 1.35.em,
                        style = TextStyle(
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .requiredWidth(width = 204.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 58.dp)
                        .requiredHeight(height = 32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .requiredSize(size = 20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .requiredSize(size = 20.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun NumeroDeCopias(modifier: Modifier = Modifier) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.15f),
            border = BorderStroke(0.8403360247612.dp, Color.White.copy(alpha = 0.2f)),
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
                        .offset(
                            x = 12.83.dp,
                            y = 12.83.dp
                        )
                        .requiredWidth(width = 91.dp)
                        .requiredHeight(height = 41.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
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
                                    fontSize = 13.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-1.84).dp
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 17.33.dp
                                )
                                .requiredWidth(width = 67.dp)
                                .requiredHeight(height = 24.dp)
                        ) {
                            Text(
                                text = "0",
                                color = Color.White,
                                lineHeight = 1.5.em,
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-2.16).dp
                                    )
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 136.46.dp,
                            y = 12.83.dp
                        )
                        .requiredWidth(width = 91.dp)
                        .requiredHeight(height = 41.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
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
                                    fontSize = 13.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-1.84).dp
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 17.33.dp
                                )
                                .requiredWidth(width = 67.dp)
                                .requiredHeight(height = 24.dp)
                        ) {
                            Text(
                                text = "5",
                                color = Color.White,
                                lineHeight = 1.5.em,
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-2.16).dp
                                    )
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 119.47.dp,
                            y = 17.49.dp
                        )
                        .requiredWidth(width = 1.dp)
                        .requiredHeight(height = 32.dp)
                        .background(color = Color.White.copy(alpha = 0.2f))
                )
            }
        }
    }

    @Preview(widthDp = 425, heightDp = 3140)
    @Composable
    private fun MovieDetailActivityNODisponiblePreview() {
        MovieDetailActivityNODisponible(Modifier)
    }
}