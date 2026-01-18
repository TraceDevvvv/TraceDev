package com.example.diagram;

/**
 * Data Transfer Object for Register.
 */
public class RegisterDTO {
    private String registerId;
    private String academicYear;
    private String className;

    /**
     * Constructs a DTO from a Register entity.
     * @param register the Register entity.
     */
    public RegisterDTO(Register register) {
        this.registerId = register.getRegisterId();
        this.academicYear = register.getAcademicYear();
        this.className = register.getClassName();
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

    @Override
    public String toString() {
        return "RegisterDTO{registerId='" + registerId + "', academicYear='" + academicYear + "', className='" + className + "'}";
    }
}