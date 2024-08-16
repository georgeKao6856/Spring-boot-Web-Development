package org.practice.week3day5georgekaoquizweb.controller;

import jakarta.servlet.http.HttpSession;
import org.practice.week3day5georgekaoquizweb.domain.Category;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuizJoin;
import org.practice.week3day5georgekaoquizweb.domain.Quiz;
import org.practice.week3day5georgekaoquizweb.domain.User;
import org.practice.week3day5georgekaoquizweb.service.CategoryService;
import org.practice.week3day5georgekaoquizweb.service.QuizService;
import org.practice.week3day5georgekaoquizweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final QuizService quizService;
    private final UserService userService;

    public HomeController(CategoryService categoryService, QuizService quizService, UserService userService) {
        this.categoryService = categoryService;
        this.quizService = quizService;
        this.userService = userService;
    }



    @GetMapping("/home")
    public String quiz(HttpSession session, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Quiz> quizzes = quizService.getAllQuiz();

        model.addAttribute("quizzes", quizzes);


        User user = (User) session.getAttribute("user");
        if(user != null){
            List<HistoryQuizJoin> historyQuizsJoin = quizService.getAllHistoryQuizByUserId(user.getUser_id());
            if(!historyQuizsJoin.isEmpty()){
                model.addAttribute("historyQuizzes", historyQuizsJoin);
            }

            if(user.isAdmin()){
                model.addAttribute("isAdmin", true);
            }
        }

        return "home";
    }
}
