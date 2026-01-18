'''
Represents a student in the system.
'''
package com.chatdev.absencetracker.model;
import java.util.Objects;
public class Student {
    private String id;
    private String name;
    private String parentEmail;
    '''
    Constructs a new Student object.
    @param id The unique identifier for the student.
    @param name The full name of the student.
    @param parentEmail The email address of the student's parent for notifications.
    '''
    public Student(String id, String name, String parentEmail) {
        this.id = id;
        this.name = name;
        this.parentEmail = parentEmail;
    }
    '''
    Returns the unique identifier of the student.
    @return The student's ID.
    '''
    public String getId() {
        return id;
    }
    '''
    Returns the full name of the student.
    @return The student's name.
    '''
    public String getName() {
        return name;
    }
    '''
    Returns the parent's email address.
    @return The parent's email.
    '''
    public String getParentEmail() {
        return parentEmail;
    }
    '''
    Compares this Student object to another object for equality.
    Equality is determined by the student's ID.
    @param o The object to compare with.
    @return true if the objects are equal, false otherwise.
    '''
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }
    '''
    Returns a hash code value for the object.
    Based on the student's ID.
    @return A hash code value for this object.
    '''
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    '''
    Returns a string representation of the Student object.
    @return A string containing the student's ID and name.
    '''
    @Override
    public String toString() {
        return "Student{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}