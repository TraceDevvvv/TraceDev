/**
 * This class represents a Teaching entity with its properties.
 * It serves as the data model for the application.
 */
public class Teaching {
    private String teachingName;
    /**
     * Constructor for Teaching object.
     *
     * @param teachingName The name of the teaching
     */
    public Teaching(String teachingName) {
        this.teachingName = teachingName;
    }
    /**
     * Gets the teaching name.
     *
     * @return the teaching name
     */
    public String getTeachingName() {
        return teachingName;
    }
    /**
     * Sets the teaching name.
     *
     * @param teachingName the new teaching name
     */
    public void setTeachingName(String teachingName) {
        this.teachingName = teachingName;
    }
    /**
     * Validates the teaching data.
     * Teaching name must not be null, empty, or too long.
     *
     * @return true if the teaching name is valid, false otherwise
     */
    public boolean isValid() {
        if (teachingName == null || teachingName.trim().isEmpty()) {
            return false;
        }
        if (teachingName.length() > 100) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return teachingName;
    }
}