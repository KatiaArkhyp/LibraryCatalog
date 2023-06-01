package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Keyword;
import com.example.librarycatalog.repository.KeywordRepository;
import com.example.librarycatalog.service.KeywordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private KeywordRepository keywordRepository;
    @Override
    public List<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword getKeywordById(Long keywordId) {
        return keywordRepository.findById(keywordId).orElse(null);
    }

    @Override
    public List<Keyword> getSelectedKeywords(HttpServletRequest request) {
        List<Keyword> selectedKeywords = new ArrayList<>();
        String[] keywordIds = request.getParameterValues("keywords");
        if (keywordIds != null) {
            for (String keywordId : keywordIds) {
                Keyword keyword = getKeywordById(Long.parseLong(keywordId));
                selectedKeywords.add(keyword);
            }
        }
        return selectedKeywords;
    }
}