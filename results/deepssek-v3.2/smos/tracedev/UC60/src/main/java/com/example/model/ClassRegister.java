package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class register containing student IDs.
 */
public class ClassRegister {
    private String classId;
    private List<String> studentIds;
    
    public ClassRegister() {
        this.studentIds = new ArrayList<>();
    }
    
    public String getClassId() {
        return classId;
    }
    
    public void setClassId(String classId) {
        this.classId = classId;
    }
    
    public List<String> getStudentIds() {
        return studentIds;
    }
    
    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }
    
    public void addStudentId(String studentId) {
        this.studentIds.add(studentId);
    }
    
    /**
     * Checks if a student exists in the register.
     */
    public boolean containsStudent(String studentId) {
        return studentIds.contains(studentId);
    }
}