package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.manager.UserManager;
import org.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (userManager.getUserByEmail(email) != null) {
            req.getSession().setAttribute("msg", "User with email " + email + " already exists!");
            resp.sendRedirect("/register");
        } else {
            userManager.addUser(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build());

            req.getSession().setAttribute("msg", "User registered!");
            resp.sendRedirect("/register");
        }
    }
}
