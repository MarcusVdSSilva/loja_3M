<?php
    require('../../Model/produto.php');
    $produto = new Produto();

    $nome=$_REQUEST['nome'];

    $listar = $produto->consultaNome($nome);
        
        $produtos = array();

        while($linha = $listar->fetch_assoc()){
            $produtos[]=$linha;
        }

        echo json_encode($produtos);

    
?>