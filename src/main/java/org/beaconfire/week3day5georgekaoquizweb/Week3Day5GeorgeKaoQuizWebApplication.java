package org.beaconfire.week3day5georgekaoquizweb;

import org.beaconfire.week3day5georgekaoquizweb.dao.hibernate.UserDaoHibernateImpl;
import org.beaconfire.week3day5georgekaoquizweb.dao.jdbc.UserDaoJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week3Day5GeorgeKaoQuizWebApplication {

    private static UserDaoJdbcImpl userDaoJdbcImpl;


    public Week3Day5GeorgeKaoQuizWebApplication(UserDaoJdbcImpl userDaoJdbcImpl) {
        this.userDaoJdbcImpl = userDaoJdbcImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(Week3Day5GeorgeKaoQuizWebApplication.class, args);

        userDaoJdbcImpl.getAllUsers().forEach((user) -> System.out.println(user));
    }

}
