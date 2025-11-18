<?php


require_once 'conexion.php';
$conexion = Conectar();


$usuario  = $_POST['usuario'] ?? '';
$pregunta = $_POST['pregunta'] ?? '';
$respuesta = $_POST['respuesta'] ?? '';


if (empty($usuario) || empty($respuesta) || empty($pregunta)) {
    echo "ERROR_CAMPOS_VACIOS";
    $conexion->close();
    exit;
}


$sql_select = "SELECT id_usuario, respuesta_seguridad 
               FROM Usuario 
               WHERE (username = ? OR email = ?) AND pregunta_seguridad = ?";

$stmt = $conexion->prepare($sql_select);
$stmt->bind_param("sss", $usuario, $usuario, $pregunta);
$stmt->execute();
$resultado = $stmt->get_result();

if ($resultado->num_rows === 1) {
    $fila = $resultado->fetch_assoc();
    $id_usuario = $fila['id_usuario'];
    $respuesta_db = $fila['respuesta_seguridad'];


    if (trim(strtolower($respuesta)) === trim(strtolower($respuesta_db))) {
        
        $nueva_contrasena = "temporal123"; 


        $sql_update = "UPDATE Usuario SET password = ? WHERE id_usuario = ?";
        $stmt_update = $conexion->prepare($sql_update);
        $stmt_update->bind_param("si", $nueva_contrasena, $id_usuario);
        
        if ($stmt_update->execute()) {

            echo "SUCCESS";
        } else {

            echo "ERROR_UPDATE_PASSWORD";
        }
        $stmt_update->close();
        
    } else {

        echo "ERROR_ANSWER";
    }
} else {

    echo "ERROR_USER";
}

$stmt->close();
$conexion->close();

?>