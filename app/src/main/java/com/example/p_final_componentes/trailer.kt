package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

class trailer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trailer)
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
}@Composable
fun TrailersSection(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 617.dp)
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
                    .offset(x = 0.dp,
                        y = (-3.05).dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 563.dp)
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
                                .offset(x = (-10.99).dp,
                                    y = (-4.7).dp)
                                .requiredWidth(width = 412.dp)
                                .requiredHeight(height = 231.dp))
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
                                        .requiredSize(size = 24.dp))
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 345.64.dp,
                                y = 188.03.dp)
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
                                fontSize = 13.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 7.99.dp,
                                    y = 2.99.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 15.99.dp,
                            y = 237.3.dp)
                        .requiredWidth(width = 361.dp)
                        .requiredHeight(height = 20.dp)
                ) {
                    Text(
                        text = "Happy Gilmore 2: Tráiler oficial",
                        color = Color.White,
                        lineHeight = 1.43.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-0.95).dp))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 289.26.dp)
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
                                .offset(x = (-0.21).dp,
                                    y = (-0.11).dp)
                                .requiredWidth(width = 399.dp)
                                .requiredHeight(height = 225.dp))
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
                                        .requiredSize(size = 24.dp))
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 345.64.dp,
                                y = 188.03.dp)
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
                                fontSize = 13.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 7.99.dp,
                                    y = 2.99.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 15.99.dp,
                            y = 237.3.dp)
                        .requiredWidth(width = 361.dp)
                        .requiredHeight(height = 20.dp)
                ) {
                    Text(
                        text = "Detrás de cámaras",
                        color = Color.White,
                        lineHeight = 1.43.em,
                        style = TextStyle(
                            fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = (-0.95).dp))
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 617)
@Composable
private fun TrailersSectionPreview() {
    TrailersSection(Modifier)
}