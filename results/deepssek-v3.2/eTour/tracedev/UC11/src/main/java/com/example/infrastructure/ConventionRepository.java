package com.example.infrastructure;

import com.example.domain.Convention;
import java.util.List;

/**
 * Repository interface for convention persistence.
 */
public interface ConventionRepository {
    List<Convention> findByPointOfRestId(String pointOfRestId);
}