package com.bookmanager.bookmanager.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Book {

    private int id;

    @NotNull
    @Size(min=1,message="name must be at least 1 character long")
    private String title;
    @NotNull
    @Size(min = 1, message = "author name must be at least 1 character long")
    private String author;
    @NotNull
    @Size(min = 1, message = "link must be at least 1 character long")
    private String link;

    private String deleteLink;

    public Book(int id, String title, String author, String link) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.link = link;
        this.deleteLink = "/delete?id=" + id;
    }

    public Book() {

    }
}