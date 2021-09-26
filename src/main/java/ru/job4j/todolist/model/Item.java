package ru.job4j.todolist.model;

/**
 * @author Akhmedkhodzhaev A.A.
 * @version 1.0 19.11.2020
 * @task 2. Создать TODO list [3786#329659]
 * @aim Работа с динамической веб страницей
 * @others В index.html можно добавлять задачи, изменять статус
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Column(name = "created")
    private LocalDate created;

    @Column(name = "done")
    private Boolean done;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories = new ArrayList<>();


    public Item() {
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public static Item of(String description, User user) {
        Item item = new Item();
        item.description = description;
        item.user = user;
        return item;
    }

    public Item(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item(String description, LocalDate created, Boolean done) {
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item(Long id, User user, String description, LocalDate created, Boolean done) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDone(Boolean done) {
        this.done = done;
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
                "\"user\"" + ":" + "\"" + user + "\"," +
                "\"created\"" + ":" + "\"" + created + "\"," +
                "\"done\"" + ":" + "\"" + done + "\"" +
                "}";
    }
}