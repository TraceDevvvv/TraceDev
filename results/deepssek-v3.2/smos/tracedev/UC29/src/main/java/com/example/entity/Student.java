package com.example.entity;

/**
 * Represents a student.
 */
public class Student {
    private String studentId;
    private String name;
    private String className; // 'class' is a reserved word, using className
    private ParentContact parentContact;

    public Student() {}

    public Student(String studentId, String name, String className, ParentContact parentContact) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.parentContact = parentContact;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ParentContact getParentContact() {
        return parentContact;
    }

    public void setParentContact(ParentContact parentContact) {
        this.parentContact = parentContact;
    }

    /**
     * Gets the parent email from the associated ParentContact.
     */
    public String getParentEmail() {
        if (parentContact != null) {
            return parentContact.getEmail();
        }
        return null;
    }
}