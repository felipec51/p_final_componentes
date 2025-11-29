<?php

ini_set('display_errors', 0);
ini_set('log_errors', 1);
header("Content-Type: application/json");

require_once "conexion.php";

try {
    $conn = Conectar();
    if (!$conn) {
        throw new Exception("Error de conexión a la BD.");
    }

    date_default_timezone_set("America/Bogota");

    
    $id_usuario = isset($_POST["id_usuario"]) ? intval($_POST["id_usuario"]) : 0;
    $id_pelicula = isset($_POST["id_pelicula"]) ? intval($_POST["id_pelicula"]) : 0;

    if ($id_usuario <= 0 || $id_pelicula <= 0) {
        throw new Exception("Datos inválidos.");
    }

    $sql_check = "SELECT id_espera FROM lista_espera 
                  WHERE Usuario_id_usuario = ? 
                  AND pelicula_id_pelicula = ?";
    
    $stmt_check = mysqli_prepare($conn, $sql_check);
    if (!$stmt_check) throw new Exception("Error preparando verificación: " . mysqli_error($conn));
    
    mysqli_stmt_bind_param($stmt_check, "ii", $id_usuario, $id_pelicula);
    mysqli_stmt_execute($stmt_check);
    mysqli_stmt_store_result($stmt_check);

    
    if (mysqli_stmt_num_rows($stmt_check) > 0) {
        echo json_encode([
            "success" => false, 
            "message" => "Ya te encuentras en la lista de espera para esta película."
        ]);
        mysqli_stmt_close($stmt_check);
        exit(); 
    }
    mysqli_stmt_close($stmt_check);

    $fecha_solicitud = date("Y-m-d H:i:s");
    $sql_insert = "INSERT INTO lista_espera (fecha_solicitud, Usuario_id_usuario, pelicula_id_pelicula) 
                   VALUES (?, ?, ?)";

    $stmt_insert = mysqli_prepare($conn, $sql_insert);
    if (!$stmt_insert) throw new Exception("Error preparando inserción: " . mysqli_error($conn));

    mysqli_stmt_bind_param($stmt_insert, "sii", $fecha_solicitud, $id_usuario, $id_pelicula);

    if (mysqli_stmt_execute($stmt_insert)) {
        echo json_encode([
            "success" => true,
            "message" => "Agregado a la lista de espera exitosamente."
        ]);
    } else {
        throw new Exception("Error al guardar: " . mysqli_stmt_error($stmt_insert));
    }

    mysqli_stmt_close($stmt_insert);
    mysqli_close($conn);

} catch (Exception $e) {
    
    http_response_code(200); 
    echo json_encode([
        "success" => false, 
        "message" => "Error: " . $e->getMessage()
    ]);
}
?>