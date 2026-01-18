
package com.system.controller;

import com.system.usecase.InsertBannerUseCase;
import com.system.repository.RefreshmentPointRepository;
import com.system.repository.RefreshmentPointRepositoryImpl;
import com.system.ETOURService;
import com.system.FormData;
import com.system.Response;
import java.util.List;

/**
 * Controller handling HTTP requests for banner operations.
 */
public class BannerController {
    private InsertBannerUseCase insertBannerUseCase;
    private RefreshmentPointRepository pointRepository;
    private ETOURService etourService;

    public BannerController(InsertBannerUseCase useCase) {
        this.insertBannerUseCase = useCase;
        this.pointRepository = new RefreshmentPointRepositoryImpl();
        this.etourService = new ETOURService();
    }

    /**
     * Simulates a login operation.
     * @return true if login successful
     */
    public boolean login() {
        // Simplified login; in real app, validate credentials.
        return true;
    }

    /**
     * Returns a list of all refreshment points.
     */
    public List<com.system.RefreshmentPoint> getRefreshmentPoints() {
        return pointRepository.findAll();
    }

    /**
     * Prepares form data for inserting a banner at a given point.
     * @param pointId the ID of the refreshment point
     * @return form data containing point information
     */
    public FormData showInsertForm(String pointId) {
        FormData form = new FormData();
        var pointOpt = pointRepository.findById(pointId);
        if (pointOpt.isPresent()) {
            var point = pointOpt.get();
            form.setPointName(point.getName());
            form.setCurrentBannerCount(point.getCurrentBannerCount());
            form.setMaxBanners(point.getMaxBanners());
        }
        return form;
    }

    /**
     * Handles image upload and validation.
     * @param imageData the uploaded image bytes
     * @param pointId the target refreshment point ID
     * @return a response indicating the result
     */
    public Response uploadImage(byte[] imageData, String pointId) {
        com.system.usecase.InsertBannerUseCase.Result result = insertBannerUseCase.execute(pointId, imageData);
        if (result.isSuccess()) {
            // If validation passes, return a confirmation request (as per sequence diagram).
            return new Response("SUCCESS", "Image validated. Please confirm insertion.", null);
        } else {
            return handleError("Invalid image: " + String.join(", ", result.getErrors()));
        }
    }

    /**
     * Confirms the banner insertion after user approval.
     * @param pointId the point ID
     * @param imageData the image data
     * @return a response indicating the result
     */
    public Response confirmInsertion(String pointId, byte[] imageData) {
        // Re-execute the use case; in a real system we might store a pending state.
        com.system.usecase.InsertBannerUseCase.Result result = insertBannerUseCase.execute(pointId, imageData);
        if (result.isSuccess()) {
            return new Response("SUCCESS", "Banner inserted successfully.", null);
        } else {
            return handleError("Insertion failed: " + String.join(", ", result.getErrors()));
        }
    }

    /**
     * Cancels the current operation.
     */
    public Response cancelOperation() {
        return new Response("CANCELLED", "Operation cancelled.", null);
    }

    /**
     * Handles errors and returns an error response.
     */
    public Response handleError(String error) {
        System.err.println("Error occurred: " + error);
        return new Response("ERROR", error, null);
    }
}
