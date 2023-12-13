$("#dropdown").click(() => {
    $("#dropdownMenu").toggle();
});

// $(".navbar").click(function(e){
//     if ($(e.target).hasClass("navbar")) {
//         $("#menuMobile").hide();   
//     }
// });

$(window).on('click', function enventoClick(evento){ 
    if ($(evento.target).closest("#menuMobile").length > 0) {

        return; }
    $(".dropdownMenu").hide();
});