<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administracion Curso</title>
<jsp:include page="./Vista/head.jsp"></jsp:include>
<%
	if(session ==null || session.getAttribute("User")==null) {
		response.sendRedirect("login.jsp");
		return;
	}
String tipo=session.getAttribute("Tipo").toString();
	%>
</head>
<body>
<jsp:include page="./Vista/header.jsp"></jsp:include>
    <main class="menu">
        <div class="btn-list">
            <div class="btn-option">
                <a href="./curso.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/curso-color.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Cursos</p>
                    </div>

                </a>
            </div>
            <div class="btn-option">
                <a href="./alumno.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/alumno-color.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Alumnos</p>
                    </div>

                </a>
            </div>
           <%if (tipo.equals("0")) {%>
            <div class="btn-option">
                <a href="./docente.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/profesor-color.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Docentes</p>
                    </div>

                </a>
            </div>
            <div class="btn-option">
                <a href="./estadistica.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/estadistica-color.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Estadistica</p>
                    </div>

                </a>
            </div>
            <%} %>
            
            
            <div class="btn-option" >
                <a href="" class="btn-link" id="logout" data-action="logout">
                    <div class="btn-icon">
                        <img src="./img/logout-color.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Salir</p>
                    </div>

                </a>
            </div>
        </div>
    </main>
<jsp:include page="./Vista/footer.jsp"></jsp:include>
</body>
</html>