package org.practice.week3day5georgekaoquizweb.dao.rowmapper;

import org.practice.week3day5georgekaoquizweb.domain.HistoryQuizJoin;
import org.practice.week3day5georgekaoquizweb.domain.jdbc.HistoryQuizJoinJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HistoryQuizJoinRowMapper implements RowMapper<HistoryQuizJoin> {
    @Override
    public HistoryQuizJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        HistoryQuizJoin hq = new HistoryQuizJoinJdbc();
        hq.setHistoryquiz_id(rs.getInt("historyquiz_id"));
        hq.setQuiz_id(rs.getInt("quiz_id"));
        hq.setQuiz_name(rs.getString("quiz_name"));
        hq.setQuiz_date(rs.getString("quiz_date"));

        return hq;
    }
}
