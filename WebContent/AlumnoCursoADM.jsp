<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Models.Alumno"%>
<%@ page import="Models.Materia"%>
<%@ page import="Models.Docente"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.AlumnoDAO"%>
<%@ page import="ModelsDao.CursoDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administracion Curso</title>
<%
	CursoDAO cDao=new CursoDAO();
	if (session == null || session.getAttribute("User") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	if (request.getParameter("curso") == null || !cDao.existCurso(Integer.parseInt(request.getParameter("curso")))) {
		response.sendRedirect("curso.jsp");
		return;
	}
	int idCurso = Integer.parseInt(request.getParameter("curso"));
	AlumnoDAO adao = new AlumnoDAO();
	String where = " id_curso=" + idCurso;

	ArrayList<Alumno> alumnos = adao.findAlumnoCurso(idCurso);
%>
<jsp:include page="./Vista/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include>
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main>
	<div class="table-responsive col-lg-8 center">
		<div class="row">
			<div class="col-xs-8 col-md-8 col-lg-8">
				<h2>
					Lista de alumno en el curso:
					<%=idCurso%></h2>
			</div>
			<div class="col-xs-4 col-md-4 col-lg-4" style="text-align: right;">
				<div class="btn-icon">
					<img data-action="agregar" data-toggle="modal"
						data-target="#modalAgregarAlumnoCurso" data-toggle="tooltip"
						data-placement="top" title="Agregar" class="btn-agregar"
						src="./img/agregar.png">
				</div>
			</div>
		</div>
		<table id="Lista" class="table no-shadow tabla-notas">
			<thead>
				<tr>
					<th scope="col" class="celda-legajo">legajo</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th style="width: 3em;">Elimnar</th>
				</tr>
			</thead>
			<%
				if (alumnos != null) {
			%>
			<tbody>
				<%
					for (Alumno a : alumnos) {
				%>
				<tr scope="row" id="legajo-<%=a.getLegajo()%>">
					<td style="width: 3em;" data-object="legajo"><%=a.getLegajo()%></td>
					<td><%=a.getNombre()%></td>
					<td><%=a.getApellido()%></td>

					<td style="width: 3em;" data-objet="eliminar"><div
							class="btn-icon-table"  data-toggle="tooltip"
								data-placement="top" title="eliminar">
							<img src="./img/eliminar.png" data-value="<%=a.getLegajo()%>"
								data-action="eliminar" data-toggle="modal"
								data-target="#ModalEliminar">
						</div></td>
				</tr>
				<%
					}
				%>
				<div class="modal fade" tabindex="-1" id="modalAgregarAlumnoCurso"
					role="dialog" aria-labelledby="modalAgregarCurso"
					aria-hidden="true" >
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Agregar alumno</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
							</div>
							<p id="boxMessage" class="hidden"></p>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="ModalEliminar" tabindex="-1"
					role="dialog" aria-labelledby="ModalEliminar" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Eliminacion
									 de  alumno de la lista</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p>Estas seguro que desea eliminar este alumno de la lista?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="btn-eliminar">Eliminar</button>
								<button type="button" class="btn btn-secondary"	data-dismiss="modal">Salir</button>
							</div>
						</div>
					</div>
				</div>
			</tbody>
			<%
				}
			%>
		</table>
	</div>
	</main>
	<script src="./js/cursoAlumno.js"></script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	<script>

		var objeto =new cursoAlumno(<%=idCurso%>);
	</script>
</body>
</html>