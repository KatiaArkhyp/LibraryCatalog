package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.*;
import com.example.librarycatalog.service.AuthorService;
import com.example.librarycatalog.service.BookService;
import com.example.librarycatalog.service.BorrowService;
import com.example.librarycatalog.service.KeywordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class AdminController {
    private BookService bookService;
    private BorrowService borrowService;
    private KeywordService keywordService;

    @GetMapping("/create")
    public String createBook(Model model){
        Book book = new Book();
        List<Keyword> keywords = keywordService.getAllKeywords();
        model.addAttribute("book", book);
        model.addAttribute("keywords", keywords);
        return "books-create";
    }

    @PostMapping("/create")
    public String saveBook(@Valid @ModelAttribute("book") Book book,
                           BindingResult result,
                           HttpServletRequest request){
        if(result.hasErrors()){
            return "books-create";
        }

        List<Keyword> selectedKeywords = keywordService.getSelectedKeywords(request);
        book.setKeywords(selectedKeywords);
        book.setStatus(BookStatus.AVAILABLE);
        bookService.saveBook(book);

        return "redirect:/";
    }

    @GetMapping("/{bookId}/edit")
    public String editBookInfo(@PathVariable("bookId") Long bookId, Model model){
        Book book = bookService.getById(bookId);
        List<Keyword> keywords = keywordService.getAllKeywords();
        model.addAttribute("book", book);
        model.addAttribute("keywords", keywords);
        return "book-edit";
    }

    @PostMapping("/{bookId}/edit")
    public String saveUpdatedBook(@PathVariable("bookId") Long bookId,
                                  @Valid @ModelAttribute("book") Book book,
                                  BindingResult result,
                                  HttpServletRequest request){
        if(result.hasErrors()){
            return "book-edit";
        }
        List<Keyword> selectedKeywords = keywordService.getSelectedKeywords(request);
        book.setKeywords(selectedKeywords);

        book.setId(bookId);
        bookService.editBook(book);

        return "redirect:/";
    }

    @GetMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return "redirect:/";
    }

    @GetMapping("/borrowed-books")
    public String showBorrowedBooks(Model model) {
        List<Borrow> borrowedBooks = borrowService.getAllBorrowedBooks();
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "/borrowed-books";
    }

    @GetMapping("/return-book/{borrowId}")
    public String returnBook(@PathVariable("borrowId") Long borrowId) {
        Borrow borrow = borrowService.getBorrowById(borrowId);
        Book book = borrow.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookService.saveBook(book);
        borrowService.returnBook(borrowId);
        return "redirect:/borrowed-books";
    }
}