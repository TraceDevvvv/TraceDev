package com.example.bannerchecker;

/**
 * Repository interface for managing RefreshmentPointConvention entities.
 * Provides methods to find, save, and retrieve previous state.
 */
public interface RefreshmentPointConventionRepository {
    RefreshmentPointConvention findById(String refreshmentPointId) throws ConnectionException;
    void save(RefreshmentPointConvention convention) throws ConnectionException;
    RefreshmentPointConvention getPreviousState(String refreshmentPointId) throws ConnectionException;
}