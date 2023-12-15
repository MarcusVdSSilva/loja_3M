<?php 
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require_once ('../../Model/usuario.php');

    //SE FOR MANDADO VIA WEB
    
    if(isset($_GET['cadastro'])){
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['nome']) && isset($_POST['cpf_cnpj']) && isset($_POST['email']) && isset($_POST['senha']) && isset($_POST['telefone']) && isset($_POST['perfil'])){
                
                // $nome = $_POST['nome'];
                // $cpf_cnpj = $_POST['cpf_cnpj'];
                // $email = $_POST['email'];
                // $senha = $_POST['senha'];
                // $telefone = $_POST['telefone'];
                // $perfil = $_POST['perfil'];
                
                //TESTE DE CADASTRO NO BANCO
                $nome = "admin";
                $cpf_cnpj = "039.169.230-29";
                $email = "admin@gmail.com";
                $senha = "123";
                $telefone = "(51) 9 96111502";
                $perfil = "2";

                //FILTRO
                $telefone = preg_replace("/\D/", "", "$telefone");
                $cpf_cnpj = preg_replace("/\D/", "", "$cpf_cnpj");

                //CRIPTOGRAFIA
                $hashedPassword = password_hash($senha, PASSWORD_BCRYPT);

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $cadastrar = $usuario->cadastrar($perfil,$nome,$cpf_cnpj,$email,$hashedPassword,$telefone);
            }
        
        }
        
    }else{
        //SE FOR MANDADO VIA MOBILE
        if($_SERVER['REQUEST_METHOD'] == "POST"){
            if(isset($_POST['cadastrarCliente']) && isset($_POST["senha"])){

                $informacoes = json_decode($_POST['cadastrarCliente']);
                echo(json_encode($informacoes));
                $nome = $informacoes->nome;
                $cpf_cnpj = $informacoes->cpf_cnpj;
                $email = $informacoes->email;
                $senha = $_POST["senha"];
                $telefone = $informacoes->telefone;

                //SALVANDO NO BANCO
                $usuario = new Usuario();
                $cadastrar = $usuario->cadastrarCliente($nome,$cpf_cnpj,$email,$senha,$telefone);
            }
        }

        

    }

    
?>