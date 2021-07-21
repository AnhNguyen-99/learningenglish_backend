package com.learningenglish.service.impl;

import com.learningenglish.entity.Exam;
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

    @Override
    public Page<Exam> findAllByPage(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

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
        if(exam.getId() == null){
            newExam.setQuestionData(exam.getQuestionData());
            newExam.setStatus(true);
            newExam.setCreateById(exam.getCreateById());
            newExam.setCreateDate(new Date());
            newExam.setUpdateDate(new Date());
            newExam.setDurationExam(exam.getDurationExam());
            newExam.setCourseId(exam.getCourseId());
            newExam.setTitle(exam.getTitle());
        }else{
            Exam oldExam = findById(exam.getId());
            newExam.setId(exam.getId());
            // Titile
            if(exam.getTitle() == null)
                newExam.setTitle(oldExam.getTitle());
            else
                newExam.setTitle(exam.getTitle());
            // Question Data
            if(exam.getQuestionData() == null)
                newExam.setQuestionData(oldExam.getQuestionData());
            else
                newExam.setQuestionData(exam.getQuestionData());
            // Status
            if(exam.getStatus() == null)
                newExam.setStatus(oldExam.getStatus());
            else
                newExam.setStatus(exam.getStatus());
            // Duration
            if(exam.getDurationExam() == null)
                newExam.setDurationExam(oldExam.getDurationExam());
            else
                newExam.setDurationExam(exam.getDurationExam());
            // Course
            if(exam.getCourseId() == null)
                newExam.setCourseId(oldExam.getCourseId());
            else
                newExam.setCourseId(exam.getCourseId());
            // Create UserId
            if(exam.getCreateById() == null)
                newExam.setCreateById(oldExam.getCreateById());
            else
                newExam.setCreateById(exam.getCreateById());
            // CreateDate
            newExam.setCreateDate(oldExam.getCreateDate());
            newExam.setUpdateDate(new Date());
        }
        return examRepository.save(newExam);
    }

    @Override
    public Exam changeStatus(Exam exam) {
        if(exam.getStatus() == true)
            exam.setStatus(false);
        else
            exam.setStatus(true);
        return save(exam);
    }

    @Override
    public Boolean deleteById(int id) {
        try {
            examRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Exam> findByCourseId(int id, Pageable pageable) {
        return examRepository.findByCourseId(id, pageable);
    }

    @Override
    public Exam exitByTitle(String title) {
        return examRepository.findByTitle(title);
    }
}
