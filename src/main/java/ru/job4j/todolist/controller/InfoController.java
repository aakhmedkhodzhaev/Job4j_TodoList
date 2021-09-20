package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.User;
import ru.job4j.todolist.persistence.TodoStore;
import ru.job4j.todolist.util.TodoUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class InfoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = TodoUtils.getLoginedUser(session);
        List<Category> categories = TodoStore.getInstance().getAllCategory();
        req.setAttribute("user", user);
        req.setAttribute("allCategories", categories);
        req.getRequestDispatcher("info.jsp").forward(req, resp);
    }

}