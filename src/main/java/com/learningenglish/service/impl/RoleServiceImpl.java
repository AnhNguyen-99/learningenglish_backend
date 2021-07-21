package com.learningenglish.service.impl;

import com.learningenglish.entity.Role;
import com.learningenglish.entity.RoleName;
import com.learningenglish.repository.RoleRepository;
import com.learningenglish.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
