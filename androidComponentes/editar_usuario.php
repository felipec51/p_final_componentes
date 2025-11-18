<?php
// editar_usuario.php - Script para actualizar un usuario

require_once 'conexion.php'; 

$response = array();

// 1. Verificar el método y la existencia de datos mínimos
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    $response['success'] = false;
    $response['message'] = 'Método no permitido. Use POST.';
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}

// 2. Validar campos requeridos
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

// 3. Conectar a la base de datos
$link = Conectar();

if (!$link) {
    $response['success'] = false;
    $response['message'] = 'Error de conexión a la base de datos: ' . mysqli_connect_error();
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}

// 4. Sanitizar y obtener datos
$id_usuario = mysqli_real_escape_string($link, $_POST['id_usuario']);
$username = mysqli_real_escape_string($link, $_POST['username']);
$nombre = mysqli_real_escape_string($link, $_POST['nombre']);
$direccion = mysqli_real_escape_string($link, $_POST['direccion']);
$telefono = mysqli_real_escape_string($link, $_POST['telefono']);
$email = mysqli_real_escape_string($link, $_POST['email']);
$password_received = isset($_POST['password']) ? mysqli_real_escape_string($link, $_POST['password']) : null;

// 5. Construir la consulta de actualización
$sql = "UPDATE Usuario SET 
            username = '$username',
            nombre = '$nombre',
            direccion = '$direccion',
            telefono = '$telefono',
            email = '$email'";

// Solo actualizar la contraseña si se proporcionó una nueva
if (!empty($password_received)) {
    // Si usaras hashing (como bcrypt), aquí harías el hash: $hashed_password = password_hash($password_received, PASSWORD_DEFAULT);
    // Pero asumiendo que usas texto plano como en tu volcado SQL:
    $sql .= ", password = '$password_received'";
}

$sql .= " WHERE id_usuario = '$id_usuario'";

// 6. Ejecutar la consulta
if (mysqli_query($link, $sql)) {
    if (mysqli_affected_rows($link) > 0) {
        $response['success'] = true;
        $response['message'] = '✅ Usuario actualizado correctamente.';
    } else {
        // No hubo error, pero tampoco cambios (el usuario no cambió nada o el ID no existe)
        $response['success'] = true; 
        $response['message'] = '👍 Usuario encontrado, pero no se detectaron cambios.';
    }
} else {
    $response['success'] = false;
    $response['message'] = '❌ Error al actualizar en la DB: ' . mysqli_error($link);
}

// 7. Cerrar la conexión y devolver la respuesta
mysqli_close($link);
header('Content-Type: application/json');
echo json_encode($response);
?>