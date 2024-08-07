package org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper;

import org.beaconfire.week3day5georgekaoquizweb.domain.AdminHistoryQuizResult;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.AdminHistoryQuizResultJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdminHistoryQuizResultRowMapper implements RowMapper<AdminHistoryQuizResult> {
    @Override
    public AdminHistoryQuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdminHistoryQuizResult adminHistoryQuizResultJdbc = new AdminHistoryQuizResultJdbc();
        adminHistoryQuizResultJdbc.setHistoryquiz_id(rs.getInt("historyquiz_id"));
        adminHistoryQuizResultJdbc.setUser_id(rs.getInt("user_id"));
        adminHistoryQuizResultJdbc.setUsername(rs.getString("username"));
        adminHistoryQuizResultJdbc.setFirstname(rs.getString("firstname"));
        adminHistoryQuizResultJdbc.setLastname(rs.getString("lastname"));
        adminHistoryQuizResultJdbc.setQuiz_id(rs.getInt("quiz_id"));
        adminHistoryQuizResultJdbc.setQuiz_name(rs.getString("quiz_name"));
        adminHistoryQuizResultJdbc.setCategory_id(rs.getInt("category_id"));
        adminHistoryQuizResultJdbc.setCategory_name(rs.getString("category_name"));
        adminHistoryQuizResultJdbc.setQuiz_date(rs.getString("quiz_date"));
        adminHistoryQuizResultJdbc.setQuiz_end_time(rs.getString("quiz_end_time"));
        adminHistoryQuizResultJdbc.setScore(rs.getInt("score"));

        return adminHistoryQuizResultJdbc;
    }
}
