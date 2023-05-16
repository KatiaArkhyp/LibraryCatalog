package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Keyword;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KeywordService {
    List<Keyword> getAllKeywords();
    Keyword saveKeyword(Keyword keyword);
    void deleteKeyword(Long id);
}
