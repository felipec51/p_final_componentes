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
        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)

        composeView.setContent {

            MaterialTheme {
                funtionaddpeli()
            }
        }
    }

    @Composable
    fun funtionaddpeli(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 468.dp)
                .requiredHeight(height = 937.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xff141414))
                    .padding(
                        top = 63.98809814453125.dp,
                        bottom = -0.00006103515625.dp
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(31.99.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 873.dp)
                        .background(color = Color(0xff141414))
                        .padding(
                            start = 23.98459243774414.dp,
                            end = 23.98458480834961.dp,
                            top = 23.98455810546875.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 431.dp)
                            .requiredHeight(height = 599.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.02.dp,
                                    y = 0.03.dp
                                )
                                .requiredWidth(width = 423.dp)
                                .requiredHeight(height = 137.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .background(color = Color(0xff141414))
                                .border(
                                    border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(
                                    start = 23.98459243774414.dp,
                                    top = 23.98459243774414.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 405.dp)
                                    .requiredHeight(height = 105.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 357.dp)
                                        .requiredHeight(height = 105.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "Total Películas",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 304.dp,
                                                y = 25.dp
                                            )
                                            .requiredSize(size = 48.dp)
                                            .clip(shape = RoundedCornerShape(10.dp))
                                            .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                                            .padding(end = 0.01753997802734375.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .requiredSize(size = 24.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 27.98.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 24.dp)
                                    ) {
                                        Text(
                                            text = "7",
                                            color = Color.White,
                                            lineHeight = 1.5.em,
                                            style = TextStyle(
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-1.88).dp
                                                )
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 55.97.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "En el catálogo",
                                            color = Color(0xff6a7282),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 82.62.dp
                                            )
                                            .requiredWidth(width = 73.dp)
                                            .requiredHeight(height = 23.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color(0xff00c950).copy(alpha = 0.1f))
                                    ) {
                                        Text(
                                            text = "↑ +12.5%",
                                            color = Color(0xff00c950),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.91.dp,
                                                    y = 1.31.dp
                                                )
                                                .requiredWidth(width = 70.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.02.dp,
                                    y = 160.03.dp
                                )
                                .requiredWidth(width = 422.dp)
                                .requiredHeight(height = 147.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .background(color = Color(0xff141414))
                                .border(
                                    border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(
                                    start = 23.98459243774414.dp,
                                    top = 23.98459243774414.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 388.dp)
                                    .requiredHeight(height = 105.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 357.dp)
                                        .requiredHeight(height = 105.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "Rating Promedio",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 304.dp,
                                                y = 25.dp
                                            )
                                            .requiredSize(size = 48.dp)
                                            .clip(shape = RoundedCornerShape(10.dp))
                                            .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                                            .padding(end = 0.01753997802734375.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .requiredSize(size = 24.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 27.98.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 24.dp)
                                    ) {
                                        Text(
                                            text = "8.3",
                                            color = Color.White,
                                            lineHeight = 1.5.em,
                                            style = TextStyle(
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-1.88).dp
                                                )
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 55.97.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "De todas las películas",
                                            color = Color(0xff6a7282),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 82.62.dp
                                            )
                                            .requiredWidth(width = 54.dp)
                                            .requiredHeight(height = 23.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color(0xff00c950).copy(alpha = 0.1f))
                                    ) {
                                        Text(
                                            text = "↑ +0.3",
                                            color = Color(0xff00c950),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.98.dp,
                                                    y = 1.dp
                                                )
                                                .requiredWidth(width = 39.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.02.dp,
                                    y = 322.03.dp
                                )
                                .requiredWidth(width = 422.dp)
                                .requiredHeight(height = 117.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .background(color = Color(0xff141414))
                                .border(
                                    border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(
                                    start = 23.98459243774414.dp,
                                    top = 23.98459243774414.dp,
                                    bottom = 23.98459243774414.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .requiredWidth(width = 388.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        7.98.dp,
                                        Alignment.Top
                                    ),
                                    modifier = Modifier
                                        .requiredWidth(width = 357.dp)
                                        .requiredHeight(height = 75.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "Géneros",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .requiredHeight(height = 24.dp)
                                    ) {
                                        Text(
                                            text = "6",
                                            color = Color.White,
                                            lineHeight = 1.5.em,
                                            style = TextStyle(
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-1.88).dp
                                                )
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "Categorías disponibles",
                                            color = Color(0xff6a7282),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 304.dp,
                                            y = 25.dp
                                        )
                                        .requiredSize(size = 48.dp)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                                        .padding(end = 0.01753997802734375.dp)
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
                        Column(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.02.dp,
                                    y = 454.03.dp
                                )
                                .requiredWidth(width = 422.dp)
                                .requiredHeight(height = 145.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .background(color = Color(0xff141414))
                                .border(
                                    border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(
                                    start = 23.98459243774414.dp,
                                    top = 23.98459243774414.dp
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 388.dp)
                                    .requiredHeight(height = 105.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(width = 357.dp)
                                        .requiredHeight(height = 105.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "Visualizaciones",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 27.98.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 24.dp)
                                    ) {
                                        Text(
                                            text = "156K",
                                            color = Color.White,
                                            lineHeight = 1.5.em,
                                            style = TextStyle(
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 0.dp,
                                                    y = (-1.88).dp
                                                )
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 55.97.dp
                                            )
                                            .requiredWidth(width = 357.dp)
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "Este mes",
                                            color = Color(0xff6a7282),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(
                                                x = 0.dp,
                                                y = 82.62.dp
                                            )
                                            .requiredWidth(width = 66.dp)
                                            .requiredHeight(height = 23.dp)
                                            .clip(shape = MaterialTheme.shapes.small)
                                            .background(color = Color(0xff00c950).copy(alpha = 0.1f))
                                    ) {
                                        Text(
                                            text = "↑ +8.2%",
                                            color = Color(0xff00c950),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 7.91.dp,
                                                    y = 0.55.dp
                                                )
                                                .requiredWidth(width = 65.dp)
                                        )
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 304.dp,
                                            y = 25.dp
                                        )
                                        .requiredSize(size = 48.dp)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = Color(0xffe50914).copy(alpha = 0.1f))
                                        .padding(end = 0.01753997802734375.dp)
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
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 192.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(3.99.dp, Alignment.Top),
                            modifier = Modifier
                                .requiredWidth(width = 150.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "Catálogo de Películas",
                                    color = Color.White,
                                    lineHeight = 1.5.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-1.88).dp
                                        )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 20.dp)
                            ) {
                                Text(
                                    text = "7 películas encontradas",
                                    color = Color(0xff99a1af),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-2).dp
                                        )
                                        .requiredWidth(width = 145.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopCenter)
                                .offset(
                                    x = (-0.5).dp,
                                    y = 71.04.dp
                                )
                                .requiredWidth(width = 425.dp)
                                .requiredHeight(height = 147.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 15.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 15.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_13),
                                    contentDescription = "image 13",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(height = 115.dp)
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 243.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 107.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 243.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 107.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        end = 0.4541015625.dp,
                        bottom = 873.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 47.97.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 1.dp)
                        .requiredHeight(height = 63.dp)
                        .background(color = Color(0xff2f2f2f))
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 65.dp,
                            y = 13.dp
                        )
                        .requiredWidth(width = 106.dp)
                        .requiredHeight(height = 36.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(81.23.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 115.dp,
                                y = 0.dp
                            )
                            .requiredWidth(width = 117.dp)
                            .requiredHeight(height = 36.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xff2f2f2f))
                            .border(
                                border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(
                                start = 11.992297172546387.dp,
                                end = 11.992291450500488.dp
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .requiredWidth(width = 37.dp)
                                .requiredHeight(height = 20.dp)
                        ) {
                            Text(
                                text = "Todos",
                                color = Color.White,
                                lineHeight = 1.43.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "Icon",
                            alpha = 0.5f,
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 109.dp)
                            .requiredHeight(height = 36.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .requiredWidth(width = 106.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color(0xff2f2f2f))
                                .border(
                                    border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(
                                    start = 36.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                )
                        ) {
                            Text(
                                text = "Buscar ",
                                color = Color(0xff6a7282),
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .requiredWidth(width = 70.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "Icon",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 11.99.dp,
                                    y = 10.01.dp
                                )
                                .requiredSize(size = 16.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 306.dp,
                            y = 14.dp
                        )
                        .requiredWidth(width = 145.dp)
                        .requiredHeight(height = 36.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 36.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xffe50914))
                    ) {
                        Text(
                            text = "Agregar Película",
                            color = Color.White,
                            lineHeight = 1.43.em,
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 34.dp,
                                    y = 6.dp
                                )
                                .requiredWidth(width = 114.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "Icon",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 11.99.dp,
                                    y = 10.dp
                                )
                                .requiredSize(size = 16.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 11.99.dp,
                            y = 17.44.dp
                        )
                        .requiredSize(size = 28.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 25.47.dp,
                            y = 30.92.dp
                        )
                        .requiredSize(size = 1.dp)
                ) {
                    Text(
                        text = "Toggle Sidebar",
                        color = Color.White,
                        lineHeight = 1.43.em,
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .padding(
                        start = 0.4541015625.dp,
                        top = 64.dp,
                        bottom = 20.dp
                    )
            )
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xff141414),
                border = BorderStroke(1.1204500198364258.dp, Color(0xff2f2f2f)),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(
                        start = 10.dp,
                        end = 10.4541015625.dp,
                        top = 187.dp,
                        bottom = 187.dp
                    )
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 448.dp)
                        .requiredHeight(height = 563.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 414.9.dp,
                                y = 17.1.dp
                            )
                            .requiredSize(size = 16.dp)
                            .clip(shape = RoundedCornerShape(2.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "Icon",
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = (-1).dp,
                                    y = 14.99.dp
                                )
                                .requiredSize(size = 1.dp)
                        ) {
                            Text(
                                text = "Close",
                                color = Color.White,
                                lineHeight = 1.5.em,
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = (-1.88).dp
                                    )
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(11.97.dp, Alignment.Top),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 25.11.dp,
                                y = 25.1.dp
                            )
                            .requiredWidth(width = 398.dp)
                            .requiredHeight(height = 70.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 398.dp)
                                .requiredHeight(height = 18.dp)
                        ) {
                            Text(
                                text = "Editar Película",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                lineHeight = 1.em,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 136.99.dp,
                                        y = (-1.36).dp
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .requiredWidth(width = 398.dp)
                        ) {
                            Text(
                                text = "Complete los detalles de la película para actualizar",
                                color = Color(0xff99a1af),
                                textAlign = TextAlign.Center,
                                lineHeight = 1.43.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 3.29.dp,
                                        y = (-2).dp
                                    )
                                    .requiredWidth(width = 392.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 25.11.dp,
                                y = 111.05.dp
                            )
                            .requiredWidth(width = 398.dp)
                            .requiredHeight(height = 426.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.98.dp, Alignment.Top),
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 346.dp)
                                .padding(top = 15.983917236328125.dp)
                        ) {
                            TextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Título",
                                        color = Color(0xffd1d5dc),
                                        lineHeight = 1.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                },
                                placeholder = { Text("Interestelar") },
                                textStyle = TextStyle(
                                    fontSize = 16.sp
                                ),
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
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 50.dp)
                            ) {
                                TextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Año",
                                            color = Color(0xffd1d5dc),
                                            lineHeight = 1.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            )
                                        )
                                    },
                                    placeholder = { Text("2025") },
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier
                                        .requiredWidth(width = 193.dp)
                                        .requiredHeight(height = 50.dp)
                                )
                                TextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Rating",
                                            color = Color(0xffd1d5dc),
                                            lineHeight = 1.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            )
                                        )
                                    },
                                    placeholder = { Text("5") },
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 204.88.dp,
                                            y = 0.dp
                                        )
                                        .requiredWidth(width = 193.dp)
                                        .requiredHeight(height = 50.dp)
                                )
                            }
                            TextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Género",
                                        color = Color(0xffd1d5dc),
                                        lineHeight = 1.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                },
                                supportingText = {
                                    Text(
                                        text = "Accion",
                                        color = Color.White,
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .requiredWidth(width = 157.dp)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 50.dp)
                            )
                            TextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Director",
                                        color = Color(0xffd1d5dc),
                                        lineHeight = 1.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                },
                                placeholder = { Text("Christopher Nolan") },
                                textStyle = TextStyle(
                                    fontSize = 16.sp
                                ),
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
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "URL del Póster",
                                        color = Color(0xffd1d5dc),
                                        lineHeight = 1.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                },
                                placeholder = { Text("https://images.unsplash.com/photo-168263265465...") },
                                textStyle = TextStyle(
                                    fontSize = 16.sp
                                ),
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
                        Column(
                            verticalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Bottom),
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 80.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    8.dp,
                                    Alignment.CenterHorizontally
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .requiredWidth(width = 398.dp)
                                    .requiredHeight(height = 36.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xffe50914))
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                            ) {
                                Text(
                                    text = "Guardar Cambios",
                                    color = Color.White,
                                    lineHeight = 1.43.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    8.dp,
                                    Alignment.CenterHorizontally
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .requiredWidth(width = 398.dp)
                                    .requiredHeight(height = 36.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xff2f2f2f))
                                    .border(
                                        border = BorderStroke(
                                            1.1204500198364258.dp,
                                            Color(0xff2f2f2f)
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                            ) {
                                Text(
                                    text = "Cancelar",
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
        }
    }

    @Preview(widthDp = 468, heightDp = 917)
    @Composable
    private fun addpeli() {
        funtionaddpeli(Modifier)
    }
}