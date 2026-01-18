package com.example.smos;

/**
 * Represents the domain entity 'Teaching' as retrieved from the data source.
 * Contains detailed attributes of a teaching.
 */
public class Teaching {
    // Attributes as defined in the Class Diagram
    public String id;
    public String name;
    public String courseCode;
    public String teacherId; // Maps to teacherName in DTO
    public String semester;
    // ... more detailed entity attributes (e.g., from DB)

    /**
     * Constructor for the Teaching entity.
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param courseCode The course code.
     * @param teacherId The ID of the teacher.
     * @param semester The semester the teaching is offered.
     */
    public Teaching(String id, String name, String courseCode, String teacherId, String semester) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.teacherId = teacherId;
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

    public String getTeacherId() {
        return teacherId;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Teaching{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}