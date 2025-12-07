<?php
header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

require_once 'conexion.php'; 

// --- CORRECCIÓN AQUÍ ---
// Llamamos a la función y guardamos el resultado en la variable $conexion
$conexion = Conectar(); 

// Ya no es necesario verificar connect_error aquí porque la función Conectar
// ya lo maneja, pero si quieres doble seguridad:
if ($conexion->connect_error) {
    echo json_encode([
        "success" => false,
        "message" => "Error de conexión: " . $conexion->connect_error
    ]);
    exit();
}

// Obtener parámetros
$tipo_reporte = isset($_POST['tipo_reporte']) ? $_POST['tipo_reporte'] : 'todos';
$fecha_inicio = isset($_POST['fecha_inicio']) ? $_POST['fecha_inicio'] : date('Y-m-d', strtotime('-30 days'));
$fecha_fin = isset($_POST['fecha_fin']) ? $_POST['fecha_fin'] : date('Y-m-d');
$usuario_id = isset($_POST['usuario_id']) ? $_POST['usuario_id'] : '';

// Query base
$query = "
    SELECT 
        p.id_prestamo,
        u.nombre as nombre_usuario,
        u.id_usuario,
        pel.titulo as titulo_pelicula,
        pel.id_pelicula,
        p.fecha_prestamo,
        p.fecha_devolucion,
        f.precio_alquiler,
        p.estado_alquiler
    FROM prestamo p
    INNER JOIN Usuario u ON p.Usuario_id_usuario = u.id_usuario
    INNER JOIN cinta c ON p.cinta_id_cinta = c.id_cinta
    INNER JOIN pelicula pel ON c.pelicula_id_pelicula = pel.id_pelicula
    LEFT JOIN factura f ON f.Usuario_id_usuario = u.id_usuario 
        AND f.nombre_pelicula = c.id_cinta
";

$where_conditions = [];

// Aplicar filtros
switch ($tipo_reporte) {
    case 'semanal':
        $fecha_inicio = date('Y-m-d', strtotime('-7 days'));
        $fecha_fin = date('Y-m-d');
        $where_conditions[] = "DATE(p.fecha_prestamo) BETWEEN '$fecha_inicio' AND '$fecha_fin'";
        break;
        
    case 'quincenal':
        $fecha_inicio = date('Y-m-d', strtotime('-15 days'));
        $fecha_fin = date('Y-m-d');
        $where_conditions[] = "DATE(p.fecha_prestamo) BETWEEN '$fecha_inicio' AND '$fecha_fin'";
        break;
        
    case 'mensual':
        $fecha_inicio = date('Y-m-d', strtotime('-30 days'));
        $fecha_fin = date('Y-m-d');
        $where_conditions[] = "DATE(p.fecha_prestamo) BETWEEN '$fecha_inicio' AND '$fecha_fin'";
        break;
        
    case 'usuario':
        if (!empty($usuario_id)) {
            $where_conditions[] = "u.id_usuario = " . intval($usuario_id);
        }
        break;
        
    case 'rango':
        $where_conditions[] = "DATE(p.fecha_prestamo) BETWEEN '$fecha_inicio' AND '$fecha_fin'";
        break;
        
    case 'top10':
        // QUERY ESPECIAL PARA TOP 10
        // Nota: Asegúrate que sql_mode no tenga ONLY_FULL_GROUP_BY si da error aquí
        $query = "
            SELECT 
                pel.id_pelicula,
                pel.titulo as titulo_pelicula,
                COUNT(DISTINCT p.id_prestamo) as cantidad_alquileres,
                COALESCE(AVG(f.precio_alquiler), pel.precio_alquiler) as precio_alquiler,
                'Top 10' as nombre_usuario,
                'N/A' as estado_alquiler,
                MIN(p.fecha_prestamo) as fecha_prestamo,
                MAX(p.fecha_devolucion) as fecha_devolucion
            FROM pelicula pel
            LEFT JOIN cinta c ON pel.id_pelicula = c.pelicula_id_pelicula
            LEFT JOIN prestamo p ON c.id_cinta = p.cinta_id_cinta
            LEFT JOIN factura f ON f.nombre_pelicula = c.id_cinta
            GROUP BY pel.id_pelicula, pel.titulo, pel.precio_alquiler
            ORDER BY cantidad_alquileres DESC, pel.titulo ASC
            LIMIT 10
        ";
        break;
        
    case 'todos':
    default:
        break;
}

// Construir la consulta final
if (!empty($where_conditions) && $tipo_reporte != 'top10') {
    $query .= " WHERE " . implode(" AND ", $where_conditions);
}

if ($tipo_reporte != 'top10') {
    $query .= " ORDER BY p.fecha_prestamo DESC";
}

// Ejecutar consulta principal
$resultado = $conexion->query($query);

$datos = [];
if ($resultado && $resultado->num_rows > 0) {
    while ($fila = $resultado->fetch_assoc()) {
        $datos[] = $fila;
    }
}

// --- ESTADÍSTICAS ---
$estadisticas = [];

// 1. Total de ingresos
$query_ingresos = "SELECT SUM(precio_alquiler) as total_ingresos FROM factura";
// Aplicar filtro de fecha a los ingresos también para que coincida con el reporte
if (!empty($where_conditions) && $tipo_reporte != 'top10' && $tipo_reporte != 'todos') {
    // Nota: factura tiene fecha_factura, no fecha_prestamo. Asumimos consistencia.
    $query_ingresos .= " WHERE DATE(fecha_factura) BETWEEN '$fecha_inicio' AND '$fecha_fin'";
}

$resultado_ingresos = $conexion->query($query_ingresos);
$row_ingresos = $resultado_ingresos ? $resultado_ingresos->fetch_assoc() : null;
$estadisticas['total_ingresos'] = floatval($row_ingresos['total_ingresos'] ?? 0);

// 2. Total de alquileres (basado en los resultados actuales)
$estadisticas['total_alquileres'] = count($datos);

// 3. Película más rentada
if ($tipo_reporte != 'top10') {
    $query_mas_rentada = "
        SELECT pel.titulo, COUNT(p.id_prestamo) as total
        FROM prestamo p
        INNER JOIN cinta c ON p.cinta_id_cinta = c.id_cinta
        INNER JOIN pelicula pel ON c.pelicula_id_pelicula = pel.id_pelicula
    ";

    if (!empty($where_conditions)) {
        $query_mas_rentada .= " WHERE " . implode(" AND ", $where_conditions);
    }

    $query_mas_rentada .= " GROUP BY pel.id_pelicula ORDER BY total DESC LIMIT 1";

    $resultado_mas_rentada = $conexion->query($query_mas_rentada);
    $estadisticas['pelicula_mas_rentada'] = 'N/A';
    if ($resultado_mas_rentada && $resultado_mas_rentada->num_rows > 0) {
        $row_mas_rentada = $resultado_mas_rentada->fetch_assoc();
        $estadisticas['pelicula_mas_rentada'] = $row_mas_rentada['titulo'] . " (" . $row_mas_rentada['total'] . " rentas)";
    }
} else {
    // Si es top 10, la primera del array es la más rentada
    if (!empty($datos)) {
        $primera = $datos[0];
        $estadisticas['pelicula_mas_rentada'] = $primera['titulo_pelicula'] . " (" . $primera['cantidad_alquileres'] . " rentas)";
    } else {
        $estadisticas['pelicula_mas_rentada'] = 'N/A';
    }
}

// 4. Usuario más activo
$query_usuario_activo = "
    SELECT u.nombre, COUNT(p.id_prestamo) as total
    FROM prestamo p
    INNER JOIN Usuario u ON p.Usuario_id_usuario = u.id_usuario
";

if (!empty($where_conditions) && $tipo_reporte != 'top10') {
    $query_usuario_activo .= " WHERE " . implode(" AND ", $where_conditions);
}

$query_usuario_activo .= " GROUP BY u.id_usuario ORDER BY total DESC LIMIT 1";

$resultado_usuario_activo = $conexion->query($query_usuario_activo);
$estadisticas['usuario_mas_activo'] = 'N/A';
if ($resultado_usuario_activo && $resultado_usuario_activo->num_rows > 0) {
    $row_usuario = $resultado_usuario_activo->fetch_assoc();
    $estadisticas['usuario_mas_activo'] = $row_usuario['nombre'] . " (" . $row_usuario['total'] . " alquileres)";
}

// Respuesta final
echo json_encode([
    "success" => true,
    "message" => "Reporte generado exitosamente",
    "datos" => $datos,
    "estadisticas" => $estadisticas,
    "filtros" => [
        "tipo" => $tipo_reporte,
        "desde" => $fecha_inicio,
        "hasta" => $fecha_fin
    ]
], JSON_UNESCAPED_UNICODE);

$conexion->close();
?>