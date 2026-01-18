package com.example.repositories;

import com.example.entities.Registry;
import com.example.exceptions.SMOSConnectionException;

/**
 * Repository interface for registry data access.
 */
public interface RegistryRepository {
    Registry findByAcademicYearAndClassName(String academicYear, String className);
    void save(Registry registry) throws SMOSConnectionException;
}