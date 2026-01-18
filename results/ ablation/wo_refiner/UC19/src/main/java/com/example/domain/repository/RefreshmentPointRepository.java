package com.example.domain.repository;

import com.example.domain.RefreshmentPoint;
import java.util.List;

/**
 * Repository port for RefreshmentPoint entities.
 * Implements REQ-006 requirement.
 */
public interface RefreshmentPointRepository {
    List<RefreshmentPoint> searchByName(String name);
}