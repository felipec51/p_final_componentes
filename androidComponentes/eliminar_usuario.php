<?php



require_once 'conexion.php'; 

$response = array();


if ($_SERVER['REQUEST_METHOD'] !== 'POST' || !isset($_POST['id_usuario']) || empty($_POST['id_usuario'])) {
    $response['success'] = false;
    $response['message'] = 'Solicitud POST o ID de usuario no válidos.';
    header('Content-Type: application/json');
    echo json_encode($response);
    exit;
}


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


foreach ($sqls_delete_related as $sql_related) {
    if (!mysqli_query($link, $sql_related)) {
        $error_found = true;
        
        $error_message = "Error al limpiar: " . mysqli_error($link);
        break; 
    }
}

if (!$error_found) {
    $sql_delete_user = "DELETE FROM Usuario WHERE id_usuario = '$id_usuario'";

    if (mysqli_query($link, $sql_delete_user)) {
        if (mysqli_affected_rows($link) > 0) {
            
            mysqli_commit($link);
            $response['success'] = true;
            $response['message'] = 'Usuario eliminado correctamente.';
        } else {
            
            mysqli_rollback($link); 
            $response['success'] = false;
            $response['message'] = 'No se encontró el usuario con ID: ' . $id_usuario;
        }
    } else {
        
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