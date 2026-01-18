package com.example.dto;

import com.example.model.Register;
import com.example.model.ClassDetails;

public class RegisterDTO {
    private String registerId;
    private int academicYear;
    private String className;
    private String teacherName;

    /**
     * Constructor that creates a DTO from a Register and ClassDetails.
     * Follows the flow from sequence diagram step 4 and class diagram.
     */
    public RegisterDTO(Register register, ClassDetails classDetails) {
        this.registerId = register.getRegisterId();
        this.academicYear = register.getAcademicYear();
        this.className = classDetails.getClassName();
        // Mock teacher name derivation: Using teacherId from ClassDetails.
        // In a real system, we might fetch teacher details from another service.
        this.teacherName = "Prof. " + classDetails.getTeacherId();
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "registerId='" + registerId + '\'' +
                ", academicYear=" + academicYear +
                ", className='" + className + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}