<?php
    require('../Model/venda.php');
    $venda = new Venda();

    $listar = $venda->listar();
            

    while($linha = $listar->fetch_assoc()){
        $id = $linha['id_venda'];
        $valor = $linha['valor'];
        $data = $linha['data'];
        $nomeCliente = $linha['nome_usuario'];
        $produto = $linha['nome_produto'];
        
        
        echo('
            <tr>
                <td>' . $id . ' </td>
                <td>' . $valor . ' </td>
                <td>' . $data . ' </td>
                <td>' . $nomeCliente . ' </td>
                <td>' . $produto . ' </td>
            <tr>
        ');

            
    }
?>