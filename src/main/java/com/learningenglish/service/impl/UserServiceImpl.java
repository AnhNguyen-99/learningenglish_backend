package com.learningenglish.service.impl;

import com.learningenglish.entiy.Role;
import com.learningenglish.entiy.RoleName;
import com.learningenglish.entiy.User;
import com.learningenglish.repository.UserRepository;
import com.learningenglish.service.RoleService;
import com.learningenglish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findUserByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        User newUser = new User(user.getUsername(), passwordEncoder.encode("1"), user.getEmail());
        Set<Role> reqRole = user.getRoles();
        Set<Role> roles = new HashSet<>();
        if (reqRole == null) {
            Role userRole = roleService.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            reqRole.forEach(role -> {
                switch (role.getName()) {
                    case ROLE_ADMIN: {
                        addRoles(RoleName.ROLE_ADMIN, roles);
                    }
                    default: {
                        addRoles(RoleName.ROLE_USER, roles);
                    }
                }
            });
        }
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    @Override
    public User exitByEmail(String email) {
        return userRepository.exitsByEmail(email);
    }

    public void addRoles(RoleName roleName, Set<Role> roles) {
        Role userRole = roleService.findByName(roleName).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        roles.add(userRole);
    }
}
