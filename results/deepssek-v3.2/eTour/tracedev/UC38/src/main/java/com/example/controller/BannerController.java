package com.example.controller;

import com.example.dto.*;
import com.example.entity.Banner;
import com.example.repository.*;
import com.example.service.*;
import java.util.UUID;

/**
 * Main controller for banner insertion operations.
 * Implements the complete flow from the sequence diagram.
 */
public class BannerController {
    private ImageValidator imageValidator;
    private BannerLimitValidator bannerLimitValidator;
    private BannerRepository bannerRepository;
    private PointRepository pointRepository;
    private NotificationService notificationService;
    private AuthenticationService authenticationService;
    
    private BannerInsertionRequest currentRequest;
    private Banner currentBanner;
    private InitializationOperator operator;

    public BannerController(
            ImageValidator imageValidator,
            BannerLimitValidator bannerLimitValidator,
            BannerRepository bannerRepository,
            PointRepository pointRepository,
            NotificationService notificationService,
            AuthenticationService authenticationService) {
        this.imageValidator = imageValidator;
        this.bannerLimitValidator = bannerLimitValidator;
        this.bannerRepository = bannerRepository;
        this.pointRepository = pointRepository;
        this.notificationService = notificationService;
        this.authenticationService = authenticationService;
    }

    public void setOperator(InitializationOperator operator) {
        this.operator = operator;
    }

    public BannerInsertionResponse handleInsertBanner(BannerInsertionRequest request) {
        this.currentRequest = request;
        
        // Step 1: Authenticate operator
        // Assumption: operatorId is used for authentication in this simplified version
        if (!authenticationService.authenticate(request.getOperatorId())) {
            return new BannerInsertionResponse(false, "Authentication failed");
        }
        
        // Step 2: Show image selection form
        displayImageForm();
        showImageSelectionForm();
        
        // Step 3: Image should be selected at this point (mocked in this flow)
        if (request.getImageData() == null || request.getImageData().length == 0) {
            notificationService.notifyFailure(request.getOperatorId(), "No image selected");
            return new BannerInsertionResponse(false, "No image selected", null, "NO_IMAGE");
        }
        
        // Step 4: Validate image
        ValidationResult imageValidation = validateImage(request.getImageData());
        if (!imageValidation.isValid()) {
            notificationService.notifyFailure(request.getOperatorId(), imageValidation.getErrorMessage());
            displayErrorMessage(imageValidation.getErrorMessage());
            return errorResponse(imageValidation.getErrorMessage());
        }
        
        // Step 5: Check banner limit
        boolean limitValid = checkBannerLimit(request.getPointId());
        if (!limitValid) {
            notificationService.notifyFailure(request.getOperatorId(), "Maximum banners reached for this point");
            displayLimitError("Maximum banners reached for this point");
            return limitExceededResponse("Maximum banners reached");
        }
        
        // Step 6: Create BannerInsertionRequest (sequence diagram message m7)
        createBannerInsertionRequest(request);
        
        // Step 7: Request confirmation
        requestConfirmation();
        showConfirmationDialog();
        
        // In the real flow, the operator would confirm here
        // For this simulation, we proceed as if confirmed
        
        // Step 8: Create and store banner
        boolean stored = storeBanner(createBannerFromRequest(request));
        if (!stored) {
            notificationService.notifyFailure(request.getOperatorId(), "Failed to store banner");
            return new BannerInsertionResponse(false, "Storage failed", null, "STORAGE_ERROR");
        }
        
        // Step 9: Notify success
        notificationService.notifySuccess(request.getOperatorId());
        displaySuccessMessage("Banner insertion successful");
        
        return new BannerInsertionResponse(true, "Banner inserted successfully", 
                                         currentBanner.getBannerId(), null);
    }

    public ValidationResult validateImage(byte[] imageData) {
        return imageValidator.validate(imageData);
    }

    public boolean checkBannerLimit(String pointId) {
        int currentCount = bannerRepository.countByPointId(pointId);
        int maxBanners = bannerLimitValidator.getMaxBannersForPoint(pointId);
        return currentCount < maxBanners;
    }

    public void showImageSelectionForm() {
        System.out.println("Displaying image selection form...");
        // In a real application, this would show a UI form for image selection
    }

    public void requestConfirmation() {
        System.out.println("Requesting user confirmation for banner insertion...");
        // In a real application, this would show a confirmation dialog
    }

    public boolean storeBanner(Banner banner) {
        currentBanner = banner;
        try {
            return bannerRepository.save(banner);
        } catch (DataAccessException e) {
            // Handle server connection error as shown in alternative flow
            displayConnectionError(e.getMessage());
            errorResponse("Connection error: " + e.getMessage());
            return false;
        }
    }

    private Banner createBannerFromRequest(BannerInsertionRequest request) {
        String bannerId = UUID.randomUUID().toString();
        return new Banner(bannerId, request.getPointId(), request.getImageData());
    }

    /**
     * Method for sequence diagram message m4
     */
    public void displayImageForm() {
        System.out.println("Displaying image form...");
    }

    /**
     * Method for sequence diagram message m6 (called by operator)
     */
    public void selectImage(byte[] imageData) {
        System.out.println("Image selected in controller with data size: " + (imageData != null ? imageData.length : 0));
        if (operator != null) {
            operator.selectImage(imageData);
        }
    }

    /**
     * Method for sequence diagram message m7
     */
    public void createBannerInsertionRequest(BannerInsertionRequest request) {
        System.out.println("Creating BannerInsertionRequest for point: " + request.getPointId());
        currentRequest = request;
    }

    /**
     * Method for sequence diagram message m8 (called by operator)
     */
    public void sendRequest(BannerInsertionRequest request) {
        System.out.println("Processing banner insertion request from operator");
        handleInsertBanner(request);
    }

    /**
     * Method for sequence diagram message m12
     */
    public void displayErrorMessage(String message) {
        System.out.println("Displaying error message to operator: " + message);
        if (operator != null) {
            notificationService.notifyFailure(currentRequest != null ? currentRequest.getOperatorId() : "unknown", message);
        }
    }

    /**
     * Method for sequence diagram message m13
     */
    public BannerInsertionResponse errorResponse(String errorMessage) {
        return new BannerInsertionResponse(false, errorMessage, null, "ERROR");
    }

    /**
     * Method for sequence diagram message m19
     */
    public void displayLimitError(String message) {
        System.out.println("Displaying limit error to operator: " + message);
        if (operator != null) {
            notificationService.notifyFailure(currentRequest != null ? currentRequest.getOperatorId() : "unknown", message);
        }
    }

    /**
     * Method for sequence diagram message m20
     */
    public BannerInsertionResponse limitExceededResponse(String message) {
        return new BannerInsertionResponse(false, message, null, "LIMIT_EXCEEDED");
    }

    /**
     * Method for sequence diagram message m23
     */
    public void showConfirmationDialog() {
        System.out.println("Showing confirmation dialog to operator...");
    }

    /**
     * Method for sequence diagram message m24 (called by operator)
     */
    public void confirmInsertion() {
        System.out.println("Operator confirmed insertion");
        if (operator != null) {
            operator.confirmInsertion();
        }
    }

    /**
     * Method for sequence diagram message m25
     */
    public void createBannerObject(byte[] imageData) {
        System.out.println("Creating Banner object...");
        if (currentRequest != null) {
            currentBanner = createBannerFromRequest(currentRequest);
        }
    }

    /**
     * Method for sequence diagram message m29
     */
    public void displaySuccessMessage(String message) {
        System.out.println("Displaying success message: " + message);
        if (operator != null) {
            notificationService.notifySuccess(currentRequest != null ? currentRequest.getOperatorId() : "unknown");
        }
    }

    /**
     * Method for sequence diagram message m31 (called by operator)
     */
    public void sendRequest(BannerInsertionRequest request, boolean retry) {
        System.out.println("Processing retry request from operator");
        handleInsertBanner(request);
    }

    /**
     * Method for sequence diagram message m35
     */
    public void displayConnectionError(String message) {
        System.out.println("Displaying connection error: " + message);
        if (operator != null) {
            notificationService.notifyFailure(currentRequest != null ? currentRequest.getOperatorId() : "unknown", "Connection error: " + message);
        }
    }

    /**
     * Method for sequence diagram message m36
     */
    public BannerInsertionResponse connectionErrorResponse(String errorMessage) {
        return new BannerInsertionResponse(false, errorMessage, null, "CONNECTION_ERROR");
    }

    /**
     * Method for sequence diagram message m37 (called by operator)
     */
    public void cancelOperation() {
        System.out.println("Operation canceled by operator");
        if (operator != null) {
            operator.cancelOperation();
        }
        displayOperationCanceled("Operation canceled by user");
        cancellationResponse();
    }

    /**
     * Method for sequence diagram message m39
     */
    public void displayOperationCanceled(String message) {
        System.out.println("Displaying operation canceled message: " + message);
    }

    /**
     * Method for sequence diagram message m40
     */
    public void cancellationResponse() {
        System.out.println("Sending cancellation response to operator");
    }
}