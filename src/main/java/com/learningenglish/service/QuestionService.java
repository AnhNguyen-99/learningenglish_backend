package com.learningenglish.service;

import com.learningenglish.dto.AnswerSheet;
import com.learningenglish.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> findAll();

    Page<Question> findAllByPage(Pageable pageable);

    Question findById(int id);

    Question save(Question question);

    Question changeStatus(Question question);

    Boolean deleteById(int id);

    List<AnswerSheet> convertFromQuesionList(List<Question> questionList);
}
