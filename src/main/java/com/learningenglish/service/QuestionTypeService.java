package com.learningenglish.service;

import com.learningenglish.entity.QuestionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionTypeService {

    List<QuestionType> findAll();

    QuestionType findById(int id);

    QuestionType save(QuestionType questionType);

    Boolean deleteById(int id);

    QuestionType exitByName(String name);

    QuestionType exitCode(String code);
}
