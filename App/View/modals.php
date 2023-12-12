<link rel="stylesheet" href="../../Assets/css/modals.css">
<div id="usuario" class="modal">


    <div class="close">
        <span class="btnClose">X</span>

    </div>

    <form class="formModal" id="cadastrar" action="../Controller/usuario/cadastrar.php?cadastro" method="post" autocomplete="off">
        <input type="text" name="" id="" placeholder="Nome">
        <input type="text" name="" id="" placeholder="Telefone">
        <input type="text" name="" id="" placeholder="CPF">
        <input type="text" name="" id="" placeholder="E-mail">
        <input type="password" name="" id="" placeholder="Senha">
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

    <form class="formModal" id="cadastrar" action="../Controller/cadastrarBarbeiro.php" method="post" autocomplete="off">
        <input type="text" name="" id="" placeholder="Nome">

        <input type="text" name="" id="" placeholder="Marca">
        <input type="text" name="" id="" placeholder="Preço">
        <input type="file" id="fileInput" class="custom-file-input" accept=".png, .jpg, " onchange="updateLabel()">
      

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
<script>
        function updateLabel() {
            // Obter o nome do arquivo selecionado
            const fileName = document.getElementById('fileInput').value.split('\\').pop();
            
            // Atualizar o rótulo com o nome do arquivo
            document.querySelector('.custom-file-label').innerText = fileName || 'Escolha um arquivo';
        }
    </script>