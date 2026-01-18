package repository;

import entity.Banner;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    Banner findById(String bannerId);
    Banner save(Banner banner);
    void deleteById(String bannerId);
}