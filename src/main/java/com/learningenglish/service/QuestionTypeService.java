package com.learningenglish.service;

import com.learningenglish.entiy.QuestionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionTypeService {

    List<QuestionType> findAll();

    QuestionType findById(int id);

    QuestionType save(QuestionType questionType);

    Boolean delete(int id);
}
