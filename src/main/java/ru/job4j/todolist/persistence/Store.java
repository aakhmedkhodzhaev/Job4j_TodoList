package ru.job4j.todolist.persistence;

import ru.job4j.todolist.model.Item;

import java.util.List;

public interface Store {

    void addTask(Item item);

    void updateTask(String id, Item item);

    Item getById(Long id);

    List<Item> getAllTask();

}