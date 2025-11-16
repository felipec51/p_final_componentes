package com.example.p_final_componentes

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView
import android.webkit.WebSettings
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
    }
}

@Composable
fun Trailer(
    peliculaId: Int = 0,
    trailers: List<String> = emptyList(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
    ) {
        Text(
            text = "Tráileres y más",
            color = Color.White,
            lineHeight = 1.33.em,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = 13.78.dp, bottom = 16.dp)
        )

        if (trailers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xff1d1d1d)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No hay tráilers disponibles",
                        color = Color(0xff99a1af),
                        fontSize = 14.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(trailers) { trailerUrl ->
                    TrailerItem(
                        trailerUrl = trailerUrl,
                        index = trailers.indexOf(trailerUrl) + 1
                    )
                }
            }
        }
    }
}

@Composable
fun TrailerItem(
    trailerUrl: String,
    index: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xff1d1d1d))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // WebView para mostrar el video de YouTube
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.apply {
                            javaScriptEnabled = true
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            domStorageEnabled = true
                            mediaPlaybackRequiresUserGesture = false
                        }

                        // Extraer el ID del video de YouTube
                        val videoId = trailerUrl.substringAfterLast("/")

                        // HTML para incrustar el video
                        val html = """
                            <html>
                            <body style="margin:0; padding:0; background-color:#1d1d1d;">
                                <iframe 
                                    width="100%" 
                                    height="100%" 
                                    src="$trailerUrl" 
                                    frameborder="0" 
                                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                    allowfullscreen
                                    style="display:block;">
                                </iframe>
                            </body>
                            </html>
                        """.trimIndent()

                        loadData(html, "text/html", "utf-8")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Text(
                text = "Tráiler #$index",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(widthDp = 393, heightDp = 672)
@Composable
private fun TrailerPreview() {
    Trailer(
        peliculaId = 1,
        trailers = listOf(
            "https://www.youtube.com/embed/PssKpzB0Ah0",
            "https://www.youtube.com/embed/d0JYlUTbv1A"
        )
    )
}