/*
 * Represents a student with an ID and a name.
 */
package model;
public class Student {
    private String id;
    private String name;
    /**
     * Constructs a new Student.
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the student's ID.
     * @return The student's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Provides a string representation of the Student object, primarily for display in UI components.
     * @return The student's name.
     */
    @Override
    public String toString() {
        return name;
    }
}