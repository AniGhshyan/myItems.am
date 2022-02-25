<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myItems.am</title>
    <style>
        table, th, td {
            border: 1px solid black;
            background-color: #f5f2f2
        }
    </style>
</head>
<body>
<% List<Category> categories = (List<Category>) request.getAttribute("categories");
// List<Item> items = (List<Item>) request.getAttribute("items");
%>
<a href="/userRegister">Register</a><br>
<a href="/login"> Login</a>
<div>
    <table >
        <tr>
        <% for (Category category : categories) {%>

            <td>
              <a  href="/category">  <%=category.getName()%></a>
            </td>
        <% }
        %>
        </tr>

    </table>
</div>
<%--<div>--%>
<%--    <table >--%>
<%--        <tr>--%>
<%--        <% for (Category category : categories) {%>--%>

<%--            <td>--%>
<%--                <option value="<%=category.getId()%>"><a href="/category">  <%=category.getName()%></a>--%>
<%--                </option>--%>
<%--            </td>--%>
<%--        <% }--%>
<%--        %>--%>
<%--        </tr>--%>

<%--    </table>--%>
<%--</div>--%>

<%--<div >--%>
<%--    <%--%>
<%--        for (Item item : items) {--%>
<%--            if (item.getPictureUrl() != null) {%>--%>
<%--    <img src="/image?path=<%=item.getPictureUrl()%>" width="150"/>--%>
<%--    <%--%>
<%--        }%>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</div>--%>

</body>
</html>