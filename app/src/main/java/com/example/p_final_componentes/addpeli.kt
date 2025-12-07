package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Hashtable


class addpeli : AppCompatActivity() {

    private val URL_ADD_PELICULA = "${ApiConfig.BASE_URL}/add_pelicula.php"

    private val isLoadingState = mutableStateOf(false)
    private val errorMessageState = mutableStateOf<String?>(null)
    private lateinit var requestQueue: RequestQueue

    private fun attemptAddPelicula(
        titulo: String,
        anio_lanzamiento: String,
        duracion: String,
        descripcion: String,
        imagen_url: String,
        precio_alquiler: String,
        clasificacion: String,
        director_id_director: String,
        idioma: String,
        ncopias: String,
        actores_ids: String,
        generos_ids: String
    ) {
        isLoadingState.value = true
        errorMessageState.value = null

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_ADD_PELICULA,
            // Listener de Éxito
            Response.Listener { response ->
                isLoadingState.value = false
                Log.d("AddPeliculaActivity", "Respuesta PHP: $response")

                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    if (success) {
                        Toast.makeText(this, "✅ $message", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        errorMessageState.value = message
                        Toast.makeText(this, "❌ Error: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error de parseo JSON o respuesta inesperada: $response"
                    errorMessageState.value = errorMsg
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("AddPeliculaActivity", "Error de parseo: ${e.message}", e)
                }
            },
            // Listener de Error
            Response.ErrorListener { volleyError ->
                isLoadingState.value = false

                val networkResponse = volleyError.networkResponse
                val statusCode = networkResponse?.statusCode ?: -1
                val responseBody = networkResponse?.data?.let {
                    try {
                        String(it, Charsets.UTF_8)
                    } catch (e: Exception) {
                        "Cuerpo no legible o binario"
                    }
                } ?: "N/A"

                Log.e("AddPeliculaActivity", "Error Volley DETALLADO: Código $statusCode, Cuerpo: '$responseBody', Mensaje: ${volleyError.message}")

                val errorMsg = if (statusCode != -1) {
                    "Error de Servidor (HTTP $statusCode). Ver logcat para detalles de PHP."
                } else {
                    "Error de red: ${volleyError.message ?: "Verifique la IP y el servidor."}"
                }

                errorMessageState.value = errorMsg
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        ) {
            // Envío de parámetros POST
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = Hashtable()

                parametros["titulo"] = titulo
                parametros["anio"] = anio_lanzamiento
                parametros["duracion_min"] = duracion
                parametros["descripcion"] = descripcion
                parametros["poster_path"] = imagen_url
                parametros["precio_alquiler"] = precio_alquiler
                parametros["calificacion"] = clasificacion
                parametros["director_id_director"] = director_id_director
                parametros["idioma"] = idioma
                parametros["ncopias"] = ncopias
                parametros["actores_ids"] = actores_ids
                parametros["generos_ids"] = generos_ids

                Log.d("AddPeliculaActivity", "Parámetros POST enviados: $parametros")

                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestQueue = Volley.newRequestQueue(this)

        setContentView(R.layout.activity_addpeli)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val composeView = findViewById<ComposeView>(R.id.render) // Asumiendo que tu ComposeView tiene id R.id.render
        composeView.setContent {
            MaterialTheme {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xFF141414))) {
                    FormAadirPelicula(
                        isLoading = isLoadingState.value,
                        errorMessage = errorMessageState.value,
                        onClearError = { errorMessageState.value = null },
                        onAddPelicula = { tit, anioLan, dur, desc, imgUrl, prec, clasif, dirId, idiom, ncop, actIds, genIds ->
                            attemptAddPelicula(tit, anioLan, dur, desc, imgUrl, prec, clasif, dirId, idiom, ncop, actIds, genIds)
                        },
                        onCancel = { finish() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun FormAadirPelicula(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    onClearError: () -> Unit,
    onAddPelicula: (
        String, String, String, String, String, String, String, String, String,
        String, // ncopias
        String, String // actIds, genIds
    ) -> Unit,
    onCancel: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var anioLanzamiento by remember { mutableStateOf("") }
    var duracionMinutos by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenURL by remember { mutableStateOf("") }
    var precioAlquiler by remember { mutableStateOf("") }
    var clasificacionPeli by remember { mutableStateOf("") }
    var directorId by remember { mutableStateOf("") }
    var idioma by remember { mutableStateOf("") }
    var nCopias by remember { mutableStateOf("") } // <--- NUEVO ESTADO
    var actoresInput by remember { mutableStateOf("") }
    var generosInput by remember { mutableStateOf("") }
    //validaciones
    val isFormValid = titulo.isNotEmpty() && anioLanzamiento.isNotEmpty() && duracionMinutos.isNotEmpty() &&
            descripcion.isNotEmpty() && imagenURL.isNotEmpty() && precioAlquiler.isNotEmpty() &&
            clasificacionPeli.isNotEmpty() && directorId.isNotEmpty() && idioma.isNotEmpty() &&
            nCopias.isNotEmpty() && // <--- NUEVO CHEQUEO
            actoresInput.isNotEmpty() && generosInput.isNotEmpty()

    if (errorMessage != null) {
        LaunchedEffect(errorMessage) {
            onClearError()
        }
    }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xff141414),
        border = BorderStroke(1.12.dp, Color(0xff2f2f2f)),
        modifier = modifier
            .fillMaxWidth(0.95f)
            .clip(shape = RoundedCornerShape(10.dp))
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Agregar Nueva Película",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            Text(
                text = "Complete los detalles de la película para agregar al catálogo",
                color = Color(0xff99a1af),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            )

            InputFieldPeli(label = "Título (*)", value = titulo, onValueChange = { titulo = it })

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                InputFieldPeli(
                    label = "Año Lanzamiento (*)", value = anioLanzamiento,
                    onValueChange = { anioLanzamiento = it.filter { it.isDigit() } },
                    modifier = Modifier.weight(1f), keyboardType = KeyboardType.Number
                )
                InputFieldPeli(
                    label = "Duración (min) (*)", value = duracionMinutos,
                    onValueChange = { duracionMinutos = it.filter { it.isDigit() } },
                    modifier = Modifier.weight(1f), keyboardType = KeyboardType.Number
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                InputFieldPeli(
                    label = "Clasificación (*)", value = clasificacionPeli,
                    onValueChange = { clasificacionPeli = it },
                    modifier = Modifier.weight(1f)
                )
                InputFieldPeli(
                    label = "Precio Alquiler (*)", value = precioAlquiler,
                    onValueChange = { precioAlquiler = it.filter { it.isDigit() || it == '.' } },
                    modifier = Modifier.weight(1f), keyboardType = KeyboardType.Decimal
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                InputFieldPeli(
                    label = "ID Director (FK) (*)", value = directorId,
                    onValueChange = { directorId = it.filter { it.isDigit() } },
                    modifier = Modifier.weight(1f), keyboardType = KeyboardType.Number
                )
                InputFieldPeli(
                    label = "Idioma (*)", value = idioma,
                    onValueChange = { idioma = it },
                    modifier = Modifier.weight(1f)
                )
            }

            // <--- INICIO DEL NUEVO CAMPO: ncopias --->
            InputFieldPeli(
                label = "Número de Copias (*)",
                value = nCopias,
                onValueChange = { nCopias = it.filter { it.isDigit() } },
                keyboardType = KeyboardType.Number
            )
            // <--- FIN DEL NUEVO CAMPO --->

            InputFieldPeli(label = "URL del Póster (imagen_url) (*)", value = imagenURL, onValueChange = { imagenURL = it })
            InputFieldPeli(label = "Descripción (*)", value = descripcion, onValueChange = { descripcion = it })


            // --- CAMPOS DE RELACIÓN ---

            Text(
                text = "Relaciones (IDs separadas por comas)",
                color = Color(0xff99a1af),
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )

            // IDs de Actores (RELACIÓN)
            InputFieldPeli(
                label = "IDs de Actores (*)",
                value = actoresInput,
                onValueChange = { actoresInput = it },
                keyboardType = KeyboardType.Text
            )

            // IDs de Géneros (RELACIÓN)
            InputFieldPeli(
                label = "IDs de Géneros (*)",
                value = generosInput,
                onValueChange = { generosInput = it },
                keyboardType = KeyboardType.Text
            )


            Spacer(modifier = Modifier.height(8.dp))

            // BOTONES
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Botón AGREGAR PELÍCULA
                Button(
                    onClick = {
                        // 3. PASAR LOS 12 PARÁMETROS A LA FUNCIÓN DE LA ACTIVITY
                        onAddPelicula(
                            titulo,
                            anioLanzamiento,
                            duracionMinutos,
                            descripcion,
                            imagenURL,
                            precioAlquiler,
                            clasificacionPeli,
                            directorId,
                            idioma,
                            nCopias, // <--- ENVIAMOS LA NUEVA COPIA
                            actoresInput.trim(),
                            generosInput.trim()
                        )
                    },
                    enabled = !isLoading && isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffe50914),
                        disabledContainerColor = Color(0x80e50914)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(36.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Text("Agregar Película", color = Color.White, fontSize = 14.sp)
                    }
                }

                // Botón CANCELAR
                OutlinedButton(
                    onClick = onCancel,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color(0xff2f2f2f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(36.dp)
                ) {
                    Text("Cancelar", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun InputFieldPeli(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier.padding(bottom = 4.dp)) {
        Text(text = label, color = Color(0xffd1d5dc), modifier = Modifier.padding(bottom = 4.dp), style = TextStyle(fontSize = 14.sp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xff2f2f2f),
                unfocusedContainerColor = Color(0xff2f2f2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xffe50914),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@Preview(widthDp = 390, heightDp = 1000)
@Composable
private fun FormAadirPeliculaPreview() {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF141414))) {
        FormAadirPelicula(
            isLoading = false,
            errorMessage = null,
            onClearError = {},
            onAddPelicula = { _, _, _, _, _, _, _, _, _, _, _, _ -> },
            onCancel = {},
            modifier = Modifier.align(Alignment.Center)
        )
    }
}