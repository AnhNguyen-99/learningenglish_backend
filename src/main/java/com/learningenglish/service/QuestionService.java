package com.learningenglish.service;

import com.learningenglish.entiy.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService{
    List<Question> findAll();
    boolean delete(int id);
    Question findById(int id);
    Question create(Question question);
}
