var table;
var desdeAnio=1950;
var hastaAnio=2025;
var fechaNacMax="2003/12/31";


$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})
$("#ingresar").click(function (e) {
    e.preventDefault
    console.log("login");
    var usuario=new Usuario();
    usuario.login($("#txtUsuario").val(),$("#txtPassword").val())
    
   
})
$("#insert").click((e)=>{
    e.preventDefault;
    console.log
})
$("[data-action='logout']").click(function(e){
	e.preventDefault;
	console.log(logout);
	var usuario= new Usuario();
	usuario.logout();
})
function notificacion(msg,tipo="error"){
    if($("#boxMessage").hasClass("hidden")){
        $("#boxMessage").removeClass("hidden");
        if(tipo=="error"){
            $("#boxMessage").addClass("bg-danger");
        }
        else if(tipo="success"){
            $("#boxMessage").addClass("bg-success");
        }
        
        $("#boxMessage").addClass("text-white");
        $("#boxMessage").html(msg);
        setTimeout(function(){
            $("#boxMessage").addClass("hidden");
            $("#boxMessage").removeClass("bg-danger")
            $("#boxMessage").removeClass("text-white");
        },2000);
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
    usuario.logout();
})
$("#logout").click((e)=>{
    e.preventDefault;
    var usuario=new Usuario();
    usuario.loguot();
})
/*

$("#agregarCurso").on("click",function(){
    $(".modal-title").innerHTML="Agregar Curso"
    BuscarMaterias();
    BuscarDocentes();
    if(!$("#btn-Modificar").hasClass("hidden"))$("#btn-Modificar").addClass("hidden");
    if($("#btn-Agregar").hasClass("hidden"))$("#btn-Agregar").removeClass("hidden");
})

$("[data-action='modificarCurso']").on("click",function(){
    $(".modal-title").innerHTML="Modificar Curso"
    BuscarMaterias();
    BuscarDocentes();
    if($("#btn-Modificar").hasClass("hidden"))$("#btn-Modificar").removeClass("hidden");
    if(!$("#btn-Agregar").hasClass("hidden"))$("#btn-Agregar").addClass("hidden");
    $("#btn-Modificar").attr("data-value",this.getAttribute('data-curso'));
    var fila=document.getElementById("curso-"+$(this).attr("data-curso"));
    $("#semestre").val($(fila).find("[data-object='semestre']").text());
    $("#anio").val($(fila).find("[data-object='anio']").text())
    var options=document.getElementById("selectMateria");
    for( var o of options)
    {
        if(o.value==$(fila).find("[data-object='materia']").attr("data-value")){
            o.selected=true;
            $(o).attr("selected","true");
            break;
        }
    }
    options=document.getElementById("selectDocente");
    for( var o of options)
    {
        if(o.value==$(fila).find("[data-object='docente']").attr("data-value")){
            o.selected=true;
            break;
        }
    }

    

})

$('[data-action="eliminarCurso"]').click(function(){
    $("#eliminarCurso").attr("data-value",$(this).attr("data-curso"));
})


function BuscarDocentes(){
    $.ajax( {url: "PersonaServlet",
    type: "POST",
    data:{
        action:"listar",
        tipo:"docentes"
    },
    async: false,
    success: function (response) {
        document.getElementById("selectDocente").innerHTML="";
        response.forEach(element => {
            var option=document.createElement("option");
            option.value=element.legajo;
            option.text=element.nombre +" "+ element.apellido;
            document.getElementById("selectDocente").append(option);

        });
    }
    });
}
function BuscarMaterias(){
    $.ajax( {url: "MateriaServlet",
    type: "POST",
    async: false,
    success: function (response) {
        document.getElementById("selectMateria").innerHTML="";
        response.forEach(element => {
            var option=document.createElement("option");
            option.value=element.idMateria;
            option.text=element.descripcion;
            document.getElementById("selectMateria").append(option);

        });
    }
    });
}

$("#modalAgregarCurso #btn-Agregar").click(function(){

    var datos=validarDatosCurso();
    if(datos==null){
        notificacion("complete todos los datos");
        return;
    }
    $.ajax({
        url:"CursoServlet",
        type:"POST",
        data:{
            action:"insert",
            materia:datos.materia,
            semestre:datos.semestre,
            anio:datos.anio,
            docente:datos.docente
        },
        success:(response)=>{
            if(response!="error" || response!="0"){
                notificacion("Se agrego Correctamente","success");
           
            setTimeout(function(){$("modalAgregarCurso").modal("hide")},1000);
            table.row.add([
                response,
                $("#selectMateria")[0].selectedOptions[0].innerText,
                datos.semestre,
                datos.anio,
                $("#selectDocente")[0].selectedOptions[0].innerText,
                '<div class="btn-icon-table"><a href="AlumnoCursoADM.jsp?curso='+response+'"><img src="./img/Listar.png" alt="" data-toggle="tooltip"	data-placement="top" title="Listar/agregar/elimar Alumno del curso"></a></div>',
                '<div class="btn-icon-table">                  <img src="./img/editar.png" data-action="modificarCurso"  data-curso="'+response+'" data-toggle="modal" data-target="#modalAgregarCurso" data-toggle="tooltip"	data-placement="top" title="modificar">          </div>',
			'<div class="btn-icon-table">                 <img src="./img/editar.png"  data-curso="'+response+'>"  data-action="eliminarCurso" data-toggle="modal" data-target="#cursoDelete" data-toggle="tooltip"				data-placement="top" title="eliminar">              </div>'
            ]
            )
            table.draw();
            limpiarModalCurso();
            }
            else{
                notificacion("no se pudo agregar el curso");
            }
            
        }
    })
});

$("#modalAgregarCurso #btn-Modificar").click(function(){

    var datos=validarDatosCurso();
    if(datos==null){
        notificacion("complete todos los datos");
        return;
    }
    $.ajax({
        url:"CursoServlet",
        type:"POST",
        data:{
            action:"update",
            id: this.getAttribute('data-value'),
            materia:datos.materia,
            semestre:datos.semestre,
            anio:datos.anio,
            docente:datos.docente
        },
        success:(response)=>{
            if(response!="error" || response!="0"){
                notificacion("Se modifico Correctamente","success");
           
            setTimeout(function(){$("modalAgregarCurso").modal("hide")},1000);
            var fila=document.getElementById("curso-"+response);
            fila.querySelector("[data-object='materia'").innerText=$("#selectMateria")[0].selectedOptions[0].innerTex;
            fila.querySelector("[data-object='materia'").setAttribute("data-value",$("#selectMateria")[0].value);
            fila.querySelector("[data-object='docente'").innerText=$("#selectDocente")[0].selectedOptions[0].innerTex;
            fila.querySelector("[data-object='docente'").setAttribute("data-value",$("#selectDocente")[0].value);
            fila.querySelector("[data-object='semestre']").innerText=$("#semestre")[0].value;
            fila.querySelector("[data-object='anio']").innerText=$("#anio")[0].value;
            }
            else{
                notificacion("no se pudo agregar el curso");
            }
            
        }
    })
})


$("#eliminarCurso").click(function(){
    $.ajax({
        url:"CursoServlet",
        type:"POST",
        data:{
            action:"delete",
            id: this.getAttribute('data-value'),
        },
        success:(response)=>{
            if(response!="error" || response!="0"){
            var fila=document.getElementById("curso-"+response);
         
            document.getElementById("ListaCursos").querySelector("tbody").removeChild(fila);
            $("#cursoDelete").modal("hide");
            
            }
        }
    })
})

function limpiarModalCurso(){
    $("#semestre").val("");
    $("#anio").val("");
    $("#selectMateria").val(1);
    $("#selectDocente").val(1);

}




function validarDatosCurso(){



    if($("#semestre").val()!="" && $("#anio").val()!=""){
        return {materia:$("#selectMateria")[0].value,semestre:$("#semestre").val(),anio:$("#anio").val(),docente:$("#selectDocente")[0].value}
    }
    return null
}*/


$("#selectProvincia").on("change",function(){
    objeto.rellenarLocalidad(this.value);
})

$('[data-action="eliminar"]').click(function(){
    $("#btn-eliminar").attr("data-value",$(this).attr("data-value"));
});

$("#btn-eliminar").click(function(){
    objeto.delete(this.getAttribute("data-value"));
});

$("#btn-agregar").click(function(){
    objeto.insert();
})
$("#btn-modificar").click(function(){
    objeto.update(this.getAttribute("data-value"));
})


$('[data-action="modificar"]').click(function(){
    if($("#btn-modificar").hasClass("hidden"))$("#btn-modificar").removeClass("hidden");
    if(!$("#btn-agregar").hasClass("hidden"))$("#btn-agregar").addClass("hidden");
    $("#btn-modificar").attr("data-value",this.getAttribute("data-value"));
    objeto.modificar(this.getAttribute("data-value"));
})

$('[data-action="agregar"]').click(function(){
    if(!$("#btn-modificar").hasClass("hidden"))$("#btn-modificar").addClass("hidden");
    if($("#btn-agregar").hasClass("hidden"))$("#btn-agregar").removeClass("hidden");
    objeto.agregar();
})

function generarteActionSeleccionar(){
	$('[data-action="seleccionar"]').click(function(){
    objeto.insert(this.getAttribute("data-value"));
})	
}

$('[data-action="listar"]').click(function(){
	objeto.listar(this.getAttribute("data-value"));
})

$('[data-action="calificar"]').click(function(){
	objeto.calificar(this.getAttribute("data-value"))
});


////////////////////////////////////////////////////////////////////////
/// metodo para la muestra de una  alerta
function showAlert(msg, titulo,tipo="success") {
	$.notify({
		title: titulo,
		message: msg, 
	},{
		delay: 1000,
		timer: 1000,
		type: tipo,
		placement: {
			from: "bottom"
		},z_index:2000
		
	});
}