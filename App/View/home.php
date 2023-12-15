<?php 
    session_start();
	//if(isset($_SESSION['logado'])&& isset($_SESSION['status']) && isset($_SESSION['perfil'])){
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Assets/css/home.css">
    <link rel="stylesheet" href="../../Assets/css/homeMQ.css">
    <link rel="shortcut icon" href="../../Assets/img/favicon.ico" type="image/x-icon">
    <title>Lojas 3M - Administrador</title>
</head>

<body>


    <?php require_once("header.php"); ?>
    
    <img src="../../Assets/img/bemvindo.png" alt="bem vindo">
    
</body>

</html>

<?php 
    // }else{
    //     header('Location: login.php');
    // }
    
?>