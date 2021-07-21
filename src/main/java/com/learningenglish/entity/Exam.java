package com.learningenglish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "question_data")
    private String questionData;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "duration_exam")
    private Integer durationExam;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "create_by_id")
    private Integer createById;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

}
