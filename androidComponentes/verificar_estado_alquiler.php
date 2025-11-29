<?php

require_once 'conexion.php';
header('Content-Type: application/json');

$conn = Conectar();

if (!$conn) {

    echo json_encode(["tiene_alquiler" => false, "id_prestamo" => -1, "fecha_devolucion" => ""]);
    exit();
}

$id_usuario  = isset($_POST['id_usuario'])  ? (int)$_POST['id_usuario']  : 0;
$id_pelicula = isset($_POST['id_pelicula']) ? (int)$_POST['id_pelicula'] : 0;

if ($id_usuario <= 0 || $id_pelicula <= 0) {
    echo json_encode(["tiene_alquiler" => false, "id_prestamo" => -1, "fecha_devolucion" => ""]);
    exit();
}


$sql = "SELECT p.id_prestamo, p.fecha_devolucion 
        FROM prestamo p
        JOIN cinta c ON p.cinta_id_cinta = c.id_cinta 
        WHERE p.Usuario_id_usuario = ? 
          AND c.pelicula_id_pelicula = ? 
          AND p.estado_alquiler = 'en curso'
        LIMIT 1";

$stmt = mysqli_prepare($conn, $sql);
mysqli_stmt_bind_param($stmt, "ii", $id_usuario, $id_pelicula);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

$response = [
    "tiene_alquiler" => false,
    "id_prestamo" => -1,
    "fecha_devolucion" => ""
];

if (mysqli_num_rows($result) > 0) {
    $row = mysqli_fetch_assoc($result);
    $response["tiene_alquiler"] = true;
    $response["id_prestamo"] = (int)$row['id_prestamo'];
    $response["fecha_devolucion"] = $row['fecha_devolucion'];
}

mysqli_stmt_close($stmt);
mysqli_close($conn);

echo json_encode($response);
