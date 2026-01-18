/**
 * Student.java
 * Represents a student in the system
 */
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    public Student(String id, String firstName, String lastName, String dateOfBirth,
                   String gender, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
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
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    @Override
    public String toString() {
        return getFullName() + " (" + id + ")";
    }
}