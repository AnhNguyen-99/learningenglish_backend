package com.learningenglish.service;

import com.learningenglish.entiy.Role;
import com.learningenglish.entiy.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
