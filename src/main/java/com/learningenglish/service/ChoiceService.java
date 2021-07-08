package com.learningenglish.service;

import org.springframework.stereotype.Service;

@Service
public interface ChoiceService {

    Integer findIsCorrectedById(int id);
    String findChoiceTextById(int id);
}
