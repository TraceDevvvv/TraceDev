package com.example.diagram;

import java.util.List;

/**
 * Entity representing a register.
 */
public class Register {
    private String registerId;
    private String academicYear;
    private String className;
    private List<AcademicRecord> records;

    public Register(String registerId, String academicYear, String className) {
        this.registerId = registerId;
        this.academicYear = academicYear;
        this.className = className;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<AcademicRecord> getRecords() {
        return records;
    }

    public void setRecords(List<AcademicRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Register{registerId='" + registerId + "', academicYear='" + academicYear + "', className='" + className + "'}";
    }
}