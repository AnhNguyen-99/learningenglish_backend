package com.learningenglish.service;

import com.learningenglish.entity.Role;
import com.learningenglish.entity.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
