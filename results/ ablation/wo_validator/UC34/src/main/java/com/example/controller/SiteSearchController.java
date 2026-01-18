package com.example.controller;

import com.example.domain.Coordinates;
import com.example.domain.SearchCriteria;
import com.example.domain.Site;
import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import com.example.infrastructure.ETOURSiteRepository;
import com.example.infrastructure.SiteRepository;
import com.example.service.GeolocationService;
import com.example.service.IGeolocationService;
import java.util.ArrayList;
import java.util.List;

/**
 * Application Layer controller.
 * Orchestrates the site search use case flow.
 */
public class SiteSearchController {
    private IGeolocationService geoService;
    private SiteRepository siteRepository;

    public SiteSearchController() {
        this.geoService = new GeolocationService();
        this.siteRepository = new ETOURSiteRepository();
    }

    /**
     * Main search method as per sequence diagram.
     * @param criteriaDTO DTO with user‑provided criteria
     * @return List of SiteDTOs for presentation
     */
    public List<SiteDTO> searchSites(SearchCriteriaDTO criteriaDTO) {
        try {
            // Step: get user's current position from GeolocationService
            Coordinates userPosition = geoService.getUserPosition();

            // Step: convert DTO to Domain SearchCriteria
            SearchCriteria criteria = convertToDomain(criteriaDTO);
            // set location in SearchCriteria (as per sequence diagram)
            criteria.setLocation(userPosition);

            // Step: call repository to find sites
            List<Site> sites = siteRepository.findByCriteria(criteria);

            // Step: convert Sites to SiteDTOs
            return convertToDTOs(sites);
        } catch (RepositoryException e) {
            // Handle repository exception (connection failed)
            // In a full implementation we would propagate the error to the boundary.
            // For simplicity, we return an empty list and log the error.
            System.err.println("Repository error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Converts SearchCriteriaDTO to domain SearchCriteria.
     */
    private SearchCriteria convertToDomain(SearchCriteriaDTO dto) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSiteName(dto.getSiteName());
        criteria.setCulturalPeriod(dto.getCulturalPeriod());
        criteria.setMaxDistance(dto.getMaxDistance());
        // location will be set later using geolocation service
        return criteria;
    }

    /**
     * Converts a list of domain Site objects to a list of SiteDTOs.
     */
    private List<SiteDTO> convertToDTOs(List<Site> sites) {
        List<SiteDTO> dtos = new ArrayList<>();
        for (Site site : sites) {
            SiteDTO dto = new SiteDTO();
            dto.setId(site.getId());
            dto.setName(site.getName());
            dto.setDescription(site.getDescription());
            dto.setCulturalPeriod(site.getCulturalPeriod());
            Coordinates loc = site.getLocation();
            dto.setLatitude(loc.getLatitude());
            dto.setLongitude(loc.getLongitude());
            dtos.add(dto);
        }
        return dtos;
    }
}

/**
 * Custom exception for repository failures.
 */
class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }
}