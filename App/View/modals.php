<link rel="stylesheet" href="../../Assets/css/modals.css">
<div id="usuario" class="modal">


    <div class="close">
        <span class="btnClose">X</span>

    </div>

    <form class="formModal" id="cadastrar" action="../Controller/cadastrarBarbeiro.php" method="post" autocomplete="off">
        <input type="text" name="" id="" placeholder="Nome">

        <input type="text" name="" id="" placeholder="CPF">
        <input type="text" name="" id="" placeholder="E-mail">
        <input type="text" name="" id="" placeholder="Telefone">
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
        <input type="text" name="" id="" placeholder="Imagem">
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