package com.example.bannerapp;

import java.util.List;

/**
 * Service layer for Banner-related business logic.
 * Coordinates between controller and data repositories, enforcing business rules.
 */
public class BannerService {
    private IBannerRepository bannerRepository;
    private OperatorService operatorService; // Added to satisfy recommendation REC-001

    /**
     * Constructs a BannerService with the required repositories.
     * @param bannerRepository The repository for banner data.
     * @param operatorService The service for operator-related operations.
     */
    public BannerService(IBannerRepository bannerRepository, OperatorService operatorService) {
        this.bannerRepository = bannerRepository;
        this.operatorService = operatorService;
    }

    /**
     * Retrieves all banners associated with the Point of Rest managed by the given operator.
     * Modified to satisfy recommendation REC-001 (uses operatorId instead of pointOfRestId directly).
     * @param operatorId The ID of the operator.
     * @return A list of banners.
     * @throws NetworkConnectionException If a network connection error occurs during data retrieval.
     */
    public List<Banner> getBannersByPointOfRest(String operatorId) throws NetworkConnectionException {
        System.out.println("[Service] Getting banners for operator: " + operatorId);
        String pointOfRestId = operatorService.getPointOfRestIdForOperator(operatorId);
        if (pointOfRestId == null) {
            System.err.println("[Service] No PointOfRest found for operator " + operatorId + ". Returning empty list.");
            return List.of(); // Return empty list if operator doesn't manage a POR
        }
        return bannerRepository.findBannersByPointOfRest(pointOfRestId);
    }

    /**
     * Deletes a specific banner, after performing authorization and data integrity checks.
     * @param bannerId The ID of the banner to delete.
     * @param operatorId The ID of the operator attempting the deletion.
     * @return true if the banner was successfully deleted, false otherwise (e.g., data integrity failure).
     * @throws NetworkConnectionException If a network connection error occurs during deletion.
     * @throws UnauthorizedOperationException If the operator is not authorized to delete the banner.
     */
    public boolean deleteBanner(String bannerId, String operatorId) throws NetworkConnectionException, UnauthorizedOperationException {
        System.out.println("[Service] Attempting to delete banner " + bannerId + " by operator " + operatorId);
        if (!isAuthorized(operatorId, bannerId)) {
            System.out.println("[Service] Operator " + operatorId + " is not authorized to delete banner " + bannerId);
            throw new UnauthorizedOperationException("Operator " + operatorId + " is not authorized to delete banner " + bannerId);
        }

        // Data integrity check is simplified, in a real system this would involve
        // checking dependencies, foreign keys, business rules etc.
        if (!ensureDataIntegrity(bannerId)) {
            System.out.println("[Service] Data integrity check failed for banner " + bannerId);
            return false;
        }

        return bannerRepository.delete(bannerId);
    }

    /**
     * Retrieves a banner by its ID.
     * Added to assist BannerController in getting full Banner object for confirmation prompt.
     * @param bannerId The ID of the banner to find.
     * @return The Banner object, or null if not found.
     * @throws NetworkConnectionException If a network connection error occurs.
     */
    public Banner getBannerById(String bannerId) throws NetworkConnectionException {
        System.out.println("[Service] Retrieving banner by ID: " + bannerId);
        return bannerRepository.findById(bannerId);
    }


    /**
     * Checks if the given operator is authorized to perform operations on the specified banner.
     * This is a placeholder implementation.
     * @param operatorId The ID of the operator.
     * @param bannerId The ID of the banner.
     * @return true if authorized, false otherwise.
     */
    private boolean isAuthorized(String operatorId, String bannerId) {
        // --- Placeholder for authorization logic ---
        // In a real system, this would involve checking if the operator
        // has permissions for the specific banner, perhaps by checking
        // if the banner belongs to the operator's managed PointOfRest.
        // For demonstration, always return true.
        System.out.println("[Service] Performing authorization check for operator " + operatorId + " on banner " + bannerId);
        try {
            Banner banner = bannerRepository.findById(bannerId);
            if (banner == null) {
                System.out.println("[Service] Banner " + bannerId + " not found for authorization check.");
                return false; // Cannot authorize if banner doesn't exist
            }
            String operatorPorId = operatorService.getPointOfRestIdForOperator(operatorId);
            boolean authorized = operatorPorId != null && operatorPorId.equals(banner.getPointOfRestId());
            if (!authorized) {
                System.out.println("[Service] Operator " + operatorId + "'s POR ID " + operatorPorId + " does not match banner " + bannerId + "'s POR ID " + banner.getPointOfRestId());
            }
            return authorized;
        } catch (NetworkConnectionException e) {
            // If authorization check itself fails due to network, it's a critical error
            System.err.println("[Service] Network error during authorization check: " + e.getMessage());
            // Re-throwing as an unchecked exception or handling as per business logic
            throw new RuntimeException("Authorization check failed due to connectivity issues.", e);
        }

    }

    /**
     * Ensures data integrity before deletion.
     * This is a placeholder implementation.
     * @param bannerId The ID of the banner.
     * @return true if data integrity is maintained, false otherwise.
     */
    private boolean ensureDataIntegrity(String bannerId) {
        // --- Placeholder for data integrity checks ---
        // In a real system, this would involve checking for any dependent records
        // or business rules that prevent the deletion of this banner.
        System.out.println("[Service] Performing data integrity check for banner " + bannerId);
        return true; // For demonstration, always return true.
    }
}