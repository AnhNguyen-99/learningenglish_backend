package com.learningenglish.repository;

import com.learningenglish.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Override
    Page<Exam> findAll(Pageable pageable);

    @Query("SELECT e FROM Exam e WHERE e.courseId = ?1")
    Page<Exam> findByCourseId(int id, Pageable pageable);

    @Query("SELECT e FROM Exam e WHERE e.title = ?1")
    Exam findByTitle(String title);
}
