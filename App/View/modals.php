<link rel="stylesheet" href="../../Assets/css/modals.css">
<div id="usuario" class="modal">
    <div class="modal-content">
        
        <div class="close">
            <span class="btnClose">X</span>
            
        </div>
        
        <form id="cadastrar" action="../Controller/cadastrarBarbeiro.php" method="post" autocomplete="off">
                <div class="nome">
                    <label for="nome">Digite o Nome do Barbeiro:</label>
                    <input type="text" placeholder="nome" id="nome" name="nome">
                </div>
        
                <div class="btn">
                    <button type="submit">Criar Barbeiro</button>
                </div>
            </form>
    </div>
</div>


<div class="modal" id="modal">
    
        <button class="botaoFechar">X</button>
        <form class="formModal" action="">
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

<script src="../../Assets/js/jquery.js"></script>
<script src="../../Assets/js/modal.js"></script>
