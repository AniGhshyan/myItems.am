<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/24/2022
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% Item item = (Item) request.getAttribute("item");
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");
    List<Picture> pictures = (List<Picture>) request.getAttribute("pictures");
    User user = (User) session.getAttribute("user");%>
<div style="margin: 0 auto;width: 1000px ;height: 1000px;border: 1px solid black">
    <%if (user == null) {%>
    <div style="float: right"><a href="/login">Լեգին</a> | <a href="/register">Գրանցում</a></div>
    <%} else {%>
    <div>Welcome<%=user.getName()%> | <a href="/myItems">Իմ Հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a></div>
    <%}%>
    <div>
        <ul style="overflow: hidden">
            <li style="display: inline;margin-left: 40px"><a href="/home">Գլխավոր</a></li>
            <%for (Category category : categoryList) {%>
            <li style="display: inline;margin-left: 40px"><a
                    href="/home?category_id=<%=category.getId()%><%=category.getName()%>"></a></li>
            <%}%>
        </ul>
    </div>
    <div>
        <div style="width: 500px;float: left">
            <div>

                <%
                    for (Picture picture : pictures) {%>
                <%if (picture.getPictureUrl() != null && !picture.getPictureUrl().equals("")) {%>
                <img src="/getImage?picture_url=<%=picture.getPictureUrl()%>" width="100" height="100"/>
                <%} else {%>
                <img src="/img/img.png" width="100" height="100"/>
                <%
                        }
                    }
                %>
            </div>

            <div>
                <span><%=item.getTitle()%> | <%=item.getPrice()%> </span>
            </div>
        </div>
        <span><%=item.getDescription()%></span>
        <div>
            User
            Info: <%=item.getUser().getName()%> <%=item.getUser().getSurname()%> <%=item.getUser().getPhone()%>

        </div>
    </div>
</div>

</div>

</body>
</html>
