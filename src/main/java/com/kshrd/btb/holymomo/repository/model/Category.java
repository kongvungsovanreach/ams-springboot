package com.kshrd.btb.holymomo.repository.model;

import javax.validation.constraints.NotEmpty;

public class Category {
    int id;
    @NotEmpty(message = "Category title cannot be empty.")
    String title;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
