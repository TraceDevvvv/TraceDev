package com.etour.banner.repository;

import com.etour.banner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Banner} entities.
 * Provides standard CRUD operations and custom query methods for banners.
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, String> {

    /**
     * Finds all banners associated with a specific refreshment point ID.
     *
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of banners associated with the given refreshment point ID.
     */
    List<Banner> findByRefreshmentPointId(String refreshmentPointId);
}