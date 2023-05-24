package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Keyword;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KeywordService {
    List<Keyword> getAllKeywords();
    Keyword saveKeyword(Keyword keyword);
    void deleteKeyword(Long id);
    Keyword getKeywordById(Long keywordId);
}