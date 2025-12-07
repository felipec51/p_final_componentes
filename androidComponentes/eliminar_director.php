<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

require_once 'conexion.php'; 
$conn = Conectar();

$id = $_POST['id_director'] ?? 0;

$sql = "DELETE FROM director WHERE id_director = ?";
$stmt = $conn->prepare($sql);

if ($stmt) {
    $stmt->bind_param("i", $id);
    
    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Director eliminado exitosamente"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al eliminar"]);
    }
    
    $stmt->close();
} else {
    echo json_encode(["success" => false, "message" => "Error al preparar la consulta"]);
}

$conn->close();
?>