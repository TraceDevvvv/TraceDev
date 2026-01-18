package com.example.ports;

import com.example.entities.Justice;
import java.util.Optional;

/**
 * Port interface for the justice repository.
 */
public interface JusticeRepositoryPort {
    /**
     * Finds a justice by ID.
     */
    Optional<Justice> findById(int id);

    /**
     * Saves a justice.
     */
    Justice save(Justice justice);
}