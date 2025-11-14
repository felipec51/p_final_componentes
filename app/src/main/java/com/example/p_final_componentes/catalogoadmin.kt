package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class catalogoadmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogoadmin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)

//hola
        composeView.setContent {

            MaterialTheme {
                catalogoadmin()
            }
        }
    }

    @Composable
    fun AndroidPanel(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 468.dp)
                .requiredHeight(height = 1433.dp)
                .background(color = Color.White)
        ) {
            PanelAdmin()
        }
    }

    @Composable
    fun PanelAdmin(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 468.dp)
                .requiredHeight(height = 1450.dp)
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
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 1375.dp)
                        .background(color = Color(0xff141414))
                        .padding(
                            start = 23.98459243774414.dp,
                            end = 23.98458480834961.dp,
                            top = 23.98455810546875.dp
                        )
                ) {
                    StateClosed()
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
                            .requiredHeight(height = 691.dp)
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
                                .requiredHeight(height = 619.dp)
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
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.rectangle37),
                                    contentDescription = "Rectangle 37",
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
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
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 129.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_14),
                                    contentDescription = "image 14",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-3).dp
                                        )
                                        .requiredWidth(width = 200.dp)
                                        .requiredHeight(height = 113.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 129.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_15),
                                    contentDescription = "image 15",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-4).dp
                                        )
                                        .requiredWidth(width = 200.dp)
                                        .requiredHeight(height = 113.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        7.98.dp,
                                        Alignment.Start
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = -0.000019073486328125.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 100.dp)
                                            .requiredHeight(height = 18.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .background(color = Color.White)
                                    ) {
                                        Text(
                                            text = "Editar",
                                            color = Color.Black,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.Center)
                                                .offset(
                                                    x = 0.5.dp,
                                                    y = 0.5.dp
                                                )
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .align(alignment = Alignment.CenterStart)
                                                .offset(
                                                    x = 9.dp,
                                                    y = (-0.01).dp
                                                )
                                                .requiredSize(size = 16.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .requiredSize(size = 23.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .background(color = Color(0xffe50914))
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 4.dp,
                                                    y = 4.dp
                                                )
                                                .requiredSize(size = 16.dp)
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 243.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 107.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.vistaprevia9),
                                    contentDescription = "vista previa 9",
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        7.98.dp,
                                        Alignment.Start
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = -0.000019073486328125.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 100.dp)
                                            .requiredHeight(height = 18.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .background(color = Color.White)
                                    ) {
                                        Text(
                                            text = "Editar",
                                            color = Color.Black,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .align(alignment = Alignment.Center)
                                                .offset(
                                                    x = 0.5.dp,
                                                    y = 0.5.dp
                                                )
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .align(alignment = Alignment.CenterStart)
                                                .offset(
                                                    x = 9.dp,
                                                    y = (-0.01).dp
                                                )
                                                .requiredSize(size = 16.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .requiredSize(size = 23.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .background(color = Color(0xffe50914))
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .align(alignment = Alignment.TopStart)
                                                .offset(
                                                    x = 4.dp,
                                                    y = 4.dp
                                                )
                                                .requiredSize(size = 16.dp)
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 243.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 107.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_16),
                                    contentDescription = "image 16",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = (-2).dp,
                                            y = (-4).dp
                                        )
                                        .requiredWidth(width = 203.dp)
                                        .requiredHeight(height = 115.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 358.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.vistaprevia14),
                                    contentDescription = "vista previa 14",
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 358.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_17),
                                    contentDescription = "image 17",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-4).dp
                                        )
                                        .fillMaxWidth()
                                        .requiredHeight(height = 113.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 10.dp,
                                        y = 472.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_18),
                                    contentDescription = "image 18",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-4).dp
                                        )
                                        .requiredWidth(width = 194.dp)
                                        .requiredHeight(height = 110.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 472.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image_19),
                                    contentDescription = "image 19",
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.dp,
                                            y = (-3).dp
                                        )
                                        .requiredWidth(width = 200.dp)
                                        .requiredHeight(height = 113.dp)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 1386.dp)
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
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 106.dp)
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(81.23.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
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
        }
    }

    @Composable
    fun StateClosed(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .requiredWidth(width = 350.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 1.dp)
            ) {
                ItemsList(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .offset(
                            x = 0.dp,
                            y = 218.dp
                        )
                )
            }
        }
    }

    @Composable
    fun ItemsList(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(height = 218.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(color = Color.White)
        ) {
            StateDefault()
            StateDefault()
            StateDefault()
            StateDefault()
            StateDefault()
        }
    }

    @Composable
    fun StateDefault(modifier: Modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(
                    horizontal = 24.dp,
                    vertical = 11.dp
                )
        ) {

        }
    }

    @Composable
    fun Label1(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 45.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Label1",
                color = Color(0xffececec),
                lineHeight = 1.5.em,
                style =  MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }

    @Composable
    fun Label2(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 49.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Label2",
                color = Color(0xffececec),
                lineHeight = 1.5.em,
                style =  MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }

    @Composable
    fun Label3(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 49.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Label3",
                color = Color(0xffececec),
                lineHeight = 1.5.em,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }

    @Composable
    fun Label4(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 50.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Label4",
                color = Color(0xffececec),
                lineHeight = 1.5.em,
                style =  MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }

    @Composable
    fun Label5(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 49.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Label5",
                color = Color(0xffececec),
                lineHeight = 1.5.em,
                style =  MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }

    @Preview(widthDp = 468, heightDp = 1433)
    @Composable
    private fun AndroidPanelPreview() {
        AndroidPanel(Modifier)
    }
}