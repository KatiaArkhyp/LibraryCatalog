package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class BookController {
    private BookService bookService;

    @GetMapping()
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        long bookCount = bookService.getBookCount();
        model.addAttribute("bookCount", bookCount);
        return "index";
    }

}
