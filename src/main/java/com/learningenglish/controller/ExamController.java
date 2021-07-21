package com.learningenglish.controller;

import com.learningenglish.dto.PageResult;
import com.learningenglish.dto.ServiceResult;
import com.learningenglish.entity.Exam;
import com.learningenglish.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exam")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/page")
    public PageResult getListExamByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<Exam> pageExam = examService.findAllByPage(pageable);
        return new PageResult(pageExam);
    }

    @GetMapping("")
    public ResponseEntity<List<Exam>> getAll() {
        List<Exam> list = examService.findAll();
        if (list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Exam exam = examService.findById(id);
        if (exam != null)
            return new ResponseEntity<>(exam, HttpStatus.OK);
        else
            return new ResponseEntity("Không tìm thấy", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Exam exam) {
        try {
            if (examService.exitByTitle(exam.getTitle()) != null) {
                return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.CONFLICT.value(), "Chủ để đã tồn tại", ""));
            }
            examService.save(exam);
            return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.OK.value(), "Success", exam));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Exam exam, @PathVariable int id) {
        try {
            exam.setId(id);
            return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.OK.value(), "Sửa đổi thành công", examService.save(exam)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ServiceResult(HttpStatus.NOT_FOUND.value(), "Sửa đổi không thành công", ""));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        Boolean bl = examService.deleteById(id);
        if(bl)
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    /*
    * Tìm kiếm thông tin các bài thi theo khóa học
    * @param: courseId, page: page, size
    *
    */
    @GetMapping("/course/{id}")
    public PageResult getListExamByCourseId(@PageableDefault(page = 0, size = 10, sort = "id")Pageable pageable, @PathVariable int id){
        Page<Exam> pageExam = examService.findByCourseId(id, pageable);
        return new PageResult(pageExam);
    }

    /*
    * Thay đổi trạng thái của bài thi
    * @Param exam
    */
    @PutMapping("/change_status")
    public ResponseEntity<?> changeStatus(@RequestBody Exam exam){
        return new ResponseEntity<>(examService.changeStatus(exam), HttpStatus.OK);
    }
}
