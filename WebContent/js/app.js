
$("#ingresar").click(function (e) {
    e.preventDefault
    console.log("login");
    var Usuario=new Usuario();
    Usuario.login($("#txtUsuario").val(),$("#txtPassword").val())
    
   
})
function error(msg){
    if($("#boxMessage").hasClass("hidden")){
        $("#boxMessage").removeClass("hidden");
        $("#boxMessage").addClass("bg-danger");
        $("#boxMessage").addClass("text-white");
        $("#boxMessage").html(msg);
        setTimeout(function(){
            $("#boxMessage").addClass("hidden");
            $("#boxMessage").removeClass("bg-danger")
            $("#boxMessage").removeClass("text-white");
        },3000);
    }
}

$("#txtUsuario").keypress(function(e){
    if(e.which==13){
        $("#ingresar").click();
    }
})

$("#txtPassword").keypress(function(e){
    if(e.which==13){
        $("#ingresar").click();
    }
})

$(".icon-logout").click(()=>{
    var usuario=new Usuario();
    usuario.loguot();
})
$("#logout").click((e)=>{
    e.preventDefault;
    var usuario=new Usuario();
    usuario.loguot();
})