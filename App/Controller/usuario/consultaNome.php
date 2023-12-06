<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<?php
    require("../../Model/usuario.php");
    $usuario = new Usuario();

    $nome=$_REQUEST['nome'];
    $perfil = $_REQUEST['perfil'];

    $listar = $usuario->consultaNome($nome,$perfil);

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
                <td><a href="CAMINHO AQUI.php?"><span class="material-symbols-outlined">edit</span></a></td>
            </tr>
            ');
            
    }
?>