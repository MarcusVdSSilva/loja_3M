<?php 
    require_once ('../../Model/usuario.php');

    if(isset($_GET['logar'])){
        
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            
            if(isset($_POST['email']) && isset($_POST['senha'])){
                
                $email = $_REQUEST['email'];
                $senha = $_REQUEST['senha'];

                //CRIPTOGRAFIA
                $salt = md5('cpf');
                $senhacript = crypt($senha,$salt);
                $senha = hash('sha512',$senhacript);
                
                //Salvando no banco
                $usuario = new Usuario();
                $logar = $usuario->logar($email,$senha);
                
            }
        }
        
    }else{
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['credenciais'])){
                $credenciais=json_decode($_POST['credenciais']);
                $email = $credenciais->email;
                $senha = $credenciais->senha;

                //CRIPTOGRAFIA
                $salt = md5('cpf');
                $senhacript = crypt($senha,$salt);
                $senha = hash('sha512',$senhacript);

                //Salvando no banco
                $usuario = new Usuario();
                $logar = $usuario->logarcliente($email,$senha);
            }
        }
    }

    
?>