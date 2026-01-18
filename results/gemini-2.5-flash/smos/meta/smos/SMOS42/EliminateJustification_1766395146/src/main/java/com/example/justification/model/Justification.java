package com.example.justification.model;

import java.util.Objects;

/**
 * Represents a Justification entity within the system.
 * This class holds the core data for a justification, including its unique identifier,
 * a detailed description, and its current status.
 */
public class Justification {

    private String id; // Unique identifier for the justification
    private String description; // Detailed description of the justification
    private JustificationStatus status; // Current status of the justification (e.g., ACTIVE, DELETED)

    /**
     * Default constructor.
     */
    public Justification() {
        // Default constructor for potential frameworks or deserialization
    }

    /**
     * Constructs a new Justification with the specified ID, description, and status.
     *
     * @param id The unique identifier for the justification.
     * @param description The detailed description of the justification.
     * @param status The initial status of the justification.
     */
    public Justification(String id, String description, JustificationStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    /**
     * Returns the unique identifier of the justification.
     *
     * @return The justification's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the justification.
     *
     * @param id The new ID for the justification.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the detailed description of the justification.
     *
     * @return The justification's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of the justification.
     *
     * @param description The new description for the justification.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the current status of the justification.
     *
     * @return The justification's status.
     */
    public JustificationStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the justification.
     *
     * @param status The new status for the justification.
     */
    public void setStatus(JustificationStatus status) {
        this.status = status;
    }

    /**
     * Overrides the equals method to compare Justification objects based on their ID.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the ID.
     *
     * @return The hash code for this Justification object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Overrides the toString method to provide a string representation of the Justification object.
     *
     * @return A string representation of the Justification.
     */
    @Override
    public String toString() {
        return "Justification{" +
               "id='" + id + '\'' +
               ", description='" + description + '\'' +
               ", status=" + status +
               '}';
    }
}