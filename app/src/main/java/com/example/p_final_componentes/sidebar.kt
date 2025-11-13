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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class sidebar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sidebar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Se busca el contenedor de Compose por su ID
        val composeView = findViewById<ComposeView>(R.id.render)


        composeView.setContent {

            MaterialTheme {
                sidebar()
            }
        }
    }

    @Composable
    fun Sidebar(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 468.dp)
                .requiredHeight(height = 920.dp)
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
                            .requiredHeight(height = 218.dp)
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
                                .requiredHeight(height = 235.dp)
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
                                    painter = painterResource(id = R.drawable.rectangle_33_1),
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
                                        y = 129.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 218.dp,
                                        y = 129.dp
                                    )
                                    .requiredWidth(width = 200.dp)
                                    .requiredHeight(height = 106.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 869.5677528381348.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 0.00006103515625.dp)
            ) {
                Column(
                    modifier = Modifier
                        .requiredWidth(width = 287.dp)
                        .requiredHeight(height = 94.dp)
                        .background(color = Color.Black)
                        .padding(
                            start = 7.9831929206848145.dp,
                            top = 7.9831929206848145.dp
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(width = 271.dp)
                            .requiredHeight(height = 77.dp)
                            .padding(start = 7.9831929206848145.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .requiredSize(size = 40.dp)
                                .clip(shape = MaterialTheme.shapes.small)
                                .background(color = Color(0xffe50914))
                                .padding(end = 9.5367431640625e-7.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 12.dp)
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "R",
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
                            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                            modifier = Modifier
                                .requiredWidth(width = 78.dp)
                                .requiredHeight(height = 45.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 78.dp)
                                    .requiredHeight(height = 24.dp)
                            ) {
                                Text(
                                    text = "RewindCodeFilm",
                                    color = Color(0xffe50914),
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
                                    .requiredWidth(width = 78.dp)
                                    .requiredHeight(height = 19.dp)
                            ) {
                                Text(
                                    text = "Admin Panel",
                                    color = Color(0xff99a1af),
                                    lineHeight = 1.33.em,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 287.dp)
                        .requiredHeight(height = 741.dp)
                        .background(color = Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 0.29.dp
                            )
                            .requiredWidth(width = 287.dp)
                            .requiredHeight(height = 108.dp)
                            .padding(
                                start = 7.9831929206848145.dp,
                                top = 7.983192443847656.dp
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 32.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Navegación",
                                color = Color(0xff99a1af),
                                lineHeight = 1.33.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 140.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 271.dp)
                                    .requiredHeight(height = 32.dp)
                            ) {
                                InputChip(
                                    label = {
                                        Text(
                                            text = "Inicio",
                                            color = Color(0xff171717),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            )
                                        )
                                    },
                                    leadingIcon = {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .requiredSize(size = 16.dp)
                                        )
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = Color(0xfff5f5f5)
                                    ),
                                    selected = true,
                                    onClick = { })
                            }
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 0.dp,
                                        y = 35.98.dp
                                    )
                                    .requiredWidth(width = 271.dp)
                                    .requiredHeight(height = 32.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        7.98.dp,
                                        Alignment.Start
                                    ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredWidth(width = 271.dp)
                                        .requiredHeight(height = 32.dp)
                                        .clip(shape = RoundedCornerShape(8.dp))
                                        .padding(start = 7.9831929206848145.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icon),
                                        contentDescription = "Icon",
                                        modifier = Modifier
                                            .requiredSize(size = 16.dp)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 65.dp)
                                            .requiredHeight(height = 20.dp)
                                    ) {
                                        Text(
                                            text = "Películas",
                                            color = Color(0xffd1d5dc),
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
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 1.dp,
                                y = 108.29.dp
                            )
                            .requiredWidth(width = 287.dp)
                            .requiredHeight(height = 152.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 7.98.dp,
                                    y = 7.98.dp
                                )
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 32.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Gestión",
                                color = Color(0xff99a1af),
                                lineHeight = 1.33.em,
                                style = TextStyle(
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 8.dp,
                                    y = 40.dp
                                )
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 32.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 271.dp)
                                    .requiredHeight(height = 32.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 0.02.dp,
                                            y = 0.03.dp
                                        )
                                        .requiredWidth(width = 271.dp)
                                        .requiredHeight(height = 32.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(
                                            7.98.dp,
                                            Alignment.Start
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredWidth(width = 271.dp)
                                            .requiredHeight(height = 32.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .padding(start = 7.9831929206848145.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Icon",
                                            modifier = Modifier
                                                .requiredSize(size = 16.dp)
                                        )
                                        Row(
                                            modifier = Modifier
                                                .requiredWidth(width = 70.dp)
                                                .requiredHeight(height = 20.dp)
                                        ) {
                                            Text(
                                                text = "Usuarios",
                                                color = Color(0xffd1d5dc),
                                                lineHeight = 1.43.em,
                                                style = TextStyle(
                                                    fontSize = 14.sp
                                                ),
                                                modifier = Modifier
                                                    .requiredWidth(width = 72.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .requiredWidth(width = 287.dp)
                        .requiredHeight(height = 85.dp)
                        .background(color = Color.Black)
                        .padding(
                            start = 7.9831929206848145.dp,
                            top = 7.983187675476074.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 271.dp)
                            .requiredHeight(height = 68.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 32.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    7.98.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .requiredWidth(width = 271.dp)
                                    .requiredHeight(height = 32.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .padding(start = 7.9831929206848145.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon),
                                    contentDescription = "Icon",
                                    modifier = Modifier
                                        .requiredSize(size = 16.dp)
                                )
                                Row(
                                    modifier = Modifier
                                        .requiredWidth(width = 87.dp)
                                        .requiredHeight(height = 20.dp)
                                ) {
                                    Text(
                                        text = "Configuración",
                                        color = Color(0xffd1d5dc),
                                        lineHeight = 1.43.em,
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 35.98.dp
                                )
                                .requiredWidth(width = 271.dp)
                                .requiredHeight(height = 32.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 271.dp)
                                    .requiredHeight(height = 32.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 39.95.dp,
                                            y = (-3.66).dp
                                        )
                                        .requiredWidth(width = 119.dp)
                                        .requiredHeight(height = 39.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 119.dp)
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "Admin",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .requiredWidth(width = 119.dp)
                                            .requiredHeight(height = 19.dp)
                                    ) {
                                        Text(
                                            text = "admin@rewindcodefilm.com",
                                            color = Color(0xff99a1af),
                                            lineHeight = 1.33.em,
                                            style = TextStyle(
                                                fontSize = 14.sp
                                            )
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(
                                            x = 7.98.dp,
                                            y = 4.01.dp
                                        )
                                        .requiredSize(size = 24.dp)
                                        .clip(shape = RoundedCornerShape(37596000.dp))
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .requiredHeight(height = 24.dp)
                                            .clip(shape = RoundedCornerShape(37596000.dp))
                                            .background(color = Color(0xffe50914))
                                    ) {
                                        Text(
                                            text = "AD",
                                            color = Color.White,
                                            lineHeight = 1.33.em,
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
        }
    }

    @Preview(widthDp = 468, heightDp = 920)
    @Composable
    private fun SidebarPreview() {
        Sidebar(Modifier)
    }
}