package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exam")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "question_data")
    private String questionData;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id && Objects.equals(title, exam.title) && Objects.equals(questionData, exam.questionData) && Objects.equals(status, exam.status) && Objects.equals(createDate, exam.createDate) && Objects.equals(updateDate, exam.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questionData, status, createDate, updateDate);
    }
}
