<?php

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

$conn = new mysqli("localhost", "root", "", "mydb");
$conn->set_charset("utf8mb4");

$id = $_POST['id_genero'] ?? 0;

$sql = "DELETE FROM genero WHERE id_genero = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Género eliminado exitosamente"]);
} else {
    echo json_encode(["success" => false, "message" => "Error al eliminar"]);
}

$conn->close();
?>