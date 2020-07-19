package com.bookmanager.bookmanager.controllers;

import java.util.List;

import javax.validation.Valid;

import com.bookmanager.bookmanager.data.Book;
import com.bookmanager.bookmanager.database.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    BookRepository repo;
    private static int id = 0;

    @GetMapping
    public String home(Model model) {
        List<Book> books = repo.getAll();
        if (!books.isEmpty())
            id = books.get(books.size() - 1).getId() + 1;
        model.addAttribute("books", books);

        log.info("books.size(): " + books.size());
        for (Book book : books) {
            log.info("Book #" + book.getId() + " : " + book);
        }
        model.addAttribute("newBook", new Book(0, "", "", ""));
        return "home";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("newBook") Book book, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("Something is wrong: " + book);
            model.addAttribute("newBook", book);
            model.addAttribute("books", repo.getAll());
            return "home";
        } else {
            book.setId(id);
            repo.addBook(book);
            id++;
            log.info("New book added: " + book);
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        Book book = repo.getBook(id);
        repo.removeBook(book);
        return "redirect:/";
    }

}