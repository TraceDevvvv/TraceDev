package com.example.banner;

import java.util.List;
import java.util.Map;

/**
 * Interface representing the view component for banner management.
 * It defines methods for displaying information and forms to the user.
 * Includes the onImageSelected method as per requirement R9.
 */
public interface BannerView {

    /**
     * Displays a list of banners to the user.
     * @param banners The list of Banner objects to display.
     */
    void displayBannersList(List<Banner> banners);

    /**
     * Displays an edit form pre-populated with details of a specific banner.
     * @param banner The Banner object whose details are to be displayed for editing.
     */
    void displayBannerEditForm(Banner banner);

    /**
     * Displays an interface for selecting an image (e.g., file picker, gallery).
     */
    void displayImageSelectionForm();

    /**
     * Displays a confirmation prompt to the user regarding a banner image change.
     * @param banner The banner being edited.
     * @param newImageUrl The new image URL that requires confirmation.
     */
    void displayConfirmationPrompt(Banner banner, String newImageUrl);

    /**
     * Displays a success notification to the user after an operation completes.
     */
    void displaySuccessNotification();

    /**
     * Displays a cancellation notification to the user when an operation is cancelled.
     */
    void displayCancellationNotification();

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    void displayError(String message);

    /**
     * Callback method invoked when an image has been selected by the user.
     * This method would typically be called by the UI component that handles image selection.
     * @param selectedImageUrl The URL of the selected image.
     * @param characteristics A map of characteristics of the selected image (e.g., size, format).
     *                        Added to satisfy requirement R9.
     */
    void onImageSelected(String selectedImageUrl, Map<String, String> characteristics);
}