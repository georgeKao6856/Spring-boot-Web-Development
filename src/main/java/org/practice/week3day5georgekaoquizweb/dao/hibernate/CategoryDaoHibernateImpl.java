package org.practice.week3day5georgekaoquizweb.dao.hibernate;

import org.practice.week3day5georgekaoquizweb.dao.AbstractHibernateDao;
import org.practice.week3day5georgekaoquizweb.dao.CategoryDao;
import org.practice.week3day5georgekaoquizweb.domain.Category;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.CategoryHibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository("CategoryDaoHibernateImpl")
public class CategoryDaoHibernateImpl extends AbstractHibernateDao<CategoryHibernate> implements CategoryDao {

    public CategoryDaoHibernateImpl() { setClazz(CategoryHibernate.class);}

    @Transactional
    public List<Category> getAllCategory() {
        List<CategoryHibernate> categories = this.getAll();
        return categories.stream().map(category -> (Category) category).collect(Collectors.toList());
    }
}
