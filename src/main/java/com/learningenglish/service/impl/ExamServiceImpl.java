package com.learningenglish.service.impl;

import com.learningenglish.entiy.Exam;
import com.learningenglish.repository.ExamReponsitory;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class  ExamServiceImpl implements ExamService {

    @Autowired
    private ExamReponsitory examRepon;

    @Override
    public List<Exam> findAll() {
        return examRepon.findAll();
    }

    @Override
    public Exam findById(int id) {
        return examRepon.findById(id).get();
    }

    @Override
    public Exam create(Exam exam) {
        if (exam.getId() != null){
            Exam saveExam = examRepon.findById(exam.getId()).get();
            exam.setId(exam.getId());
            exam.setCreateDate(saveExam.getCreateDate());
            exam.setUpdateDate(new Date());
            if (exam.getTitle() == null) {
                exam.setTitle(saveExam.getTitle());
            }
            if (exam.getQuestionData() == null) {
                exam.setQuestionData(saveExam.getQuestionData());
            }
            if (exam.getStatus() == null){
                exam.setStatus(saveExam.getStatus());
            }
        }
        return examRepon.save(exam);
    }

    @Override
    public boolean delete(int id) {
        try {
            examRepon.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
