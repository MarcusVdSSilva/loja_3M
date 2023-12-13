<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require('../Model/produto.php');
    $produto = new Produto();

    $listar = $produto->listar();

        while($linha = $listar->fetch_assoc()){
            $id = $linha['id'];
            $nome = $linha['nome'];
            $marca = $linha['marca'];
            $preco = $linha['preco'];
            $foto = $linha['foto'];
            $parteARemover = "../";
            $novoCaminho = str_replace($parteARemover, "", $foto);

            //CRIPTOGRAFANDO ID
            $idCripto = base64_encode($id);
            
            
            echo('
                <tr>
                    <td>' . $id . ' </td>
                    <td>' . $nome . ' </td>
                    <td>' . $marca . ' </td>
                    <td>' . $preco . ' </td>
                    <td> <img src="../../'.$novoCaminho.'" alt="imagem do produto"> </td>
                    <td> <a href="editarProduto.php?i='.$idCripto.'"><span class="material-symbols-outlined">edit</span></a> </td>
                    
                <tr>
            ');
            
        }
    
?>