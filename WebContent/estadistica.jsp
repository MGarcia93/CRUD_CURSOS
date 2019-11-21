<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Models.Persona"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ModelsDao.AlumnoDAO"%>
<%@ page import="ModelsDao.CursoDAO"%>
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
	
%>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include>
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main>
        <div class="btn-list">
            <div class="btn-option">
                <a href="./estadisticaCurso.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/estadistica-curso.svg" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Analisis Curso</p>
                    </div>
                </a>
            </div>
            <div class="btn-option">
                <a href="./estadisticaDocente.jsp" class="btn-link" >
                    <div class="btn-icon">
                        <img src="./img/estadistica-docente.svg">
                    </div>
                    <div class="btn-text">
                        <p>Analisis Docentes</p>
                    </div>
                </a>
            </div>
        </div>
    </main>
	</main>
	</main>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
	
</body>
</html>