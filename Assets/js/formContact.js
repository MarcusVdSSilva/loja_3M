$('#submitButton').click(function(e){
  e.preventDefault();
  let nome = $('#nome').val();
  let telefone = $('#telefone').val();
  let email = $('#email').val();
  let mensagem = $('#msg').val();
  
  $.ajax({
      type: 'Post',
      url: '../../App/Controller/mailContact.php',
      data: $('#formContact').serialize(),
      success: function(){
        if(nome.length !== 0 && telefone.length !== 0 && email.length !== 0 && mensagem.length !== 0 ){
            Swal.fire({
                icon: 'success',
                iconColor: '',
                title: 'Tudo Certo!',
                text: 'Seu e-mail foi enviado com sucesso!'
              })
        }else if (nome.length == 0 || telefone.length == 0 || email.length == 0 || mensagem.length == 0 ) {
            Swal.fire({
                icon: 'warning',
                iconColor: '#F40000',
                title: 'Oops...',
                text: 'Algum dos campos não está preenchido, Por favor preencha para proseguir!'
              })
        }
          
      },
      error: function(){
        Swal.fire({
            icon: 'error',
            iconColor: '#F40000',
            title: 'Oops...',
            text: 'Não foi possivel enviar seu e-mail, tente novamente mais tarde!'
          })
         
    }
  })
});