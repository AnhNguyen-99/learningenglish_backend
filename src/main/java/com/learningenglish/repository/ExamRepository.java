package com.learningenglish.repository;

import com.learningenglish.entiy.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Override
    Page<Exam> findAll(Pageable pageable);
}
