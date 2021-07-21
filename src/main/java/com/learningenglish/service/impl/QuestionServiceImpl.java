package com.learningenglish.service.impl;

import com.learningenglish.dto.AnswerSheet;
import com.learningenglish.entity.Choice;
import com.learningenglish.entity.Question;
import com.learningenglish.repository.QuestionRepository;
import com.learningenglish.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Page<Question> findAllByPage(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question findById(int id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Question save(Question question) {
        Question newQuestion = new Question();
        if (question.getId() == null) {
            question.setQuestionText(question.getQuestionText());
//            question.setQuestionType();
            newQuestion.setPoint(question.getPoint());
            newQuestion.setCreateById(question.getCreateById());
            newQuestion.setStatus(true);
            newQuestion.setCreateDate(new Date());
            newQuestion.setUpdateDate(new Date());
        } else {
            Question oldQuestion = findById(question.getId());
            newQuestion.setId(question.getId());
            // Question Type
            // Text
            if (question.getQuestionText() == null)
                newQuestion.setQuestionText(oldQuestion.getQuestionText());
            else
                newQuestion.setQuestionText(question.getQuestionText());
            // Point
            if (question.getPoint() == null)
                newQuestion.setPoint(oldQuestion.getPoint());
            else
                newQuestion.setPoint(question.getPoint());
            // Status
            if (question.getStatus() == null)
                newQuestion.setStatus(oldQuestion.getStatus());
            else
                newQuestion.setStatus(question.getStatus());
            // createDate
            question.setCreateDate(oldQuestion.getCreateDate());
            question.setUpdateDate(oldQuestion.getUpdateDate());

        }
        return questionRepository.save(question);
    }

    @Override
    public Question changeStatus(Question question) {
        if (question.getStatus() == true)
            question.setStatus(false);
        else
            question.setStatus(true);
        return save(question);
    }

    @Override
    public Boolean deleteById(int id) {
        try {
            questionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AnswerSheet> convertFromQuesionList(List<Question> questionList) {
        List<AnswerSheet> answerSheets = new ArrayList<>();
        questionList.forEach(question -> {
            List<Choice> choices = question.getChoices();
            choices.stream().map(choice -> {
                choice.setCorrected(0);
                return choice;
            }).collect(Collectors.toList());
            AnswerSheet answerSheet = new AnswerSheet(question.getId(), question.getChoices(), question.getPoint());
            answerSheets.add(answerSheet);
        });
        return answerSheets;
    }
}
