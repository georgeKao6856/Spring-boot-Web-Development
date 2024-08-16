package org.practice.week3day5georgekaoquizweb.dao.rowmapper;

import org.practice.week3day5georgekaoquizweb.domain.Question;
import org.practice.week3day5georgekaoquizweb.domain.jdbc.QuestionJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question questionJdbc = new QuestionJdbc();
        questionJdbc.setQuestion_id(rs.getInt("question_id"));
        questionJdbc.setQuestion_subject(rs.getString("question_subject"));
        questionJdbc.setQuestion_description(rs.getString("question_description"));
        questionJdbc.setCategory_id(rs.getInt("category_id"));
        questionJdbc.setAnswer(rs.getString("answer"));
        questionJdbc.setActive(rs.getBoolean("is_active"));

        return questionJdbc;
    }
}
