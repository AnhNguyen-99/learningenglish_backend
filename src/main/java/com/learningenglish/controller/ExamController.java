package com.learningenglish.controller;

import com.learningenglish.dto.PageResult;
import com.learningenglish.entiy.Exam;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/exam")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamController {
    @Autowired
    private ExamService examService;
    
    @GetMapping("")
    public PageResult getAllByPage(@PageableDefault(page = 0, size = 10, sort = "id")Pageable pageable){
        Page<Exam> examPage = examService.findAllByPage(pageable);
        return new PageResult(examPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity<>(examService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Exam exam){
        return new ResponseEntity<>(examService.save(exam), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Exam exam, @PathVariable int id){
        exam.setId(id);
        return new ResponseEntity<>(examService.save(exam), HttpStatus.OK);
    }

    @PutMapping("/change_status")
    public ResponseEntity<?> changeStatus(@RequestBody Exam exam){
        return new ResponseEntity<>(examService.changeStatus(exam), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(examService.delete(id), HttpStatus.OK);
    }
}
