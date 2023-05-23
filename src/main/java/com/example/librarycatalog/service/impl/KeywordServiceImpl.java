package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Keyword;
import com.example.librarycatalog.repository.KeywordRepository;
import com.example.librarycatalog.service.KeywordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
