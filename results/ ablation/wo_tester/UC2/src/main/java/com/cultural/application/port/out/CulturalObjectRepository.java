package com.cultural.application.port.out;

import com.cultural.domain.model.CulturalObject;
import java.util.List;

/**
 * Repository interface for CulturalObject persistence.
 */
public interface CulturalObjectRepository {
    CulturalObject save(CulturalObject culturalObject);
    CulturalObject findById(String id);
    List<CulturalObject> findByNameAndLocation(String name, String location);
    List<CulturalObject> findAll();
}