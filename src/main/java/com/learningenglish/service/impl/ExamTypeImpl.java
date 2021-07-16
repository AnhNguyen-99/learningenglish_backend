package com.learningenglish.service.impl;

import com.learningenglish.entiy.ExamType;
import com.learningenglish.repository.ExamTypeRepository;
import com.learningenglish.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ExamTypeImpl implements ExamTypeService {

    @Autowired
    private ExamTypeRepository examRepon;
    @Override
    public List<ExamType> findAll() {
        return examRepon.findAll();
    }

    @Override
    public ExamType findById(int id) {
        return examRepon.findById(id).get();
    }

    @Override
    public ExamType save(ExamType examType) {
        if(examType.getId() != null){
            ExamType saveExam = examRepon.findById(examType.getId()).get();
            examType.setId(examType.getId());
            examType.setCreateDate(saveExam.getCreateDate());
            examType.setUpdateDate(new Date());
        }
        return examRepon.save(examType);
    }

    @Override
    public Boolean delete(int id) {
        try {
            examRepon.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
