package com.learningenglish.service;

import com.learningenglish.entiy.ExamType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamTypeService {
    List<ExamType> findAll();
    ExamType findById(int id);
    ExamType save(ExamType examType);
    Boolean delete(int id);
}
