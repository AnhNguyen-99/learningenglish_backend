package com.learningenglish.controller;

import com.learningenglish.entity.Course;
import com.learningenglish.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity<List<Course>> findAll(){
        List<Course> list = courseService.findAll();
        if(list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
    }

    /*
    * Thông tin của 1 khóa học
    * Method: GET
    * @param: id
    * @code 200
    * @code 404: not_found
    */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return new ResponseEntity(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Course course){
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable int id){
        course.setId(id);
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(courseService.deleteById(id), HttpStatus.OK);
    }
}
