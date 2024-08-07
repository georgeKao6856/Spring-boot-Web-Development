package org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper;

import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuestion;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.HistoryQuestionJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HistoryQuestionRowMapper implements RowMapper<HistoryQuestion> {
    @Override
    public HistoryQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        HistoryQuestion hq = new HistoryQuestionJdbc();
        hq.setHistoryquiz_id(rs.getInt("historyquiz_id"));
        hq.setQuestion_id(rs.getInt("question_id"));
        hq.setUser_answer(rs.getString("user_answer"));

        return hq;
    }
}
