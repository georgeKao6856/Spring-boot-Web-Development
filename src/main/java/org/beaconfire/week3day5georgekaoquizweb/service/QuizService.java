package org.beaconfire.week3day5georgekaoquizweb.service;

import org.beaconfire.week3day5georgekaoquizweb.dao.QuizDao;
import org.beaconfire.week3day5georgekaoquizweb.dao.UserDao;
import org.beaconfire.week3day5georgekaoquizweb.dao.jdbc.QuizDaoJdbcImpl;
import org.beaconfire.week3day5georgekaoquizweb.domain.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.HistoryQuestionHibernate;
import org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizHibernate;
import org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.QuizHibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private QuizDao quizJdbcDao;
    private QuizDao quizHibernateDao;

    public QuizService(@Qualifier("quizDaoJdbcImpl") QuizDao quizJdbcDao, @Qualifier("QuizDaoHibernateImpl") QuizDao quizHibernateDao) {
        this.quizJdbcDao = quizJdbcDao;
        this.quizHibernateDao = quizHibernateDao;
    }

    public List<Quiz> getAllQuiz() {
        return quizHibernateDao.getAllQuiz();
    }

    public List<HistoryQuizJoin> getAllHistoryQuizByUserId(int user_id){
        return quizHibernateDao.getAllHistoryQuizByUserId(user_id);
    }

    public Map<Question, List<QuestionOption>> startQuiz(int quiz_name){
        return quizHibernateDao.startQuiz(quiz_name);
    }

    public HistoryQuiz insertAndGetHistoryQuiz(int user_id, int quiz_id, boolean ongoing) {
        return quizHibernateDao.insertAndGetHistoryQuiz(user_id, quiz_id, ongoing);
    }

    public void insertHistoryQuestion(int historyquiz_id, int question_id, String user_answer) {
        quizHibernateDao.insertHistoryQuestion(historyquiz_id, question_id, user_answer);
    }

    public HistoryQuiz getOngoingHistoryQuiz(int user_id) {
        return quizJdbcDao.getOngoingHistoryQuiz(user_id);
    }

    public HistoryQuizHibernate getOngoingHistoryQuiz_hibernate(int user_id) {
        return quizHibernateDao.getOngoingHistoryQuiz_hibernate(user_id);
    }

    public Map<Question, List<QuestionOption>> getHistoryQuizQuestion(int historyquiz_id) {
        return quizHibernateDao.getHistoryQuizQuestion(historyquiz_id);
    }

    public Quiz getQuizByQuizId(int quiz_id) {
        return quizJdbcDao.getQuizByQuizId(quiz_id);
    }

    public QuizHibernate getQuizByQuizId_hibernate(int quiz_id) {
        return quizHibernateDao.getQuizByQuizId_hibernate(quiz_id);
    }

    public void updateHistoryQuestionAnswer(int historyquiz_id, int question_id, String user_answer){
        quizHibernateDao.updateHistoryQuestionAnswer(historyquiz_id, question_id, user_answer);
    }

    public void updateOngoingHistoryQuiz(int historyquiz_id, String end_time, int score){
        quizHibernateDao.updateOngoingHistoryQuiz(historyquiz_id, end_time, score);
    }

    public HistoryQuiz getHistoryQuizById(int id) {
        return quizJdbcDao.getHistoryQuizById(id);
    }

    public HistoryQuizHibernate getHistoryQuizById_hibernate(int id) {
        return quizHibernateDao.getHistoryQuizById_hibernate(id);
    }

    public List<HistoryQuestion> getHistroyQuestionById(int historyquiz_id) {
        return quizJdbcDao.getHistroyQuestionById(historyquiz_id);
    }

    public List<HistoryQuestionHibernate> getHistroyQuestionById_hibernate(int historyquiz_id) {
        return quizHibernateDao.getHistroyQuestionById_hibernate(historyquiz_id);
    }

    public List<Question> getAllQuestions() {
        return quizHibernateDao.getAllQuestions();
    }

    public void updateQuestionStatus(int question_id){
        quizHibernateDao.updateQuestionStatus(question_id);
    }

    public void insertQuestionAndOptions(String subject, String description, int category_id, String answer, String option1, String option2, String option3, String option4){
        quizHibernateDao.insertQuestionAndOptions(subject, description, category_id, answer, option1, option2, option3, option4);
    }

    public Question getQuestionById(int question_id) {
        return quizHibernateDao.getQuestionById(question_id);
    }

    public List<QuestionOption> getQuestionOptionByQuestionId(int question_id) {
        return quizHibernateDao.getQuestionOptionByQuestionId(question_id);
    }

    public void updateQuestionAndOptions(int question_id, String subject, String description, int category_id, String answer,
                                         int option1_id, String option1, int option2_id, String option2, int option3_id, String option3, int option4_id, String option4){
        quizHibernateDao.updateQuestionAndOptions(question_id, subject, description, category_id, answer, option1_id, option1, option2_id, option2, option3_id, option3, option4_id, option4);
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResult(String orderBy){
        return quizHibernateDao.getAllHistoryQuizResult(orderBy);
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByCategory(String orderBy, String category){
        return quizHibernateDao.getAllHistoryQuizResultFilterByCategory(orderBy, category);
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByUsername(String orderBy, String username){
        return quizHibernateDao.getAllHistoryQuizResultFilterByUsername(orderBy, username);
    }
}
