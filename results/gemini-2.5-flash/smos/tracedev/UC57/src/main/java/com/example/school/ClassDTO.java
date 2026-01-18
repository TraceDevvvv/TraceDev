package com.example.school;

/**
 * Data Transfer Object (DTO) for basic Class information.
 * Used for displaying a list of classes, satisfying requirement EC3.
 */
public class ClassDTO {
    private String classId;
    private String className;

    /**
     * Constructs a new ClassDTO.
     * @param classId The unique identifier of the class.
     * @param className The name of the class.
     */
    public ClassDTO(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    /**
     * Gets the unique identifier of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ClassDTO{classId='" + classId + "', className='" + className + "'}";
    }
}