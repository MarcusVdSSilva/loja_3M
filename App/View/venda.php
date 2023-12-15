<?php 
    // session_start();
	// if(isset($_SESSION['logado'])&& isset($_SESSION['status']) && isset($_SESSION['perfil'])){
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Assets/css/venda.css">
    <title>Lojas 3M - Administrador</title>
</head>

<body>


    <?php require_once("header.php"); ?>

    <table>
        <thead>
            <th>ID</th>
            <th>Valor</th>
            <th>Data</th>
            <th>Nome do Cliente</th>
            <th>Produto</th>
            <th>Quantidade</th>
        </thead>

        <tbody>
            <?php require_once("../Controller/venda/listar.php"); ?>
        </tbody>
    </table>

    
    

    
</body>

</html>

<?php 
    // }else{
    //     header('Location: login.php');
    // }
?>