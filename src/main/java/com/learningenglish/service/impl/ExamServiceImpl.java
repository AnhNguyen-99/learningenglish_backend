package com.learningenglish.service.impl;

import com.learningenglish.entiy.Exam;
import com.learningenglish.repository.ExamRepository;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamService examService;

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
        Exam newExam = new Exam();
        if (exam.getId() == null) {
            newExam.setTitle(exam.getTitle());
            newExam.setQuestionData(exam.getQuestionData());
            newExam.setExamTypeId(exam.getExamTypeId());
            newExam.setStatus(true);
            newExam.setCreateDate(new Date());
            newExam.setUpdateDate(new Date());
        } else {
            Exam oldExam = examRepository.findById(exam.getId()).get();
            // Id
            newExam.setId(exam.getId());
            // Title
            if (exam.getTitle() == null)
                newExam.setTitle(oldExam.getTitle());
            else
                newExam.setTitle(exam.getTitle());
            // Question Data
            if (exam.getQuestionData() == null)
                newExam.setQuestionData(oldExam.getQuestionData());
            else
                newExam.setQuestionData(exam.getQuestionData());
            // Status
            if (exam.getStatus() == null)
                newExam.setStatus(oldExam.getStatus());
            else
                newExam.setStatus(exam.getStatus());
            // ExamTypeId
            if (exam.getExamTypeId() == null)
                newExam.setExamTypeId(oldExam.getExamTypeId());
            else
                newExam.setExamTypeId(exam.getExamTypeId());
            // CreateDate
            newExam.setCreateDate(oldExam.getCreateDate());
            // UpdateDate
            newExam.setUpdateDate(new Date());
        }
        return examRepository.save(newExam);
    }

    @Override
    public Exam changeStatus(Exam exam) {
        if (exam.getStatus() == true)
            exam.setStatus(false);
        else
            exam.setStatus(true);
        return save(exam);
    }

    @Override
    public Boolean delete(int id) {
        try {
            examRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Exam> findAllByPage(Pageable pageable) {
        return examRepository.findAll(pageable);
    }
}
