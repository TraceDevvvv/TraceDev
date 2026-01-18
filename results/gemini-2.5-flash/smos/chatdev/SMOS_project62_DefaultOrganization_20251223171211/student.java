'''
Student.java
Represents a student with an ID and a name in the school system.
'''
public class Student {
    private String id;
    private String name;
    /**
     * Constructs a new Student object.
     *
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the ID of the student.
     *
     * @return The student's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Provides a string representation of the Student object, primarily for display in JList components.
     *
     * @return The student's name.
     */
    @Override
    public String toString() {
        return name;
    }
}