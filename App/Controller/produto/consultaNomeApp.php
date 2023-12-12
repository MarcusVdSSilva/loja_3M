<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
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