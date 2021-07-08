package com.learningenglish.service.impl;

import com.learningenglish.entiy.QuestionType;
import com.learningenglish.repository.QuestionTypeRepository;
import com.learningenglish.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class QuestionTypeServiceImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Override
    public List<QuestionType> findAll() {
        return questionTypeRepository.findAll();
    }

    @Override
    public QuestionType findById(int id) {
        return questionTypeRepository.findById(id).get();
    }

    @Override
    public QuestionType save(QuestionType questionType) {
        QuestionType newQuestionType = new QuestionType();
        if(questionType.getId() == null){
            newQuestionType.setName(questionType.getName());
            newQuestionType.setCode(questionType.getCode());
            newQuestionType.setCreateDate(new Date());
            newQuestionType.setUpdateDate(new Date());
        }else{
            QuestionType oldQuestionType = questionTypeRepository.findById(questionType.getId()).get();
            newQuestionType.setId(questionType.getId());
            // Name
            if(questionType.getName() == null)
                newQuestionType.setName(oldQuestionType.getName());
            else
                newQuestionType.setName(questionType.getName());
            // Code
            if(questionType.getCode() == null)
                newQuestionType.setCode(oldQuestionType.getCode());
            else
                newQuestionType.setCode(questionType.getCode());
            // CreateDate
            newQuestionType.setCreateDate(oldQuestionType.getCreateDate());
            // UpdateDate
            newQuestionType.setUpdateDate(new Date());
        }
        return questionTypeRepository.save(newQuestionType);
    }

    @Override
    public Boolean delete(int id) {
        try {
            questionTypeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
