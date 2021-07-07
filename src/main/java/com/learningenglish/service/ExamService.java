package com.learningenglish.service;

import com.learningenglish.entiy.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    List<Exam> findAll();

    Exam findById(int id);

    Exam save(Exam exam);

    Exam changeStatus(Exam exam);

    Boolean delete(int id);

    Page<Exam> findAllByPage(Pageable pageable);
}
