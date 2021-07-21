package com.learningenglish.service.impl;

import com.learningenglish.entity.Course;
import com.learningenglish.repository.CourseRepository;
import com.learningenglish.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course save(Course course) {
        Course newCourse = new Course();
        if(course.getId() == null){
            newCourse.setCode(course.getCode());
            newCourse.setName(course.getName());
            newCourse.setCreateById(course.getCreateById());
            newCourse.setCreateDate(new Date());
            newCourse.setUpdateDate(new Date());
        }else{
            Course oldCourse = findById(course.getId());
            newCourse.setId(course.getId());
            // Code
            if(course.getCode() == null)
                newCourse.setCode(oldCourse.getCode());
            else
                newCourse.setCode(course.getCode());
            // Name
            if(course.getName() == null)
                newCourse.setName(oldCourse.getName());
            else
                newCourse.setName(course.getName());
            // Create userId
            if(course.getCreateById() == null)
                newCourse.setCreateById(oldCourse.getCreateById());
            else
                newCourse.setCreateById(course.getCreateById());
            newCourse.setCreateDate(oldCourse.getCreateDate());
            newCourse.setUpdateDate(new Date());
        }
        return courseRepository.save(newCourse);
    }

    @Override
    public Boolean deleteById(int id) {
        try {
            courseRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
