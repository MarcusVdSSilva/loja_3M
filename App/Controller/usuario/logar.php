<?php 
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require_once ('../../Model/usuario.php');

    if(isset($_GET['logar'])){
        
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            
            if(isset($_POST['email']) && isset($_POST['senha'])){
                
                $email = $_REQUEST['email'];
                $senha = $_REQUEST['senha'];
                
                //Salvando no banco
                $usuario = new Usuario();
                $logar = $usuario->logar($email,$senha);
                
            }
        }
        
    }else{
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['credenciais'])){

                throw new Exception("entrei");
                $credenciais=json_decode($_POST['credenciais']);
                $email = $credenciais->email;
                $senha = $credenciais->senha;

                //Salvando no banco
                $usuario = new Usuario();
                $logar = $usuario->logarcliente($email,$senha);
            }
        }
    }

    
?>