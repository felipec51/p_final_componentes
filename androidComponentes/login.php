<?php
// login.php

// Muestra todos los errores de PHP para debug.
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Incluye el archivo de conexión
include('conexion.php');
$link = Conectar();

// 1. Obtiene los datos enviados desde la aplicación de Android (Volley)
// ¡CORREGIDO! Usamos 'user' que viene de Android y lo buscamos en el campo 'username'
$username_o_email_input = isset($_REQUEST['user']) ? $_REQUEST['user'] : '';
$pass = isset($_REQUEST['passw']) ? $_REQUEST['passw'] : '';

// 2. Validación Básica
if (empty($username_o_email_input) || empty($pass)) {
    echo "ERROR 1"; // Faltan campos
    exit;
}

// 3. Consulta SQL: CORREGIDA para usar el nombre de tabla y columna correctos.
$user_safe = mysqli_real_escape_string($link, $username_o_email_input);
$pass_safe = mysqli_real_escape_string($link, $pass);

// La tabla es 'Usuario' y el campo para el usuario es 'username'.
$sql = "SELECT rol_id_rol FROM Usuario WHERE username = '$user_safe' AND password = '$pass_safe'";

// 4. Ejecutar y Manejar la Consulta
$res = mysqli_query($link, $sql);

if (!$res) {
    // Error de consulta SQL
    die("ERROR_SQL_QUERY: " . mysqli_error($link));
} 
else if (mysqli_num_rows($res) > 0) {
    // Éxito: Se encontró al usuario. Obtenemos el rol.
    $fila = mysqli_fetch_assoc($res);
    $rol = $fila['rol_id_rol'];

    // Devolvemos el rol del usuario (1 para admin, 2 para socio)
    echo $rol; 
} else {
    // Falla: Credenciales no coinciden
    echo "ERROR 2"; 
}

// 5. Cerrar Conexión
mysqli_close($link);
?>