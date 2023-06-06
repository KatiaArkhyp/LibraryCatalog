package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.models.BookStatus;
import com.example.librarycatalog.models.Borrow;
import com.example.librarycatalog.models.UserWithRole;
import com.example.librarycatalog.service.BookService;
import com.example.librarycatalog.service.BorrowService;
import com.example.librarycatalog.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class BorrowController {

    private BookService bookService;
    private BorrowService borrowService;
    private UserService userService;
    private EntityManager entityManager;


    @GetMapping("/borrow-book/{bookId}")
    public String borrowBook(@PathVariable("bookId") Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
        Book book = bookService.getById(bookId);
        if (book.getStatus() == BookStatus.AVAILABLE) {
            UserWithRole user = userService.findByEmail(userDetails.getUsername());
            book.setStatus(BookStatus.UNAVAILABLE);
            bookService.saveBook(book);
            Borrow borrow = new Borrow();
            borrow.setUser(user);
            borrow.setBook(book);
            borrow.setStartDate(LocalDate.now());
            borrow.setEndDate(LocalDate.now().plusDays(30));
            borrowService.saveBorrow(borrow);
        }
        return "redirect:/borrows";
    }

    @GetMapping("/borrows")
    public String showBorrowedBooks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserWithRole user = userService.findByEmail(userDetails.getUsername());
        List<Borrow> borrowList = borrowService.getBorrowsByUser(user);
        model.addAttribute("borrowList", borrowList);
        return "borrows";
    }

    @PostMapping("/return-book/{borrowId}")
    public String returnBook(@PathVariable("borrowId") Long borrowId) {
        Borrow borrow = borrowService.getBorrowById(borrowId);
        Book book = borrow.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookService.saveBook(book);
        entityManager.remove(borrow);
        return "redirect:/borrows";
    }
}
