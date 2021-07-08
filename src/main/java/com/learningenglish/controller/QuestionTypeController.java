package com.learningenglish.controller;

import com.learningenglish.entiy.QuestionType;
import com.learningenglish.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/question_type")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("")
    public ResponseEntity<List<QuestionType>> findAll(){
        List<QuestionType> list = questionTypeService.findAll();
        if(list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return new ResponseEntity("Ko tìm thấy", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity<>(questionTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody QuestionType questionType){
        return new ResponseEntity<>(questionTypeService.save(questionType), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody QuestionType questionType, @PathVariable int id){
        questionType.setId(id);
        return new ResponseEntity<>(questionTypeService.save(questionType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(questionTypeService.delete(id), HttpStatus.OK);
    }
}
