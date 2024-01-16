package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.manager.StudentManager;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class AddStudentServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "C:\\Users\\sky\\IdeaProjects\\Java2023\\ee\\student-lesson-servlet\\uploadDirectory";
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Lesson lesson = lessonManager.getById(Integer.parseInt(req.getParameter("lessonId")));

        Part picture = req.getPart("picture");
        String picName = null;
        if (picture != null && picture.getSize() > 0) {
            picName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + picName);
        }

        studentManager.addStudent(Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .lesson(lesson)
                .picName(picName)
                .build());

        resp.sendRedirect("/students");
    }
}
