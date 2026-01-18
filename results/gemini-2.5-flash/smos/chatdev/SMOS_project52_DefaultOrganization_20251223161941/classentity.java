/**
 * Represents a 'class' entity in the system.
 */
package com.chatdev.entities;
public class ClassEntity {
    private String classId;
    private String className;
    private String department;
    // Constructor
    public ClassEntity(String classId, String className, String department) {
        this.classId = classId;
        this.className = className;
        this.department = department;
    }
    // Getters
    public String getClassId() {
        return classId;
    }
    public String getClassName() {
        return className;
    }
    public String getDepartment() {
        return department;
    }
    // Override toString() for display in UI components
    @Override
    public String toString() {
        return "Class ID: " + classId + ", Name: " + className + ", Dept: " + department;
    }
}