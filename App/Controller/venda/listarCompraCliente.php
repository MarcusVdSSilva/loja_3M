<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require('../../Model/venda.php');

    if($_SERVER['REQUEST_METHOD'] == "POST"){      
        if(isset($_POST['usuarioId'])){
            $idCliente = $informacoes->usuarioId;
            
            $venda = new Venda();

            $listar = $venda->listarVendaCliente($idCliente);
                    
            $informacoesVenda = array();

            while($linha = $listar->fetch_assoc()){
                $informacoesVenda[]=$linha;
            }

            echo json_encode($informacoesVenda);
        }
    
    }
    
?>