package com.example.ports;

import com.example.domain.Convention;
import java.util.Optional;

/**
 * Port interface for convention persistence operations.
 */
public interface ConventionRepository {
    Optional<Convention> findById(String conventionId);
    Convention save(Convention convention);
}