<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 12.01.2024
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
<form action="/addLesson" method="post">
    Name: <input type="text" name="name"><br>
    Duration: <input type="time" name="duration"><br>
    Lecturer name: <input type="text" name="lecturerName"><br>
    Price: <input type="number" step="0.01" name="price"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>
