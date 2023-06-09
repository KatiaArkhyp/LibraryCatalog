package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.Author;
import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.service.AuthorService;
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
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;

    @GetMapping()
    public String showAllBooks(Model model) {
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
    @GetMapping("/authors")
    public String showAllAuthors(Model model) {
        List<Author> authors = new ArrayList<>(authorService.getAllAuthors());
        model.addAttribute("authors", authors);
        return "authors-list";
    }


    @GetMapping("/authors/books")
    public String showBooksByAuthor(@RequestParam("authorName") String authorName,
                                    Model model) {
        List<Book> books = Stream.of(
                        bookService.getBooksByAuthorName(authorName)
                ).flatMap(List::stream)
                .distinct()
                .toList();
        model.addAttribute("books", books);
        return "index";
    }

}
