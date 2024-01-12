<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="org.example.studentlessonservlet.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 12.01.2024
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

Lessons | <a href="/addLesson">Add lesson</a>
<% if (!lessons.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Lecturer name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <% for (Lesson lesson : lessons) { %>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=DateUtil.dateToString(lesson.getDuration())%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>"><%=lesson.getName()%>
        </a></td>
    </tr>
    <% } %>
</table>
<% } %>
</body>
</html>
