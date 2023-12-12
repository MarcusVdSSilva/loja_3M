("#btnAdcUser").click(()=>{
    $("#usuario").show();
    $("#usuario").css("display","flex");
})


//Fechar Modals

//com click no botão
$(".btnClose").click(()=>{
    $("#pousada").hide();
  
    
})

//com click fora
$(".modal").click(function(e){
    if ($(e.target).hasClass("modal")) {
        $("#pousada").hide();
        
    }
});