package com.theduckfood.repositories;

import com.theduckfood.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    List<UserAccount> findAll();

    UserAccount findUserAccountByEmailAndPasswordAndStatus(String email, String password, String status);
    UserAccount findUserAccountByEmail(String email);
    UserAccount findUserAccountsByEmailAndStatus(@Email String email, String status);
    List<UserAccount> findUserAccountsByStatus(String status);
}
