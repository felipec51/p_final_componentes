<?php
// actualizar_pelicula.php

require_once 'conexion.php'; 
$link = Conectar();

header('Content-Type: application/json');

if (!$link) {
    echo json_encode(['success' => false, 'message' => 'Error de conexión a la base de datos.']);
    exit;
}

// Validar campos necesarios
if (
    !isset($_POST['id_pelicula']) || 
    !isset($_POST['titulo']) || 
    !isset($_POST['anio']) || 
    !isset($_POST['calificacion']) ||
    !isset($_POST['idioma']) ||
    !isset($_POST['descripcion']) ||
    !isset($_POST['duracion_min']) || // Nuevo campo añadido
    !isset($_POST['poster_path']) 
) {
    http_response_code(400);
    echo json_encode(['success' => false, 'message' => 'Faltan campos obligatorios para la actualización.']);
    mysqli_close($link);
    exit;
}

$id_pelicula = mysqli_real_escape_string($link, $_POST['id_pelicula']);
$titulo = mysqli_real_escape_string($link, $_POST['titulo']);
$anio = mysqli_real_escape_string($link, $_POST['anio']);
$calificacion = mysqli_real_escape_string($link, $_POST['calificacion']);
$idioma = mysqli_real_escape_string($link, $_POST['idioma']);
$descripcion = mysqli_real_escape_string($link, $_POST['descripcion']);
$duracion_min = mysqli_real_escape_string($link, $_POST['duracion_min']);
$poster_path = mysqli_real_escape_string($link, $_POST['poster_path']);

//  Consulta SQL de ACTUALIZACIÓN (incluyendo duracion_min y descripcion)
$sql = "UPDATE pelicula SET 
            titulo = '$titulo',
            descripcion = '$descripcion',
            anio = '$anio',
            duracion_min = '$duracion_min',
            calificacion = '$calificacion',
            idioma = '$idioma',
            poster_path = '$poster_path'
        WHERE 
            id_pelicula = $id_pelicula";

$resultado = mysqli_query($link, $sql);

if ($resultado) {
    $filas_afectadas = mysqli_affected_rows($link);
    mysqli_close($link);
    
    if ($filas_afectadas >= 0) { // Cero filas afectadas es éxito, solo si no se cambió nada
        echo json_encode(['success' => true, 'message' => "Película con ID $id_pelicula actualizada correctamente."]);
    } else {
        // En un caso ideal, si mysqli_affected_rows() fuera -1 indicaría error
        echo json_encode(['success' => false, 'message' => "Película encontrada, pero no se pudo determinar el resultado de la actualización."]);
    }
} else {
    http_response_code(500);
    echo json_encode(['success' => false, 'message' => 'Error al actualizar la película: ' . mysqli_error($link)]);
    mysqli_close($link);
}
?>