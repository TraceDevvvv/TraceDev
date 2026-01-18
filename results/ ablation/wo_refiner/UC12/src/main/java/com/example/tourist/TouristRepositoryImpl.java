package com.example.tourist;

/**
 * Implementation of TouristRepository.
 * Uses DataSource and ETOURServerConnection to fetch and update data.
 * Includes error handling per REQ-010.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DataSource dataSource;
    private ETOURServerConnection serverConnection;
    private ErrorHandler errorHandler;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.serverConnection = new ETOURServerConnection();
        this.errorHandler = new ErrorHandler();
    }

    @Override
    public Tourist findById(String id) {
        // Fetch tourist data from the data source
        Tourist tourist = dataSource.fetchTouristData(id);
        
        // Attempt to update data on the server
        if (tourist != null) {
            updateTouristData(tourist);
        }
        return tourist;
    }

    @Override
    public boolean updateTouristData(Tourist tourist) {
        // Check if server connection is active
        if (serverConnection.isConnected()) {
            boolean success = serverConnection.updateTouristData(tourist);
            if (success) {
                return true;
            } else {
                // Handle connection error and retry
                errorHandler.handleConnectionError("Server connection failed");
                return errorHandler.retryOperation(() -> serverConnection.updateTouristData(tourist), 3);
            }
        } else {
            // Connection not active, retry with error handling
            errorHandler.handleConnectionError("Server connection not active");
            return errorHandler.retryOperation(() -> serverConnection.updateTouristData(tourist), 3);
        }
    }

    @Override
    public boolean uploadData(Tourist tourist) {
        // Graceful error handling per R10 (as per sequence diagram m19)
        boolean result = updateTouristData(tourist);
        // Return the Tourist entity as message (as per sequence diagram m20)
        // Note: The actual return of the entity is handled by findById method
        return result;
    }
}