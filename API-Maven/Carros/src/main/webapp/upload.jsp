<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload de fotos</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/rest/carros" enctype="multipart/form-data" method="POST">
	    <input name="file" type="file">
	    <br />
	    <input type="submit" value="Enviar foto" />
	</form>
</body>
</html>