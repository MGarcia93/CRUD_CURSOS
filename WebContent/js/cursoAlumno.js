function cursoAlumno(curso){
    this.curso=curso;

    this.table=null;
    var _this=this;
    this.insert=function(legajo){
        $.ajax({
            url:"CursoServlet",
            type:"POST",
            data:{
                action:"agregarAlumno",
                legajo:legajo,
                curso:_this.curso
            },
            success:function(response){
                if(response!="error" && response!=0){
                    var datos=_this.obtenerDatos(response)
                    _this.agregarFila(response,datos);
                    _this.LimpiarTabla();
                    $("#modalAgregarAlumnoCurso").modal("hide");
                    showAlert("Se agrego correctamente","success");
                }
                else{
                    notificacion("No se pudo agregar");
                }
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })
    }


    this.delete=function(legajo){
        $.ajax({
            url:"CursoServlet",
            type:"POST",
            data:{
                action:"sacarAlumno",
                legajo:legajo,
                curso:_this.curso
            },
            success:function(response){
                if(response!="error" && response!=0){
                    _this.eliminarFila(response);
                    _this.LimpiarTabla;
                    showAlert("Eliminacion Realizada","success");
                }
                else{
                    showAlert("No se pudo eliminaar","ERROR","danger");
                }
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })
    }



    this.agregar=function(){
        if(this.table!=null){
            return;
        }
        $.ajax({
            url:"CursoServlet",
            type:"POST",
            data:{
                action:"listarAlumnoDisponibles",
                curso:_this.curso
            },
            success:function(response){
                if(response!="error"){
                    var alumnos=JSON.parse(response);
                    var body=_this.getBodyModal()
                   body.appendChild(_this.listarAlumnos(alumnos));
                   _this.generarTabla();
                }
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })
    }

    

    this.eliminarFila = function (legajo) {
        var fila = document.getElementById("legajo-" + legajo);
        document.getElementById("Lista").querySelector("tbody").removeChild(fila);
        $("#ModalEliminar").modal("hide");
    }


    this.listarAlumnos= function(alumnos){
        var tabla= document.createElement("table");
        var thead=document.createElement("thead");
        var tbody=document.createElement("tbody");
        thead.innerHTML="<tr><th>Legajo</th><th>Nombre</th><th>Apellido</th><th>Seleccionar</th></tr>";
        alumnos.forEach(element=>{
            var fila= document.createElement("tr");
            var legajo=document.createElement("td");
            var nombre=document.createElement("td");
            var apellido=document.createElement("td");
            var seleccionar=document.createElement("td");
            legajo.innerText=element.legajo;
            nombre.innerText=element.nombre;
            apellido.innerText=element.apellido;
            seleccionar.innerHTML='<i class="fas fa-user-plus"></i>';
            seleccionar.setAttribute("data-value",element.legajo);
            seleccionar.setAttribute("data-action","seleccionar");
            seleccionar.setAttribute("data-toggle","tooltip");
            seleccionar.setAttribute("data-placement","top");
            seleccionar.setAttribute("title","Agregar alumno");
            nombre.setAttribute("data-object","nombre");
            apellido.setAttribute("data-object","apellido");
            fila.setAttribute("data-value","alumno-"+element.legajo)
            fila.appendChild(legajo);
            fila.appendChild(nombre);
            fila.appendChild(apellido);
            fila.appendChild(seleccionar);
            tbody.appendChild(fila);
        })
        tabla.setAttribute("id","ListaAlumnos");
        tabla.appendChild(thead);
        tabla.appendChild(tbody);
        tabla.setAttribute('data-page-length','6');
        return tabla;

    }
    this.LimpiarTabla=function(){
        this.getBodyModal().innerHTML="";
        this.table=null;
    }

    this.getBodyModal= function(){
        return document.querySelector("#modalAgregarAlumnoCurso").querySelector(".modal-body")
    }
    this.generarTabla= function(){
    	$.fn.DataTable.ext.pager.numbers_length = 5;
        this.table = $('#ListaAlumnos').DataTable({
            "autoWidth" : false,
            "dom" : '<"top"f>t<"bottom"p><"clear">',
        }).columns.adjust().draw();
        generarteActionSeleccionar();

        $('#ListaAlumnos').on( 'page.dt', function () {
        	setTimeout(function(){  generarteActionSeleccionar();}, 100);
        
        } );
    }
    
    this.obtenerDatos= function(legajo){
    	var fila= document.querySelector("[data-value='alumno-"+legajo+"']");
    	var datos={
    			nombre: fila.querySelector("[data-object='nombre']").innerText,
    			apellido: fila.querySelector("[data-object='apellido']").innerText
    	};
    	return datos;
    }
    this.agregarFila =function(alumno,datos){
        var fila=document.createElement("tr");
        var nombre=document.createElement("td");
        var apellido=document.createElement("td");
        var legajo=document.createElement("td");
        var eliminar=document.createElement("td");
        eliminar.innerHTML='<div class="btn-icon-table" data-toggle="tooltip" data-placement="top" title="eliminar"><img src="./img/eliminar.png" data-value="'+alumno+'" data-action="eliminar" data-toggle="modal" data-target="#ModalEliminar" ></div>';
        eliminar.style="widht:3em;";
        legajo.innerText=alumno;
        apellido.innerText=datos.apellido;
        nombre.innerText=datos.nombre;
        fila.setAttribute("id","alumno-"+alumno);
        fila.setAttribute("scope","row");
        fila.appendChild(legajo);
        fila.appendChild(nombre);
        fila.appendChild(apellido);
        fila.appendChild(eliminar);
        document.getElementById("Lista").querySelector("tbody").appendChild(fila);
    }


}