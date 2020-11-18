package ru.job4j.todolist.persistence;

import ru.job4j.todolist.model.Item;

import java.util.Collection;

public interface Store {

    void addTask(Item item);

    void updateTask(String id, Boolean done);

    boolean delete(String id);

    Item getById(Long id);

    Collection<Item> getStatusTask(Boolean status);

    Collection<Item> getAllTask();

}