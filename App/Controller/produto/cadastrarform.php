<?php 
    if(isset($_FILES['imagem'])){
        echo "entrou primeiro if";
        move_uploaded_file($_FILES["imagem"]["tmp_name"] , "../../../Assets/img/img_produtos/".$_FILES["imagem"]["name"]);
        echo "funcionou";
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="cadastrar.php" method="post" enctype="multipart/form-data">
        <label for=""></label>
        <img src="../../../Assets/img/img_produtos/" alt="" srcset="">
        <input type="text" name="nome">
        <input type="text" name="marca">
        <input type="text" name="preco">
        <input type="file" name="imagem" accept="image/*">
        <button type="submit">enviar</button>
    </form>
</body>
</html>