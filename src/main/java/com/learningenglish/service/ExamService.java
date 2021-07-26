package com.learningenglish.service;

import com.learningenglish.entiy.Exam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  ExamService {
    List<Exam> findAll();
    Exam findById(int id);
    Exam create(Exam exam);
    boolean delete(int id);
}
