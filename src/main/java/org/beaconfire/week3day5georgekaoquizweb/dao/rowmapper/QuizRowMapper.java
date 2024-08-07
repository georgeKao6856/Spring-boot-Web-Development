package org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper;

import org.beaconfire.week3day5georgekaoquizweb.domain.Quiz;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.QuizJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quizJdbc = new QuizJdbc();
        quizJdbc.setQuiz_id(rs.getInt("quiz_id"));
        quizJdbc.setQuiz_name(rs.getString("quiz_name"));
        quizJdbc.setCategory_id(rs.getInt("category_id"));

        return quizJdbc;
    }
}
