<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="./Vista/head.jsp"></jsp:include>

</head>
<body>
<jsp:include page="./Vista/header.jsp"></jsp:include>


<main class="login">
	<div class="panel-login">
            <div class="panel-header">
                <h2>Entrada al sistema</h2>
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <i class="fas fa-user"></i>
                    <input id="txtUsuario" class="form-control" type="text" name="txtUsuario" placeholder="Usuario">
                </div>
                <div class="form-group">
                    <i class="fas fa-asterisk"></i>
                    <input id="txtPassword" class="form-control" type="password" name="txtPassword" placeholder="Password">
                </div>
                <p id="boxMessage" class="hidden">
                </p>
                
                <button id="ingresar" class="btn btn-primary btn-lg btn-block"><i class="fas fa-unlock-alt" ></i> Ingresar </button>
            </div>
        </div>
        <script>
            
        </script>

</main>
<jsp:include page="./Vista/footer.jsp"></jsp:include>
</body>
</html>