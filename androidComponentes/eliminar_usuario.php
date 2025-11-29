<?php
// eliminar_usuario.php - VERSIÓN FINAL Y TRANSACCIONAL

// Asegúrate de que esta ruta sea correcta para tu servidor
require_once 'conexion.php'; 

$response = array();

// 1. Verificar el método y el ID
if ($_SERVER['REQUEST_METHOD'] !== 'POST' || !isset($_POST['id_usuario']) || empty($_POST['id_usuario'])) {
    $response['success'] = false;
    $response['message'] = 'Solicitud POST o ID de usuario no válidos.';
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}

// 2. Conectar a la base de datos
$link = Conectar();

if (!$link) {
    $response['success'] = false;
    $response['message'] = ' Error fatal de conexión a DB: ' . mysqli_connect_error();
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}


mysqli_begin_transaction($link); 
$id_usuario = mysqli_real_escape_string($link, $_POST['id_usuario']);
$error_found = false;
$error_message = '';

$sqls_delete_related = [

    "DELETE FROM gusta_director WHERE Usuario_id_usuario = '$id_usuario'",
    "DELETE FROM gusto_actor WHERE Usuario_id_usuario = '$id_usuario'",
    "DELETE FROM gusto_genero WHERE Usuario_id_usuario = '$id_usuario'",
    "DELETE FROM lista_espera WHERE Usuario_id_usuario = '$id_usuario'",
    "DELETE FROM prestamo WHERE Usuario_id_usuario = '$id_usuario'"
];

// Ejecutar la limpieza. Si falla alguna, detenemos la operación.
foreach ($sqls_delete_related as $sql_related) {
    if (!mysqli_query($link, $sql_related)) {
        $error_found = true;
        // Capturamos el error específico de MySQL
        $error_message = "Error al limpiar: " . mysqli_error($link);
        break; 
    }
}

// Intentar eliminar el usuario principal
if (!$error_found) {
    $sql_delete_user = "DELETE FROM Usuario WHERE id_usuario = '$id_usuario'";

    if (mysqli_query($link, $sql_delete_user)) {
        if (mysqli_affected_rows($link) > 0) {
            // Éxito total: confirmar la transacción
            mysqli_commit($link);
            $response['success'] = true;
            $response['message'] = 'Usuario eliminado correctamente.';
        } else {
            // Usuario no encontrado: deshacer la limpieza (si se hizo)
            mysqli_rollback($link); 
            $response['success'] = false;
            $response['message'] = 'No se encontró el usuario con ID: ' . $id_usuario;
        }
    } else {
        // Error al eliminar el usuario: deshacer todo
        mysqli_rollback($link); 
        $response['success'] = false;
        $response['message'] = 'Error al eliminar el usuario: ' . mysqli_error($link);
    }
} else {

    mysqli_rollback($link); 
    $response['success'] = false;
    $response['message'] = $error_message; 
}

mysqli_close($link);
header('Content-Type: application/json');
echo json_encode($response);
?>