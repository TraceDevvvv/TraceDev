package com.system.repositories;

import com.system.entities.RefreshmentPointConvention;

/**
 * Repository interface for retrieving conventions.
 */
public interface IConventionRepository {
    RefreshmentPointConvention findById(String id);
}