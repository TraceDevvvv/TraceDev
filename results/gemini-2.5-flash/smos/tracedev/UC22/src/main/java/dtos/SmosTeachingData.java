package dtos;

/**
 * Data Transfer Object (DTO) for raw teaching data fetched from the SMOS server.
 * Represents the structure of data received from the external system.
 */
public class SmosTeachingData {
    // + smosId : String
    public String smosId;
    // + courseCode : String
    public String courseCode;
    // + courseTitle : String
    public String courseTitle;
    // + lecturerName : String
    public String lecturerName;
    // + details : String
    public String details;

    /**
     * Constructor for SmosTeachingData.
     *
     * @param smosId The ID from the SMOS system.
     * @param courseCode The course code.
     * @param courseTitle The title of the course.
     * @param lecturerName The name of the lecturer.
     * @param details Additional details about the course.
     */
    public SmosTeachingData(String smosId, String courseCode, String courseTitle, String lecturerName, String details) {
        this.smosId = smosId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.lecturerName = lecturerName;
        this.details = details;
    }

    // Getters and setters (or access fields directly as they are public based on UML)
    // For simplicity, using public fields as per '+' in UML, but typically would be private with getters/setters.
}