package com.example.justice.domain;

import com.example.justice.dto.JusticeUpdateDTO;
import java.util.Date;
import java.util.Objects;

/**
 * Represents the Justice entity in the domain model.
 * Contains information about a specific justification record.
 */
public class Justice {
    private String id;
    private Date dateJustification;
    private String status;

    /**
     * Constructs a new Justice instance.
     *
     * @param id The unique identifier for the justice record.
     * @param dateJustification The date of justification.
     * @param status The current status of the justice record.
     */
    public Justice(String id, Date dateJustification, String status) {
        this.id = id;
        this.dateJustification = dateJustification;
        this.status = status;
    }

    /**
     * Updates the justice record with details from a DTO.
     * This method applies changes to the domain entity as per the sequence diagram.
     *
     * @param details The DTO containing the updated information.
     */
    public void update(JusticeUpdateDTO details) {
        // As per the sequence diagram and class diagram, specifically updating dateJustification.
        // Other fields might be updated in a real system, but for this use case, only dateJustification is mentioned.
        if (details.getDateJustification() != null) {
            this.dateJustification = details.getDateJustification();
            // Assuming status might change upon update, or remain the same.
            // For simplicity, let's keep it consistent or assume it's "UPDATED"
            this.status = "UPDATED"; // Making a reasonable assumption
        }
        // Note: The ID is not updated as it's a primary key.
    }

    // --- Getters and Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateJustification() {
        return dateJustification;
    }

    public void setDateJustification(Date dateJustification) {
        this.dateJustification = dateJustification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Justice{" +
               "id='" + id + '\'' +
               ", dateJustification=" + dateJustification +
               ", status='" + status + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justice justice = (Justice) o;
        return Objects.equals(id, justice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}