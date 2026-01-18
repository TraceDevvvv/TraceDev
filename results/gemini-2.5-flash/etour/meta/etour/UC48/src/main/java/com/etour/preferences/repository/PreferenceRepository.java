package com.etour.preferences.repository;

import com.etour.preferences.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Preference} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * and custom query capabilities for preferences.
 */
@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    /**
     * Finds a {@link Preference} entity by its user ID.
     * This method is crucial for retrieving a tourist's existing preferences.
     *
     * @param userId The unique identifier of the user.
     * @return An {@link Optional} containing the found {@link Preference} if it exists,
     *         or an empty {@link Optional} if no preference is found for the given user ID.
     */
    Optional<Preference> findByUserId(Long userId);
}