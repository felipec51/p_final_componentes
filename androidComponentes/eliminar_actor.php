<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

require_once 'conexion.php'; 
$conn = Conectar();

$id = $_POST['id_actor'] ?? 0;

$sql = "DELETE FROM actor WHERE id_actor = ?";
$stmt = $conn->prepare($sql);

if ($stmt) {
    $stmt->bind_param("i", $id);
    
    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Actor eliminado exitosamente"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al eliminar"]);
    }
    
    $stmt->close();
} else {
    echo json_encode(["success" => false, "message" => "Error al preparar la consulta"]);
}

$conn->close();
?>