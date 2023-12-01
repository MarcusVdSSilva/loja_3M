<?php
    require('../../Model/venda.php');

    if($_SERVER['REQUEST_METHOD'] == "POST"){      
        if(isset($_POST['cliente'])){
            $informacoes=json_decode($_POST['cliente']);
            $idCliente = $informacoes->idCliente;
            
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