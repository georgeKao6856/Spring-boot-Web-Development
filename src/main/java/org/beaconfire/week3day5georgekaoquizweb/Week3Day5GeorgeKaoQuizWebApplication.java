package org.beaconfire.week3day5georgekaoquizweb;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.beaconfire.week3day5georgekaoquizweb.dao.hibernate.UserDaoHibernateImpl;
import org.beaconfire.week3day5georgekaoquizweb.dao.jdbc.UserDaoJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Provider;
import java.security.Security;

@SpringBootApplication
@EnableEncryptableProperties
public class Week3Day5GeorgeKaoQuizWebApplication {

    private static UserDaoJdbcImpl userDaoJdbcImpl;


    public Week3Day5GeorgeKaoQuizWebApplication(UserDaoJdbcImpl userDaoJdbcImpl) {
        this.userDaoJdbcImpl = userDaoJdbcImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(Week3Day5GeorgeKaoQuizWebApplication.class, args);

        //userDaoJdbcImpl.getAllUsers().forEach((user) -> System.out.println(user));

//        for (Provider provider : Security.getProviders()) {
//            System.out.println(provider.getName());
//            for (Provider.Service service : provider.getServices()) {
//                System.out.println("\t" + service.getType() + ": " + service.getAlgorithm());
//            }
//        }
    }

}
