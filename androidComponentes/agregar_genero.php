<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

require_once 'conexion.php'; 
$conn = Conectar();

$nombre = $_POST['nombre'] ?? '';

// Validación básica
if (empty($nombre)) {
    echo json_encode(["success" => false, "message" => "El nombre es obligatorio"]);
    exit();
}

$sql = "INSERT INTO genero (nombre) VALUES (?)";
$stmt = $conn->prepare($sql);

if ($stmt) {
    $stmt->bind_param("s", $nombre);
    
    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Género agregado exitosamente"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al agregar: " . $stmt->error]);
    }
    
    $stmt->close();
} else {
    echo json_encode(["success" => false, "message" => "Error al preparar la consulta"]);
}

$conn->close();
?>