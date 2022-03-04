<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a href="/home">Back</a>
<form action="/register" method="post">
    Name: <input type="text" name="name" placeholder="name"/><br>
    Surname: <input type="text" name="surname" placeholder="surname"/><br>
    Email:<input type="email" name="email" placeholder="email"/><br>
    Password:<input type="password" name="password" placeholder="password"/><br>
    Phone<input type="text" name="phone" placeholder="phone"/><br>
    <input type="submit" value="Register">

</form>
</body>
</html>
