<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Models.Curso"%>
<%@ page import="Models.Materia"%>
<%@ page import="Models.Docente"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.CursoDAO"%>


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
	CursoDAO cdao = new CursoDAO();
	String where="";
	String textoAccionAlumno = "";
	String urlAccionAlumno = "";
	String tipo = (String) session.getAttribute("Tipo").toString();
	if (tipo.equals("0")) {
		textoAccionAlumno = "Listar/agregar/elimar Alumno del curso";
		urlAccionAlumno = "AlumnoCursoADM.jsp";
	} else {
		
		textoAccionAlumno = "Asignar Notas";
		urlAccionAlumno = "AsignarNota.jsp";
		where=" id_docente="+session.getAttribute("legajo").toString();
	}
	ArrayList<Curso> cursos = cdao.FindCurso(where, false);
%>

</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include>
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main>

	<div></div>
	<div class="table-responsive col-lg-12 center">
		<div class="row">
			<div class="col-xs-8 col-md-8 col-lg-8">
				<h2>Cursos</h2>
			</div>
			<%if (tipo.equals("0")) { %>
			<div class="col-xs-4 col-md-4 col-lg-4" style="text-align: right;">
				<div class="btn-icon">
					<img data-action="agregar" data-toggle="modal"
						data-target="#modalAgregarCurso" data-toggle="tooltip"
						data-placement="top" title="Agregar" class="btn-agregar"
						src="./img/agregar.png">
				</div>
			</div>
			<%} %>
		</div>
		<table id="ListaCursos" class="display" data-page-length='10'>
			<thead>
				<tr>
					<th style="width: 3em;">Nuumero</th>
					<th>Materia</th>
					<th>Semestre</th>
					<th style="width: 3em;">A&ntilde;o</th>
					<th>profesor</th>
					<th style="width: 3em;">Alumnos</th>
					<%
						if (session.getAttribute("Tipo").equals("0")) {
					%>

					<th style="width: 3em;">Editar</th>
					<th style="width: 3em;">Modificar</th>
					<%
						}
					%>

				</tr>
			</thead>
			<%
				if (cursos != null) {
			%>
			<tbody>
				<%
					for (Curso c : cursos) {
				%>
				<tr id="curso-<%=c.getId()%>">
					<td style="width: 3em;" data-object="numero"><%=c.getId()%></td>
					<td data-object="materia"
						data-value="<%=c.getMateria().getIdMateria()%>"><%=c.getMateria().getDescripcion()%></td>
					<td data-object="semestre" data-value="<%=c.getSemestre()%>"><%=c.getSemestre()%></td>
					<td style="width: 3em;" data-object="anio"
						data-value="<%=c.getAnio()%>"><%=c.getAnio()%></td>
					<td data-object="docente"
						data-value="<%=c.getDocente().getLegajo()%>"><%=c.getDocente().getNombre() + " " + c.getDocente().getApellido()%></td>
					<td style="width: 3em;" data-objet="alumno"><div
							class="btn-icon-table"  data-toggle="tooltip"
								data-placement="top" title="<%=textoAccionAlumno%>">
							<a href="<%=urlAccionAlumno%>?curso=<%=c.getId()%>"> <img
								src="./img/Listar.png" alt="">
							</a>

						</div></td>
					<%
						if (session.getAttribute("Tipo").equals("0")) {
					%>
					<td style="width: 3em;" data-objet="modificar"><div
							class="btn-icon-table" data-toggle="tooltip"
								data-placement="top" title="modificar">
							<img src="./img/editar.png" data-action="modificar"
								data-value="<%=c.getId()%>" data-toggle="modal"
								data-target="#modalAgregarCurso" >
						</div></td>
					<td style="width: 3em;" data-objet="eliminar"><div
							class="btn-icon-table" data-toggle="tooltip"
								data-placement="top" title="eliminar">
							<img src="./img/eliminar.png" data-value="<%=c.getId()%>"
								data-action="eliminar" data-toggle="modal"
								data-target="#ModalEliminar" >
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
	<div class="modal fade" tabindex="-1" id="modalAgregarCurso"
		role="dialog" aria-labelledby="modalAgregarCurso" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Agregar Curso</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="get" action="">
						<div class="form-group group-inline">
							<label for="materia" class=" col-md-3">Materia</label> <select
								name="materia" id="selectMateria">
							</select>
						</div>
						<div class="form-group group-inline">
							<label for="semestre" class=" col-md-3">Semestre</label>
							<select id="semestre" class="form-control  col-md-8" >
							<option value="Primer">Primer</option>
							<option value="Segundo">Segundo</option>
							</select> 
						</div>
						<div class="form-group group-inline">
							<label for="anio" class=" col-md-3">A&ntilde;o</label> <input
								id="anio" class="form-control  col-md-8" type="number" name="anio">
						</div>
						<div class="form-group group-inline">
							<label for="docente" class=" col-md-3">Docente</label> <select
								name="docente" id="selectDocente">
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
	<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog"
		aria-labelledby="ModalEliminar" aria-hidden="true">
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
					<p>Estas seguro que desea eliminar este Curso?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Salir</button>
					<button type="button" class="btn btn-primary" id="btn-eliminar">Eliminar</button>
				</div>
			</div>
		</div>
	</div>
	</main>
	<script src="./js/curso.js"></script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			table = $('#ListaCursos').DataTable({
				"autoWidth" : false,
				"dom" : '<"top"f>t<"bottom"p><"clear">',
			}).columns.adjust().draw();
			
		});
		var objeto =new curso();
	</script>
</body>
</html>