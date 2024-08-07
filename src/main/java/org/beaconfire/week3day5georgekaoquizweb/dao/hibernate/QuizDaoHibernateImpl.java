package org.beaconfire.week3day5georgekaoquizweb.dao.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.beaconfire.week3day5georgekaoquizweb.dao.QuizDao;
import org.beaconfire.week3day5georgekaoquizweb.dao.UserDao;
import org.beaconfire.week3day5georgekaoquizweb.domain.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Repository("QuizDaoHibernateImpl")
public class QuizDaoHibernateImpl implements QuizDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected UserDaoHibernateImpl userDao;

    @Transactional
    public List<Quiz> getAllQuiz(){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuizHibernate> criteria = builder.createQuery(QuizHibernate.class);
        criteria.from(QuizHibernate.class);
        List<QuizHibernate> quizzes = session.createQuery(criteria).getResultList();

        return quizzes.stream().map(quiz -> (Quiz)quiz).collect(Collectors.toList());
    }

    @Transactional
    public Quiz getQuizByQuizId(int quiz_id){

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuizHibernate> criteriaQuery = criteriaBuilder.createQuery(QuizHibernate.class);
        Root<QuizHibernate> root = criteriaQuery.from(QuizHibernate.class);

        Predicate quizIdPredicate = criteriaBuilder.equal(root.get("quiz_id"), quiz_id);
        criteriaQuery.where(quizIdPredicate);

        return session.createQuery(criteriaQuery).uniqueResult();

    }

    @Transactional
    public QuizHibernate getQuizByQuizId_hibernate(int quiz_id){

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuizHibernate> criteriaQuery = criteriaBuilder.createQuery(QuizHibernate.class);
        Root<QuizHibernate> root = criteriaQuery.from(QuizHibernate.class);

        Predicate quizIdPredicate = criteriaBuilder.equal(root.get("quiz_id"), quiz_id);
        criteriaQuery.where(quizIdPredicate);

        return session.createQuery(criteriaQuery).uniqueResult();

    }

    @Transactional
    public List<Question> getAllQuestionsByCategoryId(int category_id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernate> criteriaQuery = criteriaBuilder.createQuery(QuestionHibernate.class);
        Root<QuestionHibernate> root = criteriaQuery.from(QuestionHibernate.class);

        Predicate quizIdPredicate = criteriaBuilder.equal(root.get("category_id"), category_id);
        criteriaQuery.where(quizIdPredicate);

        List<QuestionHibernate> questions = session.createQuery(criteriaQuery).getResultList();

        return questions.stream().map(question -> (Question)question).collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionHibernate> getAllQuestionsByCategoryId_hibernate(int category_id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernate> criteriaQuery = criteriaBuilder.createQuery(QuestionHibernate.class);
        Root<QuestionHibernate> root = criteriaQuery.from(QuestionHibernate.class);

        Predicate quizIdPredicate = criteriaBuilder.equal(root.get("category_id"), category_id);
        criteriaQuery.where(quizIdPredicate);

        List<QuestionHibernate> questions = session.createQuery(criteriaQuery).getResultList();

        return questions;
    }

    @Transactional
    public List<Question> getAllQuestions(){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernate> criteriaQuery = criteriaBuilder.createQuery(QuestionHibernate.class);
        criteriaQuery.from(QuestionHibernate.class);

        List<QuestionHibernate> questions = session.createQuery(criteriaQuery).getResultList();

        return questions.stream().map(question -> (Question)question).collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionOption> getQuestionOptionByQuestionId(int question_id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionOptionHibernate> criteriaQuery = criteriaBuilder.createQuery(QuestionOptionHibernate.class);
        Root<QuestionOptionHibernate> root = criteriaQuery.from(QuestionOptionHibernate.class);

        Predicate questionIdPredicate = criteriaBuilder.equal(root.get("question"), getQuestionById(question_id));
        criteriaQuery.where(questionIdPredicate);
        List<QuestionOptionHibernate> questionOptions = session.createQuery(criteriaQuery).getResultList();


        return questionOptions.stream().map(questionOption -> (QuestionOption)questionOption).collect(Collectors.toList());
    }

    @Transactional
    public List<HistoryQuizJoin> getAllHistoryQuizByUserId(int user_id){
        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT new org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.HistoryQuizJoinHibernate(" +
                "hq.historyquiz_id, hq.quiz.quiz_id, q.quiz_name, hq.quiz_date) " +
                "FROM HistoryQuizHibernate hq " +
                "JOIN hq.quiz q " +
                "WHERE hq.user.user_id = :userId " +
                "AND hq.ongoing = false";

        return session.createQuery(hql).setParameter("userId", user_id).getResultList();
    }

    @Transactional
    public Map<Question, List<QuestionOption>> startQuiz(int quiz_id){

        QuizHibernate quiz = getQuizByQuizId_hibernate(quiz_id);
        List<QuestionHibernate> questions = getAllQuestionsByCategoryId_hibernate(quiz.getCategory().getCategory_id());
        List<QuestionHibernate> randQuestions = getRandomElements(questions, 5);

        Map<Question, List<QuestionOption>> map = new HashMap<>();
        for (QuestionHibernate question : randQuestions) {
            map.put(question, question.getOptions().stream().map(option -> (QuestionOption)option).collect(Collectors.toList()));
        }

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

    @Transactional
    public HistoryQuiz insertAndGetHistoryQuiz(int user_id, int quiz_id, boolean ongoing){
        Session session = sessionFactory.getCurrentSession();

        HistoryQuizHibernate historyQuizHibernate = new HistoryQuizHibernate();
        historyQuizHibernate.setUser((UserHibernate) userDao.getUserByUserId(user_id));
        historyQuizHibernate.setQuiz(getQuizByQuizId_hibernate(quiz_id));
        historyQuizHibernate.setOngoing(ongoing);

        session.persist(historyQuizHibernate);

        return getHistoryQuizById(historyQuizHibernate.getHistoryquiz_id());
    }

    @Transactional
    public HistoryQuiz getHistoryQuizById(int id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<HistoryQuizHibernate> criteriaQuery = criteriaBuilder.createQuery(HistoryQuizHibernate.class);
        Root<HistoryQuizHibernate> root = criteriaQuery.from(HistoryQuizHibernate.class);

        Predicate idPredicate = criteriaBuilder.equal(root.get("historyquiz_id"), id);
        criteriaQuery.where(idPredicate);

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Transactional
    public HistoryQuizHibernate getHistoryQuizById_hibernate(int id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<HistoryQuizHibernate> criteriaQuery = criteriaBuilder.createQuery(HistoryQuizHibernate.class);
        Root<HistoryQuizHibernate> root = criteriaQuery.from(HistoryQuizHibernate.class);

        Predicate idPredicate = criteriaBuilder.equal(root.get("historyquiz_id"), id);
        criteriaQuery.where(idPredicate);

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Transactional
    public void insertHistoryQuestion(int historyquiz_id, int question_id, String user_answer){
        Session session = sessionFactory.getCurrentSession();

        HistoryQuestionId id = new HistoryQuestionId(historyquiz_id, question_id);

        HistoryQuestionHibernate historyQuestionHibernate = new HistoryQuestionHibernate();
        historyQuestionHibernate.setId(id);
        historyQuestionHibernate.setHistoryQuiz(session.get(HistoryQuizHibernate.class, historyquiz_id));
        historyQuestionHibernate.setQuestion(session.get(QuestionHibernate.class, question_id));
        historyQuestionHibernate.setUser_answer(user_answer);

        session.persist(historyQuestionHibernate);
    }

    @Transactional
    public HistoryQuiz getOngoingHistoryQuiz(int user_id){
        return null;
    }

    @Transactional
    public HistoryQuizHibernate getOngoingHistoryQuiz_hibernate(int user_id) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<HistoryQuizHibernate> criteriaQuery = criteriaBuilder.createQuery(HistoryQuizHibernate.class);
        Root<HistoryQuizHibernate> root = criteriaQuery.from(HistoryQuizHibernate.class);

        Predicate ongoingPredicate  = criteriaBuilder.equal(root.get("ongoing"), true);
        Predicate userIdPredicate = criteriaBuilder.equal(root.get("user"), userDao.getUserByUserId(user_id));
        criteriaQuery.where(criteriaBuilder.and(ongoingPredicate, userIdPredicate));

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Transactional
    public Map<Question, List<QuestionOption>> getHistoryQuizQuestion(int historyquiz_id){

        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT q FROM HistoryQuestionHibernate hq JOIN hq.question q WHERE hq.historyQuiz.historyquiz_id = :historyquiz_id";
        List<QuestionHibernate> questions = session.createQuery(hql, QuestionHibernate.class)
                .setParameter("historyquiz_id", historyquiz_id)
                .getResultList();

        Map<Question, List<QuestionOption>> map = new HashMap<>();
        for (QuestionHibernate question : questions) {
            map.put(question, question.getOptions().stream().map(option -> (QuestionOption)option).collect(Collectors.toList()));
        }

        return map;
    }

    @Transactional
    public void updateHistoryQuestionAnswer(int historyquiz_id, int question_id, String user_answer){
        Session session = sessionFactory.getCurrentSession();

        String hql = "UPDATE HistoryQuestionHibernate hq SET hq.user_answer = :user_answer " +
                "WHERE hq.id.historyquiz_id = :historyquiz_id AND hq.id.question_id = :question_id";

        int updatedEntities = session.createQuery(hql)
                .setParameter("user_answer", user_answer)
                .setParameter("historyquiz_id", historyquiz_id)
                .setParameter("question_id", question_id)
                .executeUpdate();

        if (updatedEntities > 0) {
            System.out.println("Update Answer Successfully");
        } else {
            System.out.println("Update Failed");
        }
    }

    @Transactional
    public void updateOngoingHistoryQuiz(int historyquiz_id, String end_time, int score){
        Session session = sessionFactory.getCurrentSession();

        String hql = "UPDATE HistoryQuizHibernate hq SET hq.score = :score, hq.quiz_end_time = :quiz_end_time, hq.ongoing = :ongoing " +
                "WHERE hq.historyquiz_id = :historyquiz_id";

        int updatedEntities = session.createQuery(hql)
                .setParameter("historyquiz_id", historyquiz_id)
                .setParameter("quiz_end_time", end_time)
                .setParameter("score", score)
                .setParameter("ongoing", false)
                .executeUpdate();

        if (updatedEntities > 0) {
            System.out.println("Update Ongoing History Quiz Successfully");
        } else {
            System.out.println("Update Failed");
        }
    }

    @Transactional
    public List<HistoryQuestion> getHistroyQuestionById(int historyquiz_id){
        return null;
    }

    @Transactional
    public List<HistoryQuestionHibernate> getHistroyQuestionById_hibernate(int historyquiz_id){
        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT hq FROM HistoryQuestionHibernate hq WHERE hq.id.historyquiz_id = :historyquiz_id";


        List<HistoryQuestionHibernate> historyQuestionHibernates =  session.createQuery(hql, HistoryQuestionHibernate.class)
                .setParameter("historyquiz_id", historyquiz_id)
                .getResultList();

        return historyQuestionHibernates;
    }

    @Transactional
    public void updateQuestionStatus(int question_id){
        Session session = sessionFactory.getCurrentSession();

        Question question = getQuestionById(question_id);

        if(question.isActive()){
            question.setActive(false);
        }else{
            question.setActive(true);
        }

        session.persist(question);
    }

    @Transactional
    public Question getQuestionById(int question_id){
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionHibernate> criteriaQuery = builder.createQuery(QuestionHibernate.class);
        Root<QuestionHibernate> root = criteriaQuery.from(QuestionHibernate.class);

        Predicate isActivePredicate  = builder.equal(root.get("question_id"), question_id);
        criteriaQuery.where(isActivePredicate);

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Transactional
    public void insertQuestionAndOptions(String subject, String description, int category_id, String answer, String option1, String option2, String option3, String option4){

        Session session = sessionFactory.getCurrentSession();

        QuestionHibernate questionHibernate = new QuestionHibernate();
        questionHibernate.setQuestion_subject(subject);
        questionHibernate.setQuestion_description(description);
        questionHibernate.setCategory_id(category_id);
        questionHibernate.setAnswer(answer);

        QuestionOptionHibernate h_option1 = new QuestionOptionHibernate();
        QuestionOptionHibernate h_option2 = new QuestionOptionHibernate();
        QuestionOptionHibernate h_option3 = new QuestionOptionHibernate();
        QuestionOptionHibernate h_option4 = new QuestionOptionHibernate();

        h_option1.setContent(option1);
        h_option2.setContent(option2);
        h_option3.setContent(option3);
        h_option4.setContent(option4);

        questionHibernate.addOption(h_option1);
        questionHibernate.addOption(h_option2);
        questionHibernate.addOption(h_option3);
        questionHibernate.addOption(h_option4);

        session.persist(questionHibernate);
    }

    @Transactional
    public void updateQuestionAndOptions(int question_id, String subject, String description, int category_id, String answer,
                                  int option1_id, String option1, int option2_id, String option2, int option3_id, String option3, int option4_id, String option4){

        Session session = sessionFactory.getCurrentSession();

        QuestionHibernate questionHibernate = session.find(QuestionHibernate.class, question_id);
        questionHibernate.setQuestion_subject(subject);
        questionHibernate.setQuestion_description(description);
        questionHibernate.setCategory_id(category_id);
        questionHibernate.setAnswer(answer);
        for(QuestionOptionHibernate option : questionHibernate.getOptions()){
            if(option.getOption_id() == option1_id){
                option.setContent(option1);
            } else if (option.getOption_id() == option2_id) {
                option.setContent(option2);
            } else if (option.getOption_id() == option3_id) {
                option.setContent(option3);
            } else if (option.getOption_id() == option4_id) {
                option.setContent(option4);
            }
        }

        session.persist(questionHibernate);

        System.out.println("Successfully Updated Question and its Options");
    }

    @Transactional
    public List<AdminHistoryQuizResult> getAllHistoryQuizResult(String orderBy){
        List<String> validOrderByColumns = Arrays.asList(
                "historyquiz_id", "user_id", "firstname", "lastname", "quiz_id",
                "quiz_name", "category_id", "category_name", "quiz_date",
                "quiz_end_time", "score", "username"
        );

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT new org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.AdminHistoryQuizResultHibernate(" +
                "hq.historyquiz_id, hq.user.user_id, u.username, u.firstname, u.lastname, " +
                "hq.quiz.quiz_id, q.quiz_name, c.category_id, c.category_name, " +
                "hq.quiz_date, hq.quiz_end_time, hq.score) " +
                "FROM HistoryQuizHibernate hq " +
                "JOIN hq.user u " +
                "JOIN hq.quiz q " +
                "JOIN q.category c " +
                "ORDER BY " + orderBy + " DESC";

        return session.createQuery(hql, AdminHistoryQuizResultHibernate.class)
                .getResultList().stream().map(historyQuizResult -> (AdminHistoryQuizResult) historyQuizResult).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByCategory(String orderBy, String category){
        List<String> validOrderByColumns = Arrays.asList(
                "historyquiz_id", "user_id", "firstname", "lastname", "quiz_id",
                "quiz_name", "category_id", "category_name", "quiz_date",
                "quiz_end_time", "score", "username"
        );

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT new org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.AdminHistoryQuizResultHibernate(" +
                "hq.historyquiz_id, hq.user.user_id, u.username, u.firstname, u.lastname, " +
                "hq.quiz.quiz_id, q.quiz_name, c.category_id, c.category_name, " +
                "hq.quiz_date, hq.quiz_end_time, hq.score) " +
                "FROM HistoryQuizHibernate hq " +
                "JOIN hq.user u " +
                "JOIN hq.quiz q " +
                "JOIN q.category c " +
                "WHERE c.category_name = :category_name " +
                "ORDER BY " + orderBy + " DESC";

        return session.createQuery(hql, AdminHistoryQuizResultHibernate.class).setParameter("category_name", category)
                .getResultList().stream().map(historyQuizResult -> (AdminHistoryQuizResult) historyQuizResult).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminHistoryQuizResult> getAllHistoryQuizResultFilterByUsername(String orderBy, String username){
        List<String> validOrderByColumns = Arrays.asList(
                "historyquiz_id", "user_id", "firstname", "lastname", "quiz_id",
                "quiz_name", "category_id", "category_name", "quiz_date",
                "quiz_end_time", "score", "username"
        );

        if (!validOrderByColumns.contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy column: " + orderBy);
        }

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT new org.beaconfire.week3day5georgekaoquizweb.domain.hibernate.AdminHistoryQuizResultHibernate(" +
                "hq.historyquiz_id, hq.user.user_id, u.username, u.firstname, u.lastname, " +
                "hq.quiz.quiz_id, q.quiz_name, c.category_id, c.category_name, " +
                "hq.quiz_date, hq.quiz_end_time, hq.score) " +
                "FROM HistoryQuizHibernate hq " +
                "JOIN hq.user u " +
                "JOIN hq.quiz q " +
                "JOIN q.category c " +
                "WHERE hq.user.username = :username " +
                "ORDER BY " + orderBy + " DESC";

        return session.createQuery(hql, AdminHistoryQuizResultHibernate.class).setParameter("username", username)
                .getResultList().stream().map(historyQuizResult -> (AdminHistoryQuizResult) historyQuizResult).collect(Collectors.toList());
    }
}
