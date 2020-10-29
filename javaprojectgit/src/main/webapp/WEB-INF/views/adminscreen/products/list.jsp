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
<jsp:include page="../header.jsp"></jsp:include>  
<div align="center">
    <h2>Product Manager</h2>
    
   
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
         	<th>SKU</th>
         	<th>Price</th>
         	<th>Stock</th>
         	<th>Status</th>
        </tr>
        <c:forEach items="${listProducts}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
             <td>${product.sku}</td>
              <td>${product.price}</td>
              <td>${product.stock}</td>
              <td>${product.status}</td>
            <td>
            <a href="${product.id}" > Show</a>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>