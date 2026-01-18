/*
Represents a student with their attendance and delay information.
This class holds the student's ID, name, whether they have a delay,
and the duration of the delay in minutes.
*/
class Student {
    private String id;
    private String name;
    private boolean hasDelay;
    private int delayMinutes;
    /**
     * Constructs a new Student object.
     *
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.hasDelay = false; // By default, student has no delay
        this.delayMinutes = 0; // By default, delay is 0 minutes
    }
    /**
     * Get the student's ID.
     * @return The student's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Get the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Check if the student has a delay.
     * @return True if the student has a delay, false otherwise.
     */
    public boolean hasDelay() {
        return hasDelay;
    }
    /**
     * Set whether the student has a delay.
     * @param hasDelay True to mark the student as having a delay, false otherwise.
     */
    public void setHasDelay(boolean hasDelay) {
        this.hasDelay = hasDelay;
    }
    /**
     * Get the duration of the delay in minutes.
     * @return The delay duration in minutes.
     */
    public int getDelayMinutes() {
        return delayMinutes;
    }
    /**
     * Set the duration of the delay in minutes.
     * @param delayMinutes The delay duration in minutes.
     */
    public void setDelayMinutes(int delayMinutes) {
        if (delayMinutes >= 0) { // Ensure delay minutes are non-negative
            this.delayMinutes = delayMinutes;
        } else {
            this.delayMinutes = 0;
        }
    }
    /**
     * Provides a string representation of the Student object, useful for debugging.
     * @return A string containing student's ID, name, delay status, and delay minutes.
     */
    @Override
    public String toString() {
        return "Student{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", hasDelay=" + hasDelay +
               ", delayMinutes=" + delayMinutes +
               '}';
    }
}