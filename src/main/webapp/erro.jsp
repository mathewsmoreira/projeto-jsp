<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projeto JSP</title>
</head>
<body>
	<h1>Opps ocorreu um erro, por favor contate a equipe de suporte!!!</h1>
	<%
	out.print(request.getAttribute("msg"));
	%>

</body>
</html>