?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id_usuario=$_POST["id_usuario"];
    $nom_usuario=$_POST["nom_usuario"];
    $apellido_paterno=$_POST["apellido_paterno"];
    $apellido_materno=$_POST["apellido_materno"];
    $pass=$_POST["pass"];
    $query="INSERT INTO login_app (id_usuario,nom_usuario,apellido_paterno,apellido_materno,pass) 
    VALUES('".$id_usuario."','".$nom_usuario."','".$apellido_paterno."','".$apellido_materno."','".$pass."')";
    $resultado=$mysql->query($query);
    if($resultado==true){
        echo "El usuario se inserto de forma exitosa";
    }else{
        echo "Error al insertar el usuario";
    }
}
