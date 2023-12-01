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
    <?php require_once("header.php");   
    ?>
    <form action="../Controller/usuario/consultaNome.php" method="get">
        
        <div class="container">
            <input type="text" placeholder="pesquisar" name="nome">
            <span class="material-symbols-outlined">
            search</span>
        </div>
    </form>

</body>
</html>