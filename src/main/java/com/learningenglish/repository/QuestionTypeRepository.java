package com.learningenglish.repository;

import com.learningenglish.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {

    @Query("SELECT q FROM QuestionType q WHERE q.name = ?1")
    QuestionType findByName(String name);

    @Query("SELECT q FROM QuestionType q WHERE q.code = ?1")
    QuestionType findByCode(String code);
}
