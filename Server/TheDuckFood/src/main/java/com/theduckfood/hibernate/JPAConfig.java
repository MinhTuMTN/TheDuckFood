package com.theduckfood.hibernate;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JPAConfig {
    static EntityManagerFactory factory;
    static EntityManager entityManager;

    static {
        factory = Persistence.createEntityManagerFactory("jpaSQL");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void main(String[] args) {
        UserProfile userProfile = new UserProfile("012345679", "Nguyễn Minh Tú");
        UserAccount userAccount = new UserAccount("minhtu@gmail.com", "minhtu", userProfile);
        UserRepository userRepository = UserRepository.getUserRepository();


        userRepository.insertUserProfile(userProfile);
        userRepository.insertUserAccount(userAccount);

        userProfile = new UserProfile("012345679", "Nguyễn Ngọc Tuyết Vi");
        userAccount = new UserAccount("tuyetvi@gmail.com", "minhtu", userProfile);

        userRepository.insertUserProfile(userProfile);
        userRepository.insertUserAccount(userAccount);
    }
}
