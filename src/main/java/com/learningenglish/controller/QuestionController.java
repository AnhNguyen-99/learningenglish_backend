package com.learningenglish.controller;

import com.learningenglish.dto.PageResult;
import com.learningenglish.entity.Question;
import com.learningenglish.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/question")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/page")
    public PageResult getAllByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<Question> pageQuestion = questionService.findAllByPage(pageable);
        return new PageResult(pageQuestion);
    }

    @GetMapping("")
    public ResponseEntity<List<Question>> getAll() {
        List<Question> list = questionService.findAll();
        if (list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.save(question), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Question question, @PathVariable int id) {
        question.setId(id);
        return new ResponseEntity<>(questionService.save(question), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(questionService.deleteById(id), HttpStatus.OK);
    }
}
