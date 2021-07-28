package com.learningenglish.controller;

import com.learningenglish.entiy.Question;
import com.learningenglish.service.QuestionService;
import com.learningenglish.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/question")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<List<Question>> getAll(){
        List<Question> questions = questionService.findAll();
        if (questions != null){
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }else {
            return new ResponseEntity("false", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Question question){
        question.setUpdateDate(new Date());
        question.setCreateDate(new Date());
        return new ResponseEntity<>(questionService.create(question), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Question question, @PathVariable int id){
        question.setId(id);
        return new ResponseEntity<>(questionService.create(question), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(questionService.delete(id), HttpStatus.OK);
    }
}
