<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="org.example.studentlessonservlet.util.DateUtil" %>
<%@ page import="org.example.studentlessonservlet.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 12.01.2024
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>

Students | <a href="/addStudent">Add student</a>
<% if (students != null && !students.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Age</th>
        <th>User added</th>
        <th>Lesson name</th>
        <th>Lesson's lecturer name</th>
        <th>Delete</th>
    </tr>
    <% for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td>
            <% if (student.getPicName() != null) { %>
            <img src="/downloadImage?imageName=<%=student.getPicName()%>" width="50">
            <% } else { %>
            <span>No picture</span>
            <% } %>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getUser().getName() + " " + student.getUser().getSurname()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><%=student.getLesson().getLecturerName()%>
        </td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <% } %>
</table>
<% } %>
</body>
</html>
