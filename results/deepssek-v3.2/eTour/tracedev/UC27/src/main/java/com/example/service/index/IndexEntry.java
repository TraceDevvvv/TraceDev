package com.example.service.index;

import java.util.Map;

/**
 * Represents an entry in the index.
 */
public class IndexEntry {
    private String path;
    private Map<String, String> metadata;

    /**
     * Constructs an IndexEntry with the specified path and metadata.
     * @param path the path
     * @param metadata the metadata map
     */
    public IndexEntry(String path, Map<String, String> metadata) {
        this.path = path;
        this.metadata = metadata;
    }

    /**
     * Gets the path.
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the metadata.
     * @return the metadata map
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Sets the metadata.
     * @param metadata the metadata map
     */
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}