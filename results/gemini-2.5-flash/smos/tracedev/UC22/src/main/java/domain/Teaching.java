package domain;

/**
 * Domain entity representing a Teaching.
 * This class holds the core business data for a teaching.
 */
public class Teaching {
    // + id : String
    public String id;
    // + name : String
    public String name;
    // + description : String
    public String description;
    // + teacherName : String
    public String teacherName;

    /**
     * Constructor for Teaching.
     *
     * @param id The unique identifier for the teaching.
     * @param name The name or title of the teaching.
     * @param description A brief description of the teaching.
     * @param teacherName The name of the teacher or lecturer.
     */
    public Teaching(String id, String name, String description, String teacherName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacherName = teacherName;
    }

    // Getters and setters (or access fields directly as they are public based on UML)
    // For simplicity, using public fields as per '+' in UML, but typically would be private with getters/setters.
}