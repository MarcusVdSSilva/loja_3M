<?php
    require('../../Model/produto.php');

    if($_SERVER['REQUEST_METHOD'] == "POST"){      
        if(isset($_POST['cliente'])){
            $informacoes=json_decode($_POST['cliente']);
            $idCliente = $informacoes->idCliente;
            
            $produto = new Produto();

            $listar = $produto->listarVendaCliente($idCliente);
                    
            $produtos = array();

            while($linha = $listar->fetch_assoc()){
                $produtos[]=$linha;
            }

            echo json_encode($produtos);
        }
    
    }
    
?>