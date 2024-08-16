package org.practice.week3day5georgekaoquizweb.dao.jdbc;

import org.practice.week3day5georgekaoquizweb.dao.CategoryDao;
import org.practice.week3day5georgekaoquizweb.domain.Category;
import org.practice.week3day5georgekaoquizweb.dao.rowmapper.CategoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDaoJdbcImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate;
    private CategoryRowMapper categoryRowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategoryDaoJdbcImpl(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Category> getAllCategory() {
        String query = "select * from Category";
        List<Category> categories = jdbcTemplate.query(query, categoryRowMapper);

        return categories;
    }
}
