package com.example.infrastructure.persistence;

import com.example.domain.Justification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Justification entities.
 */
public interface JustificationRepository {
    Justification save(Justification justification);
    Optional<Justification> findById(UUID id);
    List<Justification> findByAbsenceId(UUID absenceId);
}