package com.convention.request.repository;

import com.convention.request.model.Convention;
import com.convention.request.model.ConventionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for managing {@link Convention} entities.
 * Extends JpaRepository to provide standard CRUD operations and
 * includes custom queries for specific business logic.
 */
@Repository
public interface ConventionRepository extends JpaRepository<Convention, String> {

    /**
     * Finds a Convention by its unique identifier.
     *
     * @param conventionId The ID of the convention to find.
     * @return An Optional containing the found Convention, or empty if not found.
     */
    Optional<Convention> findById(String conventionId);

    /**
     * Updates the status of a specific convention.
     * This method uses a custom JPQL query to update the status directly in the database.
     *
     * @param conventionId The ID of the convention to update.
     * @param status The new status to set for the convention.
     * @return The number of entities updated (should be 1 if successful).
     */
    @Modifying
    @Transactional
    @Query("UPDATE Convention c SET c.status = :status, c.updatedAt = CURRENT_TIMESTAMP WHERE c.conventionId = :conventionId")
    int updateStatus(@Param("conventionId") String conventionId, @Param("status") ConventionStatus status);
}