package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.repository.BookRepository;
import com.example.librarycatalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .toList();
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByKeywordsKeywordLikeIgnoreCase(keyword);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with this ID not found: " + id));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public long getBookCount() {
        return bookRepository.count();
    }
}
