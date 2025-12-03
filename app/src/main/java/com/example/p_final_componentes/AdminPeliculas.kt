package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.util.HashMap
import androidx.compose.runtime.snapshots.SnapshotStateList

class AdminPeliculas : AppCompatActivity() {

    // 1. CONFIGURACIÓN DE URLS (Asegúrate que la IP sea correcta)
    private val URL_BASE = "http://192.168.2.4/androidComponentes/"
    private val URL_OBTENER_PELICULAS = URL_BASE + "obtener_peliculas.php"
    private val URL_ELIMINAR_PELICULA = URL_BASE + "eliminar_pelicula.php"

    private lateinit var requestQueue: RequestQueue
    private val listadoPeliculas = mutableStateListOf<Pelicula>()
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestQueue = Volley.newRequestQueue(this)

        try {
            setContent {
                MaterialTheme {
                    AdminPeliculasView(
                        peliculas = listadoPeliculas,
                        isLoading = isLoading.value,
                        onUpdateClick = { pelicula ->
                            // 🚀 CAMBIO APLICADO: Pasar el objeto Pelicula completo
                            val intent = Intent(this, editpeli::class.java).apply {
                                // Usamos la clave "pelicula_data" y casteamos a Serializable
                                putExtra("pelicula_data", pelicula as java.io.Serializable)
                            }
                            startActivity(intent)
                        },
                        onDeleteClick = { pelicula ->
                            deletePelicula(pelicula)
                        }
                    )
                }
                Log.d("AdminPeliculas", "✅ setContent ejecutado exitosamente")
            }
        } catch (e: Exception) {
            Log.e("AdminPeliculas", "❌ ERROR al inicializar UI: ${e.message}", e)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchPeliculas()
    }


    // 2. FUNCIÓN PARA OBTENER PELÍCULAS
    private fun fetchPeliculas() {
        isLoading.value = true
        listadoPeliculas.clear()

        val stringRequest = StringRequest(
            Request.Method.GET, URL_OBTENER_PELICULAS,
            { response ->
                isLoading.value = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")

                    if (success) {
                        val peliculasArray = jsonResponse.getJSONArray("peliculas")
                        listadoPeliculas.addAll(parsePeliculas(peliculasArray))
                    } else {
                        val message = jsonResponse.getString("message")
                        Toast.makeText(this, "Error al cargar: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error de parseo JSON al obtener. ¿El script PHP tiene errores de sintaxis?"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("AdminPeliculas", errorMsg, e)
                }
            },
            { volleyError ->
                isLoading.value = false
                val errorMsg = "Error de red al obtener: ${volleyError.message ?: "Verifique la conexión Wi-Fi y la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("AdminPeliculas", "Error Volley Obtener: ${volleyError.message}")
            })

        requestQueue.add(stringRequest)
    }

    private fun parsePeliculas(jsonArray: JSONArray): List<Pelicula> {
        val list = mutableListOf<Pelicula>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)

            val pelicula = Pelicula()

            pelicula.id_pelicula = obj.getInt("id_pelicula")
            pelicula.titulo = obj.getString("titulo")
            pelicula.descripcion = obj.optString("descripcion", "Sin descripción")
            pelicula.anio_lanzamiento = obj.getInt("anio_lanzamiento")
            pelicula.duracion = obj.getInt("duracion")
            pelicula.clasificacion = obj.optString("clasificacion", "N/A")
            pelicula.idioma = obj.optString("idioma", "N/A")
            pelicula.imagen_url = obj.optString("imagen_url", "")

            list.add(pelicula)
        }
        return list
    }

    // 3. FUNCIÓN PARA ELIMINAR PELÍCULA
    private fun deletePelicula(pelicula: Pelicula) {
        isLoading.value = true

        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, URL_ELIMINAR_PELICULA,
            { response ->
                isLoading.value = false
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                    if (success) {
                        fetchPeliculas()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error al procesar la respuesta de eliminación. (Server issue)"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("AdminPeliculas", errorMsg, e)
                }
            },
            { volleyError ->
                isLoading.value = false
                val errorMsg = "Error de red al eliminar: ${volleyError.message ?: "Verifique la conexión Wi-Fi o la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("AdminPeliculas", "Error Volley Eliminar: ${volleyError.message}")
            }) {

            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                parametros["id_pelicula"] = pelicula.id_pelicula.toString()
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }
}


@Composable
fun AdminHeaderPeliculas(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 85.dp)
            .background(color = Color(0xff141414))
    ) {
        Text(
            text = "ADMINISTRACIÓN DE PELÍCULAS",
            color = Color.White,
            lineHeight = 1.5.em,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 40.dp)
        )
    }
}

@Composable
fun AdminPeliculasView(
    peliculas: SnapshotStateList<Pelicula>,
    isLoading: Boolean,
    onUpdateClick: (Pelicula) -> Unit,
    onDeleteClick: (Pelicula) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        AdminHeaderPeliculas()

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xffe50914))
            }
        } else if (peliculas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay películas registradas.", color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(peliculas, key = { it.id_pelicula }) { pelicula ->
                    PeliculaRow(
                        pelicula = pelicula,
                        onUpdateClick = onUpdateClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}

@Composable
fun PeliculaRow(
    pelicula: Pelicula,
    onUpdateClick: (Pelicula) -> Unit,
    onDeleteClick: (Pelicula) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF222222))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = pelicula.titulo,
                color = Color(0xffe50914),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ID: ${pelicula.id_pelicula} | Año: ${pelicula.anio_lanzamiento} | Duración: ${pelicula.duracion} min",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                text = "Clasificación: ${pelicula.clasificacion} | Idioma: ${pelicula.idioma}",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onUpdateClick(pelicula) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF007bff))
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Editar",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            IconButton(
                onClick = { onDeleteClick(pelicula) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xffe50914))
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

