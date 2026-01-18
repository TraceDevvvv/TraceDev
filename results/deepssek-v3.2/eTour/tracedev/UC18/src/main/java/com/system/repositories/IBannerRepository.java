package com.system.repositories;

/**
 * Repository interface for banner count queries.
 */
public interface IBannerRepository {
    int countByConventionId(String conventionId);
}