<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Base64</title>
</head>
<body>
	<form enctype="application/x-www-form-urlencoded" action="<%= request.getContextPath() %>/rest/carros/postFotoBase64" method="POST">
	Nome do arquivo:
	<input name="fileName" type="text" />
	<br />
	Base64:
	<textarea name="base64" type="textarea" rows="10" cols="60"></textarea>
	<input type="submit" value="Enviar arquivo" />
	</form>
</body>
</html>