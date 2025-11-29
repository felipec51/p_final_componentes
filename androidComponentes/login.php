<?php
// login.php
require_once 'conexion.php';
header('Content-Type: application/json'); // Importante para que Android entienda JSON

$conn = Conectar();

if (!$conn) {
    echo json_encode(["success" => false, "message" => "Error de conexión BD"]);
    exit;
}

$user = isset($_POST['user']) ? $_POST['user'] : '';
$pass = isset($_POST['passw']) ? $_POST['passw'] : '';

if (empty($user) || empty($pass)) {
    echo json_encode(["success" => false, "message" => "Campos vacíos"]);
    exit;
}

// Escapar para seguridad
$user_safe = mysqli_real_escape_string($conn, $user);
$pass_safe = mysqli_real_escape_string($conn, $pass);

// Seleccionamos TODOS los datos necesarios para el objeto Usuario.java
$sql = "SELECT id_usuario, username, nombre, direccion, telefono, email, fecha_creacion, rol_id_rol 
        FROM Usuario 
        WHERE username = '$user_safe' AND password = '$pass_safe'";

$res = mysqli_query($conn, $sql);

if ($res && mysqli_num_rows($res) > 0) {
    $fila = mysqli_fetch_assoc($res);
    
    // Devolvemos éxito y el objeto de usuario completo
    echo json_encode([
        "success" => true,
        "rol" => $fila['rol_id_rol'],
        "usuario" => [
            "id" => (int)$fila['id_usuario'],
            "username" => $fila['username'],
            "nombre" => $fila['nombre'],
            // No enviamos el password por seguridad, o si quieres lo agregas
            "direccion" => $fila['direccion'],
            "telefono" => $fila['telefono'],
            "email" => $fila['email'],
            "fechaCreacion" => $fila['fecha_creacion'],
            "rolIdRol" => (int)$fila['rol_id_rol']
        ]
    ]);
} else {
    echo json_encode(["success" => false, "message" => "Credenciales incorrectas"]);
}

mysqli_close($conn);
?>