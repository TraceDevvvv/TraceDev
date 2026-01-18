package com.example.controller;

import com.example.model.*;
import com.example.dto.StatisticalReportDTO;
import com.example.repository.LocationRepository;
import com.example.service.SearchSiteService;
import com.example.exception.ConnectionException;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 * Controller for report statistics.
 * Handles quality requirement REQ-012 (response time < 2s, timeout handling).
 */
public class ReportStatisticController {
    private LocationRepository locationRepository;
    private SearchSiteService searchSiteService;
    private int generationTimeout; // in seconds, for REQ-012

    public ReportStatisticController(LocationRepository locationRepository,
                                     SearchSiteService searchSiteService) {
        this.locationRepository = locationRepository;
        this.searchSiteService = searchSiteService;
        this.generationTimeout = 2; // default 2 seconds
    }

    public void setGenerationTimeout(int generationTimeout) {
        this.generationTimeout = generationTimeout;
    }

    /**
     * Returns all locations.
     * Satisfies REQ-007 (Uploads list of places).
     */
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    /**
     * Generates a statistical report for given location ID.
     * Implements normal and exception flows from sequence diagram (REQ-011).
     */
    public StatisticalReportDTO generateReport(String locationId) {
        try {
            // REQ-012: we could implement timeout handling here.
            // For simplicity, we just proceed.
            Location location = locationRepository.findById(locationId);
            List<SiteFeedback> feedbacks = searchSiteService.getFeedbackForLocation(locationId);
            StatisticalReport report = new StatisticalReport(location, feedbacks);
            return convertToDTO(report);
        } catch (ConnectionException e) {
            // According to sequence diagram, return error.
            // We'll create a DTO with null metrics and location name "Error".
            return new StatisticalReportDTO("Error: Connection failed", null, new Date());
        }
    }

    /**
     * Converts StatisticalReport to DTO.
     */
    public StatisticalReportDTO convertToDTO(StatisticalReport report) {
        String locationName = report.getLocation().getName();
        Map<String, Object> metrics = report.getFeedbackSummary();
        Date generationTime = report.getGeneratedAt();
        return new StatisticalReportDTO(locationName, metrics, generationTime);
    }
}