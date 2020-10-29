<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include> 
<div align="center">
    <h2>Shop</h2>
    
   
    <table border="1" cellpadding="5">
        <tr>
            <th>Image</th>
            <th>Name</th>
         	<th>SKU</th>
         	<th>Price</th>
         	<th>Stock</th>
         	<th></th>
        </tr>
        <c:forEach items="${listProducts}" var="product">
        <c:if test="${product.status}">
        <tr>
            <td><img src="${product.image}" width="100" height="100"/></td>
            <td>${product.name}</td>
             <td>${product.sku}</td>
              <td>${product.price}</td>
              <td>${product.stock}</td>
            <td>
            <a href="${product.id}" > Add to cart</a>
        </tr>
        </c:if>
        </c:forEach>
    </table>
</div>   
</body>
</html>