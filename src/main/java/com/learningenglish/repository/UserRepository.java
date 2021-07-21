package com.learningenglish.repository;

import com.learningenglish.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUserName(String username);

    @Override
    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User exitsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?2")
    Page<User> findAllByUsernameContainsOrEmailContains(String username, String email, Pageable pageable);
}
