<?php

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

$conn = new mysqli("localhost", "root", "", "mydb");
$conn->set_charset("utf8mb4");

$id = $_POST['id_actor'] ?? 0;

$sql = "DELETE FROM actor WHERE id_actor = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Actor eliminado exitosamente"]);
} else {
    echo json_encode(["success" => false, "message" => "Error al eliminar"]);
}

$conn->close();
?>