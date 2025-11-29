<?php

require_once 'conexion.php'; 

$response = array();

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    $response['success'] = false;
    $response['message'] = 'Método no permitido. Use POST.';
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}


if (
    !isset($_POST['id_usuario']) || empty($_POST['id_usuario']) ||
    !isset($_POST['username']) || empty($_POST['username']) ||
    !isset($_POST['nombre']) || empty($_POST['nombre']) ||
    !isset($_POST['email']) || empty($_POST['email'])
) {
    $response['success'] = false;
    $response['message'] = 'Faltan campos obligatorios para la actualización (ID, Username, Nombre, Email).';
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}


$link = Conectar();

if (!$link) {
    $response['success'] = false;
    $response['message'] = 'Error de conexión a la base de datos: ' . mysqli_connect_error();
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}


$id_usuario = mysqli_real_escape_string($link, $_POST['id_usuario']);
$username = mysqli_real_escape_string($link, $_POST['username']);
$nombre = mysqli_real_escape_string($link, $_POST['nombre']);
$direccion = mysqli_real_escape_string($link, $_POST['direccion']);
$telefono = mysqli_real_escape_string($link, $_POST['telefono']);
$email = mysqli_real_escape_string($link, $_POST['email']);
$password_received = isset($_POST['password']) ? mysqli_real_escape_string($link, $_POST['password']) : null;


$sql = "UPDATE Usuario SET 
            username = '$username',
            nombre = '$nombre',
            direccion = '$direccion',
            telefono = '$telefono',
            email = '$email'";


if (!empty($password_received)) {
    $sql .= ", password = '$password_received'";
}

$sql .= " WHERE id_usuario = '$id_usuario'";


if (mysqli_query($link, $sql)) {
    if (mysqli_affected_rows($link) > 0) {
        $response['success'] = true;
        $response['message'] = ' Usuario actualizado correctamente.';
    } else {

        $response['success'] = true; 
        $response['message'] = '👍 Usuario encontrado, pero no se detectaron cambios.';
    }
} else {
    $response['success'] = false;
    $response['message'] = ' Error al actualizar en la DB: ' . mysqli_error($link);
}

mysqli_close($link);
header('Content-Type: application/json');
echo json_encode($response);
?>