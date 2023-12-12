<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require('../../Model/produto.php');
    $produto = new Produto();

    $nome=$_REQUEST['nome'];

    $listar = $produto->consultaNome($nome);
        while($linha = $listar->fetch_assoc()){
            $id = $linha['id'];
            $nome = $linha['nome'];
            $marca = $linha['marca'];
            $preco = $linha['preco'];
            $foto = $linha['foto'];

            //CRIPTOGRAFANDO ID
            $idCripto = $id;
            for ($i=0; $i < 10; $i++) { 
                $idCripto = base64_encode($idCripto);
            }
            
            echo('
                <tr>
                    <td>' . $id . ' </td>
                    <td>' . $nome . ' </td>
                    <td>' . $marca . ' </td>
                    <td>' . $preco . ' </td>
                    <td> <img src="'.$foto.'" alt="imagem do produto" </td>
                    <td> <a href="consultaId.php?i='.$idCripto.'">Icone</a> </td>
                <tr>
            ');
            
        }
        
    
?>