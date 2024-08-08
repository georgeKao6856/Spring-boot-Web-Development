package org.beaconfire.week3day5georgekaoquizweb.controller;

import jakarta.validation.Valid;
import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuiz;
import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuizJoin;
import org.beaconfire.week3day5georgekaoquizweb.domain.Restful.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.Restful.ResponseStatus;
import org.beaconfire.week3day5georgekaoquizweb.domain.User;
import org.beaconfire.week3day5georgekaoquizweb.service.QuizService;
import org.beaconfire.week3day5georgekaoquizweb.service.RegisterService;
import org.beaconfire.week3day5georgekaoquizweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rs_user")
public class RestUserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RegisterService registerService;

    @Autowired
    private final QuizService quizService;

    public RestUserController(UserService userService, RegisterService registerService, QuizService quizService) {
        this.userService = userService;
        this.registerService = registerService;
        this.quizService = quizService;
    }

    @GetMapping()
    public AllUsersResponse getAllUsers() {
        List<User> users = userService.getAllUsersJdbc();

        return AllUsersResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Returning all users")
                                .build()
                )
                .users(users)
                .build();
    }

    @GetMapping(params = "userId")
    public UserResponse getUserById(@RequestParam int userId) {
        User user = userService.getUserByUserId(userId);

        return UserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("get the user")
                                .build()
                )
                .user(user)
                .build();
    }

    @PostMapping()
    public UserResponse createNewUser(
            @Valid @RequestBody UserReqest user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> System.out.println(
                    "ValidationError in " + error.getObjectName() + ": " + error.getDefaultMessage()));
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Validation error")
                                    .build()
                    )
                    .build();
        }

        registerService.register(user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPasswd());
        User newUser = userService.getUserByEmailJdbc(user.getEmail());

        return UserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("New user created")
                                .build()
                )
                .user(newUser)
                .build();
    }

    @DeleteMapping()
    public UserResponse deleteUser(@RequestParam String email){
        try{
            User deleteUser = userService.getUserByEmailJdbc(email);

            userService.deleteUserByEmailJdbc(email);

            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Deleted user")
                                    .build()
                    )
                    .user(deleteUser)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Deletion failed")
                                    .build()
                    )
                    .build();
        }
    }

    @PatchMapping("/{userId}/status")
    public UserResponse updateUser(@PathVariable int userId){
        try{
            userService.updateUserStatus(userId);
            User user = userService.getUserByUserIdJdbc(userId);

            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Updated user")
                                    .build()
                    )
                    .user(user)
                    .build();

        }catch (Exception e){
            e.printStackTrace();
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Update failed")
                                    .build()
                    )
                    .build();
        }
    }

    @GetMapping("/quiz")
    public HistoryQuizJoinResponse getHistoryQuiz(@RequestParam int userId) {
        List<HistoryQuizJoin> historyQuizs = quizService.getAllHistoryQuizByUserId(userId);

        return HistoryQuizJoinResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Get Histroy Result")
                                .build()
                )
                .historyQuizJoinList(historyQuizs)
                .build();
    }


}
