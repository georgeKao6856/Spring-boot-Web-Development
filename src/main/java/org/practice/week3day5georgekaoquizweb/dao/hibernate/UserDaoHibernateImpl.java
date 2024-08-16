package org.practice.week3day5georgekaoquizweb.dao.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.practice.week3day5georgekaoquizweb.dao.AbstractHibernateDao;
import org.practice.week3day5georgekaoquizweb.dao.UserDao;
import org.practice.week3day5georgekaoquizweb.domain.User;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.UserHibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("UserDaoHibernateImpl")
public class UserDaoHibernateImpl extends AbstractHibernateDao<UserHibernate> implements UserDao {

    public UserDaoHibernateImpl() { setClazz(UserHibernate.class);}

    @Override
    public List<User> getAllUsers(){
        List<UserHibernate> users = this.getAll();
        return users.stream().map(user -> (User) user).collect(Collectors.toList());
    }

    @Override
    public User getUserByUserName(String userName){
        try{
            Session session = getCurrentSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserHibernate> criteriaQuery = criteriaBuilder.createQuery(UserHibernate.class);
            Root<UserHibernate> root = criteriaQuery.from(UserHibernate.class);

            Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), userName);
            criteriaQuery.where(usernamePredicate);

            return session.createQuery(criteriaQuery).uniqueResult();
        }catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public User getUserByUserId(int user_id){
        return findById(user_id);
    }

    @Override
    public User getUserByEmail(String email){
        try{
            Session session = getCurrentSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserHibernate> criteriaQuery = criteriaBuilder.createQuery(UserHibernate.class);
            Root<UserHibernate> root = criteriaQuery.from(UserHibernate.class);

            Predicate usernamePredicate = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.where(usernamePredicate);

            return session.createQuery(criteriaQuery).uniqueResult();

        }catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public void createUser(String username, String firstname, String lastname, String email, String passwd){
        UserHibernate user = UserHibernate.builder()
                .username(username)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .passwd(passwd)
                .build();

        add(user);  //getCurrentSession().save(user);
    }

    @Override
    public void updateUserStatus(int user_id){
        User user = getUserByUserId(user_id);

        if(user.isActive()){
            user.setActive(false);
        }else {
            user.setActive(true);
        }

        getCurrentSession().update(user);
    }

    @Override
    public void deleteUserByEmailJdbc(String email) {

    }
}
