package com.system.school.student;

import java.util.Objects;

/**
 * Represents a Student actor in the school management system.
 * This class holds basic information about a student and their parent's contact details.
 */
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private int grade;
    private String parentEmail;
    private String parentPhoneNumber;

    /**
     * Constructs a new Student instance.
     *
     * @param studentId The unique identifier for the student.
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param grade The grade level of the student.
     * @param parentEmail The email address of the student's parent for notifications.
     * @param parentPhoneNumber The phone number of the student's parent for notifications.
     * @throws IllegalArgumentException if any required parameter is null or empty, or if grade is not positive.
     */
    public Student(String studentId, String firstName, String lastName, int grade,
                   String parentEmail, String parentPhoneNumber) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (grade <= 0) {
            throw new IllegalArgumentException("Grade must be a positive number.");
        }
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent email cannot be null or empty.");
        }
        if (parentPhoneNumber == null || parentPhoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent phone number cannot be null or empty.");
        }

        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.parentEmail = parentEmail;
        this.parentPhoneNumber = parentPhoneNumber;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the first name of the student.
     *
     * @return The student's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the student.
     *
     * @return The student's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name of the student.
     *
     * @return The student's full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the grade level of the student.
     *
     * @return The student's grade.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Returns the email address of the student's parent.
     *
     * @return The parent's email.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    /**
     * Sets the email address of the student's parent.
     *
     * @param parentEmail The new parent email address.
     * @throws IllegalArgumentException if the parent email is null or empty.
     */
    public void setParentEmail(String parentEmail) {
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent email cannot be null or empty.");
        }
        this.parentEmail = parentEmail;
    }

    /**
     * Returns the phone number of the student's parent.
     *
     * @return The parent's phone number.
     */
    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    /**
     * Sets the phone number of the student's parent.
     *
     * @param parentPhoneNumber The new parent phone number.
     * @throws IllegalArgumentException if the parent phone number is null or empty.
     */
    public void setParentPhoneNumber(String parentPhoneNumber) {
        if (parentPhoneNumber == null || parentPhoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent phone number cannot be null or empty.");
        }
        this.parentPhoneNumber = parentPhoneNumber;
    }

    /**
     * Provides a string representation of the Student object.
     *
     * @return A string containing the student's ID, full name, grade, and parent contact info.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", grade=" + grade +
               ", parentEmail='" + parentEmail + '\'' +
               ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
               '}';
    }

    /**
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same studentId as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}