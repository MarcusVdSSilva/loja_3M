<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
require('../../Model/venda_produto.php');

    if($_SERVER['REQUEST_METHOD'] == "POST"){      
        if(isset($_POST['usuarioId'])){
            $idCliente = $_POST['usuarioId'];
            
            $venda = new Venda();

            $listar = $venda->listarVendaCliente($idCliente);
                    
            $informacoesVenda = array();

            while($linha = $listar->fetch_assoc()){
                $informacoesVenda[]=$linha;
            }

            foreach($informacoesVenda as $infoVenda){
                $vendaCliente = json_decode(json_encode($infoVenda));
                echo($vendaCliente);
                $produto = new venda_produto();
                $vendaCliente->produtos = $produto->listar($vendaCliente->id);
            }
        }
    
    }
    
?>