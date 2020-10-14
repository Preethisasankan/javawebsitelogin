<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in with your account</title>
</head>
<body>
<span>${error}</span>
<form action="loginSubmit">
<h2 class="form-heading">Log in</h2>
<label> Username</label>
<input type="text" name="userName">
<label> Password</label>
<input type="password" name="userPassword">
<input type="submit" name="login">

</form>
</body>
</html>