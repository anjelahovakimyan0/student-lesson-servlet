<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 12.01.2024
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<form action="/addStudent" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="email" name="email"><br>
    Age: <input type="number" name="age"><br>
    <select name="lessonId">
        <% for (Lesson lesson : lessons) { %>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <% } %>
    </select><br>
    <input type="file" name="picture"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>
