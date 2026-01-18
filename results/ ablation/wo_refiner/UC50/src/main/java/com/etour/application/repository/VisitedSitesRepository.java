package com.etour.application.repository;

import com.etour.application.dto.SiteDto;
import java.util.List;

/**
 * Repository interface for fetching visited sites data.
 */
public interface VisitedSitesRepository {
    List<SiteDto> findSitesByTouristId(String touristId);
}