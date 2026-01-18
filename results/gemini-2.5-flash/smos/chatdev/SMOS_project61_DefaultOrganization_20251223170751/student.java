'''
Represents a student (child) associated with a parent.
'''
package model;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a student (child) associated with a parent.
 */
public class Student {
    private String studentId;
    private String name;
    private List<StudentRecord> records;
    /**
     * Constructs a new Student object.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.records = new ArrayList<>(); // Initialize with an empty list
    }
    /**
     * Gets the student's ID.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the list of records for this student.
     * @return A list of StudentRecord objects.
     */
    public List<StudentRecord> getRecords() {
        return records;
    }
    /**
     * Adds a single record to the student's list of records.
     * @param record The StudentRecord to add.
     */
    public void addRecord(StudentRecord record) {
        this.records.add(record);
    }
    /**
     * Overrides the toString method to return the student's name,
     * which is useful for displaying in UI components like JComboBox.
     * @return The student's name.
     */
    @Override
    public String toString() {
        return name;
    }
}