package com.example.ui;

import com.example.application.ViewConventionHistoryUseCase;
import com.example.application.requestresponse.ViewConventionHistoryRequest;
import com.example.application.requestresponse.ViewConventionHistoryResponse;
import com.example.domain.AgencyService;

/**
 * Controller coordinating between the UI and the use case.
 */
public class ConventionHistoryController {

    private final ViewConventionHistoryUseCase useCase;
    private final AgencyService agencyService;
    private final ConventionHistoryView view;

    public ConventionHistoryController(ViewConventionHistoryUseCase useCase, AgencyService agencyService, ConventionHistoryView view) {
        this.useCase = useCase;
        this.agencyService = agencyService;
        this.view = view;
    }

    /**
     * Called by the view to display convention history. Includes precondition validation.
     */
    public void displayConventionHistory(String pointOfRestId, String agencyId) {
        // Precondition check as per sequence diagram
        if (!validateAgencyIsDesignatedPoint(agencyId)) {
            view.showError("Agency is not a designated point of rest.");
            return;
        }

        ViewConventionHistoryRequest request = new ViewConventionHistoryRequest();
        request.setPointOfRestId(pointOfRestId);
        request.setAgencyId(agencyId);

        ViewConventionHistoryResponse response = useCase.execute(request);
        if (response.isSuccess()) {
            view.showHistory(response.getConventions());
        } else {
            view.showError(response.getErrorMessage());
        }
    }

    /**
     * Validates that the agency is a designated point of rest (entry condition).
     */
    private boolean validateAgencyIsDesignatedPoint(String agencyId) {
        return agencyService.isDesignatedPointOfRest(agencyId);
    }
}