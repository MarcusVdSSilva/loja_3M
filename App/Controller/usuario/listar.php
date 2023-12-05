<?php
    require("../Model/usuario.php");
    $usuario = new Usuario();

    $listar = $usuario->listar();
            

    while($linha = $listar->fetch_assoc()){
        $id = $linha['id'];
        $perfil = $linha['perfil'];
        $status = $linha['status'];
        $nome = $linha['nome'];
        $cpf_cnpj = $linha['cpf_cnpj'];
        $email = $linha['email'];
        $telefone = $linha['telefone'];
        
        echo('
            <tr>
                <td>' . $nome . ' </td>
        ');

        //VERIFICAÇÃO DE PERFIL
        if($perfil=="0"){
            echo("<td>Cliente</td>");
        }else if($perfil=="1"){
            echo("<td>Funcionario</td>");
        }else{
            echo("<td>Administrador</td>");
        }

        //VERIFICAÇÃO DE STATUS
        if($status=="A"){
            echo("<td>Ativo</td>");
        }else{
            echo("<td>Inativo</td>");
        }

        echo('
                <td>' . $cpf_cnpj . '</td>
                <td>' . $email . '</td>
                <td>' . $telefone . '</td>
                <td><a href="CAMINHO AQUI.php?><span class="material-symbols-outlined">edit</span></a></td>
            </tr>
            ');
            
    }
?>