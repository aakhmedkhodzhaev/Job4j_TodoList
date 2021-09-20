package ru.job4j.todolist.persistence;

import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.Item;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Store {

    Serializable addTask(Item item, String [] cids);

    void updateTask(Item item);

    boolean delete(Item item);

    Item getById(Long id);

    List<Category> getAllCategory();

    Collection<Item> getStatusTask(Boolean status);

    Collection<Item> getAllTask();

}