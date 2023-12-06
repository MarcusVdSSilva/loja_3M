<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<?php
    require('../../Model/produto.php');
    $produto = new Produto();

    $listar = $produto->listar();
            
        
    $produtos = array();

    while($linha = $listar->fetch_assoc()){
        $produtos[]=$linha;
    }

    echo json_encode($produtos);
    
?>