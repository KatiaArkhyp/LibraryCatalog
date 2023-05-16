package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorByNameContainingIgnoreCase(String name);

}

