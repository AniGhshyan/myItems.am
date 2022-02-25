<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/24/2022
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<% List<Item> allItems = (List<Item>) request.getAttribute("items");
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");
    User user = (User) session.getAttribute("user");%>
Welcome <%=user.getName()%>
<div>
    Add Item:<br>
    <form action="/addItem" method="post" enctype="multipart/form-data"/>
    <input type="text" name="title" placeholder="title"/><br>
    <input type="text" name="price" placeholder="price"/><br>
    <input type="hidden" name="userId" value="<%=user.getId()%>"/>
    <input type="file" name="image"/> ><br>

    <select name="category_id">
        <%
            for (Category category : categoryList) {%>
        <option value="<%=category.getId()%>">
            <%=category.getName()%>
        </option>
        <%
            }
        %>
    </select><br>
    <input type="submit" value="Add Item">
    </form>
</div>
<div>
    <%
        for (Item item : allItems) {
            if (item.getPictureUrl() != null) {%>
    <img  src="/image?path=<%=item.getPictureUrl()%>" width="150"/>
    <%
        }%>
    <%
    }
%>
</div>
</body>
</html>
