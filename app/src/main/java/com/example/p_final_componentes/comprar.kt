package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Hashtable
import java.util.Locale
// 🚨 NUEVA IMPORTACIÓN DE COIL 🚨
import coil.compose.AsyncImage

class comprar : AppCompatActivity() {

    private val URL_DETALLE_PELICULA = "http://192.168.20.35/androidComponentes/obtener_detalle_pelicula.php"
    private lateinit var requestQueue: RequestQueue

    // Estados reactivos: usamos el objeto Pelicula de Java
    private var peliculaState by mutableStateOf(Pelicula())
    private val isLoadingState = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestQueue = Volley.newRequestQueue(this)

        val movieId = intent.getIntExtra("MOVIE_ID", 1)

        peliculaState.setTitulo("Cargando Película ID: $movieId")
        peliculaState.setId_pelicula(movieId)

        fetchPeliculaDetails(movieId)

        setContentView(R.layout.activity_comprar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render)
        composeView.setContent {
            MaterialTheme {
                Compra(
                    pelicula = peliculaState,
                    isLoading = isLoadingState.value
                )
            }
        }
    }

    private fun fetchPeliculaDetails(movieId: Int) {
        isLoadingState.value = true
        Log.d("ComprarActivity", "Fetching details for ID: $movieId")

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_DETALLE_PELICULA,
            Response.Listener { response ->
                isLoadingState.value = false
                Log.d("ComprarActivity", "Respuesta PHP Detalle: $response")

                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")

                    if (success) {
                        val peliculaJson = jsonResponse.getJSONObject("pelicula")

                        val nuevaPelicula = Pelicula()

                        nuevaPelicula.setId_pelicula(peliculaJson.getInt("id_pelicula"))
                        nuevaPelicula.setTitulo(peliculaJson.getString("titulo"))
                        nuevaPelicula.setDescripcion(peliculaJson.getString("descripcion"))
                        nuevaPelicula.setAnio_lanzamiento(peliculaJson.getInt("anio_lanzamiento"))
                        nuevaPelicula.setDuracion(peliculaJson.getInt("duracion"))
                        nuevaPelicula.setClasificacion(peliculaJson.getString("clasificacion"))
                        nuevaPelicula.setIdioma(peliculaJson.getString("idioma"))
                        nuevaPelicula.setImagen_url(peliculaJson.getString("imagen_url")) // URL de la imagen
                        nuevaPelicula.setPrecio_alquiler(peliculaJson.getDouble("precio_alquiler"))
                        nuevaPelicula.setCopias_disponibles(peliculaJson.getInt("copias_disponibles"))

                        peliculaState = nuevaPelicula
                    } else {
                        val message = jsonResponse.getString("message")
                        peliculaState.setTitulo("Error: $message")
                        Toast.makeText(this, "❌ Error: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error de parseo JSON o película no encontrada: ${e.message}"
                    peliculaState.setTitulo("Error de datos")
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("ComprarActivity", "Error de parseo: $errorMsg", e)
                }
            },
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false
                val errorMsg = "Error de red: ${volleyError.message ?: "Verifique la IP y el servidor."}"
                peliculaState.setTitulo("Error de red/servidor")
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("ComprarActivity", "Error Volley: $errorMsg", volleyError)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = Hashtable()
                parametros["id_pelicula"] = movieId.toString()
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }
}

// 4. Composable Compra adaptado para usar el objeto Pelicula de Java
@Composable
fun Compra(pelicula: Pelicula, isLoading: Boolean, modifier: Modifier = Modifier) {
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
            if (isLoading) {
                // Mostrar indicador de carga
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xffe50914)
                )
            } else {
                // --- CONTENIDO PRINCIPAL ---
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 46.73.dp, y = 43.dp)
                        .requiredWidth(width = 300.dp)
                        .requiredHeight(height = 450.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                        .shadow(elevation = 50.dp, shape = RoundedCornerShape(10.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 450.dp)
                    ) {
                        // 🚨 BLOQUE MODIFICADO: Ahora usa AsyncImage y la URL del objeto Pelicula 🚨
                        AsyncImage(
                            model = pelicula.getImagen_url(), // Usamos la URL que viene del servidor
                            contentDescription = pelicula.getTitulo(),
                            contentScale = ContentScale.Crop, // Usar Crop es mejor para posters
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp) // Ajustamos a la altura del contenedor
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.01.dp, y = 498.01.dp)
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
                                // Datos Dinámicos: Título
                                Text(
                                    text = pelicula.getTitulo() ?: "N/A",
                                    color = Color.White,
                                    lineHeight = 1.2.em,
                                    style = TextStyle(fontSize = 30.sp),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = (-3.1).dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 23.dp)
                            ) {
                                // Datos Dinámicos: Año
                                Text(
                                    text = pelicula.getAnio_lanzamiento().toString(),
                                    color = Color(0xff99a1af),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 13.dp, y = (-1.26).dp)
                                )
                                // Datos Dinámicos: Duración
                                Text(
                                    text = "${pelicula.getDuracion()} min",
                                    color = Color(0xff99a1af),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 117.dp, y = 0.dp)
                                )
                                // Datos Dinámicos: Clasificación
                                Text(
                                    text = pelicula.getClasificacion() ?: "N/A",
                                    color = Color.White,
                                    lineHeight = 1.33.em,
                                    style = TextStyle(fontSize = 13.sp),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 77.15.dp, y = 0.dp)
                                )
                            }
                        }
                        Snackbar(
                            containerColor = Color(0xffe7000b),
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            // Datos Dinámicos: Precio
                            Text(
                                text = "Rentar por \$${String.format(Locale.US, "%.2f", pelicula.getPrecio_alquiler())}",
                                color = Color.White,
                                lineHeight = 1.56.em,
                                style = TextStyle(fontSize = 18.sp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    // Datos Dinámicos: Copias
                    NumeroDeCopias(
                        disponibles = pelicula.getCopias_disponibles(),
                        // Ojo: Si quieres el total de copias del servidor, debes traer 'ncopias' en PHP
                        // Por ahora, estoy usando una simulación para no depender de más cambios en el PHP
                        totalCopias = pelicula.getCopias_disponibles() + 5,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 1.dp, y = 108.02.dp)
                    )
                    Text(
                        text = pelicula.getDescripcion() ?: "Disponible para compra", // Usamos la descripción o un texto fijo
                        color = Color(0xff99a1af),
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp, y = 180.dp)
                    )
                }
            }
        }
        // Elementos estáticos de la interfaz
        Text(
            text = "RewindCodeFilm",
            color = Color(0xffe50914),
            lineHeight = 1.35.em,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 9.99.dp, y = 7.5.dp)
                .requiredWidth(width = 204.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.99.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 319.01.dp, y = 12.dp)
                .requiredWidth(width = 58.dp)
                .requiredHeight(height = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_1n),
                contentDescription = "Icon",
                modifier = Modifier.requiredSize(size = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.iconn),
                contentDescription = "Icon",
                modifier = Modifier.requiredSize(size = 20.dp)
            )
        }
    }
}

// 5. Composable NumeroDeCopias (Sin cambios)
@Composable
fun NumeroDeCopias(disponibles: Int, totalCopias: Int, modifier: Modifier = Modifier) {
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
                    .offset(x = 12.83.dp, y = 12.83.dp)
                    .requiredWidth(width = 91.dp)
                    .requiredHeight(height = 41.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_3n),
                    contentDescription = "Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
                Column {
                    Text(
                        text = "Disponibles",
                        color = Color.White.copy(alpha = 0.6f),
                        lineHeight = 1.33.em,
                        style = TextStyle(fontSize = 13.sp)
                    )
                    Text(
                        text = disponibles.toString(),
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 136.46.dp, y = 12.83.dp)
                    .requiredWidth(width = 110.dp)
                    .requiredHeight(height = 41.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_2n),
                    contentDescription = "Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
                Column {
                    Text(
                        text = "Total copias",
                        color = Color.White.copy(alpha = 0.6f),
                        lineHeight = 1.33.em,
                        style = TextStyle(fontSize = 13.sp)
                    )
                    Text(
                        text = totalCopias.toString(),
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 119.47.dp, y = 17.49.dp)
                    .requiredWidth(width = 1.dp)
                    .requiredHeight(height = 32.dp)
                    .background(color = Color.White.copy(alpha = 0.2f)))
        }
    }
}


@Preview(widthDp = 393, heightDp = 803)
@Composable
private fun CompraPreview() {
    val mockPelicula = Pelicula().apply {
        setId_pelicula(1)
        setTitulo("Película de Prueba (Mock)")
        setDescripcion("Una breve sinopsis de la película.")
        setAnio_lanzamiento(2025)
        setDuracion(113)
        setClasificacion("13+")
        setIdioma("ES")
        // URL de prueba para el Preview
        setImagen_url("https://github.com/felipec51/ProyectoFinalWEB/blob/main/imgs/thecrow.webp?raw=true")
        setPrecio_alquiler(19.99)
        setCopias_disponibles(3)
    }

    // Pasamos el objeto mock al Composable principal
    Compra(pelicula = mockPelicula, isLoading = false)
}