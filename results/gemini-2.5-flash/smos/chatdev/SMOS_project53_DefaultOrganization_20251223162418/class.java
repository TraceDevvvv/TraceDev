'''
Represents a single class offered in the system.
'''
public class Class {
    private String classId;
    private String name;
    private String description;
    private String schedule;
    private String instructor;
    /**
     * Constructs a new Class object.
     * @param classId A unique identifier for the class.
     * @param name The name of the class.
     * @param description A brief description of the class.
     * @param schedule The schedule details of the class (e.g., "Mon/Wed 10:00-11:30 AM").
     * @param instructor The instructor teaching the class.
     */
    public Class(String classId, String name, String description, String schedule, String instructor) {
        this.classId = classId;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.instructor = instructor;
    }
    /**
     * Returns the unique ID of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }
    /**
     * Returns the name of the class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the description of the class.
     * @return The class description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the schedule of the class.
     * @return The class schedule.
     */
    public String getSchedule() {
        return schedule;
    }
    /**
     * Returns the instructor of the class.
     * @return The class instructor.
     */
    public String getInstructor() {
        return instructor;
    }
    /**
     * Provides a string representation of the Class object.
     * @return A formatted string with class details.
     */
    @Override
    public String toString() {
        return "Class ID: " + classId + "\n" +
               "Name: " + name + "\n" +
               "Description: " + description + "\n" +
               "Schedule: " + schedule + "\n" +
               "Instructor: " + instructor;
    }
}