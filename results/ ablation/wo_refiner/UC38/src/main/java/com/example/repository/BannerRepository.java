package com.example.repository;

import com.example.domain.Banner;
import java.util.List;

/**
 * Repository interface for Banner persistence.
 */
public interface BannerRepository {
    Banner save(Banner banner);
    List<Banner> findByPointOfRestId(String pointOfRestId);
    int countByPointOfRestId(String pointOfRestId);
    void rememberBanners(String pointOfRestId); // Added to satisfy requirement REQ-11
}