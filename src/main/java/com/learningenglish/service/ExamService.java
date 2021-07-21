package com.learningenglish.service;

import com.learningenglish.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    Page<Exam> findAllByPage(Pageable pageable);

    List<Exam> findAll();

    Exam findById(int id);

    Exam save(Exam exam);

    Exam changeStatus(Exam exam);

    Boolean deleteById(int id);

    Page<Exam> findByCourseId(int id, Pageable pageable);

    Exam exitByTitle(String title);
}
