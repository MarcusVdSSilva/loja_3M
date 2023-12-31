<?php 
    session_start();
    if(isset($_SESSION['logado'])){echo"entrou";
        header('Location: home.php');
    }else{
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="../../Assets/css/login.css">
    <link rel="stylesheet" href="../../Assets/css/loginMQ.css">
    <link rel="shortcut icon" href="../../Assets/img/favicon.ico" type="image/x-icon">    
    <title>Lojas 3M - Interno</title>
</head>
<body translate="no">
    <h1>LOJAS <span class="logo">3M</span></h1>
    <form action="../Controller/usuario/logar.php?logar" method="post">
        <div class="form" translate="no">
            <div >
                <span class="material-symbols-outlined">mail</span>
                <input type="text" name="email" id="email" placeholder="email">
            </div>
            <div>
                <span class="material-symbols-outlined">key</span>
                <input type="password" name="senha" id="senha" placeholder="senha">
            </div>
            <button class="entrar">Entrar</button>
        </div>
    </form>
    <div class="carrossel">
        <button class="botao-esquerdo" onclick="plusDivs(-1)"><span class="material-symbols-outlined">arrow_back_ios</span></button>
        <div class="images">
            <img class="mySlides" src="../../Assets/img/modelo1.png">
            <img class="mySlides" src="../../Assets/img/modelo2.png">
            <img class="mySlides" src="../../Assets/img/modelo3.png">
        </div>
        <button class="botao-direito" onclick="plusDivs(1)"><span class="material-symbols-outlined direita">arrow_forward_ios</span></button>
    </div> 
      <script src="../../Assets/js/home.js"></script>
</body>
</html>

<?php 
    }
?>