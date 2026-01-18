package com.example.infrastructure;

import com.example.domain.Banner;

/**
 * Port interface for banner persistence operations.
 */
public interface BannerRepository {
    Banner findById(String bannerId);
    void save(Banner banner);
}