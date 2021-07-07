package com.learningenglish.repository;

import com.learningenglish.entiy.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Integer> {

}
