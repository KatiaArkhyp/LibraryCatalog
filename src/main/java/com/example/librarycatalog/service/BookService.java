package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooks();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByKeyword(String keyword);
    Book getById(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
    long getBookCount();

    void editBook(Book book);
}
