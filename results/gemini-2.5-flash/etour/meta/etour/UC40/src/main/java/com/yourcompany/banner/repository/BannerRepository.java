package com.yourcompany.banner.repository;

import com.yourcompany.banner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Banner entities.
 * This interface extends JpaRepository, providing standard CRUD operations
 * and custom query capabilities for the Banner model.
 */
@Repository // Marks this interface as a Spring Data JPA repository.
public interface BannerRepository extends JpaRepository<Banner, Long> {

    /**
     * Finds all banners associated with a specific restaurant ID.
     * This method allows operators to view banners relevant to their point of rest.
     *
     * @param restaurantId The ID of the restaurant.
     * @return A list of banners associated with the given restaurant ID.
     */
    List<Banner> findByRestaurantId(Long restaurantId);

    // No custom deletion methods are explicitly needed here as JpaRepository provides deleteById(ID id)
    // and delete(T entity) methods, which will be used by the service layer.
}