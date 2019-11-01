function Usuario() {

    this.loguot = function (da) {
        $.ajax({
            url: "UsuarioControlle",
            type: "POST",
            data: "action=logout",
            processData: false,
            contentType: false,
            success: function (response) {
                if (response == "ok") {
                    location("login.jsp");
                } else {
                    alert("no se pudo realizar la accion");
                }

            }
        })
    }
    this.login=function(user,pass){
        if(userr!=""&& pass)
    {
        var datos = new FormData();
        datos.append("action", "login");
        datos.append("txtUsuario",user );
        datos.append("txtPassword", pass);
    
        $.ajax({
            url: "UsuarioControlle",
            type: "POST",
            data: datos,
            processData: false,  
            contentType:  false, 
            success: function (response) {
                if(response=="error"){
                    error("Usuario o password incorrecto");
                }else
                {
                    location("index.jsp")
                }
               
            }
        })
    }else
    {
        error("Complete todos los datos");
    }
    }
}


