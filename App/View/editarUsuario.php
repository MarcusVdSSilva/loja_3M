<?php 
    session_start();
	if(isset($_SESSION['logado'])&& isset($_SESSION['status']) && isset($_SESSION['perfil'])){
        if($_SESSION['perfil']=="2"){
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Assets/css/funcionario.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <title>Lojas 3M - Administrador</title>
</head>
<body>
    <?php require_once("header.php");?>

    <?php require_once("../Controller/usuario/consultaId.php");?>

    <script src="../../Assets/js/jquery.js"></script>
    <script src="../../Assets/js/modal.js"></script>
    <script>
    </script>
    </body>
</html>

<?php 
        }else{
            header('Location: login.php');
        }
    }else{
        header('Location: login.php');
    }
?>