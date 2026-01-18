'''
Student model class representing a student in the system
'''
package studentnotessystem;
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private int gradeLevel;
    public Student(String studentId, String firstName, String lastName, int gradeLevel) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
    }
    // Getters
    public String getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getGradeLevel() { return gradeLevel; }
    @Override
    public String toString() {
        return lastName + ", " + firstName + " (ID: " + studentId + ")";
    }
}