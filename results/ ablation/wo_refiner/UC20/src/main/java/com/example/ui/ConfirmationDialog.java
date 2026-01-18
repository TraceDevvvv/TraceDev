package com.example.ui;

/**
 * UI component for confirmation dialogs.
 * Supports cancellation per REQ-013.
 */
public class ConfirmationDialog {

    /**
     * Shows a confirmation dialog with title and message.
     * Sequence diagram step 27.
     * In a real implementation, this would be a modal dialog.
     * For simulation, returns true (user confirms).
     */
    public boolean show(String title, String message) {
        System.out.println("Confirmation Dialog: " + title);
        System.out.println("Message: " + message);
        System.out.println("Assume user clicks OK (returning true).");
        return true;
    }

    /**
     * Shows an image preview (optional).
     */
    public void showImagePreview(byte[] imageData) {
        System.out.println("Image preview shown (size: " + (imageData != null ? imageData.length : 0) + " bytes)");
    }
}