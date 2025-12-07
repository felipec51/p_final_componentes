<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

require_once 'conexion.php'; 
$conn = Conectar();

$id = $_POST['id_genero'] ?? 0;

$sql = "DELETE FROM genero WHERE id_genero = ?";
$stmt = $conn->prepare($sql);

if ($stmt) {
    $stmt->bind_param("i", $id);
    
    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Género eliminado exitosamente"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al eliminar"]);
    }
    
    $stmt->close();
} else {
    // Esto captura errores si la consulta SQL está mal escrita o la BD falla
    echo json_encode(["success" => false, "message" => "Error en la preparación de la consulta"]);
}

$conn->close();
?>