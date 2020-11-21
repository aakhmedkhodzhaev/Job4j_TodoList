package ru.job4j.todolist.model;

/**
 * @author Akhmedkhodzhaev A.A.
 * @version 1.0 19.11.2020
 * @task 2. Создать TODO list [3786#329659]
 * @aim Работа с динамической веб страницей
 * @others В index.html можно добавлять задачи, изменять статус
 */

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class Itemt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "done")
    private Boolean done;


    public Itemt() {
    }

    public static Itemt of(String description, User user) {
        Itemt item = new Itemt();
        item.description = description;
        item.user = user;
        return item;
    }

    public Itemt(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Itemt(String description, LocalDate created, Boolean done) {
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Itemt(Long id, String description, LocalDate created, Boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getCreated(), item.getCreated()) &&
                Objects.equals(getDone(), item.getDone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreated(), getDone());
    }

    public String toJsonString() {
        return "{" +
                "\"id\"" + ":" + id + "," +
                "\"description\"" + ":" + "\"" + description + "\"," +
                "\"created\"" + ":" + "\"" + created + "\"," +
                "\"done\"" + ":" + "\"" + done + "\"" +
                "}";
    }

    public String toJsonUserString() {
        return "{" +
                "\"id\"" + ":" + id + "," +
                "\"description\"" + ":" + "\"" + description + "\"," +
                "\"user\"" + ":" + "\"" + user + "\"," +
                "\"created\"" + ":" + "\"" + created + "\"," +
                "\"done\"" + ":" + "\"" + done + "\"" +
                "}";
    }

}