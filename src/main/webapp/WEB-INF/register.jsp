<%@ page import="org.example.studentlessonservlet.model.User" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 18.01.2024
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<% if (session.getAttribute("msg") != null) { %>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<% session.removeAttribute("msg"); %>
<% } %>
<form action="/register" method="post">
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="email" name="email"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Register"> | <a href="/">Login</a>
</form>
</body>
</html>
