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
}
@Composable
fun Container(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 672.dp)
    ) {
        Text(
            text = "Tráileres y más",
            color = Color.White,
            lineHeight = 1.33.em,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 13.78.dp,
                    y = 8.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = (-1).dp,
                    y = 55.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 273.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff1d1d1d))
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
            Text(
                text = "Happy Gilmore 2: Tráiler oficial",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.99.dp,
                        y = 236.3.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = (-1).dp,
                    y = 344.26.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 273.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff1d1d1d))
        ) {
            Image(
                painter = painterResource(id = R.drawable.image22),
                contentDescription = "image 22",
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(x = (-0.21).dp,
                        y = (-26.09).dp)
                    .requiredWidth(width = 399.dp)
                    .requiredHeight(height = 225.dp))
            Text(
                text = "Detrás de cámaras",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.99.dp,
                        y = 236.3.dp))
        }
    }
}

@Preview(widthDp = 393, heightDp = 672)
@Composable
private fun ContainerPreview() {
    Container(Modifier)
}