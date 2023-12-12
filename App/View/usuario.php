<?php 
    // session_start();
	// if(isset($_SESSION['logado'])&& isset($_SESSION['status']) && isset($_SESSION['perfil'])){
    //     if($_SESSION['perfil']=="2"){
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Assets/css/usuario.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <title>Lojas 3M - Administrador</title>
</head>
<body translate="no">
    <?php require_once("header.php");?>

   
        <form id="consultaForm" class="center">
            <input type="text" name="nome" id="nome" placeholder="Busca por nome">
            <select name="perfil">
                <option value="" selected>Todos</option>
                <option value="0">Clientes</option>
                <option value="1">Funcionarios</option>
                <option value="2">Administradores</option>
            </select>
            <button type="submit"><span class="material-symbols-outlined">search</span></button>
        </form>
        <button type="button" id="btnAdcUser" class="center adicionar">Adicionar +</button>
        <table>
            <thead>
                <th>Nome</th>
                <th>Perfil</th>
                <th>Status</th>
                <th>CPF</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Editar</th>
            </thead>
            <tbody>
                <?php require_once("../Controller/usuario/listar.php");?>
            </tbody>
        </table>
   

    <?php require_once("modals.php");?>
            

    <script src="../../Assets/js/jquery.js"></script>
    <script src="../../Assets/js/modal.js"></script>
    <script>
        $(document).ready(function() {
        $("#consultaForm").submit(function(event) {
        event.preventDefault();

        // pegando dados do form
        var formData = $(this).serialize();

        // Enviando a requisição AJAX
        $.ajax({
            type: "GET",
            url: "../Controller/usuario/consultaNome.php",
            data: formData,
            success: function(response) {
                $("tbody").html(response);
            },
            error: function(error) {
                console.log("Erro na requisição AJAX:", error);
            }
        });
    });
});
    </script>
    </body>
</html>

<?php 
    //     }else{
    //         header('Location: login.php');
    //     }
    // }else{
    //     header('Location: login.php');
    // }
?>