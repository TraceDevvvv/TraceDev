package com.system.repositories;

import com.system.entities.RefreshmentPointConvention;

/**
 * Concrete implementation of IConventionRepository.
 * For simplicity, returns a dummy convention. In a real system, would query a database.
 */
public class ConventionRepositoryImpl implements IConventionRepository {
    @Override
    public RefreshmentPointConvention findById(String id) {
        // Simulate database lookup. Assume convention exists.
        // In real implementation, would fetch from database.
        return new RefreshmentPointConvention(id, "Convention " + id, 5, 0);
    }
}