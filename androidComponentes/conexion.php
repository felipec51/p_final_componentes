<?php
function Conectar(){
    $host="localhost";
    $user="root";
    $pass="2005Fe123";
    $dbname="mydb";
    
    // Conecta y selecciona la BD en un solo paso
    $link = mysqli_connect($host, $user, $pass, $dbname);
    
    // Si la conexión falla, detén el script y muestra el error de MySQL
    if (!$link) {
        die("ERROR DE CONEXIÓN A MYSQL: " . mysqli_connect_error());
    }
    
    mysqli_set_charset($link, "utf8"); 
    return $link;
}
?>