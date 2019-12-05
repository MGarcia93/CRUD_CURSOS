<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.DocenteDAO"%>
<%@ page import="ModelsDao.MateriaDAO"%>
<%@ page import="Controllers.EstadisticaController"%>
<%@ page import="Models.Curso"%>
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
	ArrayList<Curso> cursos = controller.PromedioCurso(request);
	ArrayList<Integer> anios=controller.aniosExisteCurso();  
	ArrayList<Persona> docentes= new DocenteDAO().FindPersonas("", "");
	ArrayList<Materia> materias = new MateriaDAO().FindMaterias();
%>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include> 
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main> 
	<h1>Estadisticas Sobre Curso</h1>
		<div>
            <form  onsubmit="SendParameters(this,event)" method="GET" class="col-md-12">
                <fieldset class="col-md-10 center">
                    <legend>Filtros</legend>
                    <div class="row">
                        <div class="col-lg-3 col-md-6">
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
                        <div class="col-lg-3 col-md-6">
                            <label for="docente" class="col-lg-4">Docente:</label>
                            <select name="docente" id="docente" class="col-lg-7">
                            <option value="">Sin Seleccionar</option>
                            <%for(Persona d: docentes){ %>
                                <option value="<%=d.getLegajo()%>" <%if(request.getParameter("docente")!=null && request.getParameter("docente").equals(Integer.toString(d.getLegajo()))){%> selected<%} %>> <%= d.getNombre() + " " +d.getApellido() %></option>
                                <%} %>
                            </select>
                        </div>
                        <div class="col-2 center" style="text-align: right;">
                            <button type="submit"  class="btn btn-block btn-primary">Filtrar</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>


        <div class="table-responsive col-lg-10 center">
            <table class="table tabla-notas display" id="tabla-estadistica" data-page-length='25'>

                <thead>
                    <tr>
                        <th scope="col">Curso</th>
                        <th scope="col">Materia</th>
                        <th scope="col"> A&ntilde;o</th>
                        <th scope="col">Semestre</th>
                        <th scope="col">Docente</th>
                        <th scope="col">% Aprobados</th>
                        <th scope="col">Promedio</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	if (cursos != null) {
						for (Curso c : cursos) {
					%>
                    <tr scope="row">
                    <td><%=c.getId() %> </td>
                    <td><%=c.getMateria().getDescripcion()%> </td>
                    <td><%=c.getAnio()%> </td>
                    <td><%=c.getSemestre()%> </td>
                    <td><%=c.getDocente().getNombre() + " "+ c.getDocente().getApellido()%> </td>
                    <td><%=c.getPorcentajeAprobados()%> </td>
                    <td><%=String.format("%.2f",c.getPromedio())%> </td>
                    </tr>
				<%}} %>
                </tbody>
            </table>


        </div>
	</main>
	<script>
		function SendParameters(_this,event){
			event.preventDefault();
			let param="";
			if (_this.elements['desde'].value!=""){
				param+="?esde="+_this.elements['desde'].value;
			}
			if(_this.elements["hasta"].value!=""){
				param+=(param!=""?"&":"?")+"hasta="+_this.elements['hasta'].value;
			}
			if(_this.elements["docente"].value!=""){
				param+=(param!=""?"&":"?")+"docente="+_this.elements['docente'].value;
			}
			location.href=location.origin+location.pathname+param;
			
		}
	</script>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	
	<script>
	$(document).ready(function() {
		table = $('#tabla-estadistica').DataTable({
			"autoWidth" : false,
			"dom" : 't<"bottom" p><"clear">',
		}).columns.adjust().draw();
		
	});
		</script>
</body>
</html>