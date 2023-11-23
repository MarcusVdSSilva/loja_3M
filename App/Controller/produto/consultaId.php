<?php
    require('../../Model/produto.php');
    $produto = new Produto();
    
    //DESCRIPTOGRAFANDO ID
    $idCripto = $_GET['i'];
    $idCriptografado = $idCripto;
    for ($i=0; $i < 10; $i++) { 
        $idCripto = base64_decode($idCripto);
    }
    

    $listar = $produto->consultaId($idCripto);

    while($linha = $listar->fetch_assoc()){
        $nome = $linha['nome'];
        $marca = $linha['marca'];
        $preco = $linha['preco'];
        $foto = $linha['foto'];
        //CRIPTOGRAFANDO ENDEREÇO DE FOTO
        $fotoCripto = $foto;
        $fotoCripto = base64_encode($fotoCripto);

        echo('
            <form action="editar.php?i='.$idCriptografado.'&f='.$fotoCripto.'" method="post" enctype="multipart/form-data">
                
                <label for="nome">Digite o nome do produto:</label>
                <input type="text" name="nome" id="nome" value='.$nome.'>

                <label for="marca">Digite a marca do produto:</label>
                <input type="text" name="marca" id="marca" value='.$marca.'>

                <label for="preco">Digite o valor do produto(R$):</label>
                <input type="text" name="preco" id="preco" value='.$preco.'>

                <label for="imagem">Escolha uma imagem para o produto:</label>
                <input type="file" name="imagem" id="imagem" accept="image/*">

                <button type="submit">Salvar Alterações</button>

            </form>
        ');
    }
?>