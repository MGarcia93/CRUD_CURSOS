<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="./Vista/head.jsp"></jsp:include>
<%
	if(session ==null || session.getAttribute("User")==null) {
		response.sendRedirect("login.jsp");
		return;
	}else if((session.getAttribute("Tipo").toString())=="1"){
		response.sendRedirect("listaAlumnos.jsp");
		return;
	}
	%>
</head>
<body>



<jsp:include page="./Vista/header.jsp"></jsp:include>
<jsp:include page="./Vista/aside.jsp"></jsp:include>
<main>
<h2 class="menu-titulo">Alumnos</h2>
        <div class="btn-list">
            <div class="btn-option">
                <a href="./ListaAlumnos.jsp" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/Listar.png" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Listar</p>
                    </div>
                </a>
            </div>
            <div class="btn-option">
                <a href="" class="btn-link" data-action="modal" alt="" data-toggle="modal"
                    data-target="#agregarPersona">
                    <div class="btn-icon">
                        <img src="./img/agregar.png">
                    </div>
                    <div class="btn-text">
                        <p>Agregar</p>
                    </div>
                </a>
            </div>
            <div class="btn-option">
                <a href="" class="btn-link">
                    <div class="btn-icon">
                        <img src="./img/editar.png" alt="">
                    </div>
                    <div class="btn-text">
                        <p>Editar</p>
                    </div>
                </a>
            </div>
        </div>
    </main>
    <jsp:include page="./Vista/footer.jsp"></jsp:include>
</body>
</html>