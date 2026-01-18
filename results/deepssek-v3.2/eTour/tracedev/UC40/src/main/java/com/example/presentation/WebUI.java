package com.example.presentation;

import com.example.domain.Banner;
import java.util.List;

/**
 * Web UI representation (for completeness; in real app this would be frontend).
 */
public class WebUI {
    /**
     * Displays a list of banners to the operator.
     */
    public void displayBannerList(List<Banner> banners) {
        System.out.println("Displaying " + banners.size() + " banners:");
        for (Banner b : banners) {
            System.out.println(" - " + b.getId() + ": " + b.getImageUrl() + (b.isActive() ? " (active)" : " (inactive)"));
        }
    }

    /**
     * Shows a confirmation dialog.
     * @return true if operator confirms, false if cancels.
     */
    public boolean showConfirmationDialog() {
        // Simulate user confirmation (in real UI, this would be a modal)
        return true;
    }

    /**
     * Displays a success message (sequence message m48).
     */
    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Displays an error message (sequence messages m24, m29, m53, m68, etc).
     */
    public void showErrorMessage(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Shows cancellation message (sequence message m61).
     */
    public void showCancellationMessage() {
        System.out.println("Operation cancelled.");
    }
}