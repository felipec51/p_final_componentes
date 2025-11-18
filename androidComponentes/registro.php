<?php
// Incluir el archivo de conexión. Asume que 'conexion.php' está en el mismo directorio.
require_once 'conexion.php'; 

// 1. Verificar el método de la solicitud
if ($_SERVER['REQUEST_METHOD'] != 'POST') {
    // Si no es POST, devolver un error
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Método no permitido.']);
    exit;
}

// 2. Conectar a la base de datos
$link = Conectar();

// 3. Recibir y sanitizar datos de entrada
// Utiliza mysqli_real_escape_string para prevenir inyecciones SQL
$username = isset($_POST['username']) ? mysqli_real_escape_string($link, $_POST['username']) : null;
$password = isset($_POST['password']) ? mysqli_real_escape_string($link, $_POST['password']) : null;
$nombre = isset($_POST['nombre']) ? mysqli_real_escape_string($link, $_POST['nombre']) : null;
$direccion = isset($_POST['direccion']) ? mysqli_real_escape_string($link, $_POST['direccion']) : null;
$telefono = isset($_POST['telefono']) ? mysqli_real_escape_string($link, $_POST['telefono']) : null;
$email = isset($_POST['email']) ? mysqli_real_escape_string($link, $_POST['email']) : null;

// El rol por defecto es 'socio', cuyo id_rol es 2 según tu BD
$rol_id_rol = 2; 

// 4. Validar que los campos requeridos no estén vacíos
if (empty($username) || empty($password) || empty($nombre) || empty($email)) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => 'Faltan campos obligatorios (username, password, nombre, email).']);
    mysqli_close($link);
    exit;
}

// 5. Hash de la contraseña (¡MUY IMPORTANTE para la seguridad!)
// Aunque tu BD parece almacenar la contraseña en texto plano, esta es la forma correcta.
// Se recomienda cambiar el tipo de la columna 'password' a VARCHAR(255) en tu BD.
$hashed_password = password_hash($password, PASSWORD_DEFAULT);

// 6. Preparar la consulta SQL
// Se usa NOW() para la fecha_creacion
$sql = "INSERT INTO `Usuario` 
        (`username`, `password`, `nombre`, `direccion`, `telefono`, `email`, `fecha_creacion`, `rol_id_rol`) 
        VALUES 
        (
            '$username', 
            '$password', -- Usaremos el texto plano aquí, pero lo ideal es usar $hashed_password
            '$nombre', 
            '$direccion', 
            '$telefono', 
            '$email', 
            NOW(), 
            $rol_id_rol
        )";

// NOTA DE SEGURIDAD: En un entorno de producción, la consulta debería usar:
// VALUES ('$username', '$hashed_password', ...).
// Para que coincida con tu esquema actual, uso '$password' (texto plano) en la consulta.

// 7. Ejecutar la consulta
if (mysqli_query($link, $sql)) {
    // Éxito en el registro
    header('Content-Type: application/json');
    echo json_encode(['success' => true, 'message' => 'Registro exitoso.', 'id_usuario' => mysqli_insert_id($link)]);
} else {
    // Error en la base de datos
    header('Content-Type: application/json');
    // Error 1062 es por clave duplicada (ej. username o email ya existe)
    if (mysqli_errno($link) == 1062) {
        echo json_encode(['success' => false, 'message' => 'El nombre de usuario o email ya está en uso.']);
    } else {
        echo json_encode(['success' => false, 'message' => 'Error al registrar el usuario: ' . mysqli_error($link)]);
    }
}

// 8. Cerrar la conexión
mysqli_close($link);

?>