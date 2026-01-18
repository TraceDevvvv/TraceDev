package com.example.smos;

/**
 * Data Transfer Object (DTO) for 'Teaching' data.
 * Used to transfer teaching details from the application layer to the presentation layer,
 * containing only the attributes necessary for display.
 */
public class TeachingDto {
    // Attributes as defined in the Class Diagram
    public String id;
    public String name;
    public String courseCode;
    public String teacherName; // Derived from teacherId in Teaching entity
    public String semester;
    // ... other displayable attributes

    /**
     * Constructor for the TeachingDto.
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param courseCode The course code.
     * @param teacherName The name of the teacher (for display).
     * @param semester The semester the teaching is offered.
     */
    public TeachingDto(String id, String name, String courseCode, String teacherName, String semester) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.teacherName = teacherName;
        this.semester = semester;
    }

    // Getters for attributes (implicitly public based on UML)
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "TeachingDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}