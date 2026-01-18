'''
Represents a student in the system
Contains student information and identification
'''
package models;
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String classId;
    public Student() {
    }
    public Student(String studentId, String firstName, String lastName,
                  String dateOfBirth, String classId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.classId = classId;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + studentId + ")";
    }
}