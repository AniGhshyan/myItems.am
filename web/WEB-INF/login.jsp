<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/24/2022
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/logout">logout</a><br>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="Input your email"><br>
    <input type="password" name="password" placeholder="Input your password"><br>
    <input type="submit" value="ok">
</form>

</body>
</html>
