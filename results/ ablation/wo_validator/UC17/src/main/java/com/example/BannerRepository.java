
package com.example;

import java.util.List;

/**
 * Interface for banner data operations.
 * Extends RefreshmentPointRepository for potential shared behavior (as per diagram).
 */
public interface BannerRepository extends RefreshmentPointRepository {
    List<Banner> findByRefreshmentPointId(int refreshmentPointId);
    Banner findBannerById(int bannerId);
    Banner update(Banner banner);
}
