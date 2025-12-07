<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

// 1. Usamos tu archivo de conexión
require_once 'conexion.php'; 
$conn = Conectar();

// 2. Ejecutamos la consulta
$sql = "SELECT * FROM director ORDER BY nombre ASC";
$result = $conn->query($sql);

$data = [];

if ($result) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

echo json_encode(["success" => true, "data" => $data]);

$conn->close();
?>