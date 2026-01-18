package com.example.presentation;

import com.example.application.SearchSiteService;
import com.example.application.ReportGenerator;
import com.example.application.AuthenticationService;
import com.example.infrastructure.PlaceRepository;
import com.example.domain.Place;
import com.example.domain.Location;
import com.example.domain.SiteFeedback;
import com.example.domain.StatisticalReport;
import com.example.domain.DataSourceException;
import java.util.List;

/**
 * Controller for generating statistical reports.
 * Validates user authentication before processing requests.
 * Added to satisfy requirement Entry Conditions.
 */
public class GenerateStatisticalReportController {
    private SearchSiteService searchSiteService;
    private ReportGenerator reportGenerator;
    private PlaceRepository placeRepository;
    private AuthenticationService authenticationService;
    private AgencyOperatorUI ui; // Added to allow error display

    // Current user ID for simulation. In real app, obtained from session.
    private String currentUserId = "user123";

    public GenerateStatisticalReportController(SearchSiteService searchSiteService,
                                               ReportGenerator reportGenerator,
                                               PlaceRepository placeRepository,
                                               AuthenticationService authenticationService,
                                               AgencyOperatorUI ui) {
        this.searchSiteService = searchSiteService;
        this.reportGenerator = reportGenerator;
        this.placeRepository = placeRepository;
        this.authenticationService = authenticationService;
        this.ui = ui;
    }

    /**
     * Handles initial request to generate report (without location).
     * Corresponds to sequence diagram step: UI -> Controller : handleGenerateReportRequest()
     */
    public void handleGenerateReportRequest() {
        // Entry Condition: User Authentication Check
        if (!authenticationService.isUserLoggedIn(currentUserId)) {
            ui.displayAuthenticationError();
            return;
        }

        List<Place> places = placeRepository.findAll();
        ui.displayPlaceList(places);
    }

    /**
     * Handles request to generate report for a specific location.
     * Added to satisfy requirement Flow of Events 5.
     */
    public void handleGenerateReportRequest(Location location) {
        // Entry Condition: User Authentication Check
        if (!authenticationService.isUserLoggedIn(currentUserId)) {
            ui.displayAuthenticationError();
            return;
        }

        try {
            List<SiteFeedback> feedbackList = searchSiteService.searchFeedbackForLocation(location);
            StatisticalReport report = reportGenerator.generateReport(feedbackList);
            ui.renderReport(report);
        } catch (DataSourceException e) {
            handleError("Could not fetch feedback data");
            ui.displayErrorMessage("Could not fetch feedback data.");
        } catch (Exception e) {
            // Catch other exceptions like network failures (simulated)
            handleError("Server unavailable");
            ui.displayErrorMessage("Server connection failed");
        }
    }

    /**
     * Handles errors internally.
     * Added to satisfy requirement Alternate Flow.
     */
    public void handleError(String errorMessage) {
        System.err.println("Controller error: " + errorMessage);
        // Additional error handling logic could be placed here.
    }
}