package org.practice.week3day5georgekaoquizweb.service;

import org.practice.week3day5georgekaoquizweb.dao.CategoryDao;
import org.practice.week3day5georgekaoquizweb.domain.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryDao categoryJdbcDao;
    private CategoryDao categoryHibernateDao;

    public CategoryService(@Qualifier("categoryDaoJdbcImpl") CategoryDao categoryJdbcDao, @Qualifier("CategoryDaoHibernateImpl") CategoryDao categoryHibernateDao) {
        this.categoryJdbcDao = categoryJdbcDao;
        this.categoryHibernateDao = categoryHibernateDao;
    }

    public List<Category> getAllCategories() {
        return categoryHibernateDao.getAllCategory();
    }
}
