package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "choice")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "choice_text")
    private String choiceText;

    @Column(name = "corrected")
    private Integer corrected;

    @Column(name = "question_id")
    private Integer questionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return id == choice.id && Objects.equals(choiceText, choice.choiceText) && Objects.equals(corrected, choice.corrected) && Objects.equals(questionId, choice.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, choiceText, corrected, questionId);
    }
}
