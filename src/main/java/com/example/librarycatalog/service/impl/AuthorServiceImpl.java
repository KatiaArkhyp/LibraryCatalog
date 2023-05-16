package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Author;
import com.example.librarycatalog.repository.AuthorRepository;
import com.example.librarycatalog.service.AuthorService;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .toList();
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findAuthorByNameContainingIgnoreCase(name);
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with this ID not found: " + id));
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
