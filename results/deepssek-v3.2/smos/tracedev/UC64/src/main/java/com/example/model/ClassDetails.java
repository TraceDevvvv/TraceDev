package com.example.model;

public class ClassDetails {
    private String classId;
    private String className;
    private int academicYear;
    private String teacherId;

    public ClassDetails() {}

    public ClassDetails(String classId, String className, int academicYear, String teacherId) {
        this.classId = classId;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherId = teacherId;
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

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * Static method to simulate retrieving class details from a data source.
     * In a real implementation, this would likely query a database or service.
     * Assumption: returns a mock object based on classId.
     */
    public static ClassDetails getClassDetails(String classId) {
        // Mock implementation - returns a ClassDetails with placeholder values
        return new ClassDetails(classId, "Class " + classId, 2024, "Teacher" + classId);
    }
}