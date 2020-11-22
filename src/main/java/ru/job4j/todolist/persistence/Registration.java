package ru.job4j.todolist.persistence;

import ru.job4j.todolist.model.User;

import java.util.Collection;

public interface Registration {

    void save(User user);

    boolean dropUser(Long id);

    boolean findByEmail(String email);

    User getById(Long id);

    User getByData(final String email,
                   final String password);

    Collection<User> AllUser();

}