package com.learningenglish.service.impl;

import com.learningenglish.entiy.Question;
import com.learningenglish.repository.QuestionRepository;
import com.learningenglish.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(int id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Question save(Question question) {
        Question newQuestion = new Question();
        if(question.getId() == null){
            newQuestion.setQuestionTypeId(question.getQuestionTypeId());
            newQuestion.setQuestionText(question.getQuestionText());
            newQuestion.setPoint(question.getPoint());
            newQuestion.setStatus(true);
            newQuestion.setCreateDate(new Date());
            newQuestion.setUpdateDate(new Date());
        }else{
            Question oldQuestion = questionRepository.findById(question.getId()).get();
            newQuestion.setId(question.getId());
            // QuestionTypeId
            if(question.getQuestionTypeId() == null)
                newQuestion.setQuestionTypeId(oldQuestion.getQuestionTypeId());
            else
                newQuestion.setQuestionTypeId(question.getQuestionTypeId());
            // Question Text
            if(question.getQuestionText() == null)
                newQuestion.setQuestionText(oldQuestion.getQuestionText());
            else
                newQuestion.setQuestionText(question.getQuestionText());
            // Point
            if(question.getPoint() == null)
                newQuestion.setPoint(oldQuestion.getPoint());
            else
                newQuestion.setPoint(question.getPoint());
            // Status
            if(question.getStatus() == null)
                newQuestion.setStatus(oldQuestion.getStatus());
            else
                newQuestion.setStatus(question.getStatus());
            // CreateDate
            newQuestion.setCreateDate(oldQuestion.getCreateDate());
            newQuestion.setUpdateDate(new Date());
        }
        return questionRepository.save(newQuestion);
    }

    @Override
    public Boolean delete(int id) {
        try {
            questionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
