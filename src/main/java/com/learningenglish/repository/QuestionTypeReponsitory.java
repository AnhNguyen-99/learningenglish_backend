package com.learningenglish.repository;

import com.learningenglish.entiy.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  QuestionTypeReponsitory extends JpaRepository<QuestionType, Integer> {
}
