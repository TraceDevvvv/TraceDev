package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of IFavoritesRepository that interacts with the ETOUR server.
 * This class is responsible for fetching raw data from ETOURServerConnection and
 * transforming it into a list of Bookmark objects.
 * (Satisfies R1, R5 by creating Bookmark objects).
 */
public class ETOURFavoritesRepository implements IFavoritesRepository {

    private ETOURServerConnection etourServerConnection;

    /**
     * Constructs an ETOURFavoritesRepository with a specific ETOURServerConnection.
     * @param etourServerConnection The connection instance to use for data fetching.
     */
    public ETOURFavoritesRepository(ETOURServerConnection etourServerConnection) {
        this.etourServerConnection = etourServerConnection;
    }

    /**
     * {@inheritDoc}
     * Fetches favorites data from the ETOUR server and parses it into a list of Bookmark objects.
     * Handles ConnectionError by re-throwing it, and throws FavoritesRetrievalError for parsing issues.
     */
    @Override
    public List<Bookmark> findFavoritesByTouristId(String touristId) throws ConnectionError, FavoritesRetrievalError {
        // Construct the query string as specified in the sequence diagram
        String query = "SELECT favorites WHERE touristId='" + touristId + "'";
        String dataJson;

        try {
            // Attempt to fetch data from the ETOUR server
            dataJson = etourServerConnection.fetchData(query);
            System.out.println("ETOURFavoritesRepository: Received JSON data: " + dataJson);
        } catch (ConnectionError e) {
            // As per sequence diagram, if ETOURServerConnection throws ConnectionError,
            // Repository re-throws ConnectionError.
            System.err.println("ETOURFavoritesRepository: Connection error during data fetch: " + e.getMessage());
            throw e; // Re-throw the original ConnectionError
        }

        // Parse the JSON string into List<Bookmark>
        // Assumption: A simple JSON parsing logic without external libraries for this example.
        // The expected JSON format is an array of objects: [{"id":"...", "name":"...", "url":"..."}, ...]
        return parseJsonToBookmarks(dataJson);
    }

    /**
     * Parses a JSON string representing a list of bookmarks into a List<Bookmark> objects.
     * This is a simplified parser for demonstration purposes.
     *
     * @param jsonString The JSON string to parse.
     * @return A list of Bookmark objects.
     * @throws FavoritesRetrievalError If the JSON string is malformed or cannot be parsed.
     */
    private List<Bookmark> parseJsonToBookmarks(String jsonString) throws FavoritesRetrievalError {
        List<Bookmark> bookmarks = new ArrayList<>();
        if (jsonString == null || jsonString.trim().isEmpty() || "[]".equals(jsonString.trim())) {
            return Collections.emptyList();
        }

        // Remove outer brackets and split into individual bookmark objects
        String objectsString = jsonString.substring(1, jsonString.length() - 1); // Remove '[' and ']'

        // Handle case where objectsString is empty after removing brackets (e.g., "[]")
        if (objectsString.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Split by comma outside of string literals (simple heuristic)
        // This is a very basic parser and will fail for complex JSON with nested commas/quotes.
        String[] bookmarkStrings = objectsString.split("},");

        for (String bookmarkString : bookmarkStrings) {
            try {
                // Ensure each string ends with '}' for consistent parsing
                if (!bookmarkString.endsWith("}")) {
                    bookmarkString += "}";
                }
                // Remove outer curly braces for easier key-value parsing
                String content = bookmarkString.substring(1, bookmarkString.length() - 1); // Remove '{' and '}'

                String id = extractValue(content, "id");
                String name = extractValue(content, "name");
                String url = extractValue(content, "url");

                if (id != null && name != null && url != null) {
                    bookmarks.add(new Bookmark(id, name, url));
                } else {
                    System.err.println("ETOURFavoritesRepository: Warning: Incomplete bookmark data: " + bookmarkString);
                }
            } catch (Exception e) {
                throw new FavoritesRetrievalError("Failed to parse bookmark data from JSON: " + bookmarkString, e);
            }
        }
        return bookmarks;
    }

    /**
     * Helper to extract a value for a given key from a simple JSON object string.
     * Assumes key-value pairs are like "key":"value".
     */
    private String extractValue(String jsonContent, String key) {
        String searchKey = "\"" + key + "\":\"";
        int startIndex = jsonContent.indexOf(searchKey);
        if (startIndex == -1) {
            return null; // Key not found
        }
        startIndex += searchKey.length();
        int endIndex = jsonContent.indexOf("\"", startIndex);
        if (endIndex == -1) {
            return null; // Malformed value
        }
        return jsonContent.substring(startIndex, endIndex);
    }
}