package com.example.p_final_componentes

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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class AdminCRUDActivity : AppCompatActivity() {

    private val URL_BASE = "http://192.168.2.4/androidComponentes/"
    private lateinit var requestQueue: RequestQueue

    // Estados para cada entidad
    private val generos = mutableStateListOf<Genero>()
    private val actores = mutableStateListOf<Actor>()
    private val directores = mutableStateListOf<Director>()
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestQueue = Volley.newRequestQueue(this)

        setContent {
            MaterialTheme {
                AdminCRUDScreen(
                    generos = generos,
                    actores = actores,
                    directores = directores,
                    isLoading = isLoading.value,
                    onLoadGeneros = { loadGeneros() },
                    onLoadActores = { loadActores() },
                    onLoadDirectores = { loadDirectores() },
                    onAddGenero = { nombre -> addGenero(nombre) },
                    onUpdateGenero = { id, nombre -> updateGenero(id, nombre) },
                    onDeleteGenero = { id -> deleteGenero(id) },
                    onAddActor = { nombre -> addActor(nombre) },
                    onUpdateActor = { id, nombre -> updateActor(id, nombre) },
                    onDeleteActor = { id -> deleteActor(id) },
                    onAddDirector = { nombre -> addDirector(nombre) },
                    onUpdateDirector = { id, nombre -> updateDirector(id, nombre) },
                    onDeleteDirector = { id -> deleteDirector(id) },
                    onVolver = { finish() }
                )
            }
        }

        // Cargar datos iniciales
        loadGeneros()
        loadActores()
        loadDirectores()
    }

    // ==================== GÉNEROS ====================
    private fun loadGeneros() {
        isLoading.value = true
        val request = StringRequest(
            Request.Method.GET,
            URL_BASE + "obtener_generos.php",
            { response ->
                isLoading.value = false
                try {
                    val json = JSONObject(response)
                    if (json.getBoolean("success")) {
                        generos.clear()
                        val array = json.getJSONArray("data")
                        for (i in 0 until array.length()) {
                            val obj = array.getJSONObject(i)
                            generos.add(Genero(
                                obj.getInt("id_genero"),
                                obj.getString("nombre")
                            ))
                        }
                    }
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                isLoading.value = false
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }

    private fun addGenero(nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "agregar_genero.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadGeneros()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun updateGenero(id: Int, nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "actualizar_genero.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadGeneros()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_genero" to id.toString(), "nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun deleteGenero(id: Int) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "eliminar_genero.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadGeneros()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_genero" to id.toString())
            }
        }
        requestQueue.add(request)
    }

    // ==================== ACTORES ====================
    private fun loadActores() {
        isLoading.value = true
        val request = StringRequest(
            Request.Method.GET,
            URL_BASE + "obtener_actores.php",
            { response ->
                isLoading.value = false
                try {
                    val json = JSONObject(response)
                    if (json.getBoolean("success")) {
                        actores.clear()
                        val array = json.getJSONArray("data")
                        for (i in 0 until array.length()) {
                            val obj = array.getJSONObject(i)
                            actores.add(Actor(
                                obj.getInt("id_actor"),
                                obj.getString("nombre")
                            ))
                        }
                    }
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                isLoading.value = false
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }

    private fun addActor(nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "agregar_actor.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadActores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun updateActor(id: Int, nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "actualizar_actor.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadActores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_actor" to id.toString(), "nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun deleteActor(id: Int) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "eliminar_actor.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadActores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_actor" to id.toString())
            }
        }
        requestQueue.add(request)
    }

    // ==================== DIRECTORES ====================
    private fun loadDirectores() {
        isLoading.value = true
        val request = StringRequest(
            Request.Method.GET,
            URL_BASE + "obtener_directores.php",
            { response ->
                isLoading.value = false
                try {
                    val json = JSONObject(response)
                    if (json.getBoolean("success")) {
                        directores.clear()
                        val array = json.getJSONArray("data")
                        for (i in 0 until array.length()) {
                            val obj = array.getJSONObject(i)
                            directores.add(Director(
                                obj.getInt("id_director"),
                                obj.getString("nombre")
                            ))
                        }
                    }
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                isLoading.value = false
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }

    private fun addDirector(nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "agregar_director.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadDirectores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun updateDirector(id: Int, nombre: String) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "actualizar_director.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadDirectores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_director" to id.toString(), "nombre" to nombre)
            }
        }
        requestQueue.add(request)
    }

    private fun deleteDirector(id: Int) {
        val request = object : StringRequest(
            Method.POST,
            URL_BASE + "eliminar_director.php",
            { response ->
                try {
                    val json = JSONObject(response)
                    Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                    if (json.getBoolean("success")) loadDirectores()
                } catch (e: Exception) {
                    Log.e("CRUD", "Error: ${e.message}")
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                return hashMapOf("id_director" to id.toString())
            }
        }
        requestQueue.add(request)
    }
}

// ==================== UI COMPOSABLES ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdminCRUDScreen(
    generos: List<Genero>,
    actores: List<Actor>,
    directores: List<Director>,
    isLoading: Boolean,
    onLoadGeneros: () -> Unit,
    onLoadActores: () -> Unit,
    onLoadDirectores: () -> Unit,
    onAddGenero: (String) -> Unit,
    onUpdateGenero: (Int, String) -> Unit,
    onDeleteGenero: (Int) -> Unit,
    onAddActor: (String) -> Unit,
    onUpdateActor: (Int, String) -> Unit,
    onDeleteActor: (Int) -> Unit,
    onAddDirector: (String) -> Unit,
    onUpdateDirector: (Int, String) -> Unit,
    onDeleteDirector: (Int) -> Unit,
    onVolver: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Géneros", "Actores", "Directores")

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
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.Default.ArrowBack, "Volver", tint = Color.White)
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "ADMINISTRAR CATÁLOGO",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color(0xff1a1a1a),
                    contentColor = Color(0xffe50914)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }

        // Contenido según la pestaña seleccionada
        when (selectedTab) {
            0 -> GenerosCRUD(generos, isLoading, onAddGenero, onUpdateGenero, onDeleteGenero)
            1 -> ActoresCRUD(actores, isLoading, onAddActor, onUpdateActor, onDeleteActor)
            2 -> DirectoresCRUD(directores, isLoading, onAddDirector, onUpdateDirector, onDeleteDirector)
        }
    }
}

@Composable
fun GenerosCRUD(
    generos: List<Genero>,
    isLoading: Boolean,
    onAdd: (String) -> Unit,
    onUpdate: (Int, String) -> Unit,
    onDelete: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var editingGenero by remember { mutableStateOf<Genero?>(null) }
    var nuevoNombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Botón Agregar
        Button(
            onClick = {
                editingGenero = null
                nuevoNombre = ""
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe50914)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, null)
            Spacer(Modifier.width(8.dp))
            Text("AGREGAR GÉNERO")
        }

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xffe50914))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(generos) { genero ->
                    CRUDItemCard(
                        nombre = genero.nombre,
                        onEdit = {
                            editingGenero = genero
                            nuevoNombre = genero.nombre
                            showDialog = true
                        },
                        onDelete = { onDelete(genero.id_genero) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (editingGenero == null) "Agregar Género" else "Editar Género") },
            text = {
                OutlinedTextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    label = { Text("Nombre del Género") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (nuevoNombre.isNotBlank()) {
                        if (editingGenero == null) {
                            onAdd(nuevoNombre)
                        } else {
                            onUpdate(editingGenero!!.id_genero, nuevoNombre)
                        }
                        showDialog = false
                    }
                }) {
                    Text("GUARDAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("CANCELAR")
                }
            }
        )
    }
}

@Composable
private fun ActoresCRUD(
    actores: List<Actor>,
    isLoading: Boolean,
    onAdd: (String) -> Unit,
    onUpdate: (Int, String) -> Unit,
    onDelete: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var editingActor by remember { mutableStateOf<Actor?>(null) }
    var nuevoNombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                editingActor = null
                nuevoNombre = ""
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe50914)),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Icon(Icons.Default.Add, null)
            Spacer(Modifier.width(8.dp))
            Text("AGREGAR ACTOR")
        }

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xffe50914))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(actores) { actor ->
                    CRUDItemCard(
                        nombre = actor.nombre,
                        onEdit = {
                            editingActor = actor
                            nuevoNombre = actor.nombre
                            showDialog = true
                        },
                        onDelete = { onDelete(actor.id_actor) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (editingActor == null) "Agregar Actor" else "Editar Actor") },
            text = {
                OutlinedTextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    label = { Text("Nombre del Actor") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (nuevoNombre.isNotBlank()) {
                        if (editingActor == null) {
                            onAdd(nuevoNombre)
                        } else {
                            onUpdate(editingActor!!.id_actor, nuevoNombre)
                        }
                        showDialog = false
                    }
                }) {
                    Text("GUARDAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("CANCELAR")
                }
            }
        )
    }
}

@Composable
fun DirectoresCRUD(
    directores: List<Director>,
    isLoading: Boolean,
    onAdd: (String) -> Unit,
    onUpdate: (Int, String) -> Unit,
    onDelete: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var editingDirector by remember { mutableStateOf<Director?>(null) }
    var nuevoNombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                editingDirector = null
                nuevoNombre = ""
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe50914)),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Icon(Icons.Default.Add, null)
            Spacer(Modifier.width(8.dp))
            Text("AGREGAR DIRECTOR")
        }

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xffe50914))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(directores) { director ->
                    CRUDItemCard(
                        nombre = director.nombre,
                        onEdit = {
                            editingDirector = director
                            nuevoNombre = director.nombre
                            showDialog = true
                        },
                        onDelete = { onDelete(director.id_director) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (editingDirector == null) "Agregar Director" else "Editar Director") },
            text = {
                OutlinedTextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    label = { Text("Nombre del Director") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (nuevoNombre.isNotBlank()) {
                        if (editingDirector == null) {
                            onAdd(nuevoNombre)
                        } else {
                            onUpdate(editingDirector!!.id_director, nuevoNombre)
                        }
                        showDialog = false
                    }
                }) {
                    Text("GUARDAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("CANCELAR")
                }
            }
        )
    }
}

@Composable
fun CRUDItemCard(
    nombre: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xff222222)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                nombre,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = onEdit,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xff007bff))
                ) {
                    Icon(Icons.Default.Edit, "Editar", tint = Color.White, modifier = Modifier.size(20.dp))
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xffe50914))
                ) {
                    Icon(Icons.Default.Delete, "Eliminar", tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}