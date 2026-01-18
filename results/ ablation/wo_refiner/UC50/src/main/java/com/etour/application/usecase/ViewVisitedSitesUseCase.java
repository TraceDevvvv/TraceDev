package com.etour.application.usecase;

import com.etour.application.dto.SiteDto;
import com.etour.application.repository.VisitedSitesRepository;
import java.util.List;

/**
 * Use case orchestrating the "ViewVisitedSites" business flow.
 */
public class ViewVisitedSitesUseCase {
    private VisitedSitesRepository repository;

    public ViewVisitedSitesUseCase(VisitedSitesRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to fetch visited sites for a given tourist.
     * @param touristId ID of the tourist
     * @return List of visited sites as DTOs
     */
    public List<SiteDto> execute(String touristId) {
        return repository.findSitesByTouristId(touristId);
    }
}