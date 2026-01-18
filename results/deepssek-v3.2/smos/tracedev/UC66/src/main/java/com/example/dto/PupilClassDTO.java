package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for a pupil class.
 */
public class PupilClassDTO {
    private final String classId;
    private final String className;

    public PupilClassDTO(String classId, String className) {
        this.classId = Objects.requireNonNull(classId);
        this.className = Objects.requireNonNull(className);
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return className;
    }
}