package ru.job4j.todolist.model;

/**
 * @author Akhmedkhodzhaev A.A.
 * @version 1.0 19.11.2020
 * @task 3. Категории в TODO List [#331991]
 * @aim Работа с динамической веб страницей
 * @others Добавить модель данных Category, которая будет отображать категорию задания
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "todo_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}