package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Date duration = DateUtil.stringToDate(req.getParameter("duration"));
        String lecturerName = req.getParameter("lecturerName");
        double price = Double.parseDouble(req.getParameter("price"));
        lessonManager.addLesson(Lesson.builder()
                .name(name)
                .duration(duration)
                .lecturerName(lecturerName)
                .price(price)
                .build());
        resp.sendRedirect("/lessons");
    }
}
