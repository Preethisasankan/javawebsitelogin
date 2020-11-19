<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include> 
  <h1>Add Products</h1>

  <form:form action="save" method="post" modelAttribute="products">
  <form:hidden path="id"/>
    <table>
      <tr>
        <td>Product Name</td>
        <td><form:input path="name"  /></td>
      </tr>
      <tr>
        <td>SKU</td>
        <td><form:input path="sku" /></td>
      </tr>
      <tr>
        <td>Price</td>
        <td><form:input path="price" /></td>
      </tr>
      <tr>
        <td>Category</td>
        <td>
        <form:select path="category">
        <c:forEach items="${listCategory}" var="category">
        <form:option value="${category.id }">${category.name}</form:option>
        </c:forEach>
        </form:select>
      </tr>
      <tr>
        <td>Details</td>
        <td><form:textarea path="description" /></td>
      </tr>
      <tr>
        <td>Stock</td>
        <td><form:input path="stock" /></td>
      </tr>
            <tr>
        <td>Image</td>
        <td><form:input path="image" /></td>
      </tr>
      <tr>
        <td>Status</td>
        <td><form:radiobutton path="status" value="true" />Show</td>
        <td><form:radiobutton path="status" value="false" />Hide</td>
      </tr>
      <tr>
        <td><button type="submit">Save</button></td>
      </tr>
    </table>
  </form:form>

  <span style="color: red;">${message}</span>
</body>
</html>