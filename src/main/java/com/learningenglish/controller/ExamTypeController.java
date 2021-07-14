package com.learningenglish.controller;

import com.learningenglish.entiy.ExamType;
import com.learningenglish.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exam_type")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamTypeController {
    @Autowired
    private ExamTypeService examTypeService;

    @GetMapping("")
    public ResponseEntity<List<ExamType>> getAll(){
        List<ExamType> listExam = examTypeService.findAll();
        return new ResponseEntity<>(listExam, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity<>(examTypeService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(examTypeService.delete(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody ExamType examType){
        return new ResponseEntity<>(examTypeService.save(examType), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ExamType examType, @PathVariable int id){
        examType.setId(id);
        return new ResponseEntity<>(examTypeService.save(examType), HttpStatus.OK);
    }
}
