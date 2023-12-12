<?php   
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding"); 
    require("../Model/usuario.php");
    $usuario = new Usuario();

    $id = $_GET['i'];
    $id = base64_decode($id);

    $listar = $usuario->consultaId($id);
            

    while($linha = $listar->fetch_assoc()){
        $id = $linha['id'];
        $perfil = $linha['perfil'];
        $status = $linha['status'];
        $nome = $linha['nome'];
        $cpf_cnpj = $linha['cpf_cnpj'];
        $email = $linha['email'];
        $telefone = $linha['telefone'];
        $senha = $linha['senha'];
        $idCripto = base64_encode($id);
        
        echo'
            <form action="../Controller/usuario/editar.php?editar&i='.$idCripto.'" method="post">
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome" value="'.$nome.'">
                </div>
                
                <div>
                    <label for="cpfCnpj">CPF/CNPJ</label>
                    <input type="text" name="cpf_cnpj" id="cpfCnpj" value="'.$cpf_cnpj.'">
                </div>
                
                <div>
                    <label for="email">E-mail</label>
                    <input type="text" name="email" id="email" value="'.$email.'">
                </div>
                
                <div>
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone" id="telefone" value="'.$telefone.'">
                </div>
        ';

        if($status=="A"){
            echo'
                <select name="status" id="status">
                    <option value="A" selected>Ativo</option>
                    <option value="I">Inativo</option>
                </select>
            ';
        }else{
            echo'
                <select name="status" id="status">
                    <option value="A">Ativo</option>
                    <option value="I" selected>Inativo</option>
                </select>
            ';
        }

        if($perfil=="0"){
            echo'
                <select name="perfil">
                    <option value="0" selected>Clientes</option>
                    <option value="1">Funcionarios</option>
                    <option value="2">Administradores</option>
                </select>
            ';
        }else if($perfil=="1"){
            echo'
                <select name="perfil">
                    <option value="0">Clientes</option>
                    <option value="1" selected>Funcionarios</option>
                    <option value="2">Administradores</option>
                </select>
            ';
        }else if($perfil=="2"){
            echo'
                <select name="perfil">
                    <option value="0">Clientes</option>
                    <option value="1">Funcionarios</option>
                    <option value="2" selected>Administradores</option>
                </select>
            ';
        }

        echo '
        <button type="submit">Editar</button>
            </form>
        ';
            
    }
?>