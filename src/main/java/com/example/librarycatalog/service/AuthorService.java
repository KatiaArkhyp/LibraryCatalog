package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<Author> getAllAuthors();
    List<Author> findByName(String name);
    Author getById(Long id);
}
