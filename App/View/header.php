<link rel="stylesheet" href="../../Assets/css/header.css">
<link rel="stylesheet" href="../../Assets/css/headerMQ.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,1,0" />

<?php 
    // if($_SESSION['perfil']=="2"){
?>

<nav class="navbar" translate="no">
        
    <div id="menuMobile" class="mobile">
        <button id="dropdown" type="button">
            <span class="material-symbols-outlined" >
                menu
            </span>
        </button>
        
        <h1>LOJAS <span class="logo">3M</span></h1>
         <ul id="dropdownMenu" class="dropdownMenu">
            <li>
                <a href="home.php">Pagina inicial</a>
            </li>
            <li>
                <a href="usuario.php">Usuario</a>
            </li>
            <li>
                <a href="produto.php">Produtos</a>
            </li>
            <li>
                <a href="venda.php">Vendas</a>
            </li>
            <li>
                <a href="../Controller/usuario/sair.php">Sair</a>"
            </li>
        </ul>
    </div>
    <ul id="menuResponsivo" class="menuResponsivo">
            <li class="link">
                <a href="home.php" id="home" class="cor">Pagina inicial <span class="material-symbols-outlined preenchido">
home
</span></a>
            </li>
            <li class="link">
                <a href="usuario.php" class="cor">Usuario <span class="material-symbols-outlined">
person
</span></a>
            </li>
            <li class="link">
                <a href="produto.php" class="cor">Produtos <span class="material-symbols-outlined">
shopping_basket
</span></a>
            </li>
            <li class="link">
                <a href="venda.php" class="cor">Vendas <span class="material-symbols-outlined">
point_of_sale
</span></a>
            </li>
            <li class="link">
                <a href="../Controller/usuario/sair.php" class="cor">Sair<span class="material-symbols-outlined power">
power_settings_new
</span></a>
            </li>
        </ul>


</nav>

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/header.js"></script>

<?php 
    // }else if($_SESSION['perfil']=="1"){
?>
<!-- 
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
            <a href="venda.php">Vendas</a>
        </li>
        <li>
            <a href="../Controller/usuario/sair.php">Sair</a>"	
        </li>
    </ul> 
</nav>
 -->
<?php 
    // }
?>

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/menu.js"></script>