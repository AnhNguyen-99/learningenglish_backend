package com.learningenglish.controller;

import com.learningenglish.entiy.QuestionType;
import com.learningenglish.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/question_type")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionTypeController {
    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("")
    public ResponseEntity<List<QuestionType>> getAll(){
        List<QuestionType> questionTypes = questionTypeService.findAll();
        if(questionTypes != null) {
            return new ResponseEntity<>(questionTypes, HttpStatus.OK);
        }else {
            return new ResponseEntity("false", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity<>(questionTypeService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody QuestionType questionType, @PathVariable int id){
        questionType.setId(id);
        return new ResponseEntity<>(questionTypeService.create(questionType), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody QuestionType questionType){
        questionType.setUpdateDate(new Date());
        questionType.setCreateDate(new Date());
        return new ResponseEntity<>(questionTypeService.create(questionType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(questionTypeService.delete(id), HttpStatus.OK);
    }
}
