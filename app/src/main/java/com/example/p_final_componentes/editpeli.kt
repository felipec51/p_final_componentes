package com.example.p_final_componentes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap

class editpeli : AppCompatActivity() {

    private val URL_BASE = "${ApiConfig.BASE_URL}"
    private val URL_ACTUALIZAR_PELICULA = URL_BASE + "/actualizar_pelicula.php"
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

class edituser : AppCompatActivity() {

    private val URL_BASE = "http://192.168.2.4/androidComponentes/"
    private val URL_ACTUALIZAR_USUARIO = URL_BASE + "editar_usuario.php"

    private lateinit var requestQueue: RequestQueue
    private var usuarioOriginal: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edituser) // Asegura que el layout tenga un ComposeView con ID 'render'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Volley
        requestQueue = Volley.newRequestQueue(this)


        @Suppress("DEPRECATION")
        usuarioOriginal = intent.getSerializableExtra("usuario_data") as? Usuario

        val composeView = findViewById<ComposeView>(R.id.render)

        composeView.setContent {
            MaterialTheme {
                FormEditarUsuario(
                    usuarioToEdit = usuarioOriginal,
                    // Lógica para el botón GUARDAR: llamar a la función de red
                    onSaveClick = { id, user, name, password, address, phone, mail ->
                        updateUsuario(id, user, name, password, address, phone, mail)
                    },
                    // Lógica para el botón CANCELAR: cerrar la Activity
                    onCancelClick = {
                        finish()
                    }
                )
            }
        }
    }

    //  FUNCIÓN DE ACTUALIZACIÓN CON VOLLEY
    private fun updateUsuario(
        id: String,
        username: String,
        nombre: String,
        password: String,
        direccion: String,
        telefono: String,
        email: String
    ) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, URL_ACTUALIZAR_USUARIO,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                    if (success) {
                        // Si la actualización fue exitosa, cerramos la Activity
                        finish()
                    }
                } catch (e: Exception) {
                    val errorMsg = "Error al procesar la respuesta del servidor. (JSON o Server issue)"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    Log.e("edituser", errorMsg, e)
                }
            },
            { volleyError ->
                val errorMsg = "Error de red al actualizar: ${volleyError.message ?: "Verifique la conexión Wi-Fi o la IP."}"
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.e("edituser", "Error Volley Actualizar: ${volleyError.message}")
            }) {

            // Envío de parámetros POST
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                // Las claves deben coincidir exactamente con el script PHP: editar_usuario.php
                parametros["id_usuario"] = id
                parametros["username"] = username
                parametros["nombre"] = nombre
                parametros["direccion"] = direccion
                parametros["telefono"] = telefono
                parametros["email"] = email

                // Solo enviamos la contraseña si el campo no está vacío
                if (password.isNotEmpty()) {
                    parametros["password"] = password
                }
                return parametros
            }
        }
        requestQueue.add(stringRequest)
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    readOnly: Boolean = false,
    isPasswordField: Boolean = false,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White.copy(alpha = 0.7f)) },
        placeholder = { Text(placeholder) },
        readOnly = readOnly,
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.Companion.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF282828),
            unfocusedContainerColor = Color(0xFF282828),
            focusedLabelColor = Color(0xFFE50914),
            unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
            focusedIndicatorColor = Color(0xFFE50914),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
            cursorColor = Color(0xFFE50914),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun FormEditarUsuario(
    usuarioToEdit: Usuario?,
    onSaveClick: (id: String, username: String, nombre: String, password: String, direccion: String, telefono: String, email: String) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estados
    var id by remember { mutableStateOf(usuarioToEdit?.id?.toString() ?: "") }
    var username by remember { mutableStateOf(usuarioToEdit?.username ?: "") }
    var nombre by remember { mutableStateOf(usuarioToEdit?.nombre ?: "") }
    var contrasena by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf(usuarioToEdit?.direccion ?: "") }
    var telefono by remember { mutableStateOf(usuarioToEdit?.telefono ?: "") }
    var email by remember { mutableStateOf(usuarioToEdit?.email ?: "") }

    Surface(
        color = Color(0xff141218),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .shadow(6.dp, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff141218))
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),   // 🔥 SCROLL AGREGADO
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Título
            Text(
                text = "Editar Usuario",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Campos
            Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {

                CustomTextField(
                    label = "ID Usuario (No Editable)",
                    value = id,
                    onValueChange = {},
                    placeholder = "ID",
                    readOnly = true
                )

                CustomTextField(
                    label = "Username",
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Ingrese nuevo username"
                )

                CustomTextField(
                    label = "Nombre Completo",
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = "Ingrese nuevo nombre"
                )

                CustomTextField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Ingrese nuevo email"
                )

                CustomTextField(
                    label = "Dirección",
                    value = direccion,
                    onValueChange = { direccion = it },
                    placeholder = "Ingrese nueva dirección"
                )

                CustomTextField(
                    label = "Teléfono",
                    value = telefono,
                    onValueChange = { telefono = it },
                    placeholder = "Ingrese nuevo teléfono"
                )

                CustomTextField(
                    label = "Nueva Contraseña (Opcional)",
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    placeholder = "********",
                    isPasswordField = true,
                    modifier = Modifier.padding(bottom = 20.dp) // más aire 🔥
                )
            }

            // BOTONES
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),  // 🔥 MÁS SEPARADOS
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                // Guardar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)            // 🔥 botón más grande
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xffe50914))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onSaveClick(
                                id, username, nombre, contrasena,
                                direccion, telefono, email
                            )
                        }
                        .padding(vertical = 10.dp)
                ) {
                    Text("Guardar Cambios", color = Color.White, fontSize = 15.sp)
                }

                // Cancelar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xff2f2f2f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onCancelClick() }
                        .padding(vertical = 10.dp)
                ) {
                    Text("Cancelar", color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}


@Preview(widthDp = 448, heightDp = 746)
@Composable
private fun FormEditarUsuarioPreview() {
    val dummyUser = Usuario().apply {
        id = 101
        username = "jdoe"
        nombre = "John Doe"
        direccion = "Av. Siempre Viva 123"
        telefono = "555-1234"
        email = "john.doe@example.com"
    }
    MaterialTheme {
        FormEditarUsuario(
            usuarioToEdit = dummyUser,
            onSaveClick = { _,_,_,_,_,_,_ -> }, // Mock para Preview
            onCancelClick = {} // Mock para Preview
        )
    }
}