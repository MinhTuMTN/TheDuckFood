package com.theduckfood.repositories;

import com.theduckfood.entity.UserAddress;
import com.theduckfood.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findUserAddressesByUserProfileAndIsDeletedFalse(UserProfile userProfile);
    UserAddress findUserAddressesByUserAddressIdAndUserProfile(Long userAddressId, UserProfile userProfile);
}
