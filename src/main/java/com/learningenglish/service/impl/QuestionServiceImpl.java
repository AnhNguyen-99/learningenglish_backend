package com.learningenglish.service.impl;

import com.learningenglish.entiy.Question;
import com.learningenglish.repository.QuestionReponsitory;
import com.learningenglish.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionReponsitory questionReponsitory;

    @Override
    public List<Question> findAll() {
        return questionReponsitory.findAll();
    }

    @Override
    public boolean delete(int id) {
        try {
            questionReponsitory.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Question findById(int id) {
        return questionReponsitory.findById(id).get();
    }

    @Override
    public Question create(Question question) {
        if(question.getId() != null){
            Question saveQuestion = questionReponsitory.findById(question.getId()).get();
            question.setId(question.getId());
            question.setCreateDate(saveQuestion.getCreateDate());
            question.setUpdateDate(new Date());
            if (question.getQuestionTypeId() == null){
                question.setQuestionTypeId(saveQuestion.getQuestionTypeId());
            }
            if(question.getStatus() == null){
                question.setStatus(saveQuestion.getStatus());
            }
            if(question.getPoint() == null){
                question.setPoint(saveQuestion.getPoint());
            }
            if (question.getQuestionText() == null){
                question.setQuestionText(saveQuestion.getQuestionText());
            }
        }
        return questionReponsitory.save(question);
    }
}
