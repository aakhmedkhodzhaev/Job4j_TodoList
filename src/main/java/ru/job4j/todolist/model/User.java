package ru.job4j.todolist.model;

/**
 * @author Akhmedkhodzhaev A.A.
 * @version 1.0 19.11.2020
 * @task 2. Создать TODO list [3786#329659]
 * @aim Работа с динамической веб страницей
 * @others В index.html можно добавлять задачи, изменять статус
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "todo_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User() {
        super();
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static User of(Long id) {
        User user = new User();
        user.id = id;
        return user;
    }

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public User(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getEmail(), getName(), getPassword());
    }

    public String toJsonUserString() {
        return "{" +
                "\"id\"" + ":" + id + "," +
                "\"email\"" + ":" + "\"" + email + "\"," +
                "\"name\"" + ":" + "\"" + name + "\"," +
                "\"password\"" + ":" + "\"" + password + "\"" +
                "}";
    }

}