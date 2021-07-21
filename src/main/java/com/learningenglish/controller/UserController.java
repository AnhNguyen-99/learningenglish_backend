package com.learningenglish.controller;

import com.learningenglish.dto.PageResult;
import com.learningenglish.dto.ServiceResult;
import com.learningenglish.dto.UserExport;
import com.learningenglish.entity.User;
import com.learningenglish.service.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        Boolean bl = userService.deleteById(id);
        if(bl)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("False", HttpStatus.NOT_FOUND);
    }

    // Export data user to file excel
    @GetMapping("export/user.csv")
    public void exportUserCSV(HttpServletResponse response) throws Exception{
        String fileName = "users.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        StatefulBeanToCsv<UserExport> writer = new StatefulBeanToCsvBuilder<UserExport>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        writer.write(userService.findAllExport());
    }

    // Search
    @GetMapping("/search")
    public PageResult searchUserByUsernameOrEmail(@RequestParam(value = "search-keyword") String info, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        Page<User> userPage = userService.findByUserNameOrEmail(info, info, pageable);
        return new PageResult(userPage);
    }

    @PutMapping("/change_status")
    public ResponseEntity<?> changeStatus(@RequestBody User user){
        return new ResponseEntity<>(userService.changeStatus(user), HttpStatus.OK);
    }

    // Dowload file excel sample import db
    @GetMapping("/getSampleFile")
    public ResponseEntity<InputStreamResource> getSampleFile(){
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            Resource resource = new ClassPathResource("FileModelImportUser.xlsx");
            InputStream inputStream = resource.getInputStream();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<>(inputStreamResource, responseHeader, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
