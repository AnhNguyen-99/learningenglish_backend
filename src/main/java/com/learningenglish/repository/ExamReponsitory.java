package com.learningenglish.repository;

import com.learningenglish.entiy.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamReponsitory extends JpaRepository<Exam, Integer> {
}
