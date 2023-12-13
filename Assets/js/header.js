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

// $('.link').mouseleave(() => {   
//   $('.cor').css('color', 'white');
// })


$('#menuResponsivo li').each(function() {
    $(this).mouseenter(() => {   
    $(this).children().css('color', 'black');
    });
    $(this).mouseleave(() => {   
      $(this).children().css('color', 'white');
});
});

