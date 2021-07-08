package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "question")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question_type_id")
    private Integer questionTypeId;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "point")
    private Integer point;

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
        Question question = (Question) o;
        return id == question.id && Objects.equals(questionTypeId, question.questionTypeId) && Objects.equals(questionText, question.questionText) && Objects.equals(point, question.point) && Objects.equals(status, question.status) && Objects.equals(createDate, question.createDate) && Objects.equals(updateDate, question.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionTypeId, questionText, point, status, createDate, updateDate);
    }

}
