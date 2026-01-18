package com.example.application;

import com.example.application.requestresponse.ViewConventionHistoryRequest;
import com.example.application.requestresponse.ViewConventionHistoryResponse;
import com.example.domain.Convention;
import com.example.infrastructure.ConventionRepository;
import com.example.domain.AgencyService;
import java.util.List;

/**
 * Interactor implementing the use case. Validates preconditions via AgencyService.
 */
public class ViewConventionHistoryInteractor implements ViewConventionHistoryUseCase {

    private final ConventionRepository conventionRepository;
    private final AgencyService agencyService;

    public ViewConventionHistoryInteractor(ConventionRepository conventionRepository, AgencyService agencyService) {
        this.conventionRepository = conventionRepository;
        this.agencyService = agencyService;
    }

    @Override
    public ViewConventionHistoryResponse execute(ViewConventionHistoryRequest request) {
        // Entry condition validation
        if (!agencyService.isDesignatedPointOfRest(request.getAgencyId())) {
            return new ViewConventionHistoryResponse(null, false, "Agency is not a designated point of rest.");
        }

        List<Convention> conventions = conventionRepository.findByPointOfRestId(request.getPointOfRestId());
        if (conventions.isEmpty()) {
            return new ViewConventionHistoryResponse(conventions, false, "No conventions found for the given point of rest.");
        }
        return new ViewConventionHistoryResponse(conventions, true, null);
    }
}