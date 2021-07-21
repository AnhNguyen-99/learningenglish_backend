package com.learningenglish.dto;

import com.learningenglish.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSheet {

    private Integer questionId;
    private List<Choice> choices;
    private Integer point;
}
