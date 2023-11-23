<?php 
    $nomeDoArquivo="../../../Assets/img/img_produtos/guarana_charua.jpeg";
    
    if (file_exists($nomeDoArquivo)) {
        if (unlink($nomeDoArquivo)) {
            echo "Arquivo removido com sucesso.";
        } else {
            echo "Falha ao remover o arquivo.";
        }
    } else {
        echo "O arquivo não existe.";
    }
?>