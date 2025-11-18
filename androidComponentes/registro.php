<?php

require_once 'conexion.php'; 


if ($_SERVER['REQUEST_METHOD'] != 'POST') {

    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Método no permitido.']);
    exit;
}


$link = Conectar();


$username = isset($_POST['username']) ? mysqli_real_escape_string($link, $_POST['username']) : null;
$password = isset($_POST['password']) ? mysqli_real_escape_string($link, $_POST['password']) : null;
$nombre = isset($_POST['nombre']) ? mysqli_real_escape_string($link, $_POST['nombre']) : null;
$direccion = isset($_POST['direccion']) ? mysqli_real_escape_string($link, $_POST['direccion']) : null;
$telefono = isset($_POST['telefono']) ? mysqli_real_escape_string($link, $_POST['telefono']) : null;
$email = isset($_POST['email']) ? mysqli_real_escape_string($link, $_POST['email']) : null;


$pregunta_seguridad = isset($_POST['pregunta_seguridad']) ? mysqli_real_escape_string($link, $_POST['pregunta_seguridad']) : null;
$respuesta_seguridad = isset($_POST['respuesta_seguridad']) ? mysqli_real_escape_string($link, $_POST['respuesta_seguridad']) : null;


$rol_id_rol = 2; 


if (empty($username) || empty($password) || empty($nombre) || empty($email) || empty($pregunta_seguridad) || empty($respuesta_seguridad)) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Faltan campos obligatorios. Asegúrate de ingresar el usuario, contraseña, nombre, email y la pregunta de seguridad.']);
    mysqli_close($link);
    exit;
}


$sql = "INSERT INTO `Usuario` 
        (`username`, `password`, `nombre`, `direccion`, `telefono`, `email`, `fecha_creacion`, `rol_id_rol`, `pregunta_seguridad`, `respuesta_seguridad`) 
        VALUES 
        (
            '$username', 
            '$password', 
            '$nombre', 
            '$direccion', 
            '$telefono', 
            '$email', 
            NOW(), 
            $rol_id_rol,
            '$pregunta_seguridad',  
            '$respuesta_seguridad'  
        )";

//  Ejecutar la consulta
if (mysqli_query($link, $sql)) {

    header('Content-Type: application/json');
    echo json_encode(['success' => true, 'message' => 'Registro exitoso.', 'id_usuario' => mysqli_insert_id($link)]);
} else {
    // Error en la base de datos
    header('Content-Type: application/json');
    // Error 1062 es por clave duplicada (ej. username o email ya existe)
    if (mysqli_errno($link) == 1062) {
        echo json_encode(['success' => false, 'message' => 'El nombre de usuario o email ya está en uso.']);
    } else {
        echo json_encode(['success' => false, 'message' => 'Error al registrar el usuario: ' . mysqli_error($link)]);
    }
}

//Cerrar la conexión
mysqli_close($link);

?>