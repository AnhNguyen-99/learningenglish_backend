package com.learningenglish.service;

import com.learningenglish.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> findAll();

    Course findById(int id);

    Course save(Course course);

    Boolean deleteById(int id);
}
