<?php


require_once 'conexion.php'; 

header('Content-Type: application/json');
$response = array("success" => false, "message" => "Error desconocido.");


$conn = Conectar();


if (!$conn || $conn->connect_error) {
    $response["message"] = "Error de conexión a la base de datos.";
    echo json_encode($response);
    exit();
}


$titulo = $_POST['titulo'] ?? '';
$anio = $_POST['anio'] ?? '';
$duracion_min = $_POST['duracion_min'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';
$poster_path = $_POST['poster_path'] ?? '';
$precio_alquiler = $_POST['precio_alquiler'] ?? '';
$calificacion = $_POST['calificacion'] ?? '';
$director_id_director = $_POST['director_id_director'] ?? '';
$idioma = $_POST['idioma'] ?? '';
$ncopias = $_POST['ncopias'] ?? ''; 
$actores_ids_raw = $_POST['actores_ids'] ?? ''; 
$generos_ids_raw = $_POST['generos_ids'] ?? ''; 


if (empty($titulo) || empty($anio) || empty($duracion_min) || empty($descripcion) || empty($precio_alquiler) || empty($director_id_director) || empty($actores_ids_raw) || empty($generos_ids_raw) || $ncopias === '') {
    $response["message"] = "Faltan parámetros obligatorios (incluyendo el número de copias).";
    $conn->close();
    echo json_encode($response);
    exit();
}


$ncopias = intval($ncopias);
if ($ncopias < 0) {
    $response["message"] = "El número de copias debe ser un número entero no negativo.";
    $conn->close();
    echo json_encode($response);
    exit();
}


$actores_ids_array = array_map('intval', array_filter(explode(',', $actores_ids_raw)));
$generos_ids_array = array_map('intval', array_filter(explode(',', $generos_ids_raw)));


$conn->begin_transaction();

try {

    $sql_pelicula = "INSERT INTO pelicula (titulo, anio, duracion_min, descripcion, poster_path, precio_alquiler, calificacion, director_id_director, idioma, ncopias) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt_pelicula = $conn->prepare($sql_pelicula);
    

    if (!$stmt_pelicula->bind_param("sisssdsssi", $titulo, $anio, $duracion_min, $descripcion, $poster_path, $precio_alquiler, $calificacion, $director_id_director, $idioma, $ncopias)) {
        throw new Exception("Error al enlazar parámetros de película.");
    }

    if (!$stmt_pelicula->execute()) {
        throw new Exception("Error al insertar la película: " . $stmt_pelicula->error);
    }
    

    $pelicula_id = $conn->insert_id;
    $stmt_pelicula->close();
    

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

    
    $estado_cinta = 'disponible';
    $sql_cinta = "INSERT INTO cinta (estado, pelicula_id_pelicula) VALUES (?, ?)";
    $stmt_cinta = $conn->prepare($sql_cinta);

    
    for ($i = 0; $i < $ncopias; $i++) {
        
        if (!$stmt_cinta->bind_param("si", $estado_cinta, $pelicula_id)) {
            throw new Exception("Error al enlazar parámetros de cinta.");
        }
        if (!$stmt_cinta->execute()) {
            throw new Exception("Error al insertar la cinta #".($i + 1).": " . $stmt_cinta->error);
        }
    }
    $stmt_cinta->close();


    
    $conn->commit();
    $response["success"] = true;
    $response["message"] = "Película '$titulo' (ID $pelicula_id) agregada correctamente con sus relaciones y $ncopias cintas disponibles.";

} catch (Exception $e) {
    
    $conn->rollback();
    $response["message"] = "Fallo en la transacción: " . $e->getMessage();
}


$conn->close();
echo json_encode($response);

?>