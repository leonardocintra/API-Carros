<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>To base64</title>
</head>
<body>
	Converter arquivo to Base64
	<form enctype="multipart/form-data" action="<%= request.getContextPath() %>/rest/carros/toBase64" method="POST" >
	    <input type="file" name="file" />
	    <br />
	    <input type="submit" value="Enviar arquivo" />
	</form>
</body>
</html>