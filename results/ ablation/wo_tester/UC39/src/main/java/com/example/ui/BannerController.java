package com.example.ui;

import com.example.application.ChangeBannerImageUseCase;
import com.example.application.ChangeBannerImageCommand;
import com.example.application.ChangeBannerImageCommandImpl;
import com.example.application.ValidationException;
import com.example.domain.Banner;
import com.example.infrastructure.Authenticator;
import com.example.infrastructure.BannerRepository;
import com.example.infrastructure.ConnectionInterruptedException;
import com.example.infrastructure.ImageData;
import com.example.infrastructure.ImageSelectionForm;
import java.util.List;

/**
 * Controller handling banner image change requests.
 * Coordinates between UI, use case, and authentication.
 */
public class BannerController {
    private ChangeBannerImageUseCase changeBannerImageUseCase;
    private Authenticator authenticator;
    private ImageSelectionForm imageSelectionForm;
    private BannerRepository bannerRepository;
    
    public BannerController(ChangeBannerImageUseCase useCase, Authenticator auth, ImageSelectionForm form, BannerRepository repo) {
        this.changeBannerImageUseCase = useCase;
        this.authenticator = auth;
        this.imageSelectionForm = form;
        this.bannerRepository = repo;
    }
    
    /**
     * Entry point for any request; performs authentication.
     * As per R-AUTH.
     * @param userId the user ID to authenticate
     * @return true if authenticated; otherwise redirects to login (simulated)
     */
    public boolean authenticate(String userId) {
        boolean authenticated = authenticator.isAuthenticated(userId);
        if (!authenticated) {
            System.out.println("Redirect to login");
        }
        return authenticated;
    }
    
    /**
     * Flow of Events 1: Select banner editing functionality.
     * @param pointOfRestaurantId the point of restaurant ID
     */
    public void selectBannerEditing(String pointOfRestaurantId) {
        List<Banner> banners = bannerRepository.findBannersByPointOfRestaurantId(pointOfRestaurantId);
        imageSelectionForm.renderBannerList(banners);
    }
    
    /**
     * R-5: Handle image change form request.
     * @param pointOfRestaurantId the point of restaurant ID
     */
    public void handleImageChangeFormRequest(String pointOfRestaurantId) {
        imageSelectionForm.displayImageSelectionForm();
    }
    
    /**
     * R-6: Handle image upload.
     * @param bannerId the banner ID
     * @param pointOfRestaurantId the point of restaurant ID
     * @param newImageData the new image data
     */
    public void handleImageUpload(String bannerId, String pointOfRestaurantId, ImageData newImageData) {
        submitChangeRequest(bannerId, pointOfRestaurantId, newImageData);
    }
    
    /**
     * Flow of Events 7: Submit change request.
     * @param bannerId the banner ID
     * @param pointOfRestaurantId the point of restaurant ID
     * @param newImageData the new image data
     */
    public void submitChangeRequest(String bannerId, String pointOfRestaurantId, ImageData newImageData) {
        if (!validateInputs()) {
            showError("Invalid inputs");
            return;
        }
        
        ChangeBannerImageCommand command = new ChangeBannerImageCommandImpl(bannerId, pointOfRestaurantId, newImageData);
        
        try {
            changeBannerImageUseCase.execute(command);
            showConfirmationDialog();
            confirmTransaction();
        } catch (ValidationException e) {
            showError("Invalid image");
        } catch (ConnectionInterruptedException e) {
            showError("Connection lost");
        }
    }
    
    /**
     * Flow of Events 4: Enter editing mode.
     * @param bannerId the banner ID
     * @param pointOfRestaurantId the point of restaurant ID
     */
    public void enterEditingMode(String bannerId, String pointOfRestaurantId) {
        imageSelectionForm.requestImageUpload();
    }
    
    /**
     * Handles change banner image request.
     * @param bannerId the banner ID
     * @param pointOfRestaurantId the point of restaurant ID
     * @param newImageData the new image data
     */
    public void handleChangeBannerImageRequest(String bannerId, String pointOfRestaurantId, ImageData newImageData) {
        submitChangeRequest(bannerId, pointOfRestaurantId, newImageData);
    }
    
    /**
     * R-11: Confirm transaction.
     */
    public void confirmTransaction() {
        showSuccessNotification();
    }
    
    /**
     * R-EXIT-CANCEL: Cancel transaction.
     */
    public void cancelTransaction() {
        System.out.println("Operation cancelled");
    }
    
    /**
     * Show confirmation dialog.
     */
    public void showConfirmationDialog() {
        System.out.println("Show confirmation dialog");
    }
    
    /**
     * Show error message.
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
    
    /**
     * Show success notification.
     */
    public void showSuccessNotification() {
        System.out.println("Success! Banner image updated.");
    }
    
    /**
     * Validate inputs.
     * @return true if inputs are valid
     */
    private boolean validateInputs() {
        return true;
    }

    // New methods to handle sequence diagram messages

    /**
     * any request
     * As per m2: any request from PO to Controller
     */
    public void anyRequest() {
        // Delegates to authentication
    }

    /**
     * Redirect to login
     * As per m5: return from Controller to PO
     */
    public void redirectToLogin() {
        System.out.println("Redirect to login");
    }

    /**
     * Display error (Errored use case)
     * As per m29: return from Controller to PO
     */
    public void displayError(String message) {
        showError(message);
    }

    /**
     * Display confirmation dialog
     * As per m32: return from Controller to PO
     */
    public void displayConfirmationDialog() {
        showConfirmationDialog();
    }

    /**
     * Display connection error
     * As per m44: return from Controller to PO
     */
    public void displayConnectionError() {
        showError("Connection lost");
    }

    /**
     * Display success notification
     * As per m47: return from Controller to PO
     */
    public void displaySuccessNotification() {
        showSuccessNotification();
    }
}