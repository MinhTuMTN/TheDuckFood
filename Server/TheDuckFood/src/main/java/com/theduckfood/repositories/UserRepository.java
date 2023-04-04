package com.theduckfood.repositories;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.hibernate.JPAConfig;
import com.theduckfood.util.Constants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepository {
    private static UserRepository userRepository;

    public static UserRepository getUserRepository() {
        if(userRepository == null)
            return new UserRepository();
        return userRepository;
    }

    public UserAccount findUserAccountByEmail(String email){
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<UserAccount> query = entityManager.createQuery(
                "SELECT u FROM UserAccount u WHERE u.email = :email AND u.status = :status",
                UserAccount.class);
        UserAccount userAccount = query
                .setParameter("email", email)
                .setParameter("status", Constants.USER_ACCOUNT_STATUS_ACTIVATED)
                .getSingleResult();
        entityManager.close();
        return userAccount;
    }

    public UserProfile findUserProfileByEmail(String email) {
        return findUserAccountByEmail(email).getUser();
    }

    public UserProfile insertUserProfile(UserProfile userProfile) {
        return insert(userProfile);
    }

    public UserAccount insertUserAccount(UserAccount userAccount) {
        return insert(userAccount);
    }

    public List<UserProfile> findAllUserProfile() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<UserProfile> query = entityManager.createQuery(
                "SELECT u FROM UserProfile u WHERE u.userAccount.status = :status",
                UserProfile.class);
        List<UserProfile> userProfiles = query
                .setParameter("status", Constants.USER_ACCOUNT_STATUS_ACTIVATED)
                .getResultList();
        entityManager.close();
        return userProfiles;
    }

    private <T> T insert(T entity) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
