<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Models.Alumno"%>
<%@ page import="Models.Alumno"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.AlumnoDAO"%>
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
	if (request.getParameter("curso") == null) {
		response.sendRedirect("curso.jsp");
		return;
	}
	int curso = Integer.parseInt(request.getParameter("curso").toString());
	AlumnoDAO aDao = new AlumnoDAO();
	ArrayList<Alumno> alumnos = aDao.findAlumnoCursoNotas(curso);
%>
</head>
<body>
<jsp:include page="./Vista/header.jsp"></jsp:include> 
<jsp:include page="./Vista/aside.jsp"></jsp:include>
<main> 
	<div class="table-responsive col-lg-10 center">
		<table id="listaNota" class="table tabla-notas">
			<caption>
				<div class="row">
					<div class="col-xs-8 col-md-8 col-lg-8">
						<h2>
							Asignar notas - Curso:
							<%=curso%></h2>
					</div>
					<div class="col-xs-4 col-md-4 col-lg-4" style="text-align: right;">
						<button data-action="calificar" data-value="<%=curso %>" class="btn btn-success">Guardar</button>
					</div>
				</div>


			</caption>
			<thead class="thead-dark">
				<tr>
					<th scope="col">Legajo</th>
					<th scope="col">Alumno</th>
					<th scope="col" class="celda-nota">Primer Parcial</th>
					<th scope="col" class="celda-nota">Segundo Parcial</th>
					<th scope="col" class="celda-nota">Primer Recuperatorio</th>
					<th scope="col" class="celda-nota">Segundo Recuperatorio</th>
					<th scope="col">Estado</th>
				</tr>
			</thead>
			<tbody >
				<%
					if (alumnos != null) {
						for (Alumno a : alumnos) {
				%>
				<tr  scope="row" data-value="alumno-<%=a.getLegajo()%>">
				<td>
				<%=a.getLegajo()%>
				</td>
				<td>
					<%=a.getNombre()+ " " +a.getApellido()%>
				</td>
				<td class="celda-nota"><input type="number" min=0 max=10 required
					data-object="parcial-1"
					value="<%=a.getNotas().getPrimerParcial()%>"></td>
				<td class="celda-nota"><input type="number" min=0 max=10 required
					data-object="parcial-2"
					value="<%=a.getNotas().getSegundoParcial()%>"></td>
				<td class="celda-nota"><input type="number" min=0 max=10 required
					data-object="Recuperatio-1"
					value="<%=a.getNotas().getPrimerRecuperatorio()%>"></td>
				<td class="celda-nota"><input type="number" min=0 max=10 required
					data-object="Recuperatio-2"
					value="<%=a.getNotas().getPrimerRecuperatorio()%>"></td>
				<td><select name="estado" data-object="Estado">
						<option value="1" <%if (a.getNotas().getEstado() == 1) {%>
							selected <%}%>>libre</option>
						<option value="2" <%if (a.getNotas().getEstado() == 2) {%>
							selected <%}%>>regular</option>
				</select></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td colspan="6">
						<b> No se encontraron alumnos para este	curso </b>
					</td>
				</tr>
				<%
					}
				%>

			</tbody>
		</table>
		
		<div class="modal fade" id="ModalError" tabindex="-1" role="dialog"
		aria-labelledby="cursoDelete" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">ERROR</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
	</div>
	</main>
	<script src="./js/persona.js"></script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	
	<script>var objeto = new Persona("Alumnos");</script>
</body>
</html>