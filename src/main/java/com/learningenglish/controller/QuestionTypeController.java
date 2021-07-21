package com.learningenglish.controller;

import com.learningenglish.dto.ServiceResult;
import com.learningenglish.entity.QuestionType;
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
    public ResponseEntity<List<QuestionType>> getAll() {
        List<QuestionType> list = questionTypeService.findAll();
        if (list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return new ResponseEntity<>(questionTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody QuestionType questionType) {
        try {
            if (questionTypeService.exitByName(questionType.getName()) != null) {
                return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.CONFLICT.value(), "Tên loại câu hỏi đã có", ""));
            }
            if (questionTypeService.exitCode(questionType.getCode()) != null) {
                return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.CONFLICT.value(), "Mã loại câu hỏi đã có", ""));
            }
            QuestionType result = questionTypeService.save(questionType);
            return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.OK.value(), "Success", result));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody QuestionType questionType, @PathVariable int id){
        questionType.setId(id);
        return new ResponseEntity<>(questionTypeService.save(questionType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(questionTypeService.deleteById(id), HttpStatus.OK);
    }

}
