package com.learningenglish.controller;

import com.learningenglish.dto.PageResult;
import com.learningenglish.dto.ServiceResult;
import com.learningenglish.entiy.User;
import com.learningenglish.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('Admin')")
    public PageResult getUserByPage(@PageableDefault(page = 0, size = 10, sort = "id")Pageable pageable){
        Page<User> userPage = userService.findUserByPage(pageable);
        return new PageResult(userPage);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            if (userService.findByUserName(user.getUsername()) != null) {
                return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.CONFLICT.value(), "Tên đăng nhập đã có", ""));
            }
            if (userService.exitByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.CONTINUE.value(), "Email đã được sử dụng", ""));
            }
            userService.create(user);
            return ResponseEntity.ok(new ServiceResult(HttpStatus.OK.value(), "Success", user));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id){
        user.setId(id);
        userService.create(user);
        return ResponseEntity.ok(new ServiceResult(HttpStatus.OK.value(), "Success", user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return ResponseEntity.ok(new ServiceResult(HttpStatus.OK.value(), "", userService.findById(id)));
    }

}
