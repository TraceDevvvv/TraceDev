/**
 * Represents a student with their ID, name, and delay status.
 * This class is used to store and manage individual student data within the system.
 */
public class Student {
    private String studentId;
    private String studentName;
    private boolean isDelayed;
    private int delayMinutes; // Stores the duration of the delay in minutes

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param studentName The full name of the student.
     */
    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.isDelayed = false; // By default, a student is not delayed
        this.delayMinutes = 0;  // By default, delay time is 0
    }

    /**
     * Returns the student's ID.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's ID.
     *
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the student's name.
     *
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the student's name.
     *
     * @param studentName The new student name.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Checks if the student is marked as delayed.
     *
     * @return true if the student is delayed, false otherwise.
     */
    public boolean isDelayed() {
        return isDelayed;
    }

    /**
     * Sets the delay status for the student.
     *
     * @param delayed true to mark the student as delayed, false otherwise.
     */
    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
        // If student is no longer delayed, reset delay minutes
        if (!delayed) {
            this.delayMinutes = 0;
        }
    }

    /**
     * Returns the duration of the delay in minutes.
     *
     * @return The delay duration in minutes.
     */
    public int getDelayMinutes() {
        return delayMinutes;
    }

    /**
     * Sets the duration of the delay in minutes.
     * This should only be set if `isDelayed` is true.
     *
     * @param delayMinutes The duration of the delay. Must be non-negative.
     */
    public void setDelayMinutes(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative.");
        }
        this.delayMinutes = delayMinutes;
    }

    /**
     * Provides a string representation of the Student object.
     *
     * @return A string containing the student's ID, name, delay status, and delay minutes.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", isDelayed=" + isDelayed +
               ", delayMinutes=" + delayMinutes +
               '}';
    }
}