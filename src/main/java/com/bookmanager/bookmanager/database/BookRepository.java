package com.bookmanager.bookmanager.database;

import java.util.List;

import com.bookmanager.bookmanager.data.Book;

public interface BookRepository {
    List<Book> getAll();

    Book getBook(int id);
    
    void addBook(Book book);

    void removeBook(Book book);

    void updateBook(Book oldBook, Book newBook);


}