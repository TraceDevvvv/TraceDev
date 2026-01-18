package com.example.service;

import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import com.example.exception.ETOURConnectionException;
import com.example.model.Site;
import com.example.repository.ISiteRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete search service implementing ISearchService.
 * Includes response time requirement (REQ-012) and connection error handling (REQ-011).
 */
public class SearchService implements ISearchService {
    private ISiteRepository siteRepository;

    public SearchService(ISiteRepository repository) {
        this.siteRepository = repository;
    }

    /**
     * Searches sites based on criteria.
     * Must complete within 2 seconds (REQ-012).
     * Handles connection errors.
     */
    @Override
    public List<SiteDTO> searchSites(SearchCriteriaDTO criteria) {
        long startTime = System.currentTimeMillis();
        try {
            List<Site> sites = siteRepository.findByCriteria(criteria);
            List<SiteDTO> dtos = convertToDTO(sites);
            long endTime = System.currentTimeMillis();
            // Check response time (REQ-012)
            if (endTime - startTime > 2000) {
                System.err.println("Warning: Search operation took longer than 2 seconds.");
            }
            return dtos;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof ETOURConnectionException) {
                handleConnectionError();
                throw new RuntimeException("Search failed due to connection error", e);
            }
            throw e;
        }
    }

    /**
     * Converts a list of Site entities to SiteDTOs.
     */
    public List<SiteDTO> convertToDTO(List<Site> sites) {
        List<SiteDTO> dtos = new ArrayList<>();
        for (Site site : sites) {
            // Simplified mapping; imageUrl is not in Site entity, so using placeholder.
            SiteDTO dto = new SiteDTO(
                site.getId(),
                site.getName(),
                site.getDescription(),
                site.getLocation(),
                "https://example.com/images/" + site.getId() + ".jpg" // placeholder
            );
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Handles connection errors (REQ-011).
     */
    public void handleConnectionError() {
        // Log error, maybe trigger retry logic, etc.
        System.err.println("ETOUR server connection error handled in SearchService.");
    }
}