package com.learningenglish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "choice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Choice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "choice_text")
    private String choiceText;

    @Column(name = "corrected")
    private Integer corrected;

    @Column(name = "question_id")
    private Integer questionId;

}
