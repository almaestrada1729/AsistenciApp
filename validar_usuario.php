<?php
header('Content-Type: application/json');
include 'conexion.php';
$is_usuario=intval($_POST['id_usuario']);
$pass=$_POST['pass'];

$sentencia=$conexion->prepare("SELECT * FROM login_app WHERE id_usuario=? AND pass=?");
$sentencia->bind_param('is',$id_usuario,$pass);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
        echo json_encode($fila, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
$sentencia->close();
$conexion->close();
?>
