package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
}


@Composable
fun Component1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 281.dp)
            .requiredHeight(height = 717.dp)
            .background(color = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 9.dp,
                    y = 6.dp)
                .requiredWidth(width = 42.dp)
                .requiredHeight(height = 35.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = Color(0xffe50914)))
        Text(
            text = "RewindCodeFilm",
            color = Color(0xffe50914),
            lineHeight = 1.5.em,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 148.dp,
                    y = 8.3.dp))
        Text(
            text = "Admin Panel",
            color = Color(0xff99a1af),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 14.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 58.dp,
                    y = 10.8.dp))
        Text(
            text = "R",
            color = Color.White,
            lineHeight = 1.5.em,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.95.dp,
                    y = 11.3.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 51.dp)
                .requiredWidth(width = 275.dp)
                .requiredHeight(height = 121.dp)
        ) {
            Text(
                text = "Gestión",
                color = Color(0xff99a1af),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 9.98.dp,
                        y = 14.48.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.02.dp,
                        y = 33.48.dp)
                    .requiredWidth(width = 271.dp)
                    .requiredHeight(height = 32.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .padding(start = 7.9831929206848145.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .requiredSize(size = 16.dp))
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
                            fontSize = 14.sp),
                        modifier = Modifier
                            .requiredWidth(width = 72.dp))
                }
            }
            Text(
                text = "Películas",
                color = Color(0xffd1d5dc),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.dp,
                        y = 77.dp))
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Icon",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 7.98.dp,
                        y = 78.98.dp)
                    .requiredSize(size = 16.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 1.dp,
                    y = 648.dp)
                .requiredWidth(width = 271.dp)
                .requiredHeight(height = 68.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 271.dp)
                    .requiredHeight(height = 32.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.98.dp, Alignment.Start),
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
                            .requiredSize(size = 16.dp))
                    Text(
                        text = "Configuración",
                        color = Color(0xffd1d5dc),
                        lineHeight = 1.43.em,
                        style = TextStyle(
                            fontSize = 14.sp))
                }
            }
            Text(
                text = "Admin",
                color = Color.White,
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 39.95.dp,
                        y = 32.32.dp)
                    .requiredWidth(width = 119.dp))
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 7.98.dp,
                        y = 39.99.dp)
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
                            fontSize = 14.sp))
                }
            }
            Text(
                text = "admin@rewindcodefilm.com",
                color = Color(0xff99a1af),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 41.48.dp,
                        y = 47.48.dp))
        }
    }
}

@Preview(widthDp = 281, heightDp = 717)
@Composable
private fun Component1Preview() {
    Component1(Modifier)
}