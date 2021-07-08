package com.learningenglish.service.impl;

import com.learningenglish.repository.ChoiceRepository;
import com.learningenglish.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChoiceServiceImpl implements ChoiceService {
    @Autowired
    private ChoiceRepository choiceRepository;

    @Override
    public Integer findIsCorrectedById(int id) {
        return choiceRepository.findIsCorrectedById(id);
    }

    @Override
    public String findChoiceTextById(int id) {
        return choiceRepository.findChoiceTextById(id);
    }
}
