<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Manager</title>
</head>
<body>
<div align="center">
    <h2>User Manager</h2>
    <!--  <form method="get" action="search">
        <input type="text" name="keyword" /> &nbsp;
        <input type="submit" value="Search" />
    </form>-->
    <h3><a href="/new">New Customer</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listCustomer}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.username}</td>
            <td>
            <a href="${customer.id}" > Show</a>
            <c:if test="${customer.active}">
            <a href="update/${customer.id}" > Deactivate</a>
            </c:if>
            <c:if test="${customer.active ==false}">
            <a href="update/${customer.id}" > Activate</a>
            </c:if>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>