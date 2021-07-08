package com.learningenglish.service;

import com.learningenglish.entiy.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> findAll();

    Question findById(int id);

    Question save(Question question);

    Boolean delete(int id);
}
