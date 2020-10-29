<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include> 
<div class="container">
  <h1>User Detail</h1>
	<br />
	<a href="/javaprojectgit/user/">Back</a>
		<p><label>ID:</label>
		<span>${user.id}</span></p>
	
		<p><label>Username:</label>
		<span>${user.username}</span></p>
		<p><label>Name:</label>
		<span>${user.name}</span></p>
		
</div>
</body>
</html>