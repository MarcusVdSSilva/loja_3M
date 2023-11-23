<?php 
    require_once("../../Model/produto.php");

    if($_SERVER['REQUEST_METHOD'] == "POST"){
        if(isset($_POST['nome']) && isset($_POST['marca']) && isset($_POST['preco']) && isset($_FILES['imagem'])){
            $nome = $_POST['nome'];
            $marca = $_POST['marca'];
            $preco = $_POST['preco'];

            $foto = "../../../Assets/img/img_produtos/".$_FILES["imagem"]["name"];
            move_uploaded_file($_FILES['imagem']["tmp_name"],$foto);

            //SALVANDO NO BANCO
            $produto = new Produto();
            $cadastrar = $produto->cadastrar($nome,$marca,$preco,$foto);
        }
    }

?>