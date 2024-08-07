package org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper;

import org.beaconfire.week3day5georgekaoquizweb.domain.QuestionOption;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.QuestionOptionJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionOptionRowMapper implements RowMapper<QuestionOption> {
    public QuestionOption mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionOption questionOptionJdbc = new QuestionOptionJdbc();
        questionOptionJdbc.setOption_id(rs.getInt("option_id"));
        questionOptionJdbc.setQuestion_id(rs.getInt("question_id"));
        questionOptionJdbc.setContent(rs.getString("content"));

        return questionOptionJdbc;
    }
}
