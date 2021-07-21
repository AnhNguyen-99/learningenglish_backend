package com.learningenglish.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exam_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duration_exam")
    private String durationExam;

    @Column(name = "is_finished")
    private Integer isFinished;

    @Column(name = "is_started")
    private Integer isStarted;

    @Column(name = "remaning_time")
    private Integer remaningTime;

    @Column(name = "time_finish")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFinish;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Column(name = "total_point")
    private Double totalPoint;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

}
