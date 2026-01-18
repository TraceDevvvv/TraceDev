package com.example.repository;

import com.example.model.ConventionHistory;
import com.example.model.PointOfRest;
import com.example.exception.ConnectionException;
import com.example.network.ServerConnection;
import com.example.network.ServerData;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Concrete repository that connects to an ETOUR server to fetch convention history.
 * Implements retry logic and error handling as per sequence diagram.
 */
public class ETOURConventionHistoryRepository implements ConventionHistoryRepository {
    private ServerConnection serverConnection;

    public ETOURConventionHistoryRepository(ServerConnection connection) {
        this.serverConnection = connection;
    }

    @Override
    public List<ConventionHistory> findByPointOfRest(PointOfRest pointOfRest) {
        // Retry loop as per sequence diagram (3 attempts)
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                if (serverConnection.connect()) {
                    Map<String, String> params = Map.of("pointOfRestId", pointOfRest.getId());
                    ServerData data = serverConnection.fetchData("conventions", params);
                    // Assuming data contains a list, we convert the first item for simplicity
                    ConventionHistory history = convertToConventionHistory(data, pointOfRest);
                    return List.of(history);
                } else {
                    handleConnectionError();
                }
            } catch (Exception e) {
                handleConnectionError();
                if (attempt == 3) {
                    throw new ConnectionException("Server connection interrupted");
                }
            }
        }
        throw new ConnectionException("All connection attempts failed");
    }

    /**
     * Converts server data to ConventionHistory entity.
     * This is a simplified conversion; real implementation would map fields.
     */
    private ConventionHistory convertToConventionHistory(ServerData data, PointOfRest pointOfRest) {
        // Placeholder conversion: assuming ServerData contains relevant fields
        String conventionId = data.getField("conventionId");
        Date conventionDate = data.getDateField("conventionDate");
        String details = data.getField("details");
        // Use the provided pointOfRest
        return new ConventionHistory(conventionId, pointOfRest, conventionDate, details);
    }

    /**
     * Handles a connection error (logging, etc.).
     * As per sequence diagram, this is called within the retry loop.
     */
    private void handleConnectionError() {
        // Log the error, increment metrics, etc.
        System.err.println("Connection attempt failed. Retrying...");
    }
}