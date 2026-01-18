package com.example.dto;

/**
 * DTO for Class information.
 */
public class ClassDTO {
    private String classId;
    private String className;

    public ClassDTO() {}

    public ClassDTO(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}