<?php
// login.php

// 1. INICIAR EL BUFFER DE SALIDA
// Captura cualquier advertencia o texto no deseado de archivos incluidos.
ob_start();

// Muestra todos los errores de PHP para debug.
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Incluye el archivo de conexión y establece la conexión
include('conexion.php');
$link = Conectar();

// 2. LIMPIAR EL BUFFER DESPUÉS DE LA CONEXIÓN
// Eliminamos todo el contenido (warnings, notices, etc.) generado por 'conexion.php'.
ob_clean();

// 3. Obtiene los datos enviados desde la aplicación de Android (Volley)
$user = isset($_REQUEST['user']) ? $_REQUEST['user'] : '';
$pass = isset($_REQUEST['passw']) ? $_REQUEST['passw'] : '';

// 4. Validación Básica
if (empty($user) || empty($pass)) {
    echo "ERROR 1"; // Faltan campos
    exit;
}

// 5. Consulta SQL: SELECCIONAR EL CAMPO 'rol_id_rol' (NOMBRE CORRECTO DE LA COLUMNA)
$user_safe = mysqli_real_escape_string($link, $user);
$pass_safe = mysqli_real_escape_string($link, $pass);

// Las columnas de la BD son 'username', 'password' y 'rol_id_rol' en la tabla 'Usuario'
$sql = "SELECT username, rol_id_rol FROM Usuario WHERE username = '$user_safe' AND password = '$pass_safe'";

// 6. Ejecutar y Manejar la Consulta
$res = mysqli_query($link, $sql);

if (!$res) {
    // Si la consulta SQL tiene un error de sintaxis o la tabla no existe
    die("ERROR_SQL_QUERY: " . mysqli_error($link)); 
} 
else if (mysqli_num_rows($res) > 0) {
    // Éxito: Se encontró al usuario
    $fila = mysqli_fetch_assoc($res);
    $rol = $fila['rol_id_rol']; // Obtener el valor del campo 'rol_id_rol'
    
    // Devolver el rol (ej: "1" o "2") al cliente de Android
    echo $rol;
} else {
    // Falla: Credenciales no coinciden
    echo "ERROR 2"; 
}

// 7. Cerrar Conexión
mysqli_close($link);
?>