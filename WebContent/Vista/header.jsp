<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<header>
            <h1> CRUD CURSOS</h1>
            <% if(session ==null || session.getAttribute("User")!=null) {
         %>
            <span class="Usuaraio">Usuario: <%= session.getAttribute("User") %></span>
            <%} %>
    </header>