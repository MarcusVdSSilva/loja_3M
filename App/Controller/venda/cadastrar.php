<?php 
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
require_once("../../Model/venda.php");

if($_SERVER['REQUEST_METHOD'] == "POST"){      
    if(isset($_POST['venda']) && isset($_POST["usuarioId"])){
        $informacoes=json_decode($_POST['venda']);
        $valor = $informacoes->valor;
        $produtos = $informacoes->produtos;
        $id_usuario = $_POST["usuarioId"];
        
        //SALVANDO NO BANCO
        $venda = new Venda();
        $cadastrar = $venda->cadastrar($valor,$id_usuario,$produtos);
    }

}
?>