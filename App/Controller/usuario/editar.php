<?php 
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require_once ('../../Model/usuario.php');

    //SE FOR MANDADO VIA WEB
    
    if(isset($_GET['editar'])){
        
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            
            if(isset($_POST['nome']) && isset($_POST['cpf_cnpj']) && isset($_POST['email']) && isset($_POST['telefone']) && isset($_POST['perfil']) && isset($_POST['status'])){
               
                
                $id = $_GET['i'];
                $id= base64_decode($id);
                


                $nome = $_POST['nome'];
                $cpf_cnpj = $_POST['cpf_cnpj'];
                $email = $_POST['email'];
                $telefone = $_POST['telefone'];
                $perfil = $_POST['perfil'];
                $status = $_POST['status'];

                //FILTRO
                $telefone = preg_replace("/\D/", "", "$telefone");
                $cpf_cnpj = preg_replace("/\D/", "", "$cpf_cnpj");

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $editar = $usuario->editar($perfil,$status,$nome,$cpf_cnpj,$email,$telefone,$id);
            }
        
        }
        
    }else{
        //SE FOR MANDADO VIA MOBILE
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['editarCliente'])){
                $informacoes = json_decode($_POST['editarCliente']);
                $id = $informacoes->id;
                $nome = $informacoes->nome;
                $cpf_cnpj = $informacoes->cpf_cnpj;
                $email = $informacoes->email;
                $senha = $informacoes->senha;
                $telefone = $informacoes->telefone;

                
                //FILTRO
                $telefone = preg_replace("/\D/", "", "$telefone");
                $cpf_cnpj = preg_replace("/\D/", "", "$cpf_cnpj");

                //CRIPTOGRAFIA
                $password = $senha;
                $hashedPassword = password_hash($password, PASSWORD_BCRYPT);

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $editar = $usuario->editarCliente($nome,$cpf_cnpj,$email,$$hashedPassword,$telefone,$id);

                return;
            }
        }
    }

    
    ?>