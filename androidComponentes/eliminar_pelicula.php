<?php



require_once 'conexion.php'; 
$link = Conectar();

header('Content-Type: application/json');


if ($_SERVER['REQUEST_METHOD'] !== 'POST' || !isset($_POST['id_pelicula']) || empty($_POST['id_pelicula'])) {
    $message = ($_SERVER['REQUEST_METHOD'] !== 'POST') ? 'Método no permitido. Use POST.' : 'Falta el parámetro id_pelicula.';
    echo json_encode(['success' => false, 'message' => $message]);
    mysqli_close($link);
    exit;
}

$id_pelicula = mysqli_real_escape_string($link, $_POST['id_pelicula']);


mysqli_begin_transaction($link);
$success = true;
$message = 'Película y todos los datos relacionados (cintas, préstamos, etc.) eliminados correctamente.';

try {

    $cinta_ids_query = "SELECT id_cinta FROM cinta WHERE pelicula_id_pelicula = '$id_pelicula'";
    $cinta_result = mysqli_query($link, $cinta_ids_query);
    
    $cinta_ids = [];
    while ($row = mysqli_fetch_assoc($cinta_result)) {
        $cinta_ids[] = $row['id_cinta'];
    }
    
    
    if (!empty($cinta_ids)) {
        $ids_string = implode(',', $cinta_ids);

        
        $sql_prestamos = "DELETE FROM prestamo WHERE cinta_id_cinta IN ($ids_string)";
        if (!mysqli_query($link, $sql_prestamos)) {
            throw new Exception("Error al eliminar préstamos: " . mysqli_error($link));
        }


        $sql_cintas = "DELETE FROM cinta WHERE pelicula_id_pelicula = '$id_pelicula'";
        if (!mysqli_query($link, $sql_cintas)) {
            throw new Exception("Error al eliminar cintas: " . mysqli_error($link));
        }
    }
    

    $sql_actores = "DELETE FROM pelicula_actor WHERE pelicula_id_pelicula = '$id_pelicula'";
    if (!mysqli_query($link, $sql_actores)) {
        throw new Exception("Error al eliminar actores relacionados: " . mysqli_error($link));
    }


    $sql_generos = "DELETE FROM pelicula_genero WHERE pelicula_id_pelicula = '$id_pelicula'";
    if (!mysqli_query($link, $sql_generos)) {
        throw new Exception("Error al eliminar géneros relacionados: " . mysqli_error($link));
    }

    $sql_traileres = "DELETE FROM traileres WHERE pelicula_id_pelicula = '$id_pelicula'";
    if (!mysqli_query($link, $sql_traileres)) {
        throw new Exception("Error al eliminar tráileres: " . mysqli_error($link));
    }

    $sql_pelicula = "DELETE FROM pelicula WHERE id_pelicula = '$id_pelicula'";
    
    if (mysqli_query($link, $sql_pelicula)) {
        if (mysqli_affected_rows($link) > 0) {
            
            mysqli_commit($link);
        } else {
            
            mysqli_rollback($link);
            $success = false;
            $message = 'No se encontró la película con el ID especificado para eliminar.';
        }
    } else {
        throw new Exception("Error al eliminar la película: " . mysqli_error($link));
    }

} catch (Exception $e) {
    
    mysqli_rollback($link);
    $success = false;
    $message = "Error en la eliminación. Operación revertida: " . $e->getMessage();
    error_log("Error de eliminación de película: " . $e->getMessage()); 
}

echo json_encode(['success' => $success, 'message' => $message]);

mysqli_close($link);
?>