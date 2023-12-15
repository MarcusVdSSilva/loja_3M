<link rel="stylesheet" href="../../Assets/css/modals.css">
<div id="usuario" class="modal">


    <div class="close">
        <span class="btnClose">X</span>

    </div>

    <form class="formModal" id="cadastrar" action="../Controller/usuario/cadastrar.php?cadastro" method="post" autocomplete="off">
        <input type="text" name="nome" id="" placeholder="Nome" required>
        <input type="text" name="telefone" id="" placeholder="Telefone" required>
        <input type="text" name="cpf_cnpj" id="" placeholder="CPF" required>
        <input type="text" name="email" id="" placeholder="E-mail" required>
        <input type="password" name="senha" id="" placeholder="Senha" required>
        <select name="perfil">
            <option value="0">Cliente</option>
            <option value="1">Funcionario</option>
            <option value="2">Administrador</option>
        </select>
        <button type="submit">Cadastrar</button>
    </form>

</div>


<div class="modal" id="modal">

    <button class="botaoFechar">X</button>
    <form class="formModal" action="">

    </form>
</div>

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/modal.js"></script>

<!-- MODAL PRODUTO -->
<div id="produto" class="modal">


    <div class="close">
        <span class="btnClose">X</span>

    </div>

    <form class="formModal" id="cadastrar" action="../Controller/produto/cadastrar.php" method="post" autocomplete="off" enctype="multipart/form-data">
        <input type="text" name="nome" id="" placeholder="Nome" required>
        <input type="text" name="marca" id="" placeholder="Marca" required>
        <input type="text" name="preco" id="" placeholder="Preço" required>
        <input required type="file" id="fileInput" name="imagem" class="custom-file-input" accept=".png">
     
 
        <button class="cadastrar" type="submit">Cadastrar</button>
    </form>

</div>


<div class="modal" id="modal">

    <button class="botaoFechar">X</button>
    <form class="formModal" action="">

    </form>
</div>

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/modal.js"></script>
<script>
    

        function updateLabel() {
            // Obter o nome do arquivo selecionado
            const fileName = document.getElementById('fileInput').value.split('\\').pop();
            
            // Atualizar o rótulo com o nome do arquivo
            document.querySelector('.custom-file-label').innerText = fileName || 'Escolha um arquivo';
        }
    </script>