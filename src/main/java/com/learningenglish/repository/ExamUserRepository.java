package com.learningenglish.repository;

import com.learningenglish.entity.ExamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamUserRepository extends JpaRepository<ExamUser, Integer> {

//    @Query("SELECT e FROM ExamUser e WHERE e.user = ?1")
//    List<ExamUser> findAllByUserName(String username);
//
//    ExamUser findByExam_IdAndUserName(int id, String username);
//
//    List<ExamUser> findByExamId(int id);
}
