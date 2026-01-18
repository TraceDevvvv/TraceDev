/*
 * Provides serv for checking banner limits at refreshment points according to business rules.
 * This service encapsulates the core logic of the CONTROLLANUMEROBANNER use case.
 */
package com.chatdev.bannerapp;
import javax.swing.JOptionPane;
public class BannerCheckService {
    /*
     * Implements the core logic of the CONTROLLANUMEROBANNER use case.
     * It verifies if a new banner can be potentially added to the given refreshment point.
     * As per the use case, if the maximum banner limit is reached or exceeded,
     * a notification is displayed to the user.
     *
     * Flow of events (Use Case steps 1 & 2):
     * 1. Verifies that the number of banners at the 'point' is less than the specified maximum.
     *    If not, the operation ends, and a notification is displayed.
     * 2. The notification display itself implicitly provides confirmation of reading,
     *    as the user must dismiss the dialog.
     *
     * @param point The RefreshmentPointData object containing the details of the refreshment point.
     * @return true if a new banner can be added (current banners are less than max),
     *         false otherwise (limit reached or exceeded).
     */
    public boolean verifyBannerLimit(RefreshmentPointData point) {
        // Step 1: Verify that the number of banners is less than the specified maximum.
        if (point.canAddMoreBanners()) {
            // If true, the limit has not been reached, and a new banner can be added.
            return true;
        } else {
            // If false, the limit has been reached or exceeded.
            // As per use case step 1, end the operation input and display a notification.
            String message = String.format(
                "Notification: The refreshment point '%s' has reached its maximum allowed number of banners (%d).\n" +
                "Current banners: %d. No more banners can be added.",
                point.getName(), point.getMaxBanners(), point.getCurrentBanners()
            );
            // Step 2: Confirmation of the reading of the notification (JOptionPane provides this).
            JOptionPane.showMessageDialog(null, message, "Banner Limit Exceeded", JOptionPane.WARNING_MESSAGE);
            return false; // Indicate that a new banner cannot be added.
        }
    }
}