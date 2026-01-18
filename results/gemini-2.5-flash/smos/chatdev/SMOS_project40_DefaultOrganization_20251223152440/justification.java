'''
Represents the data model for a "Justification".
Contains fields such as ID, date, and a textual description.
'''
package model;
/**
 * Represents a Justification entity in the system.
 * This is a simple POJO (Plain Old Java Object) for data encapsulation.
 */
public class Justification {
    private int id;
    private String justificationDate; // Using String for date for simplicity as per use case notes "date justification"
    private String description;       // Represents the justification text
    /**
     * Constructs a new Justification object.
     * @param id A unique identifier for the justification.
     * @param justificationDate The date associated with the justification (e.g., "YYYY-MM-DD").
     * @param description The detailed text of the justification.
     */
    public Justification(int id, String justificationDate, String description) {
        this.id = id;
        this.justificationDate = justificationDate;
        this.description = description;
    }
    /**
     * Gets the ID of the justification.
     * @return The unique identifier.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the justification.
     * @param id The new unique identifier.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets the date of the justification.
     * @return The justification date as a String.
     */
    public String getJustificationDate() {
        return justificationDate;
    }
    /**
     * Sets the date of the justification.
     * @param justificationDate The new justification date as a String.
     */
    public void setJustificationDate(String justificationDate) {
        this.justificationDate = justificationDate;
    }
    /**
     * Gets the description (text) of the justification.
     * @return The justification description as a String.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description (text) of the justification.
     * @param description The new justification description as a String.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns a string representation of the Justification object.
     * This is useful for displaying in lists or debugging.
     * @return A formatted string containing the ID, date, and description.
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Date: " + justificationDate + " | Description: " + description;
    }
}