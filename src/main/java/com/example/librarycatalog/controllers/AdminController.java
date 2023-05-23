package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.Author;
import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.service.AuthorService;
import com.example.librarycatalog.service.BookService;
import com.example.librarycatalog.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class AdminController {
    private BookService bookService;
    private AuthorService authorService;
    private KeywordService keywordService;

    @GetMapping("/create")
    public String createBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "books-create";
    }

    @PostMapping("/create")
    public String saveBook(@ModelAttribute("book") Book book, @ModelAttribute("author") Author author){
        bookService.saveBook(book);
        book.setAuthor(author);
        return "redirect:/";
    }
}
