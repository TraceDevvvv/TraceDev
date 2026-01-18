package com.etour.banner.repository;

import com.etour.banner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for Banner entities.
 * Provides methods for CRUD operations and custom queries related to banners.
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, String> {

    /**
     * Counts the number of banners associated with a specific Point of Rest.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @return The count of banners for the given Point of Rest.
     */
    long countByPointOfRestId(String pointOfRestId);

    /**
     * Finds all banners associated with a specific Point of Rest.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @return A list of banners associated with the given Point of Rest.
     */
    List<Banner> findByPointOfRestId(String pointOfRestId);
}