package com.example.application.boundary;

import com.example.application.dto.TagSearchDTO;
import com.example.application.controller.InsertTagSearchController;
import com.example.application.service.Result;

import java.time.LocalDateTime;

/**
 * Boundary class representing the tag search form.
 */
public class TagSearchForm {
    private InsertTagSearchController controller;

    public TagSearchForm(InsertTagSearchController controller) {
        this.controller = controller;
    }

    public void display() {
        System.out.println("Displaying tag search form...");
    }

    public TagSearchDTO getFormData() {
        // Simulate getting form data from user input
        // In a real application, this would collect data from UI
        String tag = "exampleTag";
        String operatorId = controller.getOperatorId();
        LocalDateTime timestamp = LocalDateTime.now();
        
        return new TagSearchDTO(tag, operatorId, timestamp);
    }

    public Result submitForm(TagSearchDTO formData) {
        // Step 5: Submit form data to controller
        return controller.insertTagSearch(formData);
    }

    public void displayResult(Result result) {
        System.out.println("Result: " + result.getMessage());
        System.out.println("Success: " + result.isSuccess());
    }
}