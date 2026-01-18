package com.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Parent entity.
 * Corresponds to the Parent class in the class diagram.
 */
public class Parent {
    private int id;
    private String name;
    private String contactInfo;
    private List<Student> children;

    public Parent(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Student> getChildren() {
        return new ArrayList<>(children); // Return a copy to protect encapsulation
    }

    /**
     * Adds a child (Student) to this parent.
     * Also sets the student's parent reference to this parent.
     * Corresponds to addChild(student : Student) in class diagram.
     */
    public void addChild(Student student) {
        if (student != null && !children.contains(student)) {
            children.add(student);
            student.setParent(this);
        }
    }

    /**
     * Removes a child (Student) by student ID.
     * Also clears the student's parent reference if found.
     * Corresponds to removeChild(studentId : int) in class diagram.
     */
    public void removeChild(int studentId) {
        Student toRemove = null;
        for (Student child : children) {
            if (child.getId() == studentId) {
                toRemove = child;
                break;
            }
        }
        if (toRemove != null) {
            children.remove(toRemove);
            toRemove.setParent(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}