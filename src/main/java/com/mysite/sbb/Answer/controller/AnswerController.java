package com.mysite.sbb.Answer.controller;

import com.mysite.sbb.Answer.answer.AnswerService;
import com.mysite.sbb.Answer.dao.AnswerRepository;
import com.mysite.sbb.Answer.domain.Answer;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.Question.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

   @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
       Question question = this.questionService.getQuestion(id);
       // 질문만들기
       this.answerService.create(question, content);
       return String.format("redirect:/question/detail/%s", id);
    }

    @PostMapping("/like/{questionId}/{answerId}")
    public String createAnswer(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId) {
        this.answerService.setLike(answerId);

        return String.format("redirect:/question/detail/%s", questionId);
    }

}
