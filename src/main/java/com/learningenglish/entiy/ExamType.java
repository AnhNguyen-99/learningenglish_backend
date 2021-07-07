package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exam_type", schema = "learning_english", catalog = "")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamType examType = (ExamType) o;
        return id == examType.id && Objects.equals(name, examType.name) && Objects.equals(code, examType.code) && Objects.equals(createDate, examType.createDate) && Objects.equals(updateDate, examType.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createDate, updateDate);
    }
}
