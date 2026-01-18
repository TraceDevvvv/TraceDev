package com.example.infrastructure;

import com.example.domain.Enrollment;

/**
 * Interface for data access operations related to Enrollments.
 * Defines the contract for saving Enrollment entities.
 */
public interface IEnrollmentRepository {
    /**
     * Saves an Enrollment entity to the repository.
     * @param enrollment The Enrollment object to save.
     */
    void save(Enrollment enrollment);
}