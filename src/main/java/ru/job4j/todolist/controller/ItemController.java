package ru.job4j.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistence.TodoStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Item> items;
        String status = req.getParameter("status");
        if (status.equals("true")) {
            items = TodoStore.getInstance().getStatusTask(true);
        } else if (status.equals("false")) {
            items = TodoStore.getInstance().getStatusTask(false);
        } else {
            items = TodoStore.getInstance().getAllTask();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int lengthItems = items.size();
        for (int i = 0; i < lengthItems; i++) {
            stringBuilder.append(((ArrayList<Item>) items).get(i).toJsonString());
            if (i == lengthItems - 1) {
                break;
            }
            stringBuilder.append(",");
        }
        stringBuilder.append("]");

        PrintWriter writer = resp.getWriter();
        writer.println(stringBuilder);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if (bufferedReader != null) {
            json = bufferedReader.readLine();
        }

        ObjectMapper mapper = new ObjectMapper();
        Item item = mapper.readValue(json, Item.class);
        Long itemId = item.getId();
        resp.setContentType("application/json");
     if (itemId == null || itemId == 0) {
            TodoStore.getInstance().addTask(item);
        } else {
         Item result = TodoStore.getInstance().getById(itemId);
         TodoStore.getInstance().updateTask(result);
        }
        item.getDone();
        item.getDone();
        mapper.writeValue(resp.getOutputStream(), item);
    }
}