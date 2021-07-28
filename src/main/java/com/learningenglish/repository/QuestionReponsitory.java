package com.learningenglish.repository;

import com.learningenglish.entiy.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionReponsitory extends JpaRepository<Question, Integer> {
}
