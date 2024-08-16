package org.practice.week3day5georgekaoquizweb.controller;

import org.practice.week3day5georgekaoquizweb.domain.Question;
import org.practice.week3day5georgekaoquizweb.domain.QuestionOption;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuestionHibernate;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizHibernate;
import org.practice.week3day5georgekaoquizweb.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz-result")
public class QuizResultController {
    private final QuizService quizService;

    public QuizResultController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{historyquiz_id}")
    public String getQuizResult(@PathVariable("historyquiz_id") int historyquiz_id, Model model) {
        HistoryQuizHibernate historyQuiz = quizService.getHistoryQuizById_hibernate(historyquiz_id);
        System.out.println("KK: "+historyQuiz.toString());
        Map<Question, List<QuestionOption>> historyQuizQuestion = quizService.getHistoryQuizQuestion(historyquiz_id);

        ArrayList<String[]> checkedSelectedResult = new ArrayList<String[]>();
        for(Question question : historyQuizQuestion.keySet()){
            String[] arr = new String[3];
            arr[0] = String.valueOf(question.getQuestion_id());
            arr[1] = question.getAnswer();
            arr[2] = null;
            checkedSelectedResult.add(arr);
        }

        List<HistoryQuestionHibernate> historyQuestions = quizService.getHistroyQuestionById_hibernate(historyquiz_id);

        for(String[] arr : checkedSelectedResult){
            for(HistoryQuestionHibernate hq : historyQuestions){
                if(hq.getQuestion().getQuestion_id() == Integer.parseInt(arr[0])){
                    arr[2] = hq.getUser_answer();
                }
            }
        }

        int corrects = 0;
        for(String[] arr : checkedSelectedResult){
            System.out.println(Arrays.asList(arr));
            if(arr[1].equals(arr[2])){
                corrects += 1;
            }
        }

        System.out.println("GG: "+historyQuiz.getQuiz_id());

        int score = corrects*20;
        model.addAttribute("score", score);
        model.addAttribute("checkedSelectedResult", checkedSelectedResult);
        model.addAttribute("quiz_name", historyQuiz.getQuiz().getQuiz_name());
        model.addAttribute("questions", historyQuizQuestion);
        model.addAttribute("pass", score>=60 ? "Pass":"Fail");
        model.addAttribute("start_time", historyQuiz.getQuiz_date());
        model.addAttribute("end_time", historyQuiz.getQuiz_end_time());

        return "quizresult";
    }
}
