<?php

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

$conn = new mysqli("localhost", "root", "", "mydb");
$conn->set_charset("utf8mb4");

$id = $_POST['id_director'] ?? 0;

$sql = "DELETE FROM director WHERE id_director = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Director eliminado exitosamente"]);
} else {
    echo json_encode(["success" => false, "message" => "Error al eliminar"]);
}

$conn->close();
?>