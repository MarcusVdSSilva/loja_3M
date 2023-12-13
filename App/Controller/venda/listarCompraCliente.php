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
                $infoVenda = json_decode(json_encode($infoVenda));
                $produto = new venda_produto();

                $listar = $produto->listar($infoVenda->id);
                $infoProd = array();

                while($linha = $listar->fetch_assoc()){
                    $infoProd[]=$linha;
                }
                
                $infoVenda->produtos = $infoProd;

                echo(json_encode($infoProd));

            }


        }
    
    }
    
?>