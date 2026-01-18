package com.example.report;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * ReportService class
 * Acts as an intermediary between the ReportViewController and data repositories/report generator.
 * Orchestrates the fetching of data and generation of reports.
 */
public class ReportService {
    private ILocationRepository locationRepository;
    private ISiteFeedbackRepository siteFeedbackRepository;
    private ReportGenerator reportGenerator;
    private Random random = new Random(); // For simulating errors

    /**
     * Constructor for ReportService, injecting necessary dependencies.
     * @param locationRepository Repository for location data.
     * @param siteFeedbackRepository Repository for site feedback data.
     * @param reportGenerator Generator for statistical reports.
     */
    public ReportService(ILocationRepository locationRepository,
                         ISiteFeedbackRepository siteFeedbackRepository,
                         ReportGenerator reportGenerator) {
        this.locationRepository = locationRepository;
        this.siteFeedbackRepository = siteFeedbackRepository;
        this.reportGenerator = reportGenerator;
    }

    /**
     * Retrieves all locations and converts them into DTOs for the view layer.
     * @return A list of LocationDTO objects.
     */
    public List<LocationDTO> getLocations() {
        System.out.println("[RS] Calling locationRepository.findAll().");
        List<Location> locations = locationRepository.findAll();
        // SD: m7
        System.out.println("[RS] Converting List<Location> to List<LocationDTO>.");
        return locations.stream()
                .map(Location::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Generates a statistical report for a given location ID.
     * Simulates a "Connection Interrupted" error with a 30% chance.
     * @param locationId The ID of the location for which to generate the report.
     * @return A StatisticalReportDTO containing the generated report data.
     * @throws RuntimeException If a simulated connection error occurs.
     */
    public StatisticalReportDTO generateStatisticalReport(String locationId) {
        System.out.println("[RS] Generating statistical report for location ID: " + locationId);

        // Simulate connection interruption (30% chance)
        if (random.nextInt(100) < 30) {
            System.err.println("[RS] --- SIMULATED ERROR: Connection Interrupted! ---");
            // SD: m26
            throw new RuntimeException("Connection to server interrupted. Please try again later.");
        }

        System.out.println("[RS] Calling locationRepository.findById(" + locationId + ").");
        // SD: m16
        Location location = locationRepository.findById(locationId);
        if (location == null) {
            throw new IllegalArgumentException("Location with ID " + locationId + " not found.");
        }

        System.out.println("[RS] Calling siteFeedbackRepository.findByLocationId(" + locationId + ").");
        List<SiteFeedback> feedback = siteFeedbackRepository.findByLocationId(locationId);

        System.out.println("[RS] Calling reportGenerator.prepareReport().");
        // SD: m22
        StatisticalReport statisticalReport = reportGenerator.prepareReport(location, feedback);

        // SD: m23
        System.out.println("[RS] Converting StatisticalReport to StatisticalReportDTO.");
        // SD: m24
        return statisticalReport.toDTO();
    }
}