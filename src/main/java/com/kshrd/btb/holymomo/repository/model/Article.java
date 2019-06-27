package com.kshrd.btb.holymomo.repository.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@JsonPropertyOrder({"thumbnail","author","title"})
public class Article {
    private int id;
    @NotEmpty(message = "{empty.title}")
    private String title;
    @NotNull
    @Valid
    private Category category;
    @NotEmpty(message = "{empty.author}")
    private String author;
    @NotEmpty(message = "{empty.description}")
    private String description;
    private String thumbnail;

    public Article(int id, String title,Category category, String author, String description, String thumbnail) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Article() {}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
