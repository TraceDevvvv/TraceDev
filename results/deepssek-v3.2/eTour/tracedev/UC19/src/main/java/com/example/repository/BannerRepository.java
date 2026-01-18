package com.example.repository;

import com.example.model.Banner;
import java.util.List;

/**
 * Repository interface for Banner entities.
 */
public interface BannerRepository {
    /**
     * Finds all banners for a given refreshment point ID.
     * @param pointId the refreshment point ID
     * @return list of banners (could be empty)
     */
    List<Banner> findAllByRefreshmentPointId(long pointId);

    /**
     * Deletes a banner by its ID.
     * @param bannerId the banner ID
     * @return true if deletion was successful, false otherwise
     */
    boolean deleteById(long bannerId);
}