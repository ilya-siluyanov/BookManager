package com.bookmanager.bookmanager.database;

import java.util.List;

import com.bookmanager.bookmanager.data.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcBookRepository implements BookRepository {

    @Autowired
    JdbcTemplate db;

    @Override
    public List<Book> getAll() {
        final String sql = "select * from book";
        return db.query(sql, (rs, rowNum) -> new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                rs.getString("link")));

    }

    @Override
    public void addBook(final Book book) {
        String sql = "insert into book values(?,?,?,?)";
        db.update(sql, new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.getLink() });
        log.info("New book added: " + book);
    }

    @Override
    public void removeBook(final Book book) {
        String sql = "delete from book where id=?";
        db.update(sql, new Object[] { book.getId() });
        log.info("book deleted: " + book);

    }

    @Override
    public void updateBook(final Book oldBook, final Book newBook) {
        // TODO Auto-generated method stub
        String sql = "update book set id=? where id=?";
        db.update(sql, newBook.getId(), oldBook.getId());
        sql = "update book set title=? where id=?";
        db.update(sql, newBook.getTitle(), oldBook.getId());
        sql = "update book set author=? where id=?";
        db.update(sql, newBook.getAuthor(), oldBook.getId());
        sql = "update book set link=? where id=?";
        db.update(sql, newBook.getLink(), oldBook.getId());
    }

    @Override
    public Book getBook(int id) {
        String sql = "select * from book where id=?";
    
        return db.queryForObject(sql, new Object[] {id}, (rs, rowNum) -> new Book(
                rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("link")));
    }

}