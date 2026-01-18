package com.yourcompany.banner.controller;

import com.yourcompany.banner.model.Banner;
import com.yourcompany.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing banner operations.
 * This class handles HTTP requests related to banners, acting as the entry point
 * for the DeleteBanner feature.
 */
@RestController // Marks this class as a REST controller, handling incoming web requests.
@RequestMapping("/api/banners") // Base URL path for all endpoints in this controller.
public class BannerController {

    private final BannerService bannerService;

    /**
     * Constructor for BannerController, injecting BannerService.
     * Spring's dependency injection automatically provides an instance of BannerService.
     *
     * @param bannerService The service layer for banner business logic.
     */
    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * Endpoint to retrieve all banners associated with a specific restaurant.
     * Corresponds to "2. View the list of banner associated with the point of rest."
     *
     * @param restaurantId The ID of the restaurant. This would typically come from the authenticated user's context.
     * @return A ResponseEntity containing a list of banners or an error status.
     */
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Banner>> getBannersByRestaurant(@PathVariable Long restaurantId) {
        // In a real application, the restaurantId would likely be extracted from the authenticated user's token
        // or session, rather than being passed as a path variable directly by the client.
        // For this exercise, we assume the operator is authenticated and the restaurantId is valid.
        List<Banner> banners = bannerService.getBannersByRestaurantId(restaurantId);
        if (banners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no banners found
        }
        return new ResponseEntity<>(banners, HttpStatus.OK); // 200 OK with the list of banners
    }

    /**
     * Endpoint to delete a specific banner.
     * Corresponds to "3. Select a banner from the list and enter the function of elimination."
     * "4. Displays a message confirming the deletion." (handled by client-side UI)
     * "5. Confirm the operation." (handled by client-side UI)
     * "6. Removes the banner."
     *
     * @param bannerId The ID of the banner to be deleted.
     * @param restaurantId The ID of the restaurant associated with the banner.
     *                     This is crucial for security to ensure an operator can only delete their own banners.
     * @return A ResponseEntity indicating the success or failure of the deletion.
     */
    @DeleteMapping("/{bannerId}/restaurant/{restaurantId}")
    public ResponseEntity<String> deleteBanner(@PathVariable Long bannerId, @PathVariable Long restaurantId) {
        try {
            // Attempt to delete the banner through the service layer.
            boolean deleted = bannerService.deleteBanner(bannerId, restaurantId);

            if (deleted) {
                // "Exit conditions: The system shall notify the successful elimination of the selected banner."
                return new ResponseEntity<>("Banner with ID " + bannerId + " successfully deleted.", HttpStatus.OK); // 200 OK
            } else {
                // This case should ideally be caught by the service layer if banner not found,
                // but as a fallback, if service returns false, it means not found or not authorized.
                // The service layer throws SecurityException for unauthorized access.
                return new ResponseEntity<>("Banner with ID " + bannerId + " not found or could not be deleted.", HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (SecurityException e) {
            // Handles unauthorized deletion attempts.
            // "Exit conditions: The Point Of Operator Restaurant cancels the operation." (This is more about user action,
            // but unauthorized access is a form of operational interruption from system's perspective).
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e); // 403 Forbidden
        } catch (Exception e) {
            // Catches any other unexpected errors, including potential ETOUR server interruption.
            // "Exit conditions: Interruption of the connection to the server ETOUR."
            // In a real scenario, specific exception handling for ETOUR connection issues would be more granular.
            System.err.println("Error deleting banner " + bannerId + ": " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred while deleting banner. Please try again later.", e); // 500 Internal Server Error
        }
    }

    /**
     * Helper endpoint to get banner details for confirmation message.
     * This is not explicitly in the flow but useful for UI to display "Are you sure you want to delete 'Banner Name'?"
     *
     * @param bannerId The ID of the banner.
     * @return A ResponseEntity containing the Banner object or a 404 Not Found.
     */
    @GetMapping("/{bannerId}")
    public ResponseEntity<Banner> getBannerDetails(@PathVariable Long bannerId) {
        Optional<Banner> banner = bannerService.getBannerById(bannerId);
        return banner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}