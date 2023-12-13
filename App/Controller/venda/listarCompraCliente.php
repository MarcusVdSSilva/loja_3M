<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
require('../../Model/venda_produto.php');

    if($_SERVER['REQUEST_METHOD'] == "POST"){      
        if(isset($_POST['usuarioId'])){
            $idCliente = $_POST['usuarioId'];
            
            $vendas = new Venda();

            $vendas->listarVendaCliente($idCliente);

            echo(json_encode($vendas));
            foreach($vendas as $venda){
                echo(json_encode($venda));
                $produto = new venda_produto();
                $venda->produtos = $produto->listar($venda->id);
            }
        }
    
    }
    
?>