<?php 
    require_once ('../../Model/usuario.php');

    //SE FOR MANDADO VIA WEB
    
    if(isset($_GET['cadastro'])){
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['nome']) && isset($_POST['cpf_cnpj']) && isset($_POST['email']) && isset($_POST['senha']) && isset($_POST['telefone']) && isset($_POST['perfil']) && $id = $_POST['id']){
                
                $id = $_POST['id'];
                $nome = $_POST['nome'];
                $cpf_cnpj = $_POST['cpf_cnpj'];
                $email = $_POST['email'];
                $senha = $_POST['senha'];
                $telefone = $_POST['telefone'];
                $perfil = $_POST['perfil'];

                //FILTRO
                $telefone = preg_replace("/\D/", "", "$telefone");
                $cpf_cnpj = preg_replace("/\D/", "", "$cpf_cnpj");

                //CRIPTOGRAFIA
                $salt = md5('email');
                $senhacript = crypt($senha,$salt);
                $senha = hash('sha512',$senhacript);

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $editar = $usuario->editar($perfil,$status,$nome,$cpf_cnpj,$email,$senha,$telefone,$id);
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

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $editar = $usuario->editarCliente($nome,$cpf_cnpj,$email,$senha,$telefone,$id);

                return;
            }
        }
    }

    
    ?>