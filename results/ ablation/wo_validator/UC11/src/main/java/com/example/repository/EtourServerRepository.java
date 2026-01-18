package com.example.repository;

import com.example.entities.Convention;
import com.example.utils.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation that connects to an external ETOUR server.
 * Implements sequence diagram interactions: establishConnection, fetch data, parse, create objects.
 */
public class EtourServerRepository implements ConventionRepository {
    private String serverUrl;
    private ServerConnection connection;

    /**
     * Constructor as per class diagram.
     */
    public EtourServerRepository(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connection = null;
    }

    /**
     * Finds conventions by restaurant ID.
     * Implements the flow from sequence diagram with connection handling.
     */
    @Override
    public List<Convention> findByRestaurantId(String restaurantId) {
        // Simulate connection attempt from sequence diagram
        if (!establishConnection()) {
            throw new ConnectionException("ETOUR server connection interrupted");
        }

        // Simulate fetching data from server (as per sequence diagram)
        String rawData = fetchConventionData(restaurantId);

        closeConnection();

        // Parse data and create convention objects (as per sequence diagram)
        return parseData(rawData, restaurantId);
    }

    /**
     * Simulates establishing connection to server.
     * Returns true if connection succeeds, false otherwise.
     * As per sequence diagram alternative flow, can throw connection exception.
     */
    private boolean establishConnection() {
        // Simulate connection logic; in real scenario would use serverUrl
        connection = new ServerConnection();
        return connection.connect();
    }

    /**
     * Simulates closing the connection as per sequence diagram.
     */
    private void closeConnection() {
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }

    /**
     * Simulates fetching convention data from server.
     * As per sequence diagram: Repo -> Server : fetchConventionData(restaurantId)
     */
    private String fetchConventionData(String restaurantId) {
        // Simulated response; in reality would make HTTP/DB call
        return "Conv1|" + restaurantId + "|Special Discount|2023-01-01|2023-12-31|10% off";
    }

    /**
     * Simulates parsing raw data and creating Convention objects.
     * As per sequence diagram: Repo -> Repo : parseData()
     * Then Repo -> Conv : createConventionObjects()
     */
    private List<Convention> parseData(String rawData, String restaurantId) {
        List<Convention> conventions = new ArrayList<>();
        // Simple parsing for demonstration
        String[] parts = rawData.split("\\|");
        if (parts.length >= 6) {
            String id = parts[0];
            String name = parts[2];
            Date effectiveDate = parseDate(parts[3]);
            Date expirationDate = parseDate(parts[4]);
            String terms = parts[5];
            conventions.add(new Convention(id, restaurantId, name, effectiveDate, expirationDate, terms));
        }
        return conventions;
    }

    /**
     * Helper to parse date string in yyyy-MM-dd format.
     */
    private Date parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            return new Date(year, month, day);
        } catch (Exception e) {
            return new Date(2000, 1, 1);
        }
    }

    /**
     * Inner class to simulate server connection.
     */
    private static class ServerConnection {
        boolean connect() {
            // Simulate connection success 80% of the time
            return Math.random() > 0.2;
        }

        void disconnect() {
            // Simulate disconnection
        }
    }
}

/**
 * Exception thrown when connection fails as per sequence diagram alternative flow.
 */
class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}