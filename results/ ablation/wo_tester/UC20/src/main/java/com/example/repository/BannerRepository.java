package com.example.repository;

import com.example.entity.Banner;

/**
 * Interface for banner repository.
 */
public interface BannerRepository {
    /**
     * Saves a banner.
     */
    Banner save(Banner banner);
    
    /**
     * Counts banners by point ID.
     */
    int countByPointId(String pointId);
    
    /**
     * Stores banner association.
     */
    void storeBannerAssociation(String refreshmentPointId, String bannerId);
}