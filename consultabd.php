<?php
if($_SERVER["REQUEST_METHOD"]=="GET"){
    require_once 'conexion.php';
    $id_usuario=$_GET['id_usuario'];
    $query="SELECT * FROM lista_usuario WHERE id_usuario='".$id_usuario."'";
    $resultado=$mysql->query($query);
    if($mysql->affected_rows > 0){
        while($row=$resultado->fetch_assoc()){
            $array=$row;
        }
        echo json_encode($array);
    }else{
        echo "No hay registros con ese usuario";
    }
    $resultado->close();
    $mysql->close();
}
