package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import com.example.dto.*;
import com.example.handler.ErroredUseCaseHandler;
import java.util.List;

/**
 * Controller that handles the banner insertion use case.
 * Coordinates between serv, repositories, and the operator.
 */
public class InsertBannerController {
    private BannerService bannerService;
    private RefreshmentPointService refreshmentPointService;
    private NotificationService notificationService;
    private ErroredUseCaseHandler errorHandler;
    private ServerConnectionService serverConnectionService;
    private AuthenticationService authenticationService;

    public InsertBannerController(BannerService bannerService,
                                 RefreshmentPointService refreshmentPointService,
                                 NotificationService notificationService,
                                 ErroredUseCaseHandler errorHandler,
                                 ServerConnectionService serverConnectionService,
                                 AuthenticationService authenticationService) {
        this.bannerService = bannerService;
        this.refreshmentPointService = refreshmentPointService;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
        this.serverConnectionService = serverConnectionService;
        this.authenticationService = authenticationService;
    }

    /**
     * Main method to handle banner insertion request.
     */
    public OperationResultDTO handleInsertBannerRequest(InsertBannerRequestDTO request) {
        System.out.println("Handling insert banner request for point: " + request.getRefreshmentPointId());
        
        // Validate request
        ValidationResult validationResult = validateRequest(request);
        if (!validationResult.isValid()) {
            return new OperationResultDTO(false, "Invalid request: " + validationResult.getErrors().get(0), "VALIDATION_ERROR", 0);
        }
        
        // Create banner from request
        Banner banner = new Banner(
            "BANNER_" + System.currentTimeMillis(),
            request.getImageData(),
            request.getImageFormat(),
            request.getSizeInKB()
        );
        banner.setAssociatedPointId(request.getRefreshmentPointId());
        
        // Validate banner image
        ValidationResult imageValidation = bannerService.validateBannerImage(banner);
        if (!imageValidation.isValid()) {
            errorHandler.handleError(new RuntimeException("Invalid image: " + imageValidation.getErrors()));
            notificationService.sendErrorNotification(null, "Invalid image");
            return new OperationResultDTO(false, "Invalid image", "IMAGE_VALIDATION_ERROR", 0);
        }
        
        // Check banner limit
        if (refreshmentPointService.hasReachedBannerLimit(request.getRefreshmentPointId())) {
            notificationService.sendErrorNotification(null, "Maximum banners reached");
            return new OperationResultDTO(false, "Maximum banners reached", "BANNER_LIMIT_REACHED", 0);
        }
        
        // Perform insertion
        OperationResultDTO result = bannerService.insertBanner(banner, request.getRefreshmentPointId());
        
        if (result.isSuccess()) {
            notificationService.sendSuccessNotification(null);
        } else {
            notificationService.sendErrorNotification(null, "Insertion failed: " + result.getMessage());
        }
        
        return result;
    }

    /**
     * Validates the insertion request DTO.
     */
    public ValidationResult validateRequest(InsertBannerRequestDTO request) {
        ValidationResult result = new ValidationResult();
        
        if (request.getRefreshmentPointId() == null || request.getRefreshmentPointId().isEmpty()) {
            result.addError("Refreshment point ID is required");
        }
        
        if (request.getImageData() == null || request.getImageData().length == 0) {
            result.addError("Image data is required");
        }
        
        if (request.getImageFormat() == null || request.getImageFormat().isEmpty()) {
            result.addError("Image format is required");
        }
        
        if (request.getSizeInKB() <= 0) {
            result.addError("Image size must be positive");
        }
        
        return result;
    }

    /**
     * Confirms the insertion operation.
     */
    public OperationResultDTO confirmInsertion(String confirmationId) {
        System.out.println("Confirming insertion with ID: " + confirmationId);
        // In a real implementation, this would verify the confirmation
        return new OperationResultDTO(true, "Insertion confirmed", "SUCCESS", 0);
    }

    /**
     * Requests banner form for a specific point.
     */
    public void requestBannerForm(String pointId) {
        System.out.println("Requesting banner form for point: " + pointId);
        RefreshmentPoint point = refreshmentPointService.getRefreshmentPointById(pointId);
        if (point != null) {
            displayImageSelectionForm(point);
        }
    }

    /**
     * Handles image selection.
     */
    public void selectImage(byte[] imageData, String format, int size) {
        System.out.println("Image selected: format=" + format + ", size=" + size + "KB");
    }

    /**
     * Displays the image selection form for a refreshment point.
     */
    public void displayImageSelectionForm(RefreshmentPoint point) {
        System.out.println("Displaying image selection form for point: " + point.getName());
    }

    /**
     * Displays a confirmation dialog.
     */
    public boolean displayConfirmationDialog(String message) {
        System.out.println("Confirmation dialog: " + message);
        // In a real implementation, this would show UI dialog
        return true; // Assume user confirms
    }

    /**
     * Checks if operator is logged in.
     */
    public boolean loginCheck(String operatorId) {
        return authenticationService.isLoggedIn(operatorId);
    }

    /**
     * Handles operation cancellation.
     */
    public void operationCancelled() {
        System.out.println("Operation cancelled - cleanup performed");
    }

    /**
     * Entry point for the sequence diagram flow.
     */
    public void startInsertBannerFlow(AgencyOperator operator) {
        System.out.println("=== Starting Insert Banner Flow ===");
        
        // Login check
        if (!loginCheck(operator.getOperatorId())) {
            System.out.println("Operator not logged in");
            return;
        }
        
        // Server connection check
        if (!serverConnectionService.checkConnection()) {
            errorHandler.handleError(new RuntimeException("Server connection lost"));
            notificationService.sendErrorNotification(operator, "Server connection lost");
            System.out.println("Display server connection error");
            return;
        }
        
        // Get refreshment points
        List<RefreshmentPoint> points = refreshmentPointService.searchRefreshmentPoints();
        System.out.println("Display refreshment points list (" + points.size() + " points)");
        
        // Simulate operator selecting a point
        if (!points.isEmpty()) {
            RefreshmentPoint selectedPoint = points.get(0);
            operator.selectRefreshmentPoint(selectedPoint);
            requestBannerForm(selectedPoint.getPointId());
            
            // Simulate image selection and submission
            byte[] dummyImage = new byte[100];
            InsertBannerRequestDTO request = new InsertBannerRequestDTO(
                selectedPoint.getPointId(),
                dummyImage,
                "JPEG",
                50
            );
            
            // Check if operator cancels (simulated)
            boolean shouldCancel = false; // Change to true to test cancel flow
            if (shouldCancel) {
                operator.cancelOperation();
                refreshmentPointService.resetSelection(selectedPoint.getPointId());
                operationCancelled();
                notificationService.sendNotification(operator, "Operation cancelled");
                System.out.println("Operation cancelled");
                return;
            }
            
            // Show confirmation dialog
            if (displayConfirmationDialog("Insert banner?")) {
                operator.confirmInsertion("CONFIRM_" + System.currentTimeMillis());
                OperationResultDTO result = handleInsertBannerRequest(request);
                System.out.println("Insertion result: " + result.getMessage());
            }
        }
    }
}