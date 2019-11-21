<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.DocenteDAO"%>
<%@ page import="ModelsDao.MateriaDAO"%>
<%@ page import="Controllers.EstadisticaController"%>
<%@ page import="Models.EstadisticaDocente"%>
<%@ page import="Models.Persona"%>
<%@ page import="Models.Materia"%>
<%@ page import="java.util.Collections" %>
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
	EstadisticaController controller= new EstadisticaController();
	ArrayList<EstadisticaDocente> docente = controller.EstaditicaDocente(request);
	ArrayList<Integer> anios=controller.aniosExisteCurso();  
	ArrayList<Materia> materias = new MateriaDAO().FindMaterias();
%>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include> 
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main> 
	<h1>Estadisticas Sobre Docentes</h1>
		<div>
            <form action="estadisticaDocente.jsp" method="GET" class="col-md-12">
                <fieldset class="col-md-10 center">
                    <legend>Filtros</legend>
                    <div class="row">
                        <div class="col-lg-4 col-md-6">
                            <label for="desde" class="col-lg-4">Desde:</label>
                            <select name="desde" id="desde" class="col-lg-7">
                            <option value="">Sin Seleccionar</option>
                            <%for(int anio:anios){ %>
                                <option value="<%=anio %>" <%if(request.getParameter("desde")!=null && request.getParameter("desde").equals(Integer.toString(anio))){%> selected<%} %>><%=anio %>					
                                </option>
                                <%} %>
                            </select>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <label for="hasta" class="col-lg-4">Hasta:</label>
                           <select name="hasta" id="hasta" class="col-lg-7">
                                                   <option value="">Sin Seleccionar</option>
                            <%Collections.sort(anios,Collections.reverseOrder());
                            for(int anio:anios){ %>
    
                                <option value="<%=anio %>" <%if(request.getParameter("hasta")!=null && request.getParameter("hasta").equals(Integer.toString(anio))){%> selected<%} %>><%=anio %>					
                                </option>
                                <%} %>
                            </select>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <label for="materia" class="col-lg-4">Materia:</label>
                            <select name="materia" id="materia" class="col-lg-7">
                            <option value="">Sin Seleccionar</option>
                            <%for(Materia m:materias){ %>
                                <option value="<%=m.getIdMateria()%>" <%if(request.getParameter("materia")!=null && request.getParameter("materia").equals(Integer.toString(m.getIdMateria()))){%> selected<%} %>><%=m.getDescripcion() %> </option>
                                <%} %>
                            </select>
                        </div>
                        <div class="col-2 center" style="text-align: right;">
                            <button type="submit" class="btn btn-block btn-primary">Filtrar</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>


        <div class="table-responsive col-lg-10 center">
            <table class="table tabla-notas display" id="tabla-estadistica" data-page-length='25'>

                <thead>
                    <tr>
                        <th scope="col">Legajo</th>
                        <th scope="col">Docente</th>
                        <th scope="col">Cant Curso</th>
                        <th scope="col">Promedio Alumno curso</th>
                        <th scope="col">Proemedio Nota</th>
                        <th scope="col">% Aprobados</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	if (docente != null) {
						for (EstadisticaDocente ed : docente) {
					%>
                    <tr scope="row">
                    <td><%=ed.getLegajo() %> </td>
                    <td><%=ed.getNombre() + " "+ ed.getApellido()%> </td>
                    <td data-toggle="tooltip" data-placement="top"	title="Ver Cursos asignado">
                    	<p class="cursosAsignados" data-value="<%=ed.getLegajo()%>" data-action="listar" data-toggle="modal" data-target="#modalVerCursos">
                    	<%=ed.getCantidadCursos()%> </p> 
                    </td>
                    <td><%=ed.getPromedioAlumno()%> </td>
                    <td><%=ed.getPromedioNota()%> </td>
                    <td><%=ed.getPorncentaAprobado()%> </td>
                    </tr>
				<%}} %>
                </tbody>
            </table>


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
	</main>
	
	<script src="./js/persona.js"></script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	
	<script>
	$(document).ready(function() {
		table = $('#tabla-estadistica').DataTable({
			"autoWidth" : false,
			"dom" : 't<"bottom" p><"clear">',
		}).columns.adjust().draw();
		
	});
	var objeto = new Persona("Docentes");
		</script>
</body>
</html>