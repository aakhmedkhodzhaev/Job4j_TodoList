package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistence.TodoStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ItemController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Item> items = TodoStore.getInstance().getAllTask();
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
}