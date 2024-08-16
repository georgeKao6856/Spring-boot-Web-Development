package org.practice.week3day5georgekaoquizweb.dao.rowmapper;

import org.practice.week3day5georgekaoquizweb.domain.Category;
import org.practice.week3day5georgekaoquizweb.domain.jdbc.CategoryJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category categoryJdbc = new CategoryJdbc();
        categoryJdbc.setCategory_id(rs.getInt("category_id"));
        categoryJdbc.setCategory_name(rs.getString("category_name"));

        return categoryJdbc;
    }
}
