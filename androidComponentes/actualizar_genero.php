<?php

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

$conn = new mysqli("localhost", "root", "", "mydb");
$conn->set_charset("utf8mb4");

$id = $_POST['id_genero'] ?? 0;
$nombre = $_POST['nombre'] ?? '';

if (empty($nombre)) {
    echo json_encode(["success" => false, "message" => "El nombre es obligatorio"]);
    exit();
}

$sql = "UPDATE genero SET nombre = ? WHERE id_genero = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("si", $nombre, $id);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Género actualizado exitosamente"]);
} else {
    echo json_encode(["success" => false, "message" => "Error al actualizar"]);
}

$conn->close();
?>