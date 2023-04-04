package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFavorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFavoritesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private UserProfile userProfile;
}
