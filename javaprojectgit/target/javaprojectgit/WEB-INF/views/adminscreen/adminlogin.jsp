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

  <h1>User Login</h1>

  <form:form action="adminloginsubmit" method="post" modelAttribute="user">
    <table>
      <tr>
        <td>Email</td>
        <td><form:input path="Username" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><form:password path="password" /></td>
      </tr>
      <tr>
        <td><button type="submit">Login</button></td>
      </tr>
    </table>
  </form:form>

  <span style="color: red;">${message}</span>
</body>
</html>