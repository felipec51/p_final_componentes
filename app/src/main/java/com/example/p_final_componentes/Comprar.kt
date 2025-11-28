package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Hashtable
import java.util.Locale

class Comprar : AppCompatActivity() {

    private val URL_DETALLE_PELICULA =
        "http://192.168.20.35/androidComponentes/obtener_detalle_pelicula.php"

    private lateinit var requestQueue: RequestQueue
    private var peliculaState by mutableStateOf(Pelicula())
    private val isLoadingState = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestQueue = Volley.newRequestQueue(this)

        val movieId = intent.getIntExtra("MOVIE_ID", 1)
        val userId = intent.getIntExtra("USER_ID", 1)
        val userName = intent.getStringExtra("USER_NAME") ?: "Usuario"

        // Estado inicial
        peliculaState = Pelicula().apply {
            setTitulo("Cargando Película ID: $movieId")
            setId_pelicula(movieId)
        }

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
                // Se agrega un Box para que el fondo sea consistente
                // y se añade padding para bajar todo el contenido.
                Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp).background(color = Color(0xff1a1a1a))) {
                    Compra(
                        pelicula = peliculaState,
                        isLoading = isLoadingState.value,
                        userId = userId,
                        userName = userName,
                        modifier = Modifier.weight(1f)
                    )
                }
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
                        val nuevaPelicula = Pelicula().apply {
                            setId_pelicula(peliculaJson.getInt("id_pelicula"))
                            setTitulo(peliculaJson.getString("titulo"))
                            setDescripcion(peliculaJson.getString("descripcion"))
                            setAnio_lanzamiento(peliculaJson.getInt("anio_lanzamiento"))
                            setDuracion(peliculaJson.getInt("duracion"))
                            setClasificacion(peliculaJson.getString("clasificacion"))
                            setIdioma(peliculaJson.getString("idioma"))
                            setImagen_url(peliculaJson.getString("imagen_url"))
                            setPrecio_alquiler(peliculaJson.getDouble("precio_alquiler"))
                            setCopias_disponibles(peliculaJson.getInt("copias_disponibles"))
                            setcopiasTotales(peliculaJson.getInt("copias_totales"))
                            setGeneros_detalle(peliculaJson.optString("generos_detalle", "Géneros no especificados"))
                            setElenco(peliculaJson.optString("elenco", "Elenco no disponible"))
                            setDirector_nombre(peliculaJson.optString("director_nombre", "Director no disponible"))
                        }
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

@Composable
fun Compra(
    pelicula: Pelicula,
    isLoading: Boolean,
    userId: Int,
    userName: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xff1a1a1a))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 200.dp),
                    color = Color(0xffe50914)
                )
            } else {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .requiredWidth(width = 300.dp)
                        .height(450.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                        .shadow(elevation = 50.dp, shape = RoundedCornerShape(10.dp))
                ) {
                    AsyncImage(
                        model = pelicula.getImagen_url(),
                        contentDescription = pelicula.getTitulo(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 15.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = pelicula.getTitulo() ?: "N/A",
                        color = Color.White,
                        lineHeight = 1.2.em,
                        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 5.dp, bottom = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = pelicula.getAnio_lanzamiento().toString(),
                            color = Color(0xff99a1af),
                            style = TextStyle(fontSize = 14.sp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = pelicula.getClasificacion() ?: "N/A",
                            color = Color.White,
                            style = TextStyle(fontSize = 13.sp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "${pelicula.getDuracion()} min",
                            color = Color(0xff99a1af),
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }

                    Button(
                        onClick = {
                            try {
                                val intent = Intent(context, Rentar::class.java)
                                intent.putExtra("PELICULA_OBJ", pelicula)
                                intent.putExtra("USER_ID", userId)
                                intent.putExtra("USER_NAME", userName)
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al navegar: ${e.message}", Toast.LENGTH_SHORT).show()
                                Log.e("Comprar", "Error navigation", e)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffe7000b)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = "Rentar por \$${String.format(Locale.US, "%.2f", pelicula.getPrecio_alquiler())}",
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                    NumeroDeCopias(
                        disponibles = pelicula.getCopias_disponibles(),
                        totalCopias = pelicula.getcopiasTotales(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.Start)
                    )
                }
                MasInformacionDetalle(
                    pelicula = pelicula,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun NumeroDeCopias(disponibles: Int, totalCopias: Int, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.15f),
        border = BorderStroke(0.841.dp, Color.White.copy(alpha = 0.2f)),
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .height(67.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 13.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon_3n),
                    contentDescription = "Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Disponibles",
                        color = Color.White.copy(alpha = 0.6f),
                        style = TextStyle(fontSize = 13.sp)
                    )
                    Text(
                        text = disponibles.toString(),
                        color = Color.White,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(32.dp)
                    .background(color = Color.White.copy(alpha = 0.2f))
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon_2n),
                    contentDescription = "Icon",
                    modifier = Modifier.requiredSize(size = 16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Total copias",
                        color = Color.White.copy(alpha = 0.6f),
                        style = TextStyle(fontSize = 13.sp)
                    )
                    Text(
                        text = totalCopias.toString(),
                        color = Color.White,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }
        }
    }
}


@Preview(widthDp = 393, heightDp = 1503)
@Composable
private fun CompraPreview() {
    val mockPelicula = Pelicula().apply {
        setId_pelicula(1)
        setTitulo("El Cuervo Vengador")
        setDescripcion("Una breve sinopsis de la película.")
        setAnio_lanzamiento(1994)
        setDuracion(102)
        setClasificacion("R")
        setIdioma("EN")
        setImagen_url("https://via.placeholder.com/150")
        setPrecio_alquiler(19.99)
        setCopias_disponibles(3)
        setcopiasTotales(10)
        setGeneros_detalle("Acción")
        setElenco("Brandon Lee")
    }

    Compra(pelicula = mockPelicula, isLoading = false, userId = 1, userName = "Test User")
}
