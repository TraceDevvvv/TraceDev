package com.etour.banner.repository;

import com.etour.banner.model.RefreshmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Spring Data JPA repository for {@link RefreshmentPoint} entities.
 * Provides standard CRUD operations and custom query methods for refreshment points.
 */
@Repository
public interface RefreshmentPointRepository extends JpaRepository<RefreshmentPoint, String> {

    /**
     * Finds a refreshment point by its unique ID.
     *
     * @param id The unique identifier of the refreshment point.
     * @return An {@link Optional} containing the found RefreshmentPoint, or empty if not found.
     */
    Optional<RefreshmentPoint> findById(String id);

    /**
     * Retrieves all refreshment points.
     *
     * @return A list of all RefreshmentPoint entities.
     */
    List<RefreshmentPoint> findAll();
}