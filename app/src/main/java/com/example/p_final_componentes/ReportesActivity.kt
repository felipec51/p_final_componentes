package com.example.p_final_componentes

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ReportesActivity : AppCompatActivity() {

    private val URL_BASE = "${ApiConfig.BASE_URL}"
    private val URL_GENERAR_REPORTE = URL_BASE + "/generar_reporte.php"

    private lateinit var requestQueue: RequestQueue
    private val reportes = mutableStateListOf<ItemReporte>()
    private val isLoading = mutableStateOf(false)
    private val estadisticas = mutableStateOf<EstadisticasReporte?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestQueue = Volley.newRequestQueue(this)

        setContent {
            MaterialTheme {
                ReportesScreen(
                    reportes = reportes,
                    estadisticas = estadisticas.value,
                    isLoading = isLoading.value,
                    onGenerarReporte = { tipo, fechaInicio, fechaFin, usuarioId ->
                        generarReporte(tipo, fechaInicio, fechaFin, usuarioId)
                    },
                    onVolver = { finish() }
                )
            }
        }
    }

    private fun generarReporte(
        tipoReporte: String,
        fechaInicio: String,
        fechaFin: String,
        usuarioId: String
    ) {
        isLoading.value = true
        reportes.clear()
        estadisticas.value = null

        val stringRequest = object : StringRequest(
            Request.Method.POST, URL_GENERAR_REPORTE,
            { response ->
                isLoading.value = false
                Log.d("Reportes", "Respuesta: $response")

                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")

                    if (success) {
                        // Parsear datos
                        val datosArray = jsonResponse.getJSONArray("datos")
                        val listaReportes = parseReportes(datosArray)
                        reportes.addAll(listaReportes)

                        // Parsear estadísticas
                        val statsJson = jsonResponse.getJSONObject("estadisticas")
                        estadisticas.value = EstadisticasReporte(
                            totalIngresos = statsJson.getString("total_ingresos"),
                            totalAlquileres = statsJson.getInt("total_alquileres"),
                            peliculaMasRentada = statsJson.optString("pelicula_mas_rentada", "N/A"),
                            usuarioMasActivo = statsJson.optString("usuario_mas_activo", "N/A")
                        )

                        Toast.makeText(this, "✅ Reporte generado: ${reportes.size} registros", Toast.LENGTH_SHORT).show()
                    } else {
                        val message = jsonResponse.getString("message")
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error procesando reporte: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("Reportes", "Error parseando respuesta", e)
                }
            },
            { error ->
                isLoading.value = false
                Toast.makeText(this, "Error de conexión: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("Reportes", "Error Volley: ${error.message}")
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["tipo_reporte"] = tipoReporte
                params["fecha_inicio"] = fechaInicio
                params["fecha_fin"] = fechaFin
                params["usuario_id"] = usuarioId
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun parseReportes(jsonArray: JSONArray): List<ItemReporte> {
        val lista = mutableListOf<ItemReporte>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val item = ItemReporte()

            // Campos comunes
            item.id = obj.optInt("id_prestamo", i + 1) // Usar índice como fallback
            item.tituloPerlicula = obj.optString("titulo_pelicula", "N/A")
            item.precioAlquiler = obj.optString("precio_alquiler", "N/A")
            item.nombreUsuario = obj.optString("nombre_usuario", "N/A")
            item.fechaAlquiler = obj.optString("fecha_prestamo", "N/A")
            item.fechaDevolucion = obj.optString("fecha_devolucion", "N/A")
            item.estadoAlquiler = obj.optString("estado_alquiler", "N/A")
            item.cantidadAlquileres = obj.optInt("cantidad_alquileres", 0)

            lista.add(item)
        }

        return lista
    }
}

data class EstadisticasReporte(
    val totalIngresos: String,
    val totalAlquileres: Int,
    val peliculaMasRentada: String,
    val usuarioMasActivo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportesScreen(
    reportes: List<ItemReporte>,
    estadisticas: EstadisticasReporte?,
    isLoading: Boolean,
    onGenerarReporte: (String, String, String, String) -> Unit,
    onVolver: () -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    var tipoReporte by remember { mutableStateOf("todos") }
    var fechaInicio by remember { mutableStateOf(dateFormat.format(calendar.apply { add(Calendar.DAY_OF_MONTH, -30) }.time)) }
    var fechaFin by remember { mutableStateOf(dateFormat.format(Date())) }
    var usuarioId by remember { mutableStateOf("") }
    var expandedTipo by remember { mutableStateOf(false) }

    val tiposReporte = listOf(
        "todos" to "Todos los Alquileres",
        "semanal" to "Última Semana",
        "quincenal" to "Últimos 15 Días",
        "mensual" to "Último Mes",
        "top10" to "Top 10 Películas",
        "usuario" to "Por Usuario",
        "rango" to "Rango de Fechas"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff141414))
    ) {
        // Header
        Surface(
            color = Color(0xff1a1a1a),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onVolver) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    "MÓDULO DE REPORTES",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Contenido principal en LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Filtros
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xff222222)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "FILTROS DE BÚSQUEDA",
                            color = Color(0xffe50914),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.height(16.dp))

                        Text("Tipo de Reporte", color = Color.White, fontSize = 14.sp)
                        Spacer(Modifier.height(4.dp))

                        ExposedDropdownMenuBox(
                            expanded = expandedTipo,
                            onExpandedChange = { expandedTipo = !expandedTipo }
                        ) {
                            OutlinedTextField(
                                value = tiposReporte.find { it.first == tipoReporte }?.second ?: "",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null, tint = Color.White) },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color(0xff2f2f2f),
                                    focusedContainerColor = Color(0xff2f2f2f),
                                    focusedIndicatorColor = Color(0xffe50914),
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedTipo,
                                onDismissRequest = { expandedTipo = false },
                                modifier = Modifier.background(Color(0xff2f2f2f))
                            ) {
                                tiposReporte.forEach { (key, label) ->
                                    DropdownMenuItem(
                                        text = { Text(label, color = Color.White) },
                                        onClick = {
                                            tipoReporte = key
                                            expandedTipo = false

                                            when (key) {
                                                "semanal" -> {
                                                    calendar.time = Date()
                                                    fechaFin = dateFormat.format(calendar.time)
                                                    calendar.add(Calendar.DAY_OF_MONTH, -7)
                                                    fechaInicio = dateFormat.format(calendar.time)
                                                }
                                                "quincenal" -> {
                                                    calendar.time = Date()
                                                    fechaFin = dateFormat.format(calendar.time)
                                                    calendar.add(Calendar.DAY_OF_MONTH, -15)
                                                    fechaInicio = dateFormat.format(calendar.time)
                                                }
                                                "mensual" -> {
                                                    calendar.time = Date()
                                                    fechaFin = dateFormat.format(calendar.time)
                                                    calendar.add(Calendar.MONTH, -1)
                                                    fechaInicio = dateFormat.format(calendar.time)
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }

                        if (tipoReporte == "usuario") {
                            Spacer(Modifier.height(12.dp))
                            Text("ID del Usuario", color = Color.White, fontSize = 14.sp)
                            Spacer(Modifier.height(4.dp))
                            OutlinedTextField(
                                value = usuarioId,
                                onValueChange = { usuarioId = it },
                                placeholder = { Text("Ingrese ID del usuario", color = Color.Gray) },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color(0xff2f2f2f),
                                    focusedContainerColor = Color(0xff2f2f2f),
                                    focusedIndicatorColor = Color(0xffe50914),
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        if (tipoReporte == "rango") {
                            Spacer(Modifier.height(12.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Fecha Inicio", color = Color.White, fontSize = 14.sp)
                                    Spacer(Modifier.height(4.dp))
                                    OutlinedTextField(
                                        value = fechaInicio,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            IconButton(onClick = {
                                                val datePicker = DatePickerDialog(
                                                    context,
                                                    { _, year, month, day ->
                                                        calendar.set(year, month, day)
                                                        fechaInicio = dateFormat.format(calendar.time)
                                                    },
                                                    calendar.get(Calendar.YEAR),
                                                    calendar.get(Calendar.MONTH),
                                                    calendar.get(Calendar.DAY_OF_MONTH)
                                                )
                                                datePicker.show()
                                            }) {
                                                Icon(Icons.Default.DateRange, null, tint = Color.White)
                                            }
                                        },
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color(0xff2f2f2f),
                                            focusedContainerColor = Color(0xff2f2f2f),
                                            focusedIndicatorColor = Color(0xffe50914),
                                            unfocusedIndicatorColor = Color.Transparent,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                Spacer(Modifier.width(8.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Fecha Fin", color = Color.White, fontSize = 14.sp)
                                    Spacer(Modifier.height(4.dp))
                                    OutlinedTextField(
                                        value = fechaFin,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            IconButton(onClick = {
                                                val datePicker = DatePickerDialog(
                                                    context,
                                                    { _, year, month, day ->
                                                        calendar.set(year, month, day)
                                                        fechaFin = dateFormat.format(calendar.time)
                                                    },
                                                    calendar.get(Calendar.YEAR),
                                                    calendar.get(Calendar.MONTH),
                                                    calendar.get(Calendar.DAY_OF_MONTH)
                                                )
                                                datePicker.show()
                                            }) {
                                                Icon(Icons.Default.DateRange, null, tint = Color.White)
                                            }
                                        },
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color(0xff2f2f2f),
                                            focusedContainerColor = Color(0xff2f2f2f),
                                            focusedIndicatorColor = Color(0xffe50914),
                                            unfocusedIndicatorColor = Color.Transparent,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = {
                                onGenerarReporte(tipoReporte, fechaInicio, fechaFin, usuarioId)
                            },
                            enabled = !isLoading,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe50914)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            } else {
                                Icon(Icons.Default.Search, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("GENERAR REPORTE", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }

            // Estadísticas
            if (estadisticas != null) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xff222222)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "RESUMEN ESTADÍSTICO",
                                color = Color(0xffe50914),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Spacer(Modifier.height(16.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                EstadisticaItem(
                                    titulo = "Total Ingresos",
                                    valor = estadisticas.totalIngresos,
                                    icono = Icons.Default.AttachMoney,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(Modifier.width(8.dp))

                                EstadisticaItem(
                                    titulo = "Total Alquileres",
                                    valor = "${estadisticas.totalAlquileres}",
                                    icono = Icons.Default.Movie,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            Spacer(Modifier.height(12.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                EstadisticaItem(
                                    titulo = "Película Más Rentada",
                                    valor = estadisticas.peliculaMasRentada,
                                    icono = Icons.Default.Star,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(Modifier.width(8.dp))

                                EstadisticaItem(
                                    titulo = "Usuario Más Activo",
                                    valor = estadisticas.usuarioMasActivo,
                                    icono = Icons.Default.Person,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Header de resultados
            if (reportes.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "RESULTADOS (${reportes.size})",
                            color = Color(0xffe50914),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        IconButton(onClick = {
                            Toast.makeText(context, "Función de exportación próximamente", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(
                                Icons.Default.Download,
                                contentDescription = "Exportar",
                                tint = Color.White
                            )
                        }
                    }
                }

                // Items individuales
                items(reportes) { item ->
                    ReporteItem(item)
                }

            } else if (!isLoading) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xff222222)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Default.SearchOff,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(64.dp)
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    "No hay resultados",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                                Text(
                                    "Seleccione los filtros y genere un reporte",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EstadisticaItem(
    titulo: String,
    valor: String,
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xff2f2f2f)),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icono, contentDescription = null, tint = Color(0xffe50914), modifier = Modifier.size(24.dp))
            Spacer(Modifier.height(8.dp))
            Text(titulo, color = Color.Gray, fontSize = 11.sp, textAlign = TextAlign.Center, maxLines = 2)
            Spacer(Modifier.height(4.dp))
            Text(valor, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, maxLines = 2)
        }
    }
}

@Composable
fun ReporteItem(item: ItemReporte) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xff2f2f2f)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    item.tituloPerlicula,
                    color = Color(0xffe50914),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        item.precioAlquiler,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    // Mostrar cantidad de alquileres si es mayor a 0 (Top 10)
                    if (item.cantidadAlquileres > 0) {
                        Text(
                            "${item.cantidadAlquileres} rentas",
                            color = Color(0xffffa500),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Solo mostrar detalles si NO es Top 10 (cantidad_alquileres == 0)
            if (item.cantidadAlquileres == 0) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Usuario:", color = Color.Gray, fontSize = 12.sp)
                        Text(item.nombreUsuario, color = Color.White, fontSize = 13.sp)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("Estado:", color = Color.Gray, fontSize = 12.sp)
                        Text(
                            item.estadoAlquiler,
                            color = if (item.estadoAlquiler == "en curso") Color(0xffffa500) else Color(0xff4ade80),
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Alquiler:", color = Color.Gray, fontSize = 12.sp)
                        Text(item.fechaAlquiler.take(10), color = Color.White, fontSize = 12.sp)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("Devolución:", color = Color.Gray, fontSize = 12.sp)
                        Text(item.fechaDevolucion.take(10), color = Color.White, fontSize = 12.sp)
                    }
                }
            } else {
                // Vista simplificada para Top 10
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Ranking:", color = Color.Gray, fontSize = 12.sp)
                        Text("Top ${item.id}", color = Color(0xffffd700), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("Total Rentas:", color = Color.Gray, fontSize = 12.sp)
                        Text("${item.cantidadAlquileres} alquileres", color = Color.White, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}