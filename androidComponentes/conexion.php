<?php

function Conectar(){

    $host="localhost";
    $user="root";
    $pass="2005Fe123"; 
    $dbname="mydb"; 
    
    
    $link = mysqli_connect($host, $user, $pass, $dbname);
    
    
    if (!$link) {
        die("ERROR DE CONEXIÓN A MYSQL: " . mysqli_connect_error());
    }
    
    
    mysqli_set_charset($link, "utf8"); 
    return $link;
}
