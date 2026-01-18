/**
 * Represents a teaching/lesson in the system.
 * Contains teaching information and metadata.
 */
import java.util.Objects;
public class Teaching {
    private String id;
    private String name;
    private String description;
    private String instructor;
    private int durationHours;
    /**
     * Constructor to create a teaching.
     * 
     * @param id            Unique identifier for the teaching
     * @param name          Name of the teaching
     * @param description   Detailed description
     * @param instructor    Name of instructor
     * @param durationHours Duration in hours
     */
    public Teaching(String id, String name, String description, String instructor, int durationHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.durationHours = durationHours;
    }
    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public int getDurationHours() {
        return durationHours;
    }
    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
    @Override
    public String toString() {
        return name + " (Instructor: " + instructor + ", Duration: " + durationHours + " hours)";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(id, teaching.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}