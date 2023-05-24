package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Keyword;
import com.example.librarycatalog.repository.KeywordRepository;
import com.example.librarycatalog.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Keyword saveKeyword(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    @Override
    public void deleteKeyword(Long id) {
        keywordRepository.deleteById(id);
    }

    @Override
    public Keyword getKeywordById(Long keywordId) {
        return keywordRepository.findById(keywordId).orElse(null);
    }
}