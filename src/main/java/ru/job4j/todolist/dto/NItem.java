package ru.job4j.todolist.dto;

import ru.job4j.todolist.model.Item;

public class NItem extends Item {

    private String[] cIds;

    public String[] getcIds() {
        return cIds;
    }

    public void setcIds(String[] cIds) {
        this.cIds = cIds;
    }
}