<?php

header('Content-Type: application/json; charset=utf-8');
require_once 'conexion.php';

$conn = Conectar();

if (!$conn) {
    echo json_encode(["success" => false, "message" => "Error de conexión a BD"]);
    exit();
}

$id_usuario = isset($_POST['id_usuario']) ? (int)$_POST['id_usuario'] : 0;
$id_pelicula = isset($_POST['id_pelicula']) ? (int)$_POST['id_pelicula'] : 0;

if ($id_usuario <= 0 || $id_pelicula <= 0) {
    echo json_encode(["success" => false, "message" => "Datos inválidos"]);
    exit();
}

try {
    
    $sql = "DELETE FROM lista_espera WHERE Usuario_id_usuario = ? AND pelicula_id_pelicula = ?";

    $stmt = mysqli_prepare($conn, $sql);
    mysqli_stmt_bind_param($stmt, "ii", $id_usuario, $id_pelicula);

    if (mysqli_stmt_execute($stmt)) {
        if (mysqli_stmt_affected_rows($stmt) > 0) {
            echo json_encode(["success" => true, "message" => "Eliminado correctamente"]);
        } else {
            
            echo json_encode(["success" => true, "message" => "No estaba en la lista, pero todo ok"]);
        }
    } else {
        throw new Exception("Error al ejecutar delete");
    }
    mysqli_stmt_close($stmt);
} catch (Exception $e) {
    echo json_encode(["success" => false, "message" => "Error: " . $e->getMessage()]);
} finally {
    mysqli_close($conn);
}
