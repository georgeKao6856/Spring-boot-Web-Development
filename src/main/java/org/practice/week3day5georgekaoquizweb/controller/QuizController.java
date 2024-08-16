package org.practice.week3day5georgekaoquizweb.controller;

import jakarta.servlet.http.HttpSession;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuiz;
import org.practice.week3day5georgekaoquizweb.domain.Question;
import org.practice.week3day5georgekaoquizweb.domain.QuestionOption;
import org.practice.week3day5georgekaoquizweb.domain.User;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizHibernate;
import org.practice.week3day5georgekaoquizweb.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public String startQuiz(@RequestParam int quiz_id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        HistoryQuizHibernate ongoingQuiz = quizService.getOngoingHistoryQuiz_hibernate(user.getUser_id());

        if(ongoingQuiz != null){
            Map<Question, List<QuestionOption>> map =  quizService.getHistoryQuizQuestion(ongoingQuiz.getHistoryquiz_id());
            model.addAttribute("questions", map);
            model.addAttribute("OngoingQuiz", "True");
            model.addAttribute("quiz_name", ongoingQuiz.getQuiz().getQuiz_name());
        }else{
            Map<Question, List<QuestionOption>> map =  quizService.startQuiz(quiz_id);
            //System.out.println(map.size());
            model.addAttribute("questions", map);

            if(user != null){
                HistoryQuiz hq = quizService.insertAndGetHistoryQuiz(user.getUser_id(), quiz_id, true);
                if(hq != null){
                    for(Question questionJdbc : map.keySet()){
                        quizService.insertHistoryQuestion(hq.getHistoryquiz_id(), questionJdbc.getQuestion_id(), null);
                    }
                }
            }

            model.addAttribute("quiz_name", quizService.getQuizByQuizId(quiz_id).getQuiz_name());
        }

        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuiz(HttpSession session, @RequestParam Map<String, String> allParams, Model model){
        Map<String, String> selectedOptions = allParams.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("question"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        for (Map.Entry<String, String> entry : selectedOptions.entrySet()) {
//            System.out.println("Question ID: " + entry.getKey() + " / Selected Option: " + entry.getValue());
//        }

        User user = (User) session.getAttribute("user");
        HistoryQuizHibernate ongoingQuiz = quizService.getOngoingHistoryQuiz_hibernate(user.getUser_id());
        if(ongoingQuiz != null){
            Map<Question, List<QuestionOption>> map =  quizService.getHistoryQuizQuestion(ongoingQuiz.getHistoryquiz_id());
            ArrayList<String[]> checkedSelectedResult = new ArrayList<>();
            for(Question question : map.keySet()){
                String[] arr = new String[3];
                arr[0] = String.valueOf(question.getQuestion_id());
                arr[1] = question.getAnswer();
                arr[2] = null;
                checkedSelectedResult.add(arr);
            }

            for (Map.Entry<String, String> entry : selectedOptions.entrySet()) {
                String fullQuestionId = entry.getKey();
                String pattern = "question";
                int patternIndex = fullQuestionId.indexOf(pattern);
                int startIndex = patternIndex + pattern.length();
                String question_id = fullQuestionId.substring(startIndex);

                for(String[] arr : checkedSelectedResult){
                    if(question_id.equals(arr[0])){
                        arr[2] = entry.getValue();
                        quizService.updateHistoryQuestionAnswer(ongoingQuiz.getHistoryquiz_id(), Integer.parseInt(arr[0]), arr[2]);
                    }
                }
            }

            int corrects = 0;
            for(String[] arr : checkedSelectedResult){
                if(arr[1].equals(arr[2])){
                    corrects += 1;
                }
            }

            int score = corrects*20;
            model.addAttribute("score", score);
            model.addAttribute("checkedSelectedResult", checkedSelectedResult);
            model.addAttribute("quiz_name", ongoingQuiz.getQuiz().getQuiz_name());
            model.addAttribute("questions", map);
            model.addAttribute("pass", score>=60 ? "Pass":"Fail");
            model.addAttribute("start_time", ongoingQuiz.getQuiz_date());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String end_time = dtf.format(LocalDateTime.now());
            model.addAttribute("end_time", end_time);
            quizService.updateOngoingHistoryQuiz(ongoingQuiz.getHistoryquiz_id(), end_time, score);
        }

        return "quizresult";
    }
}
