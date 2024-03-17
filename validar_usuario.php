<?php
header('Content-Type: application/json');
include 'conexion.php';
//$usu_usuario=intval($_POST['usuario']);
//$usu_password=$_POST['password'];

$id_usuario=97250015;
$pass="Ae123456";

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
