<?php 
require_once("../../Model/venda.php");

if($_SERVER['REQUEST_METHOD'] == "POST"){      
    if(isset($_POST['venda'])){
        $informacoes=json_decode($_POST['venda']);
        $valor = $informacoes->valor;
        $data = $informacoes->data;
        $produto = $informacoes->produto;
        
        //SALVANDO NO BANCO
        $venda = new Venda();
        $cadastrar = $venda->cadastrar($valor,$data,$id_usuario,$produto);
    }

}
?>