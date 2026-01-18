'''
Data Access Object interface for tourist operations.
Defines CRUD operations for tourist data.
'''
package com.etour.agency;
import java.util.List;
public interface TouristDAO {
    /**
     * Get all tourists
     * @return List of all tourists
     */
    List<Tourist> getAllTourists();
    /**
     * Search tourists by name or email
     * @param query Search query
     * @return List of matching tourists
     */
    List<Tourist> searchTourists(String query);
    /**
     * Get tourist by ID
     * @param id Tourist ID
     * @return Tourist object or null if not found
     */
    Tourist getTouristById(String id);
    /**
     * Update tourist data
     * @param tourist Updated tourist object
     * @return true if successful, false otherwise
     */
    boolean updateTourist(Tourist tourist);
    /**
     * Check if server connection is available
     * @return true if connected, false otherwise
     */
    boolean isConnected();
}