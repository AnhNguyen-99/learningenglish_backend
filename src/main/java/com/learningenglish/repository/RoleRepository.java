package com.learningenglish.repository;

import com.learningenglish.entiy.Role;
import com.learningenglish.entiy.RoleName;
import com.learningenglish.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
