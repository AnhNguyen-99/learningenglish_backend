package com.learningenglish.service;

import com.learningenglish.entiy.QuestionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionTypeService {
    List<QuestionType> findAll();
    boolean delete(int id);
    QuestionType findId(int id);
    QuestionType create(QuestionType questionType);
}
