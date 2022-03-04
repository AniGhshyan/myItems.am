<%@ page import="model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/28/2022
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");
%>
<a href="/home">Back</a>
<form action="/addItem" method="post" enctype="multipart/form-data">
    title:<input type="text" name="title"/> <br>
    description:<textarea name="description "></textarea><br>
    price:<input type="number" name="price"/><br>
    <select name="category_id">
        <%
            for (Category category : categoryList) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }%>
    </select>
    pictures:<input type="file"   name="picture" multiple><br>
    <input type="submit" value="register">


</form>
</body>
</html>
