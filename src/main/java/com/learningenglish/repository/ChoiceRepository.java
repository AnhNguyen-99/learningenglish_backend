package com.learningenglish.repository;

import com.learningenglish.entiy.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    @Query("SELECT c.corrected FROM Choice c WHERE c.id = ?1")
    Integer findIsCorrectedById(int id);

    @Query("SELECT c.choiceText FROM Choice c WHERE c.id = ?1")
    String findChoiceTextById(int id);
}
