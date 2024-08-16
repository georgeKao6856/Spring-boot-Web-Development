package org.practice.week3day5georgekaoquizweb.dao;

import org.practice.week3day5georgekaoquizweb.domain.*;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuestionHibernate;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizHibernate;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.QuizHibernate;

import java.util.*;

public interface QuizDao {
    List<Quiz> getAllQuiz();

    Quiz getQuizByQuizId(int quiz_id);

    List<Question> getAllQuestionsByCategoryId(int category_id);

    List<Question> getAllQuestions();

    List<QuestionOption> getQuestionOptionByQuestionId(int question_id);

    List<HistoryQuizJoin> getAllHistoryQuizByUserId(int user_id);

    Map<Question, List<QuestionOption>> startQuiz(int quiz_id);

    HistoryQuiz insertAndGetHistoryQuiz(int user_id, int quiz_id, boolean ongoing);

    HistoryQuiz getHistoryQuizById(int id);

    void insertHistoryQuestion(int historyquiz_id, int question_id, String user_answer);

    HistoryQuiz getOngoingHistoryQuiz(int user_id);

    HistoryQuizHibernate getOngoingHistoryQuiz_hibernate(int user_id);

    Map<Question, List<QuestionOption>> getHistoryQuizQuestion(int historyquiz_id);

    void updateHistoryQuestionAnswer(int historyquiz_id, int question_id, String user_answer);

    void updateOngoingHistoryQuiz(int historyquiz_id, String end_time, int score);

    List<HistoryQuestion> getHistroyQuestionById(int historyquiz_id);

    List<HistoryQuestionHibernate> getHistroyQuestionById_hibernate(int historyquiz_id);

    void updateQuestionStatus(int question_id);

    Question getQuestionById(int question_id);

    void insertQuestionAndOptions(String subject, String description, int category_id, String answer, String option1, String option2, String option3, String option4);

    void updateQuestionAndOptions(int question_id, String subject, String description, int category_id, String answer,
                                         int option1_id, String option1, int option2_id, String option2, int option3_id, String option3, int option4_id, String option4);

    List<AdminHistoryQuizResult> getAllHistoryQuizResult(String orderBy);

    List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByCategory(String orderBy, String category);

    List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByUsername(String orderBy, String username);

    HistoryQuizHibernate getHistoryQuizById_hibernate(int id);

    QuizHibernate getQuizByQuizId_hibernate(int quizId);
}
