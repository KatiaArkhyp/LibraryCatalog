package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.models.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByKeywordsKeywordLikeIgnoreCase(String keyword);

    List<Book> findByAuthorId(Long authorId);

    @Modifying
    @Query("UPDATE Book b SET b.status = :status WHERE b.id = :id")
    void updateBookStatus(@Param("id") Long id, @Param("status") BookStatus status);
}
