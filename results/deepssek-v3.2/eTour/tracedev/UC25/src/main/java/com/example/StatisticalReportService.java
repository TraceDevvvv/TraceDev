package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Service layer that orchestrates the report generation process.
 * Handles business logic and data conversion.
 */
public class StatisticalReportService {
    private LocationRepository locationRepository;
    private SiteFeedbackRepository siteFeedbackRepository;
    private StatisticalReportGenerator reportGenerator;

    public StatisticalReportService() {
        this.locationRepository = new LocationRepository();
        this.siteFeedbackRepository = new SiteFeedbackRepository();
        this.reportGenerator = new StatisticalReportGenerator();
    }

    /**
     * Retrieves all locations as DTOs.
     * @return List of LocationDTO objects.
     * @throws ConnectionException if connection fails.
     */
    public List<LocationDTO> getAllLocationDTOs() throws ConnectionException {
        List<Location> locations = locationRepository.findAll();
        return convertToDTO(locations);
    }

    /**
     * Generates a statistical report for a given location.
     * @param locationId The ID of the location.
     * @return StatisticalReportDTO for the location.
     * @throws ConnectionException if connection fails.
     */
    public StatisticalReportDTO generateReport(String locationId) throws ConnectionException {
        List<SiteFeedback> feedbackList = siteFeedbackRepository.findFeedbackByLocation(locationId);
        StatisticalReport report = reportGenerator.prepareReport(feedbackList);
        return convertToDTO(report);
    }

    /**
     * Converts a list of Location objects to LocationDTO objects.
     * @param locations List of Location.
     * @return List of LocationDTO.
     */
    public List<LocationDTO> convertToDTO(List<Location> locations) {
        List<LocationDTO> dtos = new ArrayList<>();
        for (Location loc : locations) {
            dtos.add(new LocationDTO(loc.getId(), loc.getName()));
        }
        return dtos;
    }

    /**
     * Converts a StatisticalReport object to StatisticalReportDTO.
     * @param report The StatisticalReport object.
     * @return StatisticalReportDTO.
     */
    public StatisticalReportDTO convertToDTO(StatisticalReport report) {
        return new StatisticalReportDTO(report.getLocationName(), report.getStatistics());
    }

    /**
     * Handler for sequence message: prepareReport(List<SiteFeedback>)
     * @param feedbackList List of SiteFeedback objects.
     * @return A StatisticalReport object.
     * @throws ConnectionException if there is an interruption during generation.
     */
    public StatisticalReport prepareReport(List<SiteFeedback> feedbackList) throws ConnectionException {
        return reportGenerator.prepareReport(feedbackList);
    }

    /**
     * Handles connection exceptions.
     * Logs the exception and could perform recovery actions.
     * @param e The ConnectionException.
     */
    public void handleConnectionException(ConnectionException e) {
        System.err.println("ConnectionException handled: " + e.getMessage());
    }
}