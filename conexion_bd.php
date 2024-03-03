<?php
$mysql=new mysqli("localhost", "root","","asistencia_app");
if($mysql->connect_error){
    die("Error de conexion");
}
else{
    //echo "La conexion fue exitosa";
}
