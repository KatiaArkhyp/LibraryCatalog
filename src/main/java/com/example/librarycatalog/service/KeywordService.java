package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Keyword;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KeywordService {
    List<Keyword> getAllKeywords();
    Keyword getKeywordById(Long keywordId);

    List<Keyword> getSelectedKeywords(HttpServletRequest request);
}