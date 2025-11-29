<?php
require_once 'conexion.php';

date_default_timezone_set('America/Bogota');

header('Content-Type: application/json');

$conn = Conectar();

if (!$conn) {
    echo json_encode(["success" => false, "message" => "Error de conexión a BD."]);
    exit();
}

$id_usuario  = isset($_POST['id_usuario'])  ? (int)$_POST['id_usuario']  : 0;
$id_pelicula = isset($_POST['id_pelicula']) ? (int)$_POST['id_pelicula'] : 0;
$precio      = isset($_POST['precio'])      ? $_POST['precio']          : '0.00';

if ($id_usuario <= 0 || $id_pelicula <= 0 || !is_numeric($precio) || (float)$precio <= 0) {
    echo json_encode(["success" => false, "message" => "Datos inválidos."]);
    exit();
}

$id_cinta = 0;
$nombrePelicula = '';
$nombreUsuario = '';

mysqli_begin_transaction($conn);

try {
    $stmt = mysqli_prepare($conn, "SELECT nombre FROM Usuario WHERE id_usuario = ?");
    mysqli_stmt_bind_param($stmt, "i", $id_usuario);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($result)) {
        $nombreUsuario = $row['nombre'];
    } else {
        throw new Exception("Usuario no encontrado.");
    }

    mysqli_stmt_close($stmt);
    $stmt = mysqli_prepare($conn, "SELECT titulo FROM pelicula WHERE id_pelicula = ?");
    mysqli_stmt_bind_param($stmt, "i", $id_pelicula);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($result)) {
        $nombrePelicula = $row['titulo'];
    } else {
        throw new Exception("Película no encontrada.");
    }

    mysqli_stmt_close($stmt);
    $sqlCheck = "
        SELECT COUNT(*) AS total
        FROM prestamo p
        JOIN cinta c ON p.cinta_id_cinta = c.id_cinta
        WHERE p.Usuario_id_usuario = ?
          AND c.pelicula_id_pelicula = ?
          AND p.estado_alquiler = 'en curso'
    ";

    $stmt = mysqli_prepare($conn, $sqlCheck);
    mysqli_stmt_bind_param($stmt, "ii", $id_usuario, $id_pelicula);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    $row = mysqli_fetch_assoc($result);

    if ($row['total'] > 0) {
        throw new Exception("Ya tienes un préstamo activo de esta película.");
    }

    mysqli_stmt_close($stmt);
    $sqlCinta = "
        SELECT c.id_cinta
        FROM cinta c
        LEFT JOIN prestamo p 
            ON c.id_cinta = p.cinta_id_cinta 
            AND p.estado_alquiler = 'en curso'
        WHERE c.pelicula_id_pelicula = ?
          AND p.id_prestamo IS NULL
        LIMIT 1
        FOR UPDATE
    ";

    $stmt = mysqli_prepare($conn, $sqlCinta);
    mysqli_stmt_bind_param($stmt, "i", $id_pelicula);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($result)) {
        $id_cinta = $row['id_cinta'];
    } else {
        throw new Exception("No hay copias disponibles.");
    }

    mysqli_stmt_close($stmt);

    $fecha_prestamo = date('Y-m-d H:i:s');
    $fecha_devolucion = date('Y-m-d H:i:s', strtotime('+7 days'));

    $sqlPrestamo = "
        INSERT INTO prestamo 
        (fecha_prestamo, fecha_devolucion, Usuario_id_usuario, cinta_id_cinta, estado_alquiler)
        VALUES (?, ?, ?, ?, 'en curso')
    ";

    $stmt = mysqli_prepare($conn, $sqlPrestamo);
    mysqli_stmt_bind_param(
        $stmt,
        "ssii",
        $fecha_prestamo,
        $fecha_devolucion,
        $id_usuario,
        $id_cinta
    );

    mysqli_stmt_execute($stmt);
    $id_prestamo_generado = mysqli_insert_id($conn);

    mysqli_stmt_close($stmt);

    $sqlFactura = "
        INSERT INTO factura 
        (Nombre_user, precio_alquiler, fecha_factura, nombre_pelicula, Usuario_id_usuario)
        VALUES (?, ?, ?, ?, ?)
    ";

    $stmt = mysqli_prepare($conn, $sqlFactura);
    mysqli_stmt_bind_param(
        $stmt,
        "sssii",
        $nombreUsuario,
        $precio,
        $fecha_prestamo,
        $id_cinta,
        $id_usuario
    );

    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);
    mysqli_commit($conn);

    echo json_encode([
        "success" => true,
        "message" => "Alquiler realizado con éxito.",
        "id_prestamo" => $id_prestamo_generado,
        "fecha_devolucion" => $fecha_devolucion
    ]);
} catch (Exception $e) {

    mysqli_rollback($conn);
    echo json_encode([
        "success" => false,
        "message" => $e->getMessage()
    ]);
} finally {
    if ($conn) mysqli_close($conn);
}
