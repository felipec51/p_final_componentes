<?php


require_once 'conexion.php'; 


$link = Conectar();

if (!$link) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Error de conexión a la base de datos: ' . mysqli_connect_error()]);
    exit;
}

$sql = "SELECT 
            p.id_pelicula,
            p.titulo,
            p.descripcion,
            p.anio,                 
            p.duracion_min,         
            p.calificacion,
            p.idioma,           
            p.poster_path           
        FROM 
            pelicula p
        ORDER BY 
            p.titulo ASC";


$resultado = mysqli_query($link, $sql);

if ($resultado) {
    $peliculas = array();
    
    
    while ($fila = mysqli_fetch_assoc($resultado)) {
        
        $pelicula_data = array(
            'id_pelicula'       => (int) $fila['id_pelicula'],
            'titulo'            => $fila['titulo'],
            'descripcion'       => $fila['descripcion'],
            'anio_lanzamiento'  => (int) $fila['anio'],       
            'duracion'          => (int) $fila['duracion_min'], 
            'clasificacion'     => $fila['calificacion'],   
            'idioma'            => $fila['idioma'],                 
            'imagen_url'        => $fila['poster_path']       
        );
        
        $peliculas[] = $pelicula_data;
    }

    mysqli_free_result($resultado);
    mysqli_close($link);

    header('Content-Type: application/json');
    echo json_encode(['success' => true, 'peliculas' => $peliculas]);
} else {
    header('Content-Type: application/json');
    http_response_code(500); 
    echo json_encode(['success' => false, 'message' => 'Error al consultar películas: ' . mysqli_error($link)]);
    mysqli_close($link);
}
?>