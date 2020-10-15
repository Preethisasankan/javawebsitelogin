<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>



<h1> Login </h1>
<p class="error">${message}</p>
<form action="adminloginsubmit" method="post">
<p><label> Username</label>
<input type="email" name="userName"></p>
<p><label> Password</label>
<input type="password" name="userPassword"></p>
<input type="submit" name="login">
</form>
</body>
</html>