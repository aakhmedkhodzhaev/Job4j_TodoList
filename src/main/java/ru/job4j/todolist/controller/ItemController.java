package ru.job4j.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.model.User;
import ru.job4j.todolist.persistence.TodoStore;
import ru.job4j.todolist.util.TodoUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        HttpSession session = req.getSession();
        User user = TodoUtils.getLoginedUser(session); // (User) session.getAttribute("loginedUser");
        Long userId = user.getId();
        req.setCharacterEncoding("UTF-8"); // Map<String, String[]> cat = req.getParameterMap();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if (bufferedReader != null) {
            json = bufferedReader.readLine();
        }

        String description = json.substring(16, json.indexOf(",")-1);
        String itemDescription = "{\"description\":" + "\"" + description+ "\"" + "}";

        String cIds = json.substring(json.indexOf("[")+2, json.indexOf("}")-2);
        String[] strArray = new String[] {cIds};

        String [] categories = strArray;

        ObjectMapper mapper = new ObjectMapper();
        Item item = mapper.readValue(itemDescription, Item.class);
        Long itemId = item.getId();
        resp.setContentType("application/json");
        if (itemId == null || itemId == 0) {
            item.setUser(User.of(userId));
            TodoStore.getInstance().addTask(item, categories);
        } else {
            Item result = TodoStore.getInstance().getById(itemId);
            TodoStore.getInstance().updateTask(result);
        }
        mapper.writeValue(resp.getOutputStream(), item);
    }
}