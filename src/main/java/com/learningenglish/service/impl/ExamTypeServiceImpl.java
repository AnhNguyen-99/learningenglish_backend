package com.learningenglish.service.impl;

import com.learningenglish.entiy.ExamType;
import com.learningenglish.repository.ExamTypeRepository;
import com.learningenglish.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamTypeServiceImpl implements ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Override
    public ExamType findById(int id) {
        return examTypeRepository.findById(id).get();
    }

    @Override
    public ExamType save(ExamType examType) {
        if(examType.getId() != null){
            ExamType oldExamType = examTypeRepository.findById(examType.getId()).get();
            examType.setId(examType.getId());
            examType.setCreateDate(oldExamType.getCreateDate());
        }
        return examTypeRepository.save(examType);
    }

    @Override
    public List<ExamType> findByAll() {
        return examTypeRepository.findAll();
    }

    @Override
    public Boolean delete(int id) {
        try {
            examTypeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
