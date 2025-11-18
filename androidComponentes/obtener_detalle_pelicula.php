<?php
// obtener_detalle_pelicula.php

// 🚨 Reemplaza 'conexion.php' con tu ruta real de conexión si es diferente.
require_once 'conexion.php'; 

header('Content-Type: application/json');

$conn = Conectar();

if (!$conn) {
    http_response_code(500); 
    echo json_encode(["success" => false, "message" => "Error de conexión a la base de datos."]);
    exit();
}

if (!isset($_POST['id_pelicula'])) {
    http_response_code(400); 
    echo json_encode(["success" => false, "message" => "ID de película no proporcionado."]);
    mysqli_close($conn);
    exit();
}

$id_pelicula = (int)$_POST['id_pelicula'];

$sql = "SELECT 
            p.id_pelicula, 
            p.titulo, 
            p.descripcion, 
            p.anio AS anio_lanzamiento,  
            p.duracion_min AS duracion, 
            p.calificacion AS clasificacion, 
            p.idioma, 
            p.poster_path AS imagen_url,
            p.precio_alquiler, 
            p.ncopias AS copias_totales,
            
            (SELECT COUNT(id_cinta) FROM cinta WHERE pelicula_id_pelicula = p.id_pelicula AND estado = 'disponible') AS copias_disponibles,
            
            d.nombre AS director_nombre, 
            
            GROUP_CONCAT(DISTINCT g.nombre SEPARATOR ', ') AS generos_detalle,
            
            GROUP_CONCAT(DISTINCT a.nombre SEPARATOR ', ') AS elenco
            
        FROM pelicula p
        
        JOIN director d ON p.director_id_director = d.id_director
        
        LEFT JOIN pelicula_genero pg ON p.id_pelicula = pg.pelicula_id_pelicula
        LEFT JOIN genero g ON pg.genero_id_genero = g.id_genero
        
        LEFT JOIN pelicula_actor pa ON p.id_pelicula = pa.pelicula_id_pelicula
        LEFT JOIN actor a ON pa.actor_id_actor = a.id_actor
        
        WHERE p.id_pelicula = ?
        
        GROUP BY 
            p.id_pelicula, p.titulo, p.descripcion, p.anio, p.duracion_min, 
            p.calificacion, p.idioma, p.poster_path, p.precio_alquiler, 
            p.ncopias, d.nombre";
        
$stmt = mysqli_prepare($conn, $sql);

if ($stmt === false) {
    http_response_code(500);
    echo json_encode(["success" => false, "message" => "Error al preparar la consulta: " . mysqli_error($conn)]);
    mysqli_close($conn);
    exit();
}

mysqli_stmt_bind_param($stmt, "i", $id_pelicula);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

if (mysqli_num_rows($result) === 1) {
    $pelicula = mysqli_fetch_assoc($result);

    $pelicula['id_pelicula'] = (int)$pelicula['id_pelicula'];
    $pelicula['anio_lanzamiento'] = (int)$pelicula['anio_lanzamiento'];
    $pelicula['duracion'] = (int)$pelicula['duracion'];
    $pelicula['precio_alquiler'] = (double)$pelicula['precio_alquiler']; 
    $pelicula['copias_disponibles'] = (int)$pelicula['copias_disponibles'];
    $pelicula['copias_totales'] = (int)$pelicula['copias_totales']; 

    echo json_encode(["success" => true, "pelicula" => $pelicula]);
} else {
    http_response_code(404);
    echo json_encode(["success" => false, "message" => "Película con ID $id_pelicula no encontrada."]);
}

mysqli_stmt_close($stmt);
mysqli_close($conn);
?>