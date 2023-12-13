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
            
            $resultado = [];

            foreach($informacoesVenda as $infoVenda){
                $infoVenda = json_decode(json_encode($infoVenda));
                $produto = new venda_produto();

                $listarProd = $produto->listar($infoVenda->id_venda);
                $infoProd = array();

                while($linhaProd = $listarProd->fetch_assoc()){
                    $infoProd[]=$linhaProd;
                }

                $infoVenda->produtos = $infoProd;

                array_push($resultado, $infoVenda);

            }

            echo(json_encode($resultado));

        }
    
    }
    
?>