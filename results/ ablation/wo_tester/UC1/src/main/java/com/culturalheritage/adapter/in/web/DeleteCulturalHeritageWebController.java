package com.culturalheritage.adapter.in.web;

import com.culturalheritage.application.controller.DeleteCulturalHeritageUseCaseController;
import com.culturalheritage.application.request.DeleteCulturalHeritageRequest;
import com.culturalheritage.application.response.DeleteCulturalHeritageResponse;

/**
 * Web Controller (Boundary) handling HTTP requests.
 * Interacts with Agency Operator (UI) and delegates to use case controller.
 */
public class DeleteCulturalHeritageWebController {
    private DeleteCulturalHeritageUseCaseController useCaseController;

    public DeleteCulturalHeritageWebController(DeleteCulturalHeritageUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }

    // Handles selection of cultural heritage (Step 2)
    public void selectCulturalHeritage(String id) {
        // In a real scenario, store selection in session or context
        System.out.println("Cultural heritage selected: " + id);
    }

    // Handles activation of delete function (Step 3)
    public DeleteCulturalHeritageResponse activateDelete(String culturalHeritageId,
                                                         String confirmationToken,
                                                         String operatorId,
                                                         boolean isCancelled) {
        // Step 3: Create request
        DeleteCulturalHeritageRequest request = new DeleteCulturalHeritageRequest(
                culturalHeritageId, confirmationToken, operatorId);
        request.setIsCancelled(isCancelled);

        // Step 3: Delegate to use case controller
        return useCaseController.deleteCulturalHeritage(request);
    }

    // Main handler for delete request (used from UI)
    public DeleteCulturalHeritageResponse handleDeleteRequest(DeleteCulturalHeritageRequest request) {
        return useCaseController.deleteCulturalHeritage(request);
    }
}