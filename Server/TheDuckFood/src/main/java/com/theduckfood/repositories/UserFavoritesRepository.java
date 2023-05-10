package com.theduckfood.repositories;

import com.theduckfood.entity.Store;
import com.theduckfood.entity.UserFavorites;
import com.theduckfood.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoritesRepository extends JpaRepository<UserFavorites, Long> {
    UserFavorites getUserFavoritesByUserProfileAndStore(UserProfile userProfile, Store store);
    List<UserFavorites> getUserFavoritesByUserProfile(UserProfile userProfile);
}
