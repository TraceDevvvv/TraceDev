package com.example;

/**
 * Simulates a connection to an ETOUR server to fetch data.
 * This class handles low-level data fetching and can simulate connection errors.
 */
public class ETOURServerConnection {

    /**
     * Simulates fetching data from an ETOUR server based on a query.
     * This method includes logic to simulate successful data retrieval,
     * an empty result, or a connection error.
     *
     * @param query The query string to send to the server.
     * @return A JSON string representing the fetched data.
     * @throws ConnectionError If the simulated connection fails.
     */
    public String fetchData(String query) throws ConnectionError {
        // Simulate network latency
        try {
            Thread.sleep(500); // Simulate 500ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionError("ETOUR server connection interrupted.", e);
        }

        // Simulate connection errors based on specific query patterns
        if (query.contains("error_connection")) {
            System.out.println("ETOURServerConnection: Simulating ConnectionError for query: " + query);
            throw new ConnectionError("Failed to connect to ETOUR server. Please check your network.");
        }

        // Simulate empty data for specific tourist IDs or queries
        if (query.contains("touristId='emptyUser'") || query.contains("empty_data")) {
            System.out.println("ETOURServerConnection: Simulating empty data for query: " + query);
            return "[]"; // Return an empty JSON array
        }

        // Simulate successful data retrieval
        // The query string is used to return dynamic content for demonstration
        System.out.println("ETOURServerConnection: Fetching data for query: " + query);
        if (query.contains("touristId='tourist123'")) {
            return "[{\"id\":\"b1\",\"name\":\"Eiffel Tower\",\"url\":\"http://eiffel.com\"}," +
                   "{\"id\":\"b2\",\"name\":\"Louvre Museum\",\"url\":\"http://louvre.fr\"}]";
        } else if (query.contains("touristId='anotherUser'")) {
            return "[{\"id\":\"b3\",\"name\":\"Berlin Wall\",\"url\":\"http://berlinwall.de\"}]";
        } else {
            // Default response for other queries
            return "[{\"id\":\"b99\",\"name\":\"Default Site\",\"url\":\"http://default.com\"}]";
        }
    }
}