/**
 * Represents a Teaching object in the system.
 */
public class Teaching {
    private String id;
    private String name;
    private String description;
    private String instructor;
    private int credits;
    /**
     * Constructs a new Teaching object.
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param description A brief description of the teaching.
     * @param instructor The instructor teaching this course.
     * @param credits The number of credits for the teaching.
     */
    public Teaching(String id, String name, String description, String instructor, int credits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.credits = credits;
    }
    // --- Getters ---
    /**
     * @return The unique identifier of the teaching.
     */
    public String getId() {
        return id;
    }
    /**
     * @return The name of the teaching.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The description of the teaching.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return The instructor of the teaching.
     */
    public String getInstructor() {
        return instructor;
    }
    /**
     * @return The number of credits for the teaching.
     */
    public int getCredits() {
        return credits;
    }
    // --- Setters ---
    /**
     * Sets the ID of the teaching.
     * @param id The new ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Sets the name of the teaching.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the description of the teaching.
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Sets the instructor of the teaching.
     * @param instructor The new instructor.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    /**
     * Sets the number of credits for the teaching.
     * @param credits The new number of credits.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    /**
     * @return A string representation of the Teaching object.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Instructor: " + instructor + ", Credits: " + credits;
    }
}