<link rel="stylesheet" href="../../Assets/css/header.css">
<link rel="stylesheet" href="../../Assets/css/headerMQ.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<?php 
    if($_SESSION['perfil']=="2"){
?>

<nav class="navbar">
        
    <button id="dropdown" type="button">
        <span class="material-symbols-outlined">
            menu
        </span>
    </button>
    
    <h1>LOJAS <span class="logo">3M</span></h1>
   
     <ul id="dropdownMenu" class="dropdownMenu">
        <li>
            <a href="home.php">Pagina inicial</a>
        </li>
        <li>
            <a href="funcionario.php">Funcionário</a>
        </li>
        <li>
            <a href="produto.php">Produtos</a>
        </li>
        <li>
            <a href="estoque.php">Estoque</a>
        </li>
    </ul> 
    
</nav>

<?php 
    }else if($_SESSION['perfil']=="1"){
?>

<nav class="navbar">
        
    <button id="dropdown" type="button">
        <span class="material-symbols-outlined">
            menu
        </span>
    </button>
    
    <h1>LOJAS <span class="logo">3M</span></h1>
   
     <ul id="dropdownMenu" class="dropdownMenu">
        <li>
            <a href="home.php">Pagina inicial</a>
        </li>
        <li>
            <a href="produto.php">Produtos</a>
        </li>
        <li>
            <a href="estoque.php">Estoque</a>
        </li>
    </ul> 
</nav>

<?php 
    }
?>

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/menu.js"></script>