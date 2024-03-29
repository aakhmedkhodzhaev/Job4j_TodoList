package ru.job4j.todolist.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistence.TodoStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AjaxController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Item> items = TodoStore.getInstance().getAllTask();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(items);
        resp.getWriter().write(json);
    }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String [] categories = req.getParameterValues("cIds");
        String description = req.getParameter("description");
        TodoStore.getInstance().addTask(new Item(Long.valueOf(id), description), categories);

        resp.sendRedirect(req.getContextPath()+"/ajax");
    }
}