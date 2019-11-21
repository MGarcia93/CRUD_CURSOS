function curso() {
    var _this = this;

    this.insert = function () {
        var datos = this.validarDatos();
        if (datos == null) {
            notificacion("algunos datos no son validos");
            return;
        }
        $.ajax({
            url: "CursoServlet",
            type: "POST",
            data: {
                action: "insert",
                materia: datos.materiaId,
                semestre: datos.semestre,
                anio: datos.anio,
                docente: datos.docenteId
            },
            success: (response) => {
                if (response != "error" || response != "0") {
                    showAlert("Se agrego Correctamente", "");
                    var datos = _this.obtenerDatosModal();
                    _this.agregarFila(response, datos);
                    _this.limpiarModal();
                }
                else {
                    notificacion("no se pudo agregar el curso");
                }

            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })

    }
    this.update = function (curso) {
        var datos = this.validarDatos();
        if (datos == null) {
            notificacion("algunos datos no son validos");
            return;
        }
        $.ajax({
            url: "CursoServlet",
            type: "POST",
            data: {
                action: "update",
                id: curso,
                materia: datos.materiaId,
                semestre: datos.semestre,
                anio: datos.anio,
                docente: datos.docenteId
            },
            success: (response) => {
                if (response != "error" || response != "0") {
                    notificacion("Se modifico Correctamente", "success");
                    var datos = _this.obtenerDatosModal();
                    _this.actualizarFila(response, datos);
                }
                else {
                    notificacion("no se pudo agregar el curso");
                }

            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })

    }
    this.delete = function (curso) {
        $.ajax({
            url: "CursoServlet",
            type: "POST",
            data: {
                action: "delete",
                id: curso,
            },
            success: (response) => {
                if (response != "error" || response != "0") {
                    this.eliminarFila(response)
                    showAlert("Se ha eliminado correctamente", " ")
                }
                else{
                	showAlert("No se pudo realizar la eliminacion", "ERROR: ","danger")
                }
            },
            error:(response)=>{
            	error.warn(response);
            	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
            }
        })
    }

    this.agregar = function () {
        $(".modal-title").innerHTML="Agregar Curso",
        this.rellenarMaterias();
        this.rellenarDocentes();
    }




    this.modificar = function (curso) {

        $(".modal-title").innerHTML="Modificar Curso",
        this.rellenarMaterias();
        this.rellenarDocentes();
        var datos=this.obtenerDatosTabla(curso);
        $("#selectMateria")[0].options[datos.materia].selected=true;
        $("#semestre").val(datos.semestre);
        $("#anio").val(datos.anio);
        $("#selectDocente")[0].options[datos.docente].selected=true;

    }


    this.agregarFila = function (curso, datos) {
        var row = [];
        row.push(curso);
        row.push(datos.materiaNombre);
        row.push(datos.semestre);
        row.push(datos.anio);
        row.push(datos.docenteNombre);
        row.push('<div class="btn-icon-table"><a href="AlumnoCursoADM.jsp?curso=' + curso + '"><img src="./img/Listar.png" alt="" data-toggle="tooltip"	data-placement="top" title="Listar/agregar/elimar Alumno del curso"></a></div>');
        row.push('<div class="btn-icon-table"> <img src="./img/editar.png" data-action="modificar"  data-curso="' + curso + '" data-toggle="modal" data-target="#modalAgregarCurso" data-toggle="tooltip"	data-placement="top" title="modificar"> </div>');
        row.push('<div class="btn-icon-table">  <img src="./img/eliminar.png"  data-curso="' + curso + '>"  data-action="eliminar" data-toggle="modal" data-target="#ModalEliminar" data-toggle="tooltip"	data-placement="top" title="eliminar"> </div>');

        table.row.add(row)
        table.draw();
    }

    this.actualizarFila = function (curso, datos) {
        var fila = document.getElementById("curso-" + curso);
        fila.querySelector("[data-object='materia'").innerText = datos.materiaNombre;
        fila.querySelector("[data-object='materia'").setAttribute("data-value", datos.materiaId);
        fila.querySelector("[data-object='docente'").innerText = datos.docenteNombre;
        fila.querySelector("[data-object='docente'").setAttribute("data-value", datos.docenteId);
        fila.querySelector("[data-object='semestre']").innerText = datos.semestre;
        fila.querySelector("[data-object='anio']").innerText = datos.anio;
    }



    this.eliminarFila = function (curso) {
        var fila = document.getElementById("curso-" + curso);
        document.getElementById("ListaCursos").querySelector("tbody").removeChild(fila);
        $("#ModalEliminar").modal("hide");
    }

    this.obtenerDatosTabla=function(curso){
        var fila=document.getElementById("curso-"+curso);
        var materia,docente;
        var options=document.getElementById("selectMateria");
        for( var i=0; i<options.length;i++)
        {
            if(options[i].value==fila.querySelector("[data-object='materia']").getAttribute("data-value")){
                materia=i;
                break;
            }
        }
        var options=document.getElementById("selectDocente");
        for( var i=0; i<options.length;i++)
        {
            if(options[i].value==fila.querySelector("[data-object='docente']").getAttribute("data-value")){
                docente=i;
                break;
            }
        }
        var datos={
            materia:materia,
            semestre:fila.querySelector("[data-object='semestre']").innerText,
            anio:fila.querySelector("[data-object='anio']").innerText,
            docente:docente
        }
         return datos;
    };
    this.obtenerDatosModal = function () {
        var datos = {
            materiaId: $("#selectMateria").val(),
            materiaNombre: $("#selectMateria")[0].selectedOptions[0].innerText,
            semestre: $("#semestre").val(),
            anio: $("#anio").val(),
            docenteId: $("#selectDocente").val(),
            docenteNombre: $("#selectDocente")[0].selectedOptions[0].innerText
        }
        return datos;
    }

    this.limpiarModal=function(){
        $("#semestre").val("");
        $("#anio").val("");
        $("#selectMateria").val(1);
        $("#selectDocente").val(1);
    
    }


    this.rellenarDocentes=function(){
        if(document.getElementById("selectDocente").options.length!=0){
            return;
        }
        $.ajax( {url: "PersonaServlet",
        type: "POST",
        data:{
            action:"listar",
            tipo:"docentes"
        },
        async: false,
        success: function (response) {
            response.forEach(element => {
                var option=document.createElement("option");
                option.value=element.legajo;
                option.text=element.nombre +" "+ element.apellido;
                document.getElementById("selectDocente").append(option);
    
            });
        },
        error:(response)=>{
        	error.warn(response);
        	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
        }
        });
    }
    this.rellenarMaterias=function(){
        if(document.getElementById("selectMateria").options.length!=0){
            return;
        }
        $.ajax( {url: "MateriaServlet",
        type: "POST",
        async: false,
        success: function (response) {
            response.forEach(element => {
                var option=document.createElement("option");
                option.value=element.idMateria;
                option.text=element.descripcion;
                document.getElementById("selectMateria").append(option);
    
            });
        },
        error:(response)=>{
        	error.warn(response);
        	showAlert("No se pudo conectar con el servidor", "ERROR: ","danger")
        }
        });
    }

    this.validarDatos=function(){
        var datos=this.obtenerDatosModal();
        var valido=true;
        if($("#semestre").val()==""){
        	$("#semestre").css("background-color","red");
        	$("#semestre").attr("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	};
        if( $("#anio").val()=="" || datos.anio<desdeAnio || datos.anio>hastaAnio || datos.anio.match(/\D/)){
        	$("#anio").css("background-color","red");
        	$("#anio").attr("onkeypress","this.removeAttribute('style');this.removeAttribute('onkeypress');");
    		valido=false
    	};

        return valido?datos:null;
    }
}