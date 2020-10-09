<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<%=request.getAttribute("message") %>
<form action="loginSubmit">
<label> Username</label>
<input type="text" name="userName">
<label> Password</label>
<input type="password" name="userPassword">
<input type="submit" name="login">
</form>
</body>
</html>