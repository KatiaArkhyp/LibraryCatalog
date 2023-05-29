package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.models.UserWithRole;
import com.example.librarycatalog.service.BookService;
import com.example.librarycatalog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class BookController {
    private BookService bookService;
    private UserService userService;

    @GetMapping()
    public String showAllBooks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        long bookCount = bookService.getBookCount();
        model.addAttribute("bookCount", bookCount);
        return "index";
    }
    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "query") String query, Model model) {
        List<Book> books = Stream.of(
                        bookService.findByTitle(query),
                        bookService.findByAuthor(query),
                        bookService.findByKeyword(query)
                ).flatMap(List::stream)
                .distinct()
                .toList();
        model.addAttribute("books", books);
        return "index";
    }

}
