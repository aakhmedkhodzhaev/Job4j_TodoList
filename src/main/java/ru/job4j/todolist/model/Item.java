package ru.job4j.todolist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Description")
    private String description;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "done")
    private Boolean done;

    public Item() {
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

    public Item(Long id, String description, LocalDate created, Boolean done) {
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
}