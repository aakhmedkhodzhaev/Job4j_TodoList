package ru.job4j.todolist.persistence;

import jdk.nashorn.internal.runtime.options.Option;
import ru.job4j.todolist.model.User;

import java.util.Collection;

public interface Registration {

    void createUser(User user);

    void updateUser(User user);

    boolean dropUser(Long id);

    User getById(Long id);

    User getByData(final String email,
                   final String password);

    Collection<User> AllUser();
}