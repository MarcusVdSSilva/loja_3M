<?php 
    require_once("../../Model/produto.php");

    if($_SERVER['REQUEST_METHOD'] == "POST"){
        if(isset($_POST['nome']) && isset($_POST['marca']) && isset($_POST['preco']) && isset($_FILES['imagem'])){
            
            $nome = $_POST['nome'];
            $marca = $_POST['marca'];
            $preco = $_POST['preco'];
            $foto = "../../../Assets/img/img_produtos/".$_FILES["imagem"]["name"];
            move_uploaded_file($_FILES['imagem']["tmp_name"],$foto);

            //DESCRIPTOGRAFANDO IF
            $id = $_GET['i'];
            for ($i=0; $i < 10; $i++) { 
                $id = base64_decode($id);
            }

            //DESCRIPTOGRAFANDO CAMINHO DA IMAGEM PARA APAGAR
            $fotoApagar = $_GET['f'];
            $fotoApagar = base64_decode($fotoApagar);

            if (file_exists($fotoApagar)) {
                unlink($fotoApagar);
            }

            //SALVANDO NO BANCO
            $produto = new Produto();
            $editar = $produto->editar($nome,$marca,$preco,$foto,$id);
        }
    
    }
?>