package com.example.model;

/**
 * Represents a specific teaching assignment (e.g., Algebra lesson, English literature)
 * offered within a school class.
 */
public class Teaching {
    private String id;
    private String name;
    private String schoolClassId; // Renamed from classId to schoolClassId to match SchoolClass

    public Teaching(String id, String name, String schoolClassId) {
        this.id = id;
        this.name = name;
        this.schoolClassId = schoolClassId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    @Override
    public String toString() {
        return "Teaching{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", schoolClassId='" + schoolClassId + '\'' +
               '}';
    }
}