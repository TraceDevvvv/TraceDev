package domain;

import java.util.Date;

/**
 * Represents a Late Entry in the domain layer.
 * This class holds details about an entry that was submitted late.
 */
public class LateEntry {
    private String id; // Unique identifier for the late entry itself
    private String studentId; // Reference to the student who made the late entry
    private Date entryDate; // The date of the late entry
    private String inputDetails; // Details about what was entered late

    /**
     * Constructs a new LateEntry.
     * @param id A unique identifier for this specific late entry record.
     * @param studentId The ID of the student associated with this late entry.
     * @param entryDate The date the late entry was recorded.
     * @param inputDetails Descriptive details about the late entry.
     */
    public LateEntry(String id, String studentId, Date entryDate, String inputDetails) {
        this.id = id;
        this.studentId = studentId;
        this.entryDate = entryDate;
        this.inputDetails = inputDetails;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public String getInputDetails() {
        return inputDetails;
    }

    // Setters are typically omitted for immutable domain objects or added as needed.

    @Override
    public String toString() {
        return "LateEntry{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", entryDate=" + entryDate +
               ", inputDetails='" + inputDetails + '\'' +
               '}';
    }
}