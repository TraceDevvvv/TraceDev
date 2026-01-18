package com.example.infrastructure.persistence;

import com.example.domain.Absence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple in-memory implementation of AbsenceRepository.
 */
public class AbsenceRepositoryImpl implements AbsenceRepository {
    private Map<UUID, Absence> dataStore = new HashMap<>();

    public AbsenceRepositoryImpl() {
        // Prepopulate with a sample absence for testing
        dataStore.put(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                new Absence(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                        UUID.fromString("223e4567-e89b-12d3-a456-426614174000"),
                        java.time.LocalDate.now(), "pending"));
    }

    @Override
    public Optional<Absence> findById(UUID id) {
        return Optional.ofNullable(dataStore.get(id));
    }
}