'''
Represents a single course offered in the system.
(Renamed from 'Class' to 'Course' to avoid conflict with Java's reserved 'Class' keyword).
'''
public class Course {
    private String classId; // Kept as classId as it represents the unique identifier for what the user perceives as a 'class'
    private String name;
    private String description;
    private String schedule;
    private String instructor;
    /**
     * Constructs a new Course object.
     * @param classId A unique identifier for the course.
     * @param name The name of the course.
     * @param description A brief description of the course.
     * @param schedule The schedule details of the course (e.g., "Mon/Wed 10:00-11:30 AM").
     * @param instructor The instructor teaching the course.
     */
    public Course(String classId, String name, String description, String schedule, String instructor) {
        this.classId = classId;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.instructor = instructor;
    }
    /**
     * Returns the unique ID of the course (referred to as classId for user context).
     * @return The course ID.
     */
    public String getClassId() {
        return classId;
    }
    /**
     * Returns the name of the course.
     * @return The course name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the description of the course.
     * @return The course description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the schedule of the course.
     * @return The course schedule.
     */
    public String getSchedule() {
        return schedule;
    }
    /**
     * Returns the instructor of the course.
     * @return The course instructor.
     */
    public String getInstructor() {
        return instructor;
    }
    /**
     * Provides a string representation of the Course object.
     * @return A formatted string with course details.
     */
    @Override
    public String toString() {
        return "Course ID: " + classId + "\n" +
               "Name: " + name + "\n" +
               "Description: " + description + "\n" +
               "Schedule: " + schedule + "\n" +
               "Instructor: " + instructor;
    }
}