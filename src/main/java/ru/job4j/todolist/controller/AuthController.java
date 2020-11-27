package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.User;
import ru.job4j.todolist.persistence.TodoRegistration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = TodoRegistration.getInstance().getByData(email, password);
        if(user != null){
            HttpSession sc = req.getSession();
            user.setId(user.getId());
            user.setEmail(user.getEmail());
            user.setName(user.getName());
            user.setPassword(user.getPassword());
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "info.jsp");
        } else {
            req.setAttribute("error", "Не верный email или пероль");
            req.getRequestDispatcher("req.jsp").forward(req, resp);
        }
    }
}