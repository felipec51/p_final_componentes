<?php
// obtener_notificaciones.php
header('Content-Type: application/json; charset=utf-8');
require_once 'conexion.php';

// Mostrar errores para depuración
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$conn = Conectar();

if (!$conn) {
    echo json_encode(["success" => false, "message" => "Error BD"]);
    exit();
}

$id_usuario = isset($_POST['id_usuario']) ? (int)$_POST['id_usuario'] : 0;

try {
    // CORRECCIÓN IMPORTANTE:
    // Usamos 'AS' para que el nombre de la columna en la BD (ej: duracion_min)
    // se convierta en el nombre que espera Android (ej: duracion).
    
    $sql = "
        SELECT 
            p.id_pelicula,
            p.titulo,
            p.descripcion,
            p.duracion_min AS duracion,           -- Corregido: duracion_min -> duracion
            p.anio AS anio_lanzamiento,           -- Corregido: anio -> anio_lanzamiento
            p.precio_alquiler,
            p.poster_path AS imagen_url,          -- Corregido: poster_path -> imagen_url
            
            -- Subconsulta 1: Total de copias físicas que existen
            (SELECT COUNT(*) FROM cinta c WHERE c.pelicula_id_pelicula = p.id_pelicula) as total_cintas,
            
            -- Subconsulta 2: Copias que están prestadas actualmente ('en curso')
            (SELECT COUNT(*) 
             FROM cinta c 
             JOIN prestamo pr ON c.id_cinta = pr.cinta_id_cinta 
             WHERE c.pelicula_id_pelicula = p.id_pelicula 
             AND pr.estado_alquiler = 'en curso') as cintas_ocupadas
             
        FROM lista_espera le
        JOIN pelicula p ON le.pelicula_id_pelicula = p.id_pelicula
        WHERE le.Usuario_id_usuario = ?
    ";

    $stmt = mysqli_prepare($conn, $sql);
    if (!$stmt) {
        throw new Exception("Error SQL: " . mysqli_error($conn));
    }
    
    mysqli_stmt_bind_param($stmt, "i", $id_usuario);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    $peliculas_disponibles = array();

    while ($row = mysqli_fetch_assoc($result)) {
        // Cálculo de disponibilidad
        $total = (int)$row['total_cintas'];
        $ocupadas = (int)$row['cintas_ocupadas'];
        $disponibles = $total - $ocupadas;

        // Solo agregamos a la lista si hay copias libres
        if ($disponibles > 0) {
            $row['copias_disponibles'] = $disponibles;
            
            // Convertimos tipos de datos para evitar errores en Gson/Android
            $row['id_pelicula'] = (int)$row['id_pelicula'];
            $row['duracion'] = (int)$row['duracion'];
            $row['anio_lanzamiento'] = (int)$row['anio_lanzamiento'];
            $row['precio_alquiler'] = (double)$row['precio_alquiler'];
            
            // Limpiamos datos técnicos que no necesita la app
            unset($row['total_cintas']);
            unset($row['cintas_ocupadas']);
            
            $peliculas_disponibles[] = $row;
        }
    }

    echo json_encode([
        "success" => true,
        "data" => $peliculas_disponibles
    ]);

} catch (Exception $e) {
    echo json_encode(["success" => false, "message" => $e->getMessage()]);
}
?>