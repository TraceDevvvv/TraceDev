package com.example.infrastructure;

import com.example.domain.CulturalSite;
import java.util.List;

/**
 * Interface for cultural site repositories.
 */
public interface CulturalSiteRepository {
    List<CulturalSite> findByCriteria(Object criteria);
}