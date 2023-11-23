<?php
    require('../../Model/venda.php');
    $venda = new Venda();

    $listar = $venda->listar();
            

    while($linha = $listar->fetch_assoc()){
        $id = $linha['id'];
        $valor = $linha['valor'];
        $data = $linha['data'];
        $idUsuario = $linha['id_usuario'];
        
        echo('
            <tr>
                <td>' . $id . ' </td>
                <td>' . $valor . ' </td>
                <td>' . $data . ' </td>
                <td>' . $idUsuario . ' </td>
            <tr>
        ');

            
    }
?>