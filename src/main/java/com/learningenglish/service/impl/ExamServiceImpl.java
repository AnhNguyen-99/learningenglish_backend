package com.learningenglish.service.impl;

import com.learningenglish.entiy.Exam;
import com.learningenglish.repository.ExamRepository;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;


    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(int id) {
        return examRepository.findById(id).get();
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam changeStatus(Exam exam) {
        if(exam.getStatus() == true)
            exam.setStatus(false);
        else
            exam.setStatus(true);
        return examRepository.save(exam);
    }

    @Override
    public Boolean delete(int id) {
        try {
            examRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Exam> findAllByPage(Pageable pageable) {
        return examRepository.findAll(pageable);
    }
}
