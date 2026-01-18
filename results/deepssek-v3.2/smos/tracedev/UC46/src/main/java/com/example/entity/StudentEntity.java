package com.example.entity;

/**
 * Entity class representing a Student in the system.
 */
public class StudentEntity {
    private int id;
    private String firstName;
    private String lastName;
    private int classId;

    public StudentEntity() {}

    public StudentEntity(int id, String firstName, String lastName, int classId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * Returns the full name of the student.
     * @return concatenation of first and last name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", classId=" + classId +
                '}';
    }
}