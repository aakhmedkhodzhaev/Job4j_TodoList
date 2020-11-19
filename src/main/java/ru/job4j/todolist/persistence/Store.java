package ru.job4j.todolist.persistence;

import ru.job4j.todolist.model.Item;

import java.io.Serializable;
import java.util.Collection;

public interface Store {

    Serializable addTask(Item item);

    void updateTask(Item item);

    boolean delete(Item item);

    Item getById(Long id);

    Collection<Item> getStatusTask(Boolean status);

    Collection<Item> getAllTask();

}