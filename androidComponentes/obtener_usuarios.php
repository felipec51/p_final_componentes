<?php
// obtener_usuarios.php - Versión de carga corregida

require_once 'conexion.php'; 


$link = Conectar();

if (!$link) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Error de conexión a la base de datos: ' . mysqli_connect_error()]);
    exit;
}

// Preparar la consulta SQL para obtener los usuarios
$sql = "SELECT 
            u.id_usuario, 
            u.username, 
            u.nombre, 
            u.direccion, 
            u.telefono, 
            u.email, 
            u.fecha_creacion, 
            r.nombre AS nombre_rol,
            u.rol_id_rol
        FROM 
            Usuario u
        JOIN 
            rol r ON u.rol_id_rol = r.id_rol
        ORDER BY 
            u.id_usuario DESC";

// Ejecutar la consulta
$resultado = mysqli_query($link, $sql);


if ($resultado) {
    $usuarios = array();
    
    // Convertir cada fila a un array asociativo
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $fila['fecha_creacion'] = date('Y-m-d H:i', strtotime($fila['fecha_creacion']));
        $usuarios[] = $fila;
    }

    mysqli_free_result($resultado);

    header('Content-Type: application/json');
    echo json_encode(['success' => true, 'usuarios' => $usuarios]);
} else {
    // Error en la base de datos
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Error al consultar usuarios: ' . mysqli_error($link)]);
}

mysqli_close($link);

?>