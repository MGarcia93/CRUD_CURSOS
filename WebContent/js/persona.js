
function Persona(tipo){
    this.tipo=tipo;
    var _this=this;

    this.insert= function (){
        var datos=this.ValidarDatos();
        if (datos == null) {
            showAlert("los datos son incorrectos");
            return;
        }
        $.ajax({
            url:"./PersonaServlet",
            type:"POST",
                data:{
                    action:"insert",
                    tipo:tipo,
                    nombre:datos.nombre,
                    apellido:datos.apellido,
                    fechaNac:datos.fechaNac,
                    direccion:datos.direccion,
                    localidad:datos.localidad,
                    email:datos.email,
                    telefono:datos.telefono
                    },
                success:(response)=>{
                	var datos=_this.obtenerDatosModal();
                    if(response!="error" &&response!=0){
                    	_this.agregarFila(response,datos);
                         _this.limpiarModal();
                         showAlert("se agrego Correctamente","exito");
                         $("#modalAgregarPersona").modal("hide");
                    }
                    else{
                    	notificacion("No se pudo realizar el registro");
                    }
                   
                },
                error:(response)=>{
                	error.warn(response);
                	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
                }
        })
    };
    this.update = function(legajo){
    	var datos=this.ValidarDatos();
        if (datos == null) {
            notificacion("los datos son incorrectos");
            return;
        }
        $.ajax({
            url:"./PersonaServlet",
            type:"POST",
                data:{
                    action:"update",
                    tipo:tipo,
                    legajo:legajo,
                    nombre:datos.nombre,
                    apellido:datos.apellido,
                    fechaNac:datos.fechaNac,
                    direccion:datos.direccion,
                    localidad:datos.localidad,
                    email:datos.email,
                    telefono:datos.telefono
                    },
                success:(response)=>{
                	if(response!="error" &&response!=0){
                		var datos=_this.obtenerDatosModal();
                        _this.actualizarFila(response,datos);
                        showAlert("se modifico Correctamente","exito");
                	}
                	else {
                        notificacion("no se pudo realizar la modificacion");
                    }
                    
                },
                error:(response)=>{
                	error.warn(response);
                	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
                }
        })
    };

    this.delete=function(legajo){
        $.ajax({
            url:"./PersonaServlet",
            type:"POST",
                data:{
                    action:"delete",
                    tipo:tipo,
                    legajo:legajo,
                    },
                success:(response)=>{
                	if(response!="error" &&response!=0){
                		_this.eliminarFila(response);
                        showAlert("se elimino Correctamente","exito");
                        $("#ModalEliminar").modal("hide");
	                }
	            	else {
	            		showAlert("no se pudo realizar la eliminacion","error","danger");
	                }
                },
                error:(response)=>{
                	error.warn(response);
                	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
                }
        })
    };


    this.calificar=function(curso){
        var datos=this.ObtenerNotasTabla(curso);
        if(datos==null){
        	showAlert("ahi datos incorrectos","ERROR","danger");
        }
        $.ajax({
            url:"./PersonaServlet",
            type:"POST",
            data:{
            	action:"calificar",
            	data:JSON.stringify(datos)
            },
            success:function(response){
                if(response="ok"){
                	showAlert("Guardado con exito","");
                }
                else{
                	_this.ShowErrorCalificacion(response);
                }
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })

    };
    this.agregar=function(){
        document.getElementById("selectLocalidad").innerHTML="";
        this.rellenarProvincia();    
        $("#txtFechaNac").val(fechaNacMax);  
    }
    
    this.modificar= function(legajo){
        var datos= this.obtenerDatosTabla(legajo);
    	$("#txtNombre").val(datos.nombre);
        $("#txtApellido").val(datos.apellido);
        $("#txtFechaNac").val(this.formatDateInput(datos.fechaNac));
        $("#txtDireccion").val(datos.direccion);
        $("#txtEmail").val(datos.email);
        $("#txtTelefono").val(datos.telefono);
        this.rellenarProvincia();
        $("#selectProvincia").val(datos.provincia);
        $("#selectLocaliad").val(datos.localidad);
    }

    
    
    
    this.actualizarFila=function(legajo,datos){
        var fila=document.querySelector("[data-value='persona-"+legajo+"']");
        fila.querySelector("[data-object='nombre']").innerText=datos.nombre;
        fila.querySelector("[data-object='apellido']").innerText=datos.apellido;
        fila.querySelector("[data-object='fechaNac']").innerText=datos.fechaNac;
        fila.querySelector("[data-object='email']").innerText=datos.email;
        fila.querySelector("[data-object='telefono']").innerText=datos.telefono;
        fila.querySelector("[data-object='direccion']").innerText=datos.direccion;
        fila.querySelector("[data-object='localidad']").innerText=this.getLocalidadModal().nombre,
        fila.querySelector("[data-object='localidad']").setAttribute("data-value",this.getLocalidadModal().id);
        fila.querySelector("[data-object='provincia']").innerText=this.getProvinciaModal().nombre,
        fila.querySelector("[data-object='provincia']").setAttribute("data-value",this.getProvinciaModal().id);
    }

    this.listar=function(legajo){
        this.limpiarListaCurso();
        $.ajax({
            url:"./CursoServlet",
            type:"POST",
            data:{
                action:tipo=="Alumnos"?"listarCursoAlumno":"listarCursoDocente",
                legajo:legajo
            },
            success:function(response){
                if(response!="[]"){
                    var datos=JSON.parse(response);
                    _this.listarCursos(datos);
                }
                else{
                    _this.vacioListaCurso();
                }
                
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })
    }

    this.obtenerDatosTabla=function(legajo){
        var fila=document.querySelector("[data-value='persona-"+legajo+"']");
        var datos={
            nombre:fila.querySelector("[data-object='nombre']").innerText,
            apellido:fila.querySelector("[data-object='apellido']").innerText,
            fechaNac:fila.querySelector("[data-object='fechaNac']").innerText,
            email:fila.querySelector("[data-object='email']").innerText,
            telefono:fila.querySelector("[data-object='telefono']").innerText,
            direccion:fila.querySelector("[data-object='direccion']").innerText,
            localidad:fila.querySelector("[data-object='localidad']").getAttribute("data-value"),
            provincia:fila.querySelector("[data-object='provincia']").getAttribute("data-value"),
        }
        return datos;
    }

    this.eliminarFila=function(legajo){
        var fila=document.querySelector("[data-value='persona-"+legajo+"']");
        document.getElementById("Lista"+tipo).querySelector("tbody").removeChild(fila);
    }

    this.getLocalidadModal=function(){
    	return Localidad ={
    			id:$("#selectLocalidad").val(),
    			nombre: $("#selectLocalidad")[0].selectedOptions[0].innerText
    	}
    };

    
    this.getProvinciaModal=function(){
    	return Provincia ={
    			id:$("#selectProvincia").val(),
    			nombre: $("#selectProvincia")[0].selectedOptions[0].innerText
    	}
    };
    this.obtenerDatosModal=function(){
    	
    	var datos={
    			 nombre:$("#txtNombre").val(),
                 apellido:$("#txtApellido").val(),
                 fechaNac:_this.formatDateSql($("#txtFechaNac").val()),
                 direccion:$("#txtDireccion").val(),
                 localidad:$("#selectLocalidad").val(),
                 provincia:$("#selectProvincia").val(),
                 email:$("#txtEmail").val(),
                 telefono:$("#txtTelefono").val()
    	}
    	return datos;
    };
    this.formatDateInput=function(date){
        if(date!=""){
            date =date.split("/");
    	    date=date[2]+"-"+date[1]+"-"+date[0];
        }
    	
    	return date
    };
    this.formatDateSql=function(date){
        if(date!=""){
            date =date.split("-");
            date=date[2]+"/"+date[1]+"/"+date[0];
        }  
        return date
    };

    this.rellenarProvincia=function(){
        if(document.getElementById("selectProvincia").options.length!=0){
        	 $("#selectProvincia").trigger("change");
            return;
        }
        $.ajax( {url: "UtilsServlet",
        type: "POST",
        async: false,
        data:{
            action:"listarProvincias"
        },
        success: function (response) {
        	response=JSON.parse(response);
            response.forEach(element => {
                var option=document.createElement("option");
                option.value=element.id_provincia;
                option.text=element.nombre;
                document.getElementById("selectProvincia").append(option);

            });
            $("#selectProvincia").trigger("change");
    },
    error:(response)=>{
    	error.warn(response);
    	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
    }
    });
    }
    this.rellenarLocalidad=function(idProvincia){
        
        $.ajax( {url: "UtilsServlet",
        type: "POST",
        async: false,
        data:{
            action:"listaLocalidad",
            provincia:idProvincia
        },
        success: function (response) {
        response=JSON.parse(response);
            document.getElementById("selectLocalidad").innerHTML="";
            response.forEach(element => {
                var option=document.createElement("option");
                option.value=element.id_localidad;
                option.text=element.Nombre;
                document.getElementById("selectLocalidad").append(option);

            });
    }
    });
    }
    
    this.ValidarDatos= function(){
    	var valido=true;
        var datos = this.obtenerDatosModal();
        if(datos.nombre==""){
    		$("#txtNombre").css("background-color","red");
    		$("#txtNombre").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
        }
        if(datos.apellido==""){
    		$("#txtApellido").css("background-color","red");
    		$("#txtApellido").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
        }
        if(datos.FechaNac==""|| Date(datos.fechaNac)>Date(fechaNacMax)){
    		$("#txtFechaNac").css("background-color","red");
    		$("#txtFechaNac").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
        }
        if(datos.direccion==""){
    		$("#txtDireccion").css("background-color","red");
    		$("#txtDireccion").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
        }
        if(datos.email=="" || !datos.email.match(/(\w+)@(\w+).com/)){
    		$("#txtEmail").css("background-color","red");
    		$("#txtEmail").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
        }
        if(datos.telefono=="" || datos.telefono.match(/\D/)){
    		$("#txtTelefono").css("background-color","red");
    		$("#txtTelefono").attr("onchange","this.removeAttribute('style');this.removeAttribute('onkeypress');this.removeAttribute('onchange');");
    		$("#txtTelefono").attr("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');this.removeAttribute('onchange');");
    		valido=false
        }
    
        return valido?datos:null;
    }
    
    this.limpiarModal =function(){
    	$("#txtNombre").val("");
        $("#txtApellido").val("");
        $("#txtFechaNac").val("");
        $("#txtDireccion").val("");
        $("#txtEmail").val("");
        $("#txtTelefono").val("");
        $("#selectProvincia").val(1);
    	
    };
    
    this.agregarFila = function(legajo,datos){
    	var row=[];
    	row.push(legajo);
    	row.push(datos.nombre);
    	row.push(datos.apellido);
    	row.push(datos.fechaNac);
    	row.push(datos.email);
    	row.push(datos.telefono);
    	row.push(datos.direccion);
    	row.push(_this.getLocalidadModal().nombre);
    	row.push(_this.getProvinciaModal().nombre);
    	if(_this.tipo=="alumnos"){
    	row.push('<div class="btn-icon-table"><a href="AlumnoCursoADM.jsp?curso='+legajo+'"><img src="./img/Listar.png" alt="" data-toggle="tooltip"	data-placement="top" title="Listar/agregar/elimar Alumno del curso"></a></div>')
    	};
    	
    	row.push( '<div class="btn-icon-table"  data-toggle="tooltip"	data-placement="top" title="modificar"> <img src="./img/editar.png" data-action="modificarCurso"  data-value="'+legajo+'" data-toggle="modal" data-target="#modalAgregarCurso"> </div>');
    	row.push( '<div class="btn-icon-table" data-toggle="tooltip"data-placement="top" title="eliminar"> <img src="./img/eliminar.png"  data-value="'+legajo+'>"  data-action="eliminarCurso" data-toggle="modal" data-target="#cursoDelete" >   </div>');
    	 table.row.add(row);
         table.draw();
    }
    this.getTablaCurso=function(){
        return document.getElementById("listaCurso").querySelector("tbody");
    }

    this.listarCursos=function(datos){
    	datos.forEach(element => {
            var fila=document.createElement("tr");
            var curso=document.createElement("td");
            var materia=document.createElement("td");
            var semestre=document.createElement("td");
            var anio=document.createElement("td");
            var docente=document.createElement("td");
            curso.innerText=element.id;
            materia.innerText=element.materia.descripcion;
            semestre.innerText=element.semestre;
            anio.innerText=element.anio;
            docente.innerText=element.docente.nombre +" "+element.docente.apellido;
            fila.appendChild(curso);
            fila.appendChild(materia);
            fila.appendChild(semestre);
            fila.appendChild(anio);
            if(tipo=="Alumnos")fila.appendChild(docente);
            this.getTablaCurso().appendChild(fila);
        });
            
    }

    this.vacioListaCurso=function(){
        var fila= document.createElement("tr");
        var mensaje=document.createElement("td");
        mensaje.setAttribute("COLSPAN",6)
        mensaje.innerText="No se encontraron resultados";
        fila.appendChild(mensaje);
        this.getTablaCurso().appendChild(fila);

    }
    this.limpiarListaCurso=function(){
        this.getTablaCurso().innerHTML="";
    }

    this.getMiscursos=function(){
        return document.getElementById("selectCurso");
    }
    this.rellenarMisCursos=function(){
        
        if(this.getMiscursos()!=null){
            $.ajax({
                url:"./CursoServlet",
                type:"POST",
                data:{
                    action:"listarCursoDocente",
                    legajo:this.getMiscursos().getAttribute("data-value")
                },
                success:function(response){
                    var json=JSON.parse(response);
                    json.forEach(curso=>{
                        var option= document.createElement("option");
                        option.value=curso.id;
                        option.innerText=curso.id+" - "+curso.materia.descripcion;
                        
                        _this.getMiscursos().appendChild(option);
                    })
                },
                error:(response)=>{
                	error.warn(response);
                	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
                }

            })
        }
    }
    this.getBodyTablaNota=function(){
        return document.getElementById("listaNota").querySelector("tbody");
    }

    
    
    this.ObtenerNotasTabla=function(curso){
        var filas= this.getBodyTablaNota().querySelectorAll("tr");
        var datos=Array();
        var valido=true;
        filas.forEach(fila=>{
        	if(!_this.validarDatosNotas(fila)){
        		valido=false;
        	}
            var nota={
                primerParcial:fila.querySelector("[data-object='parcial-1']").value,
                segundoParcial:fila.querySelector("[data-object='parcial-2']").value,
                primerRecuperatorio:fila.querySelector("[data-object='Recuperatio-1']").value,
                segundoRecuperatorio:fila.querySelector("[data-object='Recuperatio-2']").value,
                legajo:fila.getAttribute("data-value").replace("alumno-",""),
                idCurso:curso,
                estado:fila.querySelector("[data-object='Estado']").value

            }
            datos.push(nota);

        });
        return valido?datos:null;
    }
    
    
    this.validarDatosNotas=function(datos){
    	var valido=true;
    	if(datos.querySelector("[data-object='parcial-1']").value=="" || (datos.querySelector("[data-object='parcial-2']").value>10 ||datos.querySelector("[data-object='parcial-2']").value<0)){
    		datos.querySelector("[data-object='parcial-1']").style="background-color:red";
    		datos.querySelector("[data-object='parcial-1']").setAttribute("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	}
    	if(datos.querySelector("[data-object='parcial-2']").value=="" || (datos.querySelector("[data-object='parcial-2']").value>10 ||datos.querySelector("[data-object='parcial-2']").value<0)){
    		datos.querySelector("[data-object='parcial-2']").style="background-color:red";
    		datos.querySelector("[data-object='parcial-2']").setAttribute("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	}
    	if(datos.querySelector("[data-object='Recuperatio-1']").value=="" || (datos.querySelector("[data-object='Recuperatio-1']").value>10 ||datos.querySelector("[data-object='Recuperatio-1']").value<0)){
    		datos.querySelector("[data-object='Recuperatio-1']").style="background-color:red";
    		datos.querySelector("[data-object='Recuperatio-1']").setAttribute("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	}
    	if(datos.querySelector("[data-object='Recuperatio-2']").value=="" || (datos.querySelector("[data-object='Recuperatio-2']").value>10 ||datos.querySelector("[data-object='Recuperatio-2']").value<0)){
    		datos.querySelector("[data-object='Recuperatio-2']").style="background-color:red";
    		datos.querySelector("[data-object='Recuperatio-2']").setAttribute("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	}
    	return valido;
    }
    
    this.ShowErrorCalificacion=function(error){
    	var p = document.createElement("p");
    	var modal=document.getElemenById("ModalError");
    	modal.querySelector(".modal-body").appendChild(p);
    	$(modal).modal("show");
    }


}