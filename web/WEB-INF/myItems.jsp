<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/28/2022
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Item> items = (List<Item>) request.getAttribute("items");
    List<Picture> pictures= (List<Picture>) request.getAttribute("pictures");
%>
<div style="margin:0 auto;width: 1000px;height: 1000px;border: 1px solid black">
    <div>
        Welcome <%=user.getName()%> | <a href="/myItems">Իմ հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a>
    </div>
    <div>
        <%if (items != null) {%>
        <%for (Item item : items) {%>
        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="width: 105px;float: left">
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
                    <span><%=item.getTitle()%> | <%=item.getPrice()%></span>
                </div>
            </div>
        </a>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
