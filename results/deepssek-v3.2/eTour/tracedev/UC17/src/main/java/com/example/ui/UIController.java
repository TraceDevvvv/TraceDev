package com.example.ui;

import com.example.application.ChangeBannerImageUseCase;
import com.example.domain.ImageMetadata;
import com.example.dto.ChangeBannerImageRequest;
import com.example.dto.ChangeBannerImageResponse;
import com.example.dto.ImageSelectionForm;
import com.example.exceptions.ConnectionException;
import com.example.serv.ImageFileSystemService;
import com.example.serv.ETourServerGateway;
import java.util.List;

/**
 * UI Controller handling user interactions for changing banner images.
 * Implements the flow described in the sequence diagram.
 */
public class UIController {
    private ChangeBannerImageUseCase changeBannerImageUseCase;
    private ImageFileSystemService imageFileSystemService;
    private ETourServerGateway etourServerGateway;
    
    // State for current operation
    private String currentRestPointId;
    private String currentBannerId;
    private String currentAgencyOperatorId;
    private String selectedImagePath;

    public UIController(ChangeBannerImageUseCase changeBannerImageUseCase,
                        ImageFileSystemService imageFileSystemService,
                        ETourServerGateway etourServerGateway) {
        this.changeBannerImageUseCase = changeBannerImageUseCase;
        this.imageFileSystemService = imageFileSystemService;
        this.etourServerGateway = etourServerGateway;
    }

    public void accessEditBanner(String restPointId, String bannerId) {
        this.currentRestPointId = restPointId;
        this.currentBannerId = bannerId;
        
        // Load available images
        List<String> availableImages = loadAvailableImages();
        
        // Create and display form
        ImageSelectionForm form = new ImageSelectionForm(availableImages);
        displayForm(form);
    }

    public List<String> loadAvailableImages() {
        try {
            return imageFileSystemService.getAvailableImages();
        } catch (ConnectionException e) {
            displayError("Failed to load available images: " + e.getMessage());
            return List.of(); // Return empty list on error
        }
    }

    public void displayForm(ImageSelectionForm form) {
        System.out.println("Displaying image selection form with " + 
                          form.getAvailableImages().size() + " available images");
        // In a real application, this would update the UI
    }

    public void selectImage(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
        displayConfirmationDialog(selectedImagePath);
    }

    public void submitChangeRequest(String agencyOperatorId, String restPointId, 
                                   String bannerId, String selectedImagePath) {
        this.currentAgencyOperatorId = agencyOperatorId;
        ChangeBannerImageRequest request = createChangeBannerImageRequest(
            agencyOperatorId, restPointId, bannerId, selectedImagePath);
        
        ChangeBannerImageResponse response = changeBannerImageUseCase.execute(request);
        
        if (response.isSuccess()) {
            displaySuccess(response.getMessage());
        } else {
            displayError(response.getMessage());
        }
    }

    public ChangeBannerImageRequest createChangeBannerImageRequest(String agencyOperatorId, 
                                                                  String restPointId, 
                                                                  String bannerId, 
                                                                  String selectedImagePath) {
        return new ChangeBannerImageRequest(agencyOperatorId, restPointId, bannerId, selectedImagePath);
    }

    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void displayConfirmationDialog(String imagePath) {
        System.out.println("Confirm change to image: " + imagePath);
        // In a real application, this would show a confirmation dialog
        // For simulation, we auto-confirm after a short delay
        simulateUserConfirmation();
    }
    
    private void simulateUserConfirmation() {
        // Simulate user clicking "Confirm"
        System.out.println("User confirmed the change");
        sendConfirmation();
    }
    
    public void sendConfirmation() {
        if (currentAgencyOperatorId == null) {
            currentAgencyOperatorId = "operator123"; // Default for simulation
        }
        submitChangeRequest(currentAgencyOperatorId, currentRestPointId, 
                           currentBannerId, selectedImagePath);
    }
    
    // Helper method for testing
    public void setCurrentAgencyOperatorId(String id) {
        this.currentAgencyOperatorId = id;
    }
}