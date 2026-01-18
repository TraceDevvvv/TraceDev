package com.system.ui;

import com.system.domain.CulturalGood;
import java.util.Map;
import java.util.HashMap;

/**
 * UI component representing the edit form for a cultural good.
 */
public class EditForm {
    private CulturalGood culturalGood;
    private boolean isLocked = false;
    private Map<String, Object> formData = new HashMap<>();

    /**
     * Displays the cultural good data in the form.
     */
    public void display(CulturalGood culturalGood) {
        this.culturalGood = culturalGood;
        // Simulate populating form fields
        formData.put("name", culturalGood.getName());
        formData.put("description", culturalGood.getDescription());
        formData.put("category", culturalGood.getCategory());
        formData.put("location", culturalGood.getLocation());
        System.out.println("Form displayed for cultural good: " + culturalGood.getName());
    }

    /**
     * Returns the current form data (updated by user edits).
     */
    public Map<String, Object> getFormData() {
        return new HashMap<>(formData); // Return a copy
    }

    /**
     * Updates form data with user input.
     */
    public void editFormData(Map<String, Object> newData) {
        formData.putAll(newData);
        System.out.println("Form data updated.");
    }

    /**
     * Locks the form controls to prevent further edits.
     */
    public void lockControls() {
        isLocked = true;
        System.out.println("Form controls locked.");
    }

    /**
     * Unlocks the form controls.
     */
    public void unlockControls() {
        isLocked = false;
        System.out.println("Form controls unlocked.");
    }

    public boolean isLocked() {
        return isLocked;
    }
}