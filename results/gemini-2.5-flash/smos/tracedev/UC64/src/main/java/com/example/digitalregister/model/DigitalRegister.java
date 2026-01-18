package com.example.digitalregister.model;

/**
 * Represents a digital register, which belongs to an academic year and an academic class.
 */
public class DigitalRegister {
    private String id;
    private String academicYearId;
    private String academicClassId;
    private String name;
    private String filePath;

    /**
     * Constructs a new DigitalRegister instance.
     * @param id A unique identifier for the digital register.
     * @param academicYearId The ID of the academic year this register belongs to.
     * @param academicClassId The ID of the academic class this register belongs to.
     * @param name The name of the digital register (e.g., "Attendance Register").
     * @param filePath The file path where the digital register is stored.
     */
    public DigitalRegister(String id, String academicYearId, String academicClassId, String name, String filePath) {
        this.id = id;
        this.academicYearId = academicYearId;
        this.academicClassId = academicClassId;
        this.name = name;
        this.filePath = filePath;
    }

    /**
     * Gets the unique identifier for the digital register.
     * @return The register ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the academic year this register belongs to.
     * @return The academic year ID.
     */
    public String getAcademicYearId() {
        return academicYearId;
    }

    /**
     * Gets the ID of the academic class this register belongs to.
     * @return The academic class ID.
     */
    public String getAcademicClassId() {
        return academicClassId;
    }

    /**
     * Gets the name of the digital register.
     * @return The register name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the file path where the digital register is stored.
     * @return The file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Provides a detailed string representation of the digital register.
     * @return A string containing key details of the register.
     */
    public String getDetails() {
        return "DigitalRegister [ID: " + id + ", Name: " + name + ", Academic Year: " + academicYearId +
               ", Academic Class: " + academicClassId + ", Path: " + filePath + "]";
    }

    @Override
    public String toString() {
        return "DigitalRegister{" +
               "id='" + id + '\'' +
               ", academicYearId='" + academicYearId + '\'' +
               ", academicClassId='" + academicClassId + '\'' +
               ", name='" + name + '\'' +
               ", filePath='" + filePath + '\'' +
               '}';
    }
}