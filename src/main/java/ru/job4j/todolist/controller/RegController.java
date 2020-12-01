package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.User;
import ru.job4j.todolist.persistence.TodoRegistration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        if (user.getName() != null) {
            req.setAttribute("email", TodoRegistration.getInstance().findByEmail(user.getName()));
        }
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email"),
                name = req.getParameter("name"),
                password = req.getParameter("password");

        req.setCharacterEncoding("UTF-8");
        if (TodoRegistration.getInstance().findByEmail(email) == null
                && name != "" && password != ""
                && email.length() > 5 && name.length() > 3 && password.length() > 0) {
            TodoRegistration.getInstance().save(
                    new User(
                            email,
                            name,
                            password
                    )
            );
            resp.sendRedirect(req.getContextPath() + "/auth.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/reg.do");
        }
    }

}