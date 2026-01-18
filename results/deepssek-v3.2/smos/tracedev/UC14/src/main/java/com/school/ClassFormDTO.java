package com.school;

/**
 * Data Transfer Object for class form data.
 */
public class ClassFormDTO {
    public String name;
    public String address;
    public int academicYear;

    public ClassFormDTO() {}

    public ClassFormDTO(String name, String address, int academicYear) {
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }
}