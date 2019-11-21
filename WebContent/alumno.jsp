<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Models.Persona"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.AlumnoDAO"%>
<%@ page import="ModelsDao.CursoDAO"%>
<%@ page import="Models.Localidad"%>
<%@ page import="Models.Provincia"%>
<%@ page import="Models.Curso"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administracion Curso</title>
<jsp:include page="./Vista/head.jsp"></jsp:include>
<%

	if (session == null || session.getAttribute("User") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	AlumnoDAO aDAO = new AlumnoDAO();
	String textoAccionAlumno = "";
	String urlAccionAlumno = "";
	String where ="";
	String tipo = (String) session.getAttribute("Tipo").toString();
	ArrayList<Persona> alumnos=null;
	if (tipo.equals("0")) {
		textoAccionAlumno = "Listar/agregar/elimar Alumno del curso";
		urlAccionAlumno = "AlumnoCursoADM.jsp";
		alumnos =aDAO.FindPersonas("","" );
	} else {
		int docente = Integer.parseInt(session.getAttribute("legajo").toString());
		String curso="";
		if(request.getParameter("Curso")!=null){
			curso=request.getParameter("Curso");
		}
		textoAccionAlumno = "Asignar Notas";
		urlAccionAlumno = "AsignarNotaAlumnosCurso.jsp";
		alumnos =aDAO.findAlumnoDocente(docente,curso);
	}
	
%>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include>
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main>
	<div class="table-responsive col-lg-12 center">
		<div class="row">
			<div class="col-xs-4 col-md-4 col-lg-4">
				<h2>Alumnos</h2>
			</div>
			<%
				if (tipo.equals("0")) {
			%>
			<div class="col-xs-8 col-md-8 col-lg-8" style="text-align: right;">
				<div class="btn-icon">
					<img data-action="agregar" data-toggle="modal"
						data-target="#modalAgregarPersona" data-toggle="tooltip"
						data-placement="top" title="Agregar" class="btn-agregar"
						src="./img/agregar.png">
				</div>
			</div>
			<%
				}else{
			%>
			<div class="col-xs-4 col-md-4 col-lg-4" style="text-align: right;">
				<form action="./alumno.jsp" method="GET" class="form-inline">
					<div class="form-group group-inline">
							<label for="SelectCurso" class=" col-md-3">Cursos</label> 
							<select name="Curso" id="selectCurso" data-value="<%=Integer.parseInt(session.getAttribute("legajo").toString())%>">
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Seleccionar</button>
				</form>
			</div>
			<%} %>
		</div>
		<table id="ListaAlumnos" class="display table-fixed" data-page-length='15'>
			<thead>
				<tr>
					<th style="width: 3.5em;">Legajo</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Fecha Nac</th>
					<th style="width:13em" >Email</th>
					<th>Telefono</th>
					<th>Direccion</th>
					<th>Localidad</th>
					<th>Provincia</th>
					<%
						if (tipo.equals("0")) {
					%>
					<th style="width: 3em;">Editar</th>
					<th style="width: 5em;">Modificar</th>
					<%
						} else {
					%>
						<th>Cursos</th>
					<%
						}
					%>
				</tr>
			</thead>
			<%
				if (alumnos != null) {
			%>
			<tbody>
				<%
					for (Persona d : alumnos) {
				%>
				<tr data-value="persona-<%=d.getLegajo()%>">
					<td class="width:2em"><%=d.getLegajo()%></td>
					<td data-object="nombre"><%=d.getNombre()%></td>
					<td data-object="apellido"><%=d.getApellido()%></td>
					<td data-object="fechaNac"><%=d.getFechaNac()%></td>
					<td style="width:250px" data-object="email"><%=d.getEmail()%></td>
					<td data-object="telefono"><%=d.getTelefono()%></td>
					<td data-object="direccion"><%=d.getDireccion()%></td>
					<td data-object="localidad"
						data-value="<%=d.getLocalidad().getId_localidad()%>"><%=d.getLocalidad().getNombre()%></td>
					<td data-object="provincia"
						data-value="<%=d.getProvincia().getId_provincia()%>"><%=d.getProvincia().getNombre()%></td>
					<%
						if (tipo.equals("0")) {
					%>
					<td style="width: 2em;" data-objet="modificar">
						<div class="btn-icon-table" data-toggle="tooltip"
							data-placement="top" title="modificar">
							<img src="./img/editar.png" data-action="modificar"
								data-value="<%=d.getLegajo()%>" data-toggle="modal"
								data-target="#modalAgregarPersona">
						</div>
					</td>
					<td style="width: 2em;" data-objet="eliminar"><div
							class="btn-icon-table" data-toggle="tooltip" data-placement="top"
							title="eliminar">
							<img src="./img/eliminar.png" data-value="<%=d.getLegajo()%>"
								data-action="eliminar" data-toggle="modal"
								data-target="#ModalEliminar">
						</div></td>
					<%
						} else {
					%>
					<td style="width: 2em;" data-objet="cursos"><div
							class="btn-icon-table" data-toggle="tooltip" data-placement="top"
							title="Ver Cursos asignado">
							<img src="./img/Listar.png" alt=""
								data-value="<%=d.getLegajo()%>" data-action="listar"
								data-toggle="modal" data-target="#modalVerCursos">
						</div></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</tbody>

			<%
				}
			%>
		</table>
	</div>
	<div class="modal fade" tabindex="-1" id="modalAgregarPersona"
		role="dialog" aria-labelledby="modalAgregarPersona" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Agregar</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="get" action="">
						<div class="form-group group-inline">
							<label for="txtNombre" class=" col-md-3">Nombre</label> <input
								id="txtNombre" class="form-control  col-md-8" type="text"
								name="txtNombre">
						</div>
						<div class="form-group group-inline">
							<label for="txtApellido" class=" col-md-3">Apellido</label> <input
								id="txtApellido" class="form-control  col-md-8" type="text"
								name="txtApellido">
						</div>
						<div class="form-group group-inline">
							<label for="txtFechaNac" class=" col-md-3">Fecha
								Nacimiento</label> <input id="txtFechaNac"
								class="form-control  col-md-8" type="date" name="txtFechaNac"
								max="2003/01/01">
						</div>
						<div class="form-group group-inline">
							<label for="txtEmail" class=" col-md-3">Email</label> <input
								id="txtEmail" class="form-control  col-md-8" type="mail"
								name="txtEmail">
						</div>
						<div class="form-group group-inline">
							<label for="txtTelefono" class=" col-md-3">Telefono</label> <input
								id="txtTelefono" class="form-control  col-md-8" type="text"
								name="txtTelefono">
						</div>
						<div class="form-group group-inline">
							<label for="txtDireccion" class=" col-md-3">Direccion</label> <input
								id="txtDireccion" class="form-control  col-md-8" type="text"
								name="txtDireccion">
						</div>
						<div class="form-group group-inline">
							<label for="provincia" class=" col-md-3">Provincia</label> <select
								id="selectProvincia" class="form-control  col-md-8"
								name="provincia"></select>
						</div>
						<div class="form-group group-inline">
							<label for="localidad" class=" col-md-3">Localidad</label> <select
								id="selectLocalidad" class="form-control  col-md-8"
								name="Localidad">
							</select>
						</div>


					</form>
					<p id="boxMessage" class="hidden"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success hidden"
						id="btn-modificar">Modificar</button>
					<button type="button" class="btn btn-primary hidden"
						id="btn-agregar">Agregar</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalVerCursos" tabindex="-1" role="dialog"
		aria-labelledby="modalVerCursos" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cursos
						Asignados</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table id="listaCurso" class="table no-shadow table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col" style="width: 3em;">Nuumero</th>
								<th  scope="col" >Materia</th>
								<th  scope="col" >Semestre</th>
								<th  scope="col"  style="width: 3em;">A&ntilde;o</th>
								<th  scope="col" >profesor</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Salir</button>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog"
		aria-labelledby="cursoDelete" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminacion
						Curso</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Estas seguro que desea eliminar este Docente?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btn-eliminar">Eliminar</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Salir</button>

				</div>
			</div>
		</div>
	</div>
	</main>
	<script src="./js/persona.js"></script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			table = $('#ListaAlumnos').DataTable({
				"autoWidth" : false,
				"dom" : '<"top"f>t<"bottom"p><"clear">',
			});
			objeto.rellenarMisCursos();
		});
		var objeto = new Persona("Alumnos");
		
	</script>
</body>
</html>