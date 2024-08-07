package org.beaconfire.week3day5georgekaoquizweb.controller;

import jakarta.servlet.http.HttpSession;
import org.beaconfire.week3day5georgekaoquizweb.domain.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.*;
import org.beaconfire.week3day5georgekaoquizweb.service.ContactUsService;
import org.beaconfire.week3day5georgekaoquizweb.service.QuizService;
import org.beaconfire.week3day5georgekaoquizweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AdminController {

    private final UserService userService;
    private final QuizService quizService;
    private final ContactUsService contactUsService;

    Map<Integer, String> categoryMap = new HashMap<Integer, String>();


    public AdminController(UserService userService, QuizService quizService, ContactUsService contactUsService) {
        this.userService = userService;
        this.quizService = quizService;
        this.contactUsService = contactUsService;
        categoryMap.put(1, "OSINT"); categoryMap.put(2, "Password Cracking"); categoryMap.put(3, "Cryptographic");
    }

    @GetMapping("/admin_home")
    public String adminHome(Model model) {
        model.addAttribute("isAdmin", true);
        return "adminHome";
    }

    @GetMapping("/admin_user_management")
    public String adminUserManagement(HttpSession session, Model model) {
        model.addAttribute("isAdmin", true);

        List<User> userJdbcs = userService.getAllUsers();
        model.addAttribute("users", userJdbcs);

        return "adminUserManagement";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam int user_id, Model model) {
        userService.updateUserStatus(user_id);
        return "redirect:/admin_user_management";
    }

    @GetMapping("/admin_quiz_result_management")
    public String adminQuizResultManagement(@RequestParam String orderBy, @RequestParam String filterUsername, @RequestParam String filterCategory, Model model) {
        model.addAttribute("isAdmin", true);
        model.addAttribute("filterUsernameSubmit", filterUsername);
        model.addAttribute("filterCategorySubmit", filterCategory);

        List<AdminHistoryQuizResult> adminHistoryQuizResultJdbcs = null;

        if(!filterUsername.equals("All") && filterCategory.equals("All")){
            adminHistoryQuizResultJdbcs = quizService.getAllHistoryQuizResultFilterByUsername(orderBy, filterUsername);
        }else if(filterUsername.equals("All") && !filterCategory.equals("All")){
            adminHistoryQuizResultJdbcs = quizService.getAllHistoryQuizResultFilterByCategory(orderBy, filterCategory);
        }else{
            adminHistoryQuizResultJdbcs = quizService.getAllHistoryQuizResult(orderBy);
        }


        model.addAttribute("adminHistoryQuizResults", adminHistoryQuizResultJdbcs);

        Set<String> usernameSet = new HashSet<>();
        for (AdminHistoryQuizResult adminHistoryQuizResultJdbc : adminHistoryQuizResultJdbcs) {
            usernameSet.add(adminHistoryQuizResultJdbc.getUsername());
        }

        model.addAttribute("usernames", usernameSet);

        String order = "";
        if(orderBy.equals("historyquiz_id")){
            order = "ID";
        }else if(orderBy.equals("quiz_name")){
            order = "Quiz Name";
        }else if (orderBy.equals("category_name")){
            order = "Category";
        }else if(orderBy.equals("lastname")){
            order = "User";
        }else if(orderBy.equals("quiz_end_time")){
            order = "Date";
        }else if(orderBy.equals("score")){
            order = "Score";
        }else if(orderBy.equals("username")){
            order = "Username";
        }else{
            order = "Date";
        }
        model.addAttribute("sortBy", order);
        model.addAttribute("categoryShow", "All");

        return "adminQuizResultManagement";
    }

    @GetMapping("/admin_contact_us")
    public String adminContactUs(Model model) {
        model.addAttribute("isAdmin", true);
        List<ContactUs> contactUsJdbcAll = contactUsService.getAllContactUs();
        model.addAttribute("contactUsAll", contactUsJdbcAll);

        return "adminContactUs";
    }

    @GetMapping("/admin_question_management")
    public String adminQuestionManagement(Model model) {
        model.addAttribute("isAdmin", true);

        List<Question> questionsList = quizService.getAllQuestions();
        model.addAttribute("questionsList", questionsList);
        model.addAttribute("categoryShow", "All");

        return "adminQuestionManagement";
    }

    @PostMapping("/getQuestionListByCategory")
    public String getQuestionListByCategory(@RequestParam String category, Model model) {
        model.addAttribute("isAdmin", true);

        int categoryId = 0;
        for(Map.Entry<Integer, String> entry : categoryMap.entrySet()){
            if(category.contains(entry.getValue())){
                categoryId = entry.getKey();
            }
        }

        //System.out.println(category);
        if(category.equals("All") || categoryId == 0){
            return "redirect:/admin_question_management";
        }

        model.addAttribute("categoryShow", category);
        List<Question> questionsList = quizService.getAllQuestions();
        List<Question> filteredQuestionsList = new ArrayList<>();
        for(Question questionJdbc : questionsList){
            if(questionJdbc.getCategory_id() == categoryId){
                filteredQuestionsList.add(questionJdbc);
            }
        }
        model.addAttribute("questionsList", filteredQuestionsList);
        model.addAttribute("categoryShow", categoryMap.get(categoryId));

        return "adminQuestionManagement";
    }

    @GetMapping("/editQuestion")
    public String editQuestion(@RequestParam int questionID, Model model) {
        model.addAttribute("isAdmin", true);

        Question questionJdbc = quizService.getQuestionById(questionID);
        List<QuestionOption> questionOptionJdbcs = quizService.getQuestionOptionByQuestionId(questionID);

        model.addAttribute("question", questionJdbc);
        model.addAttribute("questionOptions", questionOptionJdbcs);
        model.addAttribute("edit", true);

        return "addAndEditQuestion";
    }

    @PostMapping("/editQuestion")
    public String editQuestionContent( @RequestParam int questionID,
                                       @RequestParam("subject") String subject,
                                       @RequestParam("description") String description,
                                       @RequestParam("category") String category,
                                       @RequestParam("attribute") int attribute,
                                       @RequestParam("option1") String option1,
                                       @RequestParam("option1_id") int option1_id,
                                       @RequestParam("option2") String option2,
                                       @RequestParam("option2_id") int option2_id,
                                       @RequestParam("option3") String option3,
                                       @RequestParam("option3_id") int option3_id,
                                       @RequestParam("option4") String option4,
                                       @RequestParam("option4_id") int option4_id,
                                       Model model) {

        int category_id = 0;
        for(Map.Entry<Integer, String> entry : categoryMap.entrySet()){
            if(entry.getValue().equals(category)){
                category_id = entry.getKey();
            }
        }

        String answer = "";
        if(attribute == 1){
            answer = option1;
        }else if(attribute == 2){
            answer = option2;
        }else if(attribute == 3){
            answer = option3;
        }else if(attribute == 4){
            answer = option4;
        }

        System.out.println(category_id);

        quizService.updateQuestionAndOptions(questionID, subject, description, category_id, answer, option1_id, option1, option2_id, option2, option3_id, option3, option4_id, option4);

        return "redirect:/admin_question_management";
    }

    @GetMapping("/addQuestion")
    public String addQuestion(Model model) {
        model.addAttribute("isAdmin", true);
        model.addAttribute("edit", false);

        return "addAndEditQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestionToDB( @RequestParam("subject") String subject,
                                   @RequestParam("description") String description,
                                   @RequestParam("category") String category,
                                   @RequestParam("attribute") int attribute,
                                   @RequestParam("option1") String option1,
                                   @RequestParam("option2") String option2,
                                   @RequestParam("option3") String option3,
                                   @RequestParam("option4") String option4,
                                   Model model) {

        int category_id = 0;
        for(Map.Entry<Integer, String> entry : categoryMap.entrySet()){
            if(entry.getValue().equals(category)){
                category_id = entry.getKey();
            }
        }

        String answer = "";
        if(attribute == 1){
            answer = option1;
        }else if(attribute == 2){
            answer = option2;
        }else if(attribute == 3){
            answer = option3;
        }else if(attribute == 4){
            answer = option4;
        }

        quizService.insertQuestionAndOptions(subject, description, category_id, answer, option1, option2, option3, option4);

        return "redirect:/admin_question_management";
    }

    @PostMapping("/updateQuestionStatus")
    public String updateQuestionStatus(@RequestParam int questionID, Model model) {
        model.addAttribute("isAdmin", true);

        quizService.updateQuestionStatus(questionID);

        return "redirect:/admin_question_management";
    }
}
