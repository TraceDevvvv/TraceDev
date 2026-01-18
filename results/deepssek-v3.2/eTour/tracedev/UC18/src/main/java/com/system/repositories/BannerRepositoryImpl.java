package com.system.repositories;

/**
 * Concrete implementation of IBannerRepository.
 * For simplicity, returns a dummy count. In a real system, would query a database.
 */
public class BannerRepositoryImpl implements IBannerRepository {
    @Override
    public int countByConventionId(String conventionId) {
        // Simulate database query. Return a dummy count.
        // In real implementation, would execute: SELECT COUNT(*) FROM banner WHERE convention_id = ?
        return 3; // Assume there are already 3 banners for this convention
    }
}