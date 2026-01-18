'''
Service layer that handles business logic for tourist operations.
Acts as a bridge between GUI and DAO layers.
'''
package com.etour.agency;
import java.util.List;
public class TouristService {
    private TouristDAO touristDAO;
    public TouristService() {
        this.touristDAO = new TouristDAOImpl();
    }
    /**
     * Get all tourists (for initial display)
     * @return List of all tourists
     */
    public List<Tourist> getAllTourists() {
        try {
            return touristDAO.getAllTourists();
        } catch (RuntimeException e) {
            MainApp.handleConnectionError(e.getMessage());
            return List.of(); // Return empty list on error
        }
    }
    /**
     * Search tourists by query
     * @param query Search term
     * @return List of matching tourists
     */
    public List<Tourist> searchTourists(String query) {
        try {
            return touristDAO.searchTourists(query);
        } catch (RuntimeException e) {
            MainApp.handleConnectionError(e.getMessage());
            return List.of(); // Return empty list on error
        }
    }
    /**
     * Update tourist data
     * @param tourist Updated tourist object
     * @return true if successful, false otherwise
     */
    public boolean updateTourist(Tourist tourist) {
        try {
            // Verify data is valid before updating
            if (!tourist.validate()) {
                MainApp.showErrorDialog(tourist.getValidationErrorMessage());
                return false;
            }
            boolean success = touristDAO.updateTourist(tourist);
            if (success) {
                // Success message could be shown here
                System.out.println("Tourist data updated successfully for ID: " + tourist.getId());
            }
            return success;
        } catch (RuntimeException e) {
            MainApp.handleConnectionError(e.getMessage());
            return false;
        }
    }
    /**
     * Get tourist by ID
     * @param id Tourist ID
     * @return Tourist object or null if not found
     */
    public Tourist getTouristById(String id) {
        try {
            return touristDAO.getTouristById(id);
        } catch (RuntimeException e) {
            MainApp.handleConnectionError(e.getMessage());
            return null;
        }
    }
    /**
     * Check if server connection is available
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return touristDAO.isConnected();
    }
    /**
     * Attempt to reconnect to the ETOUR server
     */
    public void reconnect() {
        // In a real implementation, this would attempt to re-establish connection
        // For the mock implementation, we'll simulate reconnection
        if (touristDAO instanceof TouristDAOImpl) {
            ((TouristDAOImpl) touristDAO).reconnect();
        }
    }
}