package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.models.BookStatus;
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
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByKeywordsKeywordLikeIgnoreCase(keyword);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(author);
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

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public void editBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + book.getId()));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setBookSummary(book.getBookSummary());
        existingBook.setCoverUrlBook(book.getCoverUrlBook());
        existingBook.setKeywords(book.getKeywords());

        bookRepository.save(existingBook);
    }

    @Override
    public List<Book> getBooksByAuthorName(String name) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(name);
    }

    @Override
    public void updateBookStatus(Long id, String bookStatus) {
        BookStatus status = BookStatus.valueOf(bookStatus);
        bookRepository.updateBookStatus(id, status);
    }

}
