package com.touristagency.application.interactor;

import com.touristagency.application.ToggleStatusRequest;
import com.touristagency.application.ToggleStatusResponse;
import com.touristagency.application.usecase.ToggleTouristStatusUseCase;
import com.touristagency.domain.Tourist;
import com.touristagency.domain.TouristRepository;

/**
 * Implementation of the use case.
 * Contains the business logic for activating/deactivating a tourist.
 * Quality Requirement: Operation must complete within 5 seconds.
 */
public class ToggleTouristStatusInteractor implements ToggleTouristStatusUseCase {
    private final TouristRepository touristRepository;

    public ToggleTouristStatusInteractor(TouristRepository repository) {
        this.touristRepository = repository;
    }

    @Override
    public ToggleStatusResponse execute(ToggleStatusRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            // 1. Find tourist
            Tourist tourist = touristRepository.findById(request.getTouristId());
            if (tourist == null) {
                return new ToggleStatusResponse(false, "Tourist not found with ID: " + request.getTouristId());
            }

            // 2. Apply status change
            boolean targetStatus = request.getTargetStatus();
            if (targetStatus) {
                tourist.activate();
            } else {
                tourist.deactivate();
            }

            // 3. Save updated tourist
            touristRepository.save(tourist);

            // 4. Check time constraint (Quality Requirement)
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed > 5000) {
                // Still return success, but log warning in a real system
                return new ToggleStatusResponse(true,
                        "Operation completed but exceeded 5-second target (took " + elapsed + "ms). Tourist status updated.");
            }

            return new ToggleStatusResponse(true, "Tourist status successfully updated to " + (targetStatus ? "ACTIVE" : "INACTIVE"));
        } catch (Exception e) {
            return new ToggleStatusResponse(false, "Error during operation: " + e.getMessage());
        }
    }
}