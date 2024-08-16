package org.practice.week3day5georgekaoquizweb.dao.jdbc;

import org.practice.week3day5georgekaoquizweb.dao.QuizDao;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuestionHibernate;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizHibernate;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.QuizHibernate;
import org.practice.week3day5georgekaoquizweb.dao.rowmapper.*;
import org.practice.week3day5georgekaoquizweb.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Component
public class QuizDaoJdbcImpl implements QuizDao {
    JdbcTemplate jdbcTemplate;
    QuizRowMapper quizRowMapper;
    HistoryQuizRowMapper historyQuizRowMapper;
    HistoryQuizJoinRowMapper historyQuizJoinRowMapper;
    QuestionRowMapper questionRowMapper;
    QuestionOptionRowMapper questionOptionRowMapper;
    HistoryQuestionRowMapper historyQuestionRowMapper;
    AdminHistoryQuizResultRowMapper adminHistoryQuizResultRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuizDaoJdbcImpl(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           HistoryQuizJoinRowMapper historyQuizJoinRowMapper, HistoryQuizRowMapper historyQuizRowMapper,
                           QuestionRowMapper questionRowMapper, QuestionOptionRowMapper questionOptionRowMapper,
                           HistoryQuestionRowMapper historyQuestionRowMapper, AdminHistoryQuizResultRowMapper adminHistoryQuizResultRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.historyQuizJoinRowMapper = historyQuizJoinRowMapper;
        this.questionRowMapper = questionRowMapper;
        this.questionOptionRowMapper = questionOptionRowMapper;
        this.historyQuizRowMapper = historyQuizRowMapper;
        this.historyQuestionRowMapper = historyQuestionRowMapper;
        this.adminHistoryQuizResultRowMapper = adminHistoryQuizResultRowMapper;
    }

    public List<Quiz> getAllQuiz() {
        String sql = "SELECT * FROM quiz";
        return jdbcTemplate.query(sql, quizRowMapper);
    }

    public Quiz getQuizByQuizId(int quiz_id) {
        String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
        return jdbcTemplate.queryForObject(sql, quizRowMapper, quiz_id);
    }

    public List<Question> getAllQuestionsByCategoryId(int category_id) {
        String sql = "SELECT * FROM Question WHERE category_id = ? and is_active = ?";
        return jdbcTemplate.query(sql, questionRowMapper, category_id, true);
    }

    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM Question";
        return jdbcTemplate.query(sql, questionRowMapper);
    }

    public List<QuestionOption> getQuestionOptionByQuestionId(int question_id) {
        String sql = "SELECT * FROM QuestionOption WHERE question_id = ?";
        return jdbcTemplate.query(sql, questionOptionRowMapper, question_id);
    }

    public List<HistoryQuizJoin> getAllHistoryQuizByUserId(int user_id) {
        String sql = "SELECT historyquiz_id, user_id, hq.quiz_id, quiz_name, quiz_date FROM historyQuiz hq inner join Quiz q on hq.quiz_id = q.quiz_id where user_id = ? and ongoing = 0";
        try{
            List<HistoryQuizJoin> historyQuizJoinJdbcs = jdbcTemplate.query(sql, historyQuizJoinRowMapper, user_id);
            return historyQuizJoinJdbcs;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public Map<Question, List<QuestionOption>> startQuiz(int quiz_id){
        Quiz quizJdbc = getQuizByQuizId(quiz_id);
        List<Question> questionJdbcs = getAllQuestionsByCategoryId(quizJdbc.getCategory_id());
        List<Question> randQuestionJdbcs = getRandomElements(questionJdbcs, 5);

        //randQuestions.forEach(question -> System.out.println(question));

        Map<Question, List<QuestionOption>> map = new HashMap<>();
        for(Question questionJdbc : randQuestionJdbcs){
            List<QuestionOption> list = new ArrayList<>();
            list = getQuestionOptionByQuestionId(questionJdbc.getQuestion_id());
            map.put(questionJdbc, list);
        }

        //System.out.println(map.toString());

        return map;
    }

    public static <T> List<T> getRandomElements(List<T> list, int numberOfElements) {
        // Create a copy of the list to avoid modifying the original list
        List<T> copy = new ArrayList<>(list);

        // Shuffle the copy of the list
        Collections.shuffle(copy);

        // Return the first 'numberOfElements' elements from the shuffled list
        return copy.subList(0, numberOfElements);
    }

    public HistoryQuiz insertAndGetHistoryQuiz(int user_id, int quiz_id, boolean ongoing) {
        String sql = "INSERT INTO historyQuiz (user_id, quiz_id, ongoing) Values (?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_id);
            ps.setInt(2, quiz_id);
            ps.setBoolean(3, ongoing);
            return ps;
        }, keyHolder);
        System.out.println("Inserted History Quiz Successfully");

        int generatedId = keyHolder.getKey().intValue();
        return getHistoryQuizById(generatedId);
    }

    public HistoryQuiz getHistoryQuizById(int id) {
        String selectSql = "SELECT * FROM historyQuiz WHERE historyquiz_id = ?";
        return jdbcTemplate.queryForObject(selectSql, historyQuizRowMapper, id);
    }

    public void insertHistoryQuestion(int historyquiz_id, int question_id, String user_answer) {
        String sql = "Insert into historyQuestion (historyquiz_id, question_id, user_answer) Values (?, ?, ?)";
        jdbcTemplate.update(sql, historyquiz_id, question_id, user_answer);
        System.out.println("Insert History Question Successfully");
    }

    public HistoryQuiz getOngoingHistoryQuiz(int user_id) {
        String sql = "SELECT * FROM historyQuiz WHERE ongoing = ? and user_id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, historyQuizRowMapper, true, user_id);
        }catch (Exception e){
            return null;
        }
    }

    public HistoryQuizHibernate getOngoingHistoryQuiz_hibernate(int user_id) {
        return null;
    }

    public Map<Question, List<QuestionOption>> getHistoryQuizQuestion(int historyquiz_id) {
        String sql = "select hq.question_id, question_subject, question_description, category_id, answer, is_active from Question q " +
                "inner join (select * from historyQuestion where historyquiz_id = ?) hq on q.question_id = hq.question_id;";
        List<Question> questionJdbcs = jdbcTemplate.query(sql, questionRowMapper, historyquiz_id);

        Map<Question, List<QuestionOption>> map = new HashMap<>();
        for(Question questionJdbc : questionJdbcs){
            List<QuestionOption> list = new ArrayList<>();
            list = getQuestionOptionByQuestionId(questionJdbc.getQuestion_id());
            map.put(questionJdbc, list);
        }

        return map;
    }

    public void updateHistoryQuestionAnswer(int historyquiz_id, int question_id, String user_answer){
        String sql = "update historyQuestion set user_answer = ? where historyquiz_id = ? and question_id = ?";
        jdbcTemplate.update(sql, user_answer, historyquiz_id, question_id);
        System.out.println("Update Answer Successfully");
    }

    public void updateOngoingHistoryQuiz(int historyquiz_id, String end_time, int score){
        String sql = "update historyQuiz set ongoing = ? , quiz_end_time = ?, score = ? where historyquiz_id = ?";
        jdbcTemplate.update(sql, false, end_time, score, historyquiz_id);
        System.out.println("Update Ongoing History Quiz Successfully");
    }

    public List<HistoryQuestion> getHistroyQuestionById(int historyquiz_id) {
        String sql = "SELECT * FROM historyQuestion WHERE historyquiz_id = ?";
        return jdbcTemplate.query(sql, historyQuestionRowMapper, historyquiz_id);
    }

    @Override
    public List<HistoryQuestionHibernate> getHistroyQuestionById_hibernate(int historyquiz_id) {
        return null;
    }

    public void updateQuestionStatus(int question_id){
        String query = "UPDATE Question SET is_active = ? WHERE question_id = ?";
        Question questionJdbc = getQuestionById(question_id);
        if(questionJdbc.isActive()){
            jdbcTemplate.update(query, false, question_id);
        }else{
            jdbcTemplate.update(query, true, question_id);
        }

        System.out.println("Successfully updated Question Status");
    }

    public Question getQuestionById(int question_id) {
        String sql = "SELECT * FROM question WHERE question_id = ?";
        return jdbcTemplate.queryForObject(sql, questionRowMapper, question_id);
    }

    public void insertQuestionAndOptions(String subject, String description, int category_id, String answer, String option1, String option2, String option3, String option4){
        String sql = "Insert into Question (question_subject, question_description, category_id, answer) Values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subject);
            ps.setString(2, description);
            ps.setInt(3, category_id);
            ps.setString(4, answer);
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        Question questionJdbc = getQuestionById(generatedId);

        String sql_options = "Insert into QuestionOption (question_id, content) values (?, ?)";
        String[] options = new String[4];
        options[0] = option1;
        options[1] = option2;
        options[2] = option3;
        options[3] = option4;
        for(String option :options){
            jdbcTemplate.update(sql_options, questionJdbc.getQuestion_id(), option);
        }

        System.out.println("Successfully Inserted Question and its Options");
    }

    public void updateQuestionAndOptions(int question_id, String subject, String description, int category_id, String answer,
                                         int option1_id, String option1, int option2_id, String option2, int option3_id, String option3, int option4_id, String option4){
        String sql = "Update Question set question_subject=?, question_description=?, category_id=?, answer=? where question_id = ?";
        jdbcTemplate.update(sql, subject, description, category_id, answer, question_id);

        String sql_options = "Update QuestionOption set content=? where option_id = ? and question_id=?";

        Map<Integer, String> optionsMap = new LinkedHashMap<>();
        optionsMap.put(option1_id, option1);
        optionsMap.put(option2_id, option2);
        optionsMap.put(option3_id, option3);
        optionsMap.put(option4_id, option4);

        for(Map.Entry<Integer, String> entry : optionsMap.entrySet()){
            jdbcTemplate.update(sql_options, entry.getValue(), entry.getKey(), question_id);
        }

        System.out.println("Successfully Updated Question and its Options");
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResult(String orderBy){
        List<String> validOrderByColumns = Arrays.asList("historyquiz_id", "user_id", "firstname", "lastname", "quiz_id", "quiz_name", "category_id", "category_name", "quiz_date", "quiz_end_time", "score", "username");

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        String sql = "SELECT hq.historyquiz_id, hq.user_id, u.username, u.firstname, u.lastname, hq.quiz_id, q.quiz_name, c.category_id, c.category_name, hq.quiz_date, hq.quiz_end_time, hq.score " +
                "FROM historyQuiz hq JOIN Users u ON hq.user_id = u.user_id JOIN Quiz q ON hq.quiz_id = q.quiz_id JOIN Category c ON q.category_id = c.category_id " +
                "Order by "+ orderBy + " desc";

        //System.out.println(sql);

        return jdbcTemplate.query(sql, adminHistoryQuizResultRowMapper);
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByCategory(String orderBy, String category){
        List<String> validOrderByColumns = Arrays.asList("historyquiz_id", "user_id", "firstname", "lastname", "quiz_id", "quiz_name", "category_id", "category_name", "quiz_date", "quiz_end_time", "score", "username");

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        String sql = "SELECT hq.historyquiz_id, hq.user_id, u.username, u.firstname, u.lastname, hq.quiz_id, q.quiz_name, c.category_id, c.category_name, hq.quiz_date, hq.quiz_end_time, hq.score " +
                "FROM historyQuiz hq JOIN Users u ON hq.user_id = u.user_id JOIN Quiz q ON hq.quiz_id = q.quiz_id JOIN Category c ON q.category_id = c.category_id " + "where category_name = ? " +
                "Order by "+ orderBy + " desc";

        System.out.println(sql);

        return jdbcTemplate.query(sql, adminHistoryQuizResultRowMapper, category);
    }

    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByUsername(String orderBy, String username){
        List<String> validOrderByColumns = Arrays.asList("historyquiz_id", "user_id", "firstname", "lastname", "quiz_id", "quiz_name", "category_id", "category_name", "quiz_date", "quiz_end_time", "score", "username");

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        String sql = "SELECT hq.historyquiz_id, hq.user_id, u.username, u.firstname, u.lastname, hq.quiz_id, q.quiz_name, c.category_id, c.category_name, hq.quiz_date, hq.quiz_end_time, hq.score " +
                "FROM historyQuiz hq JOIN Users u ON hq.user_id = u.user_id JOIN Quiz q ON hq.quiz_id = q.quiz_id JOIN Category c ON q.category_id = c.category_id " + "where username = ? " +
                "Order by "+ orderBy + " desc";

        System.out.println(sql);

        return jdbcTemplate.query(sql, adminHistoryQuizResultRowMapper, username);
    }

    public HistoryQuizHibernate getHistoryQuizById_hibernate(int id) {
        return null;
    }

    public QuizHibernate getQuizByQuizId_hibernate(int quizId) {
        return null;
    }
}
