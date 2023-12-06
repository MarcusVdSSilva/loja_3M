<?php 
    require_once("../../Model/produto.php");

    if($_SERVER['REQUEST_METHOD'] == "POST"){
        if(isset($_POST['nome']) && isset($_POST['marca']) && isset($_POST['preco']) && isset($_FILES['imagem'])){
            
            $nome = $_POST['nome'];
            $marca = $_POST['marca'];
            $preco = $_POST['preco'];
           
            $nomeArquivo = $_FILES["imagem"]["name"];
            $diretorioImagens = "../../../Assets/img/img_produtos/";

            $foto = $diretorioImagens . $nomeArquivo;

            // Verificar se o arquivo já existe no diretório
            $contador = 1;
            while (file_exists($foto)) {
                $nomeArquivo = pathinfo($_FILES["imagem"]["name"], PATHINFO_FILENAME) . "_$contador." . pathinfo($_FILES["imagem"]["name"], PATHINFO_EXTENSION);
                $foto = $diretorioImagens . $nomeArquivo;
                $contador++;
            }

           move_uploaded_file($_FILES['imagem']["tmp_name"],$foto);

            $foto64 = base64_encode(file_get_contents($foto));

            $teste = base64_encode(file_get_contents($foto));


            //DESCRIPTOGRAFANDO IF
            $id = $_GET['i'];
            $id = base64_decode($id);

            //DESCRIPTOGRAFANDO CAMINHO DA IMAGEM PARA APAGAR
            $fotoApagar = $_GET['f'];
            $fotoApagar = base64_decode($fotoApagar);

            if (file_exists($fotoApagar)) {
                unlink($fotoApagar);
            }

            //SALVANDO NO BANCO
            $produto = new Produto();
            $editar = $produto->editar($nome,$marca,$preco,$foto,$foto64,$id);
        }
    
    }
?>