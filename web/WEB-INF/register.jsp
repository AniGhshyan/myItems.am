
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div >
     User register:<br>
    <form action="/userRegister" method="post" >
        <input type="text" name="name" placeholder="name" /><br>
        <input type="text" name="surname" placeholder="surname" /><br>
        <input type="email" name="email" placeholder="email" /><br>
        <input type="password" name="password" placeholder="password" /><br>
        <input type="submit" value="Register">
    </form>
</div>

</body>
</html>
