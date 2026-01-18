package com.example.schoolreports;

/**
 * Represents a single course grade within a student's report card.
 * This is a domain model class.
 */
public class CourseGrade {
    private String courseName;
    private String grade;
    private String teacherComment;

    /**
     * Constructs a new CourseGrade instance.
     *
     * @param courseName The name of the course.
     * @param grade The grade received for the course (e.g., "A", "B+", "Pass").
     * @param teacherComment Any specific comments from the teacher for this course.
     */
    public CourseGrade(String courseName, String grade, String teacherComment) {
        this.courseName = courseName;
        this.grade = grade;
        this.teacherComment = teacherComment;
    }

    // --- Getters ---
    public String getCourseName() {
        return courseName;
    }

    public String getGrade() {
        return grade;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    @Override
    public String toString() {
        return "Course: " + courseName + ", Grade: " + grade + ", Comment: '" + teacherComment + "'";
    }
}