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
	int tipo=1;
%>
</head>
<body>
	<jsp:include page="./Vista/header.jsp"></jsp:include>
	<jsp:include page="./Vista/aside.jsp"></jsp:include>
	<main>
		<%if (tipo==0){
		%>
		<jsp:include page="./Vista/header.jsp"></jsp:include>
		<%} else {%>
<jsp:include page="./Vista/footer.jsp"></jsp:include>
		<%} %>
	</main>
	<jsp:include page="./Vista/footer.jsp"></jsp:include>
</body>
</html>