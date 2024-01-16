<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.example.studentlessonservlet.util.DateUtil" %><%--
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
<% Lesson lesson = (Lesson) request.getAttribute("lesson"); %>

<form action="/updateLesson" method="post">
    <input type="hidden" value="<%=lesson.getId()%>" name="id">
    Name: <input type="text" value="<%=lesson.getName()%>" name="name"><br>
    Duration: <input type="time" value="<%=DateUtil.dateToString(lesson.getDuration())%>" name="duration"><br>
    Lecturer name: <input type="text" value="<%=lesson.getLecturerName()%>" name="lecturerName"><br>
    Price: <input type="number" step="0.01" value="<%=lesson.getPrice()%>" name="price"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>
