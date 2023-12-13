<?php 
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
    header("Access-Control-Allow-Headers: Content-Type");
    header("Access-Control-Allow-Headers: Content-Encoding");
    
    require_once("../../Model/produto.php");

    if($_SERVER['REQUEST_METHOD'] == "POST"){
        if(isset($_POST['nome']) && isset($_POST['marca']) && isset($_POST['preco']) && isset($_FILES['imagem'])){
            $nome = $_POST['nome'];
            $marca = $_POST['marca'];
            $preco = $_POST['preco'];

            $nomeArquivo = $_FILES["imagem"]["name"];
            $imagem = $_FILES["imagem"]["name"];
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

            $image64 = base64_encode(file_get_contents($imagem));
            
            //SALVANDO NO BANCO
            $produto = new Produto();
            $cadastrar = $produto->cadastrar($nome,$marca,$preco,$foto,$image64);
        }
    }

?>