<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <aside>
        <a href="./curso.jsp"><i class="icon icon-course"></i></a>
		<a href="./alumno.jsp"><i class="icon icon-student"></i></a>
        <%	String tipo = (String) session.getAttribute("Tipo").toString();
        if (tipo.equals("0")) { %>
        <a href="./docente.jsp"><i class="icon icon-teacher" ><</i></a>
        <a href="./estadistica.jsp"><i class="icon icon-stadistics"></i></a>
        <%} %>


        <i class="icon icon-logout data-action="logout"></i>
    </aside>