package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByKeywordsKeywordLikeIgnoreCase(String keyword);

//    @Query("SELECT b FROM Book b JOIN b.keywords k WHERE LOWER(k.keyword_id) LIKE LOWER(concat('%', :keyword, '%'))")
//    List<Book> findBookByKeyword(@Param("keyword") String keyword);
}
