function Usuario() {

    this.logout = function () {
    	 var datos = new FormData();
         datos.append("action", "logout");
        $.ajax({
            url: "UsuarioServlet",
            type: "POST",
            processData: false,  
            contentType:  false, 
            data:datos,
            success: function (response) {
                if (response == "ok") {
                    location.href="./login.jsp";
                } else {
                    alert("no se pudo realizar la accion");
                }

            }
        })
    }
    this.login=function(user,pass){
        if(user!=""&& pass!="")
    {
        var datos = new FormData();
        datos.append("action", "login");
        datos.append("txtUsuario",user );
        datos.append("txtPassword", pass);
    
        $.ajax({
            url: "UsuarioServlet",
            type: "POST",
            data: datos,
            processData: false,  
            contentType:  false, 
            success: function (response) {
                if(response=="error"){
                    notificacion("Usuario o password incorrecto");
                }else
                {
                    location.href="./index.jsp";
                }
               
            }
        })
    }else
    {
        notificacion("Complete todos los datos");
    }
    }
}


