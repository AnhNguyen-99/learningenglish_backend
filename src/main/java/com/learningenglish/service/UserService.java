package com.learningenglish.service;

import com.learningenglish.entiy.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByUserName(String username);

    void update(User user);

    Page<User> findUserByPage(Pageable pageable);

    User create(User user);

    User exitByEmail(String email);
}
