package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Author;
import com.example.librarycatalog.repository.AuthorRepository;
import com.example.librarycatalog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        Set<String> uniqueNames = new HashSet<>();
        List<Author> uniqueAuthors = new ArrayList<>();

        for (Author author : authors) {
            if (uniqueNames.add(author.getName())) {
                uniqueAuthors.add(author);
            }
        }

        return uniqueAuthors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .collect(Collectors.toList());
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
