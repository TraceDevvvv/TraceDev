package com.example.modifybanner.repository;

import com.example.modifybanner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for Banner entities.
 * Provides methods for CRUD operations and custom queries for Banner objects.
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    /**
     * Finds all banners associated with a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return A list of banners belonging to the specified restaurant.
     */
    List<Banner> findByRestaurantId(Long restaurantId);
}