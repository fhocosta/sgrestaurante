$(document).foundation()

//MASCARAS

$("#telefone").mask("(99) 99999-9999");
$("#cpf").mask("999.999.999-99")
$("#cnpj").mask("99.999.999/9999-99");

//FUNCOES

$("#confirmarSenha").focusout(function(){
    $("#submit").removeAttr("disabled");
    $("#submit").html("Enviar");
    var senha = $("#senha").val();
    var confirmarSenha = $("#confirmarSenha").val();
    if(senha != confirmarSenha){
        $("#submit").attr("disabled", "disabled");
        $("#submit").html("Senhas não conferem!");
    }
})



