package com.example.bannerapp;

/**
 * Represents an Operator entity.
 */
public class Operator {
    private String id;
    private String pointOfRestId; // The ID of the Point of Rest managed by this operator.

    /**
     * Constructs a new Operator.
     * @param id The unique identifier for the operator.
     * @param pointOfRestId The ID of the Point of Rest this operator manages.
     */
    public Operator(String id, String pointOfRestId) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
    }

    /**
     * Gets the unique identifier of the operator.
     * @return The operator's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the Point of Rest ID managed by this operator.
     * @return The pointOfRestId.
     */
    public String getPointOfRestId() {
        return pointOfRestId;
    }

    @Override
    public String toString() {
        return "Operator{" +
               "id='" + id + '\'' +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               '}';
    }
}