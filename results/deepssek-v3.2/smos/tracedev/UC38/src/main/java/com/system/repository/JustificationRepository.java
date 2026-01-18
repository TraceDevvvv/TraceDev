package com.system.repository;

import com.system.entity.Justification;
import java.util.Optional;

/**
 * Repository interface for managing Justification entities.
 */
public interface JustificationRepository {
    /**
     * Finds a justification by its ID.
     * @param justificationId The justification ID.
     * @return Optional containing the justification if found.
     */
    Optional<Justification> findById(String justificationId);

    /**
     * Saves a justification (create or update).
     * @param justification The justification to save.
     * @return The saved justification.
     */
    Justification save(Justification justification);

    /**
     * Deletes a justification by its ID.
     * @param justificationId The justification ID.
     * @return true if deletion was successful.
     */
    boolean deleteById(String justificationId);
}