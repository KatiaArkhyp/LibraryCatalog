package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findBookByKeywordContainingIgnoreCase(String keyword);

}
