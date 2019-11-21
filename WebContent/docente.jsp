<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Models.Persona"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.PersonaDAO"%>
<%@ page import="Models.Localidad" %>
<%@ page import="Models.Provincia" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Administracion Curso</title><jsp:include page="./Vista/head.jsp"></jsp:include>
<%
	if(session ==null || session.getAttribute("User")==null) {
		response.sendRedirect("login.jsp");
		return;
	}

	PersonaDAO pDAO=new PersonaDAO("docentes");
	ArrayList<Persona> docentes=pDAO.FindPersonas("", "");

	%>
</head>
<body>
<jsp:include page="./Vista/header.jsp"></jsp:include>
<jsp:include page="./Vista/aside.jsp"></jsp:include>
<main>
<div class="table-responsive col-lg-12 center">
		<div class="row">
			<div class="col-xs-8 col-md-8 col-lg-8">
				<h2>Profesores</h2>
			</div>
			<div class="col-xs-4 col-md-4 col-lg-4" style="text-align: right;">
					<div class="btn-icon">
						<img data-action="agregar" data-toggle="modal" data-target="#modalAgregarPersona" data-toggle="tooltip"
							data-placement="top" title="Agregar" class="btn-agregar" src="./img/agregar.png">
					</div>
			</div>
		</div>
		<table id="ListaDocentes" class="display" data-page-length='15'>
			<thead>
				<tr>
					<th style="width:3em;">Legajo</th>
					<th >Nombre</th>
					<th >Apellido</th>
					<th style="width:3em;">Fecha Nac</th>
					<th style="width:8em;">Email</th>
					<th >Telefono</th>
					<th >Direccion</th>
					<th >Localidad</th>
					<th >Provincia</th>
					<th style="width:3em;">Editar</th>
					<th style="width:3em;">Modificar</th>
				</tr>
			</thead>
			<%
				if (docentes != null) {
			%>
			<tbody>
				<%
					for (Persona d : docentes) {
				%>
				<tr data-value="persona-<%=d.getLegajo()%>">
				<td> <%=d.getLegajo() %></td>
				<td data-object="nombre"> <%=d.getNombre() %></td>
				<td data-object="apellido"> <%=d.getApellido() %></td>
				<td data-object="fechaNac"> <%=d.getFechaNac() %></td>
				<td data-object="email"> <%=d.getEmail() %></td>
				<td data-object="telefono"> <%=d.getTelefono() %></td>
				<td data-object="direccion"> <%=d.getDireccion() %></td>
				<td data-object="localidad" data-value="<%=d.getLocalidad().getId_localidad()%>"> <%=d.getLocalidad().getNombre() %></td>
				<td  data-object="provincia" data-value="<%=d.getProvincia().getId_provincia()%>"> <%=d.getProvincia().getNombre() %></td>
				<td style="width: 3em;" data-objet="modificar"><div
							class="btn-icon-table">
							<img src="./img/editar.png" data-action="modificar"
								data-value="<%=d.getLegajo()%>" data-toggle="modal"
								data-target="#modalAgregarPersona" data-toggle="tooltip"
								data-placement="top" title="modificar">
						</div></td>
					<td style="width: 3em;" data-objet="eliminar"><div
							class="btn-icon-table">
							<img src="./img/eliminar.png" data-value="<%=d.getLegajo()%>" 
								data-action="eliminar" data-toggle="modal"
								data-target="#ModalEliminar" data-toggle="tooltip"
								data-placement="top" title="eliminar">
						</div></td>
				
				</tr>
				<%} %>
			</tbody>
			
			<%} %>
			</table>
			</div>
			  <div class="modal fade" tabindex="-1" id="modalAgregarPersona" role="dialog" aria-labelledby="modalAgregarPersona"
        aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Agregar</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="get" action="">
                        <div class="form-group group-inline">
                            <label for="txtNombre" class=" col-md-3">Nombre</label>
                            <input id="txtNombre" class="form-control  col-md-8" type="text" name="txtNombre">
                        </div>
                        <div class="form-group group-inline">
                            <label for="txtApellido" class=" col-md-3">Apellido</label>
                            <input id="txtApellido" class="form-control  col-md-8" type="text" name="txtApellido">
                        </div>
                        <div class="form-group group-inline">
                            <label for="txtFechaNac" class=" col-md-3">Fecha Nacimiento</label>
                            <input id="txtFechaNac" class="form-control  col-md-8" type="date" name="txtFechaNac" max="2003/01/01">
                        </div>
                        <div class="form-group group-inline">
                            <label for="txtEmail" class=" col-md-3">Email</label>
                            <input id="txtEmail" class="form-control  col-md-8" type="mail" name="txtEmail">
                        </div>
                        <div class="form-group group-inline">
                            <label for="txtTelefono" class=" col-md-3">Telefono</label>
                            <input id="txtTelefono" class="form-control  col-md-8" type="text" name="txtTelefono">
                        </div>
                        <div class="form-group group-inline">
                            <label for="txtDireccion" class=" col-md-3">Direccion</label>
                            <input id="txtDireccion" class="form-control  col-md-8" type="text" name="txtDireccion">
                        </div>
                         <div class="form-group group-inline">
                            <label for="provincia" class=" col-md-3">Provincia</label>
                            <select id="selectProvincia"class="form-control  col-md-8" name="provincia"></select>
                        </div>
                        <div class="form-group group-inline">
                            <label for="localidad" class=" col-md-3">Localidad</label>
                            <select id="selectLocalidad" class="form-control  col-md-8" name="Localidad">
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
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
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
			table = $('#ListaDocentes').DataTable({
				"autoWidth" : false,
				"dom" : '<"top"f>t<"bottom"p><"clear">',
			});
		});
		var objeto= new Persona("Docentes");
	</script>
</body>
</html>