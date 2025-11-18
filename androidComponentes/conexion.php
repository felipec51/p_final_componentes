<?php


function Conectar(){

    $host="localhost";
    $user="root";
    $pass="2005Fe123"; // Tus datos reales
    $dbname="mydb"; // Tu BD real
    
    // Conecta y selecciona la BD en un solo paso
    $link = mysqli_connect($host, $user, $pass, $dbname);
    
    // Si la conexión falla, detén el script y muestra el error de MySQL
    if (!$link) {
        die("ERROR DE CONEXIÓN A MYSQL: " . mysqli_connect_error());
    }
    
    // Configurar el set de caracteres a UTF8
    mysqli_set_charset($link, "utf8"); 
    return $link;
}
