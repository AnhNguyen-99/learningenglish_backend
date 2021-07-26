package com.learningenglish.controller;

import com.learningenglish.entiy.Exam;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/exam")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping(value = "")
    public ResponseEntity<List<Exam>> getall(){
        List<Exam> listExam = examService.findAll();
        if(listExam != null){
            return new ResponseEntity<>(listExam, HttpStatus.OK);
        }else {
            return new ResponseEntity("false", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<?> findID(@PathVariable int id){
        return new ResponseEntity<>(examService.findById(id), HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity<?> insert(@RequestBody Exam exam){
        exam.setUpdateDate(new Date());
        exam.setCreateDate(new Date());
        return new ResponseEntity<>(examService.create(exam), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<?> update(@RequestBody Exam exam,@PathVariable int id){
        exam.setId(id);
        return new ResponseEntity<>(examService.create(exam), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> delete(@PathVariable int id){
        return  new ResponseEntity<>(examService.delete(id), HttpStatus.OK);
    }
}
