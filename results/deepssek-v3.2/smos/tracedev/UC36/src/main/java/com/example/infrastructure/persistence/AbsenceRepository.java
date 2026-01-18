package com.example.infrastructure.persistence;

import com.example.domain.Absence;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Absence entities.
 */
public interface AbsenceRepository {
    Optional<Absence> findById(UUID id);
}