package com.learningenglish.service.impl;

import com.learningenglish.entiy.QuestionType;
import com.learningenglish.repository.QuestionTypeReponsitory;
import com.learningenglish.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class QuestionTypeImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeReponsitory questionTypeReponsitory;

    @Override
    public List<QuestionType> findAll() {
        return questionTypeReponsitory.findAll();
    }

    @Override
    public boolean delete(int id) {
        try {
            questionTypeReponsitory.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public QuestionType findId(int id) {
        return questionTypeReponsitory.findById(id).get();
    }

    @Override
    public QuestionType create(QuestionType questionType) {
        if (questionType.getId() != null){
            QuestionType questionCreate = questionTypeReponsitory.findById(questionType.getId()).get();
            questionType.setId(questionType.getId());
            questionType.setCreateDate(questionCreate.getCreateDate());
            questionType.setUpdateDate(new Date());
            if(questionType.getCode() == null){
                questionType.setCode(questionCreate.getCode());
            }
            if (questionType.getName() == null){
                questionType.setName(questionCreate.getName());
            }
        }
        return questionTypeReponsitory.save(questionType);
    }
}
