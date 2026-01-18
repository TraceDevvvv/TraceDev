'''
Represents a single teaching entity within the system.
This class holds the details of a teaching, such as its ID, title, description,
and the instructor responsible for it.
'''
public class Teaching {
    private String id;
    private String title;
    private String description;
    private String instructor;
    private int durationHours;
    private String startDate; // Using String for simplicity, could be LocalDate
    /**
     * Constructs a new Teaching object with the specified details.
     *
     * @param id The unique identifier for the teaching.
     * @param title The title or name of the teaching.
     * @param description A brief description of the teaching content.
     * @param instructor The name of the instructor teaching this course.
     * @param durationHours The total duration of the teaching in hours.
     * @param startDate The start date of the teaching (e.g., "YYYY-MM-DD").
     */
    public Teaching(String id, String title, String description, String instructor, int durationHours, String startDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.durationHours = durationHours;
        this.startDate = startDate;
    }
    /**
     * Returns the unique identifier of the teaching.
     * @return The teaching ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the title of the teaching.
     * @return The teaching title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Returns the description of the teaching.
     * @return The teaching description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the name of the instructor for this teaching.
     * @return The instructor's name.
     */
    public String getInstructor() {
        return instructor;
    }
    /**
     * Returns the duration of the teaching in hours.
     * @return The duration in hours.
     */
    public int getDurationHours() {
        return durationHours;
    }
    /**
     * Returns the start date of the teaching.
     * @return The start date as a String.
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * Provides a string representation of the Teaching object, useful for debugging.
     * @return A formatted string with all teaching details.
     */
    @Override
    public String toString() {
        return "Teaching ID: " + id + "\n" +
               "Title: " + title + "\n" +
               "Description: " + description + "\n" +
               "Instructor: " + instructor + "\n" +
               "Duration: " + durationHours + " hours\n" +
               "Start Date: " + startDate;
    }
}