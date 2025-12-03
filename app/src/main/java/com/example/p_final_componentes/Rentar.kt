package com.example.p_final_componentes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

val ColorBackground = Color(0xff18181b)
val ColorCard = Color(0xff27272a)
val ColorBorder = Color(0xff3f3f46)
val ColorPrimaryPink = Color(0xfff6395f)
val ColorSecondaryPurple = Color(0xff4f39f6)
val ColorTextSecondary = Color(0xff71717b)
val ColorTextLight = Color(0xffe4e4e7)

sealed class RentButtonState {
    object Loading : RentButtonState()
    data class RentAvailable(val price: Double) : RentButtonState()
    object AlreadyRented : RentButtonState()
    object OutOfStock : RentButtonState()
}

class Rentar : AppCompatActivity() {

   //felipe private val BASE_URL = "http://192.168.20.35/androidComponentes"
    private val BASE_URL = "http://192.168.2.4/androidComponentes"
    private val URL_RENTAR = "$BASE_URL/rentar_pelicula.php"
    private val URL_VERIFICAR = "$BASE_URL/verificar_estado_alquiler.php" // Endpoint para verificar préstamo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pelicula = intent.getSerializableExtra("PELICULA_OBJ") as? Pelicula
        val usuario = Sesion.usuarioActual

        if (pelicula == null || usuario == null) {
            Toast.makeText(this, "Error cargando datos", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            MaterialTheme {
                RentarScreen(
                    pelicula = pelicula,
                    userId = usuario.id,
                    userName = usuario.nombre,
                    onCheckStatus = { p, u, callback ->
                        verificarEstadoUsuario(p, u, callback)
                    },
                    onConfirmar = { p, u, onSuccess ->
                        realizarAlquiler(p, u, onSuccess)
                    },
                    onAgregarLista = { p, u ->
                        agregarAListaEspera(p, u, this)
                    }
                )
            }
        }
    }

    private fun agregarAListaEspera(
        pelicula: Pelicula,
        userId: Int,
        activity: AppCompatActivity
    ) {
        // CORRECCIÓN 1: Usar la variable BASE_URL en lugar de escribir la IP a mano
        // Asegúrate de que BASE_URL no tenga "/" al final o ajústalo según corresponda
        val url = "$BASE_URL/agregar_lista_espera.php"

        val request = object : StringRequest(Method.POST, url,
            { response ->
                try {
                    val json = JSONObject(response)
                    val success = json.optBoolean("success") // Verificar flag success
                    val message = json.optString("message", "Sin mensaje")

                    if(success){
                        Toast.makeText(activity, "Éxito: $message", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(activity, "Error: $message", Toast.LENGTH_LONG).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(activity, "Error JSON: ${e.message}", Toast.LENGTH_LONG).show()
                }
            },
            { error ->
                // CORRECCIÓN 2: Mejorar el mensaje de error para ver qué pasa realmente
                val networkResponse = error.networkResponse
                if (networkResponse != null && networkResponse.data != null) {
                    val errorString = String(networkResponse.data)
                    // Imprimir el error que devuelve PHP (útil si hay error 500)
                    Toast.makeText(activity, "Error PHP: $errorString", Toast.LENGTH_LONG).show()
                    println("DEBUG_ERROR: $errorString")
                } else {
                    Toast.makeText(activity, "Error de red: ${error.message}", Toast.LENGTH_LONG).show()
                }
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id_usuario"] = userId.toString()
                params["id_pelicula"] = pelicula.id_pelicula.toString()
                return params
            }
        }

        Volley.newRequestQueue(activity).add(request)
    }
    // ------------------- VERIFICAR ESTADO (DEVUELVE ID Y FECHA) -------------------

    private fun verificarEstadoUsuario(
        pelicula: Pelicula,
        userId: Int,
        // MODIFICACIÓN 2: El callback ahora incluye el ID de préstamo (Int) y la fecha de devolución (String)
        onResult: (Boolean, Int, String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(this)

        val request = object : StringRequest(
            Method.POST, URL_VERIFICAR,
            Response.Listener { response ->
                try {
                    val json = JSONObject(response)
                    val tieneAlquiler = json.optBoolean("tiene_alquiler", false)
                    val idPrestamo = json.optInt("id_prestamo", -1)
                    val fechaDevolucion = json.optString("fecha_devolucion", "") // NUEVO: Obtener fecha de devolución

                    // MODIFICACIÓN 3: Devolver todos los datos
                    onResult(tieneAlquiler, idPrestamo, fechaDevolucion)

                } catch (e: Exception) {
                    onResult(false, -1, "")
                }
            },
            Response.ErrorListener {
                onResult(false, -1, "")
            }
        ) {
            override fun getParams(): Map<String, String> {
                return mapOf(
                    "id_usuario" to userId.toString(),
                    "id_pelicula" to pelicula.id_pelicula.toString()
                )
            }
        }

        queue.add(request)
    }
    private fun realizarAlquiler(
        pelicula: Pelicula,
        userId: Int,
        onSuccess: (String) -> Unit
    ) {

        val queue = Volley.newRequestQueue(this)

        val request = object : StringRequest(
            Method.POST, URL_RENTAR,
            Response.Listener { response ->
                try {
                    val json = JSONObject(response)
                    val success = json.getBoolean("success")
                    val message = json.getString("message")

                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        onSuccess(response)
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(this, "Error procesando respuesta", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error de red", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return mapOf(
                    "id_usuario" to userId.toString(),
                    "id_pelicula" to pelicula.id_pelicula.toString(),
                    "precio" to pelicula.precio_alquiler.toString()
                )
            }
        }

        queue.add(request)
    }
}

@Composable
fun RentarScreen(
    pelicula: Pelicula,
    userId: Int,
    userName: String,
    onCheckStatus: (Pelicula, Int, (Boolean, Int, String) -> Unit) -> Unit,
    onConfirmar: (Pelicula, Int, (String) -> Unit) -> Unit,
    onAgregarLista: (Pelicula, Int) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var idFacturaDisplay by remember { mutableStateOf("PENDIENTE") }
    var fechaDevolucionDisplay by remember { mutableStateOf("") }
    var diasRestantesDisplay by remember { mutableStateOf("7") }
    var buttonState by remember { mutableStateOf<RentButtonState>(RentButtonState.Loading) }
    var isProcessing by remember { mutableStateOf(false) }

    fun calculateDaysRemaining(fechaDevolucionStr: String): Pair<String, String> {
        val dateFormatServer = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateFormatDisplay = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return try {
            val fechaDevolucion = dateFormatServer.parse(fechaDevolucionStr)
            val fechaActual = Date()

            val devolucionDisplay = dateFormatDisplay.format(fechaDevolucion)

            val diferenciaMs = fechaDevolucion.time - fechaActual.time

            val dias: Int = if (diferenciaMs > 0) {
                val diferenciaEnDias = diferenciaMs.toDouble() / (1000 * 60 * 60 * 24)
                ceil(diferenciaEnDias).toInt()
            } else {
                0
            }

            Pair(devolucionDisplay, dias.toString())

        } catch (e: Exception) {
            Pair("Error", "N/A")
        }
    }

    LaunchedEffect(Unit) {
        // Inicializar con fecha de devolución predeterminada (7 días)
        val calInicial = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 7) }
        fechaDevolucionDisplay = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calInicial.time)
        diasRestantesDisplay = "7"


        // MODIFICACIÓN 5: Usar los 3 valores devueltos por la verificación
        onCheckStatus(pelicula, userId) { yaLaTiene, idPrestamo, fechaDevolucionStr ->
            if (yaLaTiene) {
                // CORRECCIÓN PRINCIPAL: Usar el ID de préstamo real
                idFacturaDisplay = "PRESTAMO-$idPrestamo"
                buttonState = RentButtonState.AlreadyRented

                // NUEVO: Calcular días restantes y fecha para el préstamo EXISTENTE
                val (devDisplay, diasRestantes) = calculateDaysRemaining(fechaDevolucionStr)
                fechaDevolucionDisplay = devDisplay
                diasRestantesDisplay = diasRestantes

            } else if (pelicula.copias_disponibles <= 0) {
                buttonState = RentButtonState.OutOfStock
            } else {
                buttonState = RentButtonState.RentAvailable(pelicula.precio_alquiler)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {

        FacturaHeader(idFactura = idFacturaDisplay)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FacturaDetalleCard(title = "DATOS DEL CLIENTE") {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    ClientDetailItem("Nombre", userName)
                    ClientDetailItem("ID Cliente", "CLI-$userId")
                }
            }

            FacturaDetalleCard("DETALLES DE LA PELÍCULA") {
                Text(pelicula.titulo, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ClientDetailItem("Duración", "${pelicula.duracion} min")
                    ClientDetailItem("Año", pelicula.anio_lanzamiento.toString())
                    ClientDetailItem("Copias Disp.", "${pelicula.copias_disponibles}")
                }
            }

            FacturaDetalleCard("PERIODO DE ALQUILER") {
                RentPeriodItem("Fecha devolución", fechaDevolucionDisplay, Color.White)
                RentPeriodItem("Días", "$diasRestantesDisplay días", ColorPrimaryPink)
            }

            FacturaDetalleCard("RESUMEN", borderColor = ColorPrimaryPink) {
                PaymentSummaryItem("Precio Base", "\$${pelicula.precio_alquiler}", false)
                PaymentSummaryItem("Total", "\$${pelicula.precio_alquiler}", true)
            }

            DynamicRentButton(
                state = buttonState,
                isProcessing = isProcessing,
                onRentClick = {
                    isProcessing = true
                    onConfirmar(pelicula, userId) { responseString ->
                        val json = JSONObject(responseString)
                        val idPrestamoReal = json.optInt("id_prestamo", -1)
                        val fechaDevolucion = json.getString("fecha_devolucion")

                        // Actualización después de la renta
                        idFacturaDisplay = "PRESTAMO-$idPrestamoReal"

                        val (devDisplay, diasRestantes) = calculateDaysRemaining(fechaDevolucion)
                        fechaDevolucionDisplay = devDisplay
                        diasRestantesDisplay = diasRestantes


                        scope.launch {
                            delay(1000)
                            // La clase menupeliculas no está definida en este archivo, asumo que existe.
                            context.startActivity(Intent(context, Class.forName("com.example.p_final_componentes.menupeliculas")))
                            (context as? AppCompatActivity)?.finish()
                        }
                    }
                },
                onWaitListClick = {
                    onAgregarLista(pelicula, userId)
                }
            )
        }
    }
}

@Composable
fun FacturaHeader(idFactura: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                Brush.linearGradient(
                    0f to ColorPrimaryPink,
                    1f to ColorSecondaryPurple,
                    start = Offset.Zero,
                    end = Offset(400f, 150f)
                )
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Factura de Alquiler", color = Color.White, fontSize = 17.sp)
        Spacer(Modifier.height(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(idFactura, color = ColorPrimaryPink, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FacturaDetalleCard(title: String, borderColor: Color = ColorBorder, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorCard, RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Text(title, color = ColorPrimaryPink, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        content()
    }
}

@Composable
fun ClientDetailItem(label: String, value: String) {
    Column {
        Text(label, color = ColorTextSecondary, fontSize = 13.sp)
        Text(value, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun RentPeriodItem(label: String, value: String, valueColor: Color) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = ColorTextSecondary)
        Text(value, color = valueColor, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun PaymentSummaryItem(label: String, value: String, isTotal: Boolean) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = if (isTotal) Color.White else ColorTextSecondary)
        Text(
            value,
            color = if (isTotal) ColorPrimaryPink else Color.White,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun DynamicRentButton(
    state: RentButtonState,
    isProcessing: Boolean,
    onRentClick: () -> Unit,
    onWaitListClick: () -> Unit
) {
    val text: String
    val color: Color
    val enabled: Boolean
    val action: () -> Unit

    when (state) {
        RentButtonState.Loading -> {
            text = "Verificando..."
            color = ColorTextSecondary
            enabled = false
            action = {}
        }
        is RentButtonState.RentAvailable -> {
            text = "Confirmar Alquiler (\$${state.price})"
            color = ColorPrimaryPink
            enabled = true
            action = onRentClick
        }
        RentButtonState.AlreadyRented -> {
            text = "Préstamo en curso"
            color = ColorBorder
            enabled = false
            action = {}
        }
        RentButtonState.OutOfStock -> {
            text = "Unirse a lista de espera"
            color = Color(0xffffb703)
            enabled = true
            action = onWaitListClick
        }
    }

    Button(
        onClick = action,
        enabled = enabled && !isProcessing,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        if (isProcessing) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(text, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RentarScreenPreview() {
    val peliculaMock = Pelicula().apply {
        id_pelicula = 1
        titulo = "Ejemplo de Película"
        duracion = 120
        anio_lanzamiento = 2023
        copias_disponibles = 5
        precio_alquiler = 15.0
    }

    RentarScreen(
        pelicula = peliculaMock,
        userId = 123,
        userName = "Juan Pérez",
        onCheckStatus = { _, _, callback ->
            callback(false, -1, "")
        },
        onConfirmar = { _, _, onSuccess ->
            onSuccess("""{"id_prestamo":101,"fecha_devolucion":"2025-12-28 12:00:00"}""")
        },
        onAgregarLista = { _, _ -> }
    )
}
