<?php

// 1. Incluir el archivo de conexión proporcionado por el usuario
require_once 'conexion.php'; 

header('Content-Type: application/json');
$response = array("success" => false, "message" => "Error desconocido.");

// 2. Conectar a la base de datos usando la función del usuario
$conn = Conectar();

// Verifica la conexión (la función Conectar ya lo hace, pero es buena práctica)
if (!$conn || $conn->connect_error) {
    $response["message"] = "Error de conexión a la base de datos.";
    echo json_encode($response);
    exit();
}

// 3. Obtener y sanear los parámetros POST
// ¡NUEVO CAMPO: ncopias!
$titulo = $_POST['titulo'] ?? '';
$anio = $_POST['anio'] ?? '';
$duracion_min = $_POST['duracion_min'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';
$poster_path = $_POST['poster_path'] ?? '';
$precio_alquiler = $_POST['precio_alquiler'] ?? '';
$calificacion = $_POST['calificacion'] ?? '';
$director_id_director = $_POST['director_id_director'] ?? '';
$idioma = $_POST['idioma'] ?? '';
$ncopias = $_POST['ncopias'] ?? ''; // <--- NUEVO CAMPO
$actores_ids_raw = $_POST['actores_ids'] ?? ''; 
$generos_ids_raw = $_POST['generos_ids'] ?? ''; 

// 4. Validación básica (Comprobar que los campos obligatorios están presentes)
if (empty($titulo) || empty($anio) || empty($duracion_min) || empty($descripcion) || empty($precio_alquiler) || empty($director_id_director) || empty($actores_ids_raw) || empty($generos_ids_raw) || $ncopias === '') {
    $response["message"] = "Faltan parámetros obligatorios (incluyendo el número de copias).";
    $conn->close();
    echo json_encode($response);
    exit();
}

// Asegurar que ncopias sea un entero válido
$ncopias = intval($ncopias);
if ($ncopias < 0) {
    $response["message"] = "El número de copias debe ser un número entero no negativo.";
    $conn->close();
    echo json_encode($response);
    exit();
}

// Convertir las cadenas de IDs a arrays de enteros (limpieza de datos)
$actores_ids_array = array_map('intval', array_filter(explode(',', $actores_ids_raw)));
$generos_ids_array = array_map('intval', array_filter(explode(',', $generos_ids_raw)));

// 5. INICIAR LA TRANSACCIÓN
$conn->begin_transaction();

try {
    // 6. PASO 1: INSERTAR LA PELÍCULA EN LA TABLA 'pelicula'
    // Se ha añadido 'ncopias' al INSERT de la tabla 'pelicula'
    $sql_pelicula = "INSERT INTO pelicula (titulo, anio, duracion_min, descripcion, poster_path, precio_alquiler, calificacion, director_id_director, idioma, ncopias) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt_pelicula = $conn->prepare($sql_pelicula);
    
    // "sisssdsssi" -> string, integer, string, string, string, double, string, integer, string, integer
    if (!$stmt_pelicula->bind_param("sisssdsssi", $titulo, $anio, $duracion_min, $descripcion, $poster_path, $precio_alquiler, $calificacion, $director_id_director, $idioma, $ncopias)) {
        throw new Exception("Error al enlazar parámetros de película.");
    }

    if (!$stmt_pelicula->execute()) {
        throw new Exception("Error al insertar la película: " . $stmt_pelicula->error);
    }
    
    // 7. Obtener el ID de la película recién insertada
    $pelicula_id = $conn->insert_id;
    $stmt_pelicula->close();
    
    // 8. PASO 2: INSERTAR LAS RELACIONES CON ACTORES en 'pelicula_actor' (Sin cambios)
    if (!empty($actores_ids_array)) {
        $sql_actor = "INSERT INTO pelicula_actor (pelicula_id_pelicula, actor_id_actor, rol_pelicula) VALUES (?, ?, ?)";
        $stmt_actor = $conn->prepare($sql_actor);
        $rol_default = "Protagonista"; 

        foreach ($actores_ids_array as $actor_id) {
            if (!$stmt_actor->bind_param("iis", $pelicula_id, $actor_id, $rol_default)) {
                throw new Exception("Error al enlazar parámetros de actor.");
            }
            if (!$stmt_actor->execute()) {
                throw new Exception("Error al insertar el actor ID $actor_id: " . $stmt_actor->error);
            }
        }
        $stmt_actor->close();
    }
    
    // 9. PASO 3: INSERTAR LAS RELACIONES CON GÉNEROS en 'pelicula_genero' (Sin cambios)
    if (!empty($generos_ids_array)) {
        $sql_genero = "INSERT INTO pelicula_genero (pelicula_id_pelicula, genero_id_genero) VALUES (?, ?)";
        $stmt_genero = $conn->prepare($sql_genero);

        foreach ($generos_ids_array as $genero_id) {
            if (!$stmt_genero->bind_param("ii", $pelicula_id, $genero_id)) {
                throw new Exception("Error al enlazar parámetros de género.");
            }
            if (!$stmt_genero->execute()) {
                throw new Exception("Error al insertar el género ID $genero_id: " . $stmt_genero->error);
            }
        }
        $stmt_genero->close();
    }

    // 10. PASO 4: INSERTAR LAS CINTAS EN LA TABLA 'cinta' (¡NUEVO BUCLE!)
    $estado_cinta = 'disponible';
    $sql_cinta = "INSERT INTO cinta (estado, pelicula_id_pelicula) VALUES (?, ?)";
    $stmt_cinta = $conn->prepare($sql_cinta);

    // Bucle para insertar 'ncopias' cintas
    for ($i = 0; $i < $ncopias; $i++) {
        // "si" -> string, integer
        if (!$stmt_cinta->bind_param("si", $estado_cinta, $pelicula_id)) {
            throw new Exception("Error al enlazar parámetros de cinta.");
        }
        if (!$stmt_cinta->execute()) {
            throw new Exception("Error al insertar la cinta #".($i + 1).": " . $stmt_cinta->error);
        }
    }
    $stmt_cinta->close();


    // 11. Confirmar la transacción
    $conn->commit();
    $response["success"] = true;
    $response["message"] = "Película '$titulo' (ID $pelicula_id) agregada correctamente con sus relaciones y $ncopias cintas disponibles.";

} catch (Exception $e) {
    // 12. Deshacer la transacción si algo falla
    $conn->rollback();
    $response["message"] = "Fallo en la transacción: " . $e->getMessage();
}

// 13. Cerrar la conexión y enviar la respuesta JSON
$conn->close();
echo json_encode($response);

?>