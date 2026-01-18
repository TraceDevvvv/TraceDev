package com.example.repository;

import com.example.domain.Agency;

/**
 * Repository interface for Agency data access.
 */
public interface IAgencyRepository {
    Agency findById(String agencyId);
    void save(Agency agency);
}