package com.theduckfood.repositories;

import com.theduckfood.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileByPhone(String phone);
    UserProfile findUserProfileByUserId(Long userId);
}
