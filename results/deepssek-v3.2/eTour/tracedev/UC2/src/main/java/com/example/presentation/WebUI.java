package com.example.presentation;

import com.example.controller.CulturalGoodController;
import com.example.domain.CulturalGoodType;

/**
 * Web UI boundary class representing the user interface.
 * Corresponds to sequence diagram participant "UI".
 */
public class WebUI {
    private CulturalGoodController controller;
    
    public WebUI(CulturalGoodController controller) {
        this.controller = controller;
    }
    
    /**
     * Activates "Insert New Cultural Good" feature.
     * Sequence message m1.
     */
    public void activateInsertNewCulturalGoodFeature() {
        // This method is called when the user activates the feature.
        // It triggers the controller to show the form.
        FormModel formModel = controller.showInsertForm();
        displayForm(formModel);
    }
    
    /**
     * Displays the form.
     * Sequence message m4.
     * @param formModel the form model
     */
    public void displayForm(FormModel formModel) {
        // In a real UI, this would render the form.
        System.out.println("[WebUI] Displaying insert form");
    }
    
    /**
     * Receives filled form data from the user.
     * Sequence message m5.
     * @param name the name
     * @param description the description
     * @param type the type
     * @param locationData the location data
     * @return the FormData object
     */
    public FormData fillForm(String name, String description, CulturalGoodType type, LocationData locationData) {
        return new FormData(name, description, type, locationData);
    }
    
    /**
     * Submits the form.
     * Sequence message m6.
     * @param formData the form data
     */
    public void submitForm(FormData formData) {
        // This would typically call the controller's submitInsertForm method
        ViewModel viewModel = controller.submitInsertForm(formData);
        displayResult(viewModel);
    }
    
    /**
     * Displays the result.
     * Sequence message m22.
     * @param viewModel the view model
     */
    public void displayResult(ViewModel viewModel) {
        if (viewModel.isSuccess()) {
            System.out.println("[WebUI] Success: " + viewModel.getMessage());
        } else {
            System.out.println("[WebUI] Error: " + viewModel.getMessage());
        }
    }
}