package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap

class editpeli : AppCompatActivity() {

    private val URL_BASE = "http://192.168.20.35/androidComponentes/"
    private val URL_ACTUALIZAR_PELICULA = URL_BASE + "actualizar_pelicula.php"
    private lateinit var requestQueue: RequestQueue
    private lateinit var initialPelicula: Pelicula
    private val isLoading = mutableStateOf(false)
    private val tituloState = mutableStateOf("")
    private val anioState = mutableStateOf("")
    private val ratingState = mutableStateOf("") // Mapea a 'clasificacion'
    private val idiomaState = mutableStateOf("") // Mapea a 'idioma'
    private val descripcionState = mutableStateOf("")
    private val duracionState = mutableStateOf("")
    private val posterUrlState = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //  RECUPERAR EL OBJETO PELICULA
        val peliculaSerializable = intent.getSerializableExtra("pelicula_data")
        if (peliculaSerializable is Pelicula) {
            initialPelicula = peliculaSerializable

            // INICIALIZAR LOS ESTADOS CON LOS DATOS DEL OBJETO
            tituloState.value = initialPelicula.titulo
            anioState.value = initialPelicula.anio_lanzamiento.toString()
            ratingState.value = initialPelicula.clasificacion
            idiomaState.value = initialPelicula.idioma
            descripcionState.value = initialPelicula.descripcion
            duracionState.value = initialPelicula.duracion.toString()
            posterUrlState.value = initialPelicula.imagen_url

        } else {
            Toast.makeText(this, "Error: No se pudo cargar el objeto Película.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        requestQueue = Volley.newRequestQueue(this)

        setContent {
            MaterialTheme {
                FormAeditarPelicula(
                    id = initialPelicula.id_pelicula,
                    titulo = tituloState.value,
                    onTituloChange = { tituloState.value = it },
                    anio = anioState.value,
                    onAnioChange = { anioState.value = it },
                    rating = ratingState.value,
                    onRatingChange = { ratingState.value = it },
                    idioma = idiomaState.value,
                    onIdiomaChange = { idiomaState.value = it },
                    descripcion = descripcionState.value,
                    onDescripcionChange = { descripcionState.value = it },
                    duracion = duracionState.value,
                    onDuracionChange = { duracionState.value = it },
                    posterUrl = posterUrlState.value,
                    onPosterUrlChange = { posterUrlState.value = it },
                    onSaveClick = { updatePelicula() },
                    onCancelClick = { finish() },
                    isLoading = isLoading.value
                )
            }
        }
    }

    private fun updatePelicula() {
        if (tituloState.value.isBlank() || anioState.value.isBlank()) {
            Toast.makeText(this, "El título y el año son obligatorios.", Toast.LENGTH_SHORT).show()
            return
        }

        isLoading.value = true

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_ACTUALIZAR_PELICULA,
            { response ->
                isLoading.value = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                    if (success) {
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al procesar respuesta de actualización.", Toast.LENGTH_LONG).show()
                    Log.e("EditPeli", "Error de parseo JSON al actualizar: ${e.message}", e)
                }
            },
            { volleyError ->
                isLoading.value = false
                val errorMsg = "Error de red al actualizar: ${volleyError.message ?: "Verifique la conexión."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("EditPeli", "Error Volley Actualizar: ${volleyError.message}")
            }) {

            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["id_pelicula"] = initialPelicula.id_pelicula.toString()
                params["titulo"] = tituloState.value
                params["anio"] = anioState.value
                params["calificacion"] = ratingState.value
                params["idioma"] = idiomaState.value
                params["descripcion"] = descripcionState.value
                params["duracion_min"] = duracionState.value
                params["poster_path"] = posterUrlState.value

                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}

@Composable
fun FormAeditarPelicula(
    id: Int,
    titulo: String,
    onTituloChange: (String) -> Unit,
    anio: String,
    onAnioChange: (String) -> Unit,
    rating: String,
    onRatingChange: (String) -> Unit,
    idioma: String,
    onIdiomaChange: (String) -> Unit,
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    duracion: String,
    onDuracionChange: (String) -> Unit,
    posterUrl: String,
    onPosterUrlChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xff141414),
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            Text(
                text = "Editar Película ID: $id",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Text(
                text = "Modifique los detalles de la película.",
                color = Color(0xff99a1af),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xffe50914))
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

                    OutlinedTextFieldWithLabel("Título", titulo, onTituloChange)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextFieldWithLabel(
                            label = "Año",
                            value = anio,
                            onValueChange = onAnioChange,
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            keyboardType = KeyboardType.Number
                        )
                        OutlinedTextFieldWithLabel(
                            label = "Rating (Clasificación)",
                            value = rating,
                            onValueChange = onRatingChange,
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextFieldWithLabel(
                            label = "Idioma",
                            value = idioma,
                            onValueChange = onIdiomaChange,
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )
                        OutlinedTextFieldWithLabel(
                            label = "Duración (Minutos)",
                            value = duracion,
                            onValueChange = onDuracionChange,
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
                            keyboardType = KeyboardType.Number
                        )
                    }

                    OutlinedTextFieldWithLabel(
                        label = "Descripción",
                        value = descripcion,
                        onValueChange = onDescripcionChange,
                        singleLine = false,
                        maxLines = 4
                    )

                    OutlinedTextFieldWithLabel("URL del Póster", posterUrl, onPosterUrlChange)
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = onSaveClick,
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe50914)),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("Guardar Cambios")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onCancelClick,
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F2F2F)),
                    border = BorderStroke(1.dp, Color(0xFF2F2F2F)),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("Cancelar", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun OutlinedTextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color(0xffd1d5dc),
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xff2f2f2f),
                unfocusedContainerColor = Color(0xff2f2f2f),
                cursorColor = Color.White,
                focusedIndicatorColor = Color(0xffe50914),
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledContainerColor = Color(0xff2f2f2f),
                disabledTextColor = Color.Gray,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth().heightIn(min = 50.dp, max = if(maxLines > 1) 120.dp else 50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEditPelicula() {
    FormAeditarPelicula(
        id = 42,
        titulo = "Stranger Things 5",
        onTituloChange = {},
        anio = "2025",
        onAnioChange = {},
        rating = "PG-13",
        onRatingChange = {},
        idioma = "Inglés",
        onIdiomaChange = {},
        descripcion = "La última temporada de la serie de ciencia ficción de Netflix. Incluye el regreso de Vecna y los niños de Hawkins.",
        onDescripcionChange = {},
        duracion = "140",
        onDuracionChange = {},
        posterUrl = "https://example.com/poster.jpg",
        onPosterUrlChange = {},
        onSaveClick = {},
        onCancelClick = {},
        isLoading = false
    )
}