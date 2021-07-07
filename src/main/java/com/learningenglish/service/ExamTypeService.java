package com.learningenglish.service;

import com.learningenglish.entiy.ExamType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamTypeService {

    ExamType findById(int id);

    ExamType save(ExamType examType);

    List<ExamType> findByAll();

    Boolean delete(int id);
}
