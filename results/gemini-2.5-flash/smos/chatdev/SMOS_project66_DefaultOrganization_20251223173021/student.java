/**
 * Represents a student, with personal details and a link to their class.
 */
package models;
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String classId; // The class this student currently belongs to
    public Student(String id, String firstName, String lastName, String classId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
    }
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getClassId() {
        return classId;
    }
    @Override
    public String toString() {
        // This is important for JList to display the friendly name
        return lastName + ", " + firstName;
    }
}