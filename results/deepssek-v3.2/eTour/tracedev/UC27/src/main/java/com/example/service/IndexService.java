package com.example.service;

import com.example.service.index.IndexEntry;
import java.util.List;
import java.util.Map;

/**
 * External index service for searching site ind.
 * Marked as external because it may have connection interruptions.
 */
public class IndexService {
    /**
     * Searches the index for entries matching the query.
     * @param query the search query
     * @return list of index entries
     * @throws RuntimeException if connection is interrupted (simulated for demonstration)
     */
    public List<IndexEntry> searchIndex(String query) throws RuntimeException {
        // Simulate a search. In a real application, this would connect to an external server.
        // For demonstration, we return a hard-coded list.
        // To simulate a connection error, we can throw a RuntimeException when the query is "error".
        if ("error".equals(query)) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Return a dummy list of index entries.
        // In a real application, this would be the result of an external call.
        return List.of(
            new IndexEntry("/path/to/site1", Map.of("name", "Site One", "lastModified", "1727644800000")),
            new IndexEntry("/path/to/site2", Map.of("name", "Site Two", "lastModified", "1727731200000"))
        );
    }
}