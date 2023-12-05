<?php 
    session_start();
	if(isset($_SESSION['logado'])&& isset($_SESSION['status']) && isset($_SESSION['perfil'])){
        if($_SESSION['perfil']=="2"){
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Assets/css/funcionario.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <title>Lojas 3M - Administrador</title>
</head>
<body>
    <?php require_once("header.php");?>

    <form action="../Controller/usuario/consultaNome.php">
        
        <div class="container">
            <input type="text" placeholder="pesquisar" name="nome">
            <span class="material-symbols-outlined">
            search</span>
        </div>
    </form>

    <div class="funcoes">
        <div class="criar" id="criar">
            <button class="criaFuncionario"><span>+</span></button>
            <p>Cadastrar funcionário</p>
        </div>
        <div class="modalCriar" id="modalCriar">
            <form action="../Controller/usuario/cadastrar.php" method="post">
                <div class="nome">
                    <input type="text" name="nome" id="nome">
                    <label for="nome">Nome</label>
                </div>
                <div class="senha">
                    <input type="text" name="senha" id="senha">
                    <label for="senha">Senha</label>
                </div>
                <button class="cadastrarFuncionario">Cadastrar</button>
            </form>
        </div>
        <div class="apagar">
            <button class="apagaFuncionario"><span>x</span></button>
            <p>Apagar funcionário</p>
        </div>
        <div class="editar">
            <button class="editaFuncionario"><span>%</span></button>
            <p>Editar funcionário</p>
        </div>
    </div>


    <table>
        <thead>

        </thead>

        <tbody>
            <?php require_once("../Controller/usuario/listar.php");?>
        </tbody>
    </table>
            

    <script src="../../Assets/js/jquery.js"></script>
    <script src="../../Assets/js/funcionario.js"></script>
</body>
</html>

<?php 
        }else{
            header('Location: login.php');
        }
    }else{
        header('Location: login.php');
    }
?>