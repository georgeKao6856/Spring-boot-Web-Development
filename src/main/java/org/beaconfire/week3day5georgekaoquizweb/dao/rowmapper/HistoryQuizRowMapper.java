package org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper;

import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuiz;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.HistoryQuizJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HistoryQuizRowMapper implements RowMapper<HistoryQuiz> {
    @Override
    public HistoryQuiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        HistoryQuiz hq = new HistoryQuizJdbc();
        hq.setHistoryquiz_id(rs.getInt("historyquiz_id"));
        hq.setUser_id(rs.getInt("user_id"));
        hq.setQuiz_id(rs.getInt("quiz_id"));
        hq.setOngoing(rs.getBoolean("ongoing"));
        hq.setQuiz_date(rs.getString("quiz_date"));
        hq.setQuiz_end_time(rs.getString("quiz_end_time"));
        hq.setScore(rs.getInt("score"));

        return hq;
    }
}
