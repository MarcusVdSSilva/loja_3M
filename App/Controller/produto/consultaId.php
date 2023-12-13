<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Headers: Content-Encoding");
    require('../Model/produto.php');
    $produto = new Produto();
    
    //DESCRIPTOGRAFANDO ID
    $idCripto = $_GET['i'];
    $idCriptografado = base64_decode($idCripto);
    

    $listar = $produto->consultaId($idCriptografado);

    while($linha = $listar->fetch_assoc()){
        $nome = $linha['nome'];
        $marca = $linha['marca'];
        $preco = $linha['preco'];
        $foto = $linha['foto'];
        //CRIPTOGRAFANDO ENDEREÇO DE FOTO
        $fotoCripto = $foto;
        $fotoCripto = base64_encode($fotoCripto);

        $parteARemover = "../";
        $novoCaminho = str_replace($parteARemover, "", $foto);
        echo $foto;
        echo '<img src="../../'.$novoCaminho.'" alt="imagem do produto">';

        echo('
            <form action="../Controller/produto/editar.php?i='.$idCripto.'&f='.$fotoCripto.'" method="post" enctype="multipart/form-data">
                
                <label for="nome">Digite o nome do produto:</label>
                <input type="text" name="nome" id="nome" value='.$nome.'>

                <label for="marca">Digite a marca do produto:</label>
                <input type="text" name="marca" id="marca" value='.$marca.'>

                <label for="preco">Digite o valor do produto(R$):</label>
                <input type="text" name="preco" id="preco" value='.$preco.'>

                <label for="imagem">Escolha uma imagem para o produto:</label>
                <input type="file" name="imagem" id="imagem" value=../../'.$novoCaminho.' accept="image/*">

                <button type="submit">Salvar Alterações</button>

            </form>
        ');
    }
?>