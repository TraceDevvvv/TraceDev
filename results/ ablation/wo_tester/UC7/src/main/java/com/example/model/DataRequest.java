package com.example.model;

/**
 * Represents a data request associated with a Convention.
 */
public class DataRequest {
    private String id;
    private String content;
    private RequestStatus status;

    public DataRequest(String id, String content, RequestStatus status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Loads the data content.
     * In a real scenario, this might involve I/O operations.
     */
    public String loadData() {
        // Simulate loading data; in production, might fetch from external source.
        return content;
    }

    /**
     * Checks if the data request has data (content not null or empty).
     */
    public boolean hasData() {
        return content != null && !content.trim().isEmpty();
    }
}