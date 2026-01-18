package com.example.presentation;

import com.example.domain.Banner;
import com.example.domain.ConnectionInterruptedException;
import com.example.dto.AuthenticationCredentials;
import com.example.dto.AuthenticationResult;
import com.example.dto.BannerImageDTO;
import com.example.service.IAuthenticationService;
import com.example.usecase.EditBannerImageUseCase;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Presentation layer ViewModel for banner management.
 */
public class BannerViewModel {
    private EditBannerImageUseCase editBannerUseCase;
    private IAuthenticationService authenticationService;
    private String selectedBannerId;
    private String pointOfRestaurantId;

    public BannerViewModel(EditBannerImageUseCase editBannerUseCase, IAuthenticationService authenticationService) {
        this.editBannerUseCase = editBannerUseCase;
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates the user (Entry Condition).
     * @param credentials user credentials
     * @return authentication result
     */
    public AuthenticationResult authenticate(AuthenticationCredentials credentials) {
        return authenticationService.authenticate(credentials);
    }

    public List<Banner> loadBannersForPointOfRestaurant(String pointOfRestaurantId) {
        // In a real implementation, this would call a repository via a use case
        // For simplicity, we assume the use case or repository is called elsewhere
        this.pointOfRestaurantId = pointOfRestaurantId;
        return List.of(); // placeholder
    }

    public void selectBanner(String bannerId) {
        this.selectedBannerId = bannerId;
    }

    /**
     * Enters editing mode (Step 4).
     */
    public void enterEditMode() {
        // Display editing controls - in a real UI this would update the view state
    }

    /**
     * Initiates banner image editing.
     * @param imageFile the image file to upload
     */
    public void editBannerImage(File imageFile) {
        try {
            BannerImageDTO dto = createDTOFromFile(imageFile);
            // Step 5: Display image selection form
            displayImageSelectionForm();
            // Execute use case (confirmation may be required before this)
            Banner updated = editBannerUseCase.execute(dto);
            notifySuccess();
        } catch (ConnectionInterruptedException e) {
            notifyError("Connection interrupted: " + e.getErrorMessage());
        } catch (Exception e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Confirms the image change (Step 10).
     */
    public void confirmImageChange() {
        // In a real app, this would trigger the actual execution of the use case
        // For this example, we assume editBannerImage already handles it
    }

    /**
     * Cancels the operation (Alternative Flow).
     */
    public void cancelOperation() {
        // Clean up resources
        selectedBannerId = null;
        // Notify UI
    }

    /**
     * Displays the image selection form (Step 5).
     */
    public void displayImageSelectionForm() {
        // In a real UI, this would update the view to show the form
    }

    /**
     * Creates a DTO from a file.
     */
    private BannerImageDTO createDTOFromFile(File imageFile) throws IOException {
        BannerImageDTO dto = new BannerImageDTO();
        dto.setPointOfRestaurantId(pointOfRestaurantId);
        dto.setBannerId(selectedBannerId);
        dto.setImageData(Files.readAllBytes(imageFile.toPath()));
        dto.setFileName(imageFile.getName());
        dto.setContentType(Files.probeContentType(imageFile.toPath()));
        return dto;
    }

    private void notifySuccess() {
        // Notify UI of success
    }

    private void notifyError(String errorMessage) {
        // Notify UI of error
    }
}