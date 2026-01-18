package domain;

/**
 * Represents a student entity in the domain.
 */
public class Student {
    private String id;
    private String name;
    // Assuming a student has a direct reference to their report card or its ID for simplicity.
    // For this specific use case, getReportCardId() might just return the student's ID,
    // implying a 1:1 relationship where report card ID is same as student ID or derivable.
    // Modified to satisfy REQ-007 implying a student 'owns' a ReportCard.
    private String reportCardId;

    /**
     * Constructs a new Student.
     * @param id The unique identifier for the student.
     * @param name The name of the student.
     * @param reportCardId The ID of the report card associated with this student.
     */
    public Student(String id, String name, String reportCardId) {
        this.id = id;
        this.name = name;
        this.reportCardId = reportCardId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the report card associated with this student.
     * In a real system, this might involve more complex logic or a direct relationship object.
     * For now, it's a direct attribute.
     * @return The report card ID.
     */
    public String getReportCardId() {
        return reportCardId;
    }
}