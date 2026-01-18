package com.example.schoolreports;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a detailed report card for a student.
 * This is a domain model class containing comprehensive academic information.
 */
public class ReportCard {
    private String id; // Unique identifier for this specific report card
    private String studentId;
    private String studentName;
    private String term;
    private String academicYear;
    private List<CourseGrade> courseGrades; // Composition: ReportCard contains CourseGrade objects
    private String teacherComments;

    /**
     * Constructs a new ReportCard instance.
     *
     * @param id The unique ID of the report card.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param term The academic term (e.g., "Fall", "Semester 1").
     * @param academicYear The academic year (e.g., "2023-2024").
     * @param courseGrades A list of CourseGrade objects for this report card.
     * @param teacherComments General comments from the teacher.
     */
    public ReportCard(String id, String studentId, String studentName, String term,
                      String academicYear, List<CourseGrade> courseGrades, String teacherComments) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.term = term;
        this.academicYear = academicYear;
        this.courseGrades = new ArrayList<>(courseGrades); // Ensure deep copy or new list instance
        this.teacherComments = teacherComments;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTerm() {
        return term;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public List<CourseGrade> getCourseGrades() {
        return new ArrayList<>(courseGrades); // Return a copy to prevent external modification
    }

    public String getTeacherComments() {
        return teacherComments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card Details ---\n");
        sb.append("Report ID: ").append(id).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Student Name: ").append(studentName).append("\n");
        sb.append("Term: ").append(term).append(", Academic Year: ").append(academicYear).append("\n");
        sb.append("Course Grades:\n");
        if (courseGrades.isEmpty()) {
            sb.append("  No grades available.\n");
        } else {
            for (CourseGrade grade : courseGrades) {
                sb.append("  - ").append(grade).append("\n");
            }
        }
        sb.append("Teacher Comments: '").append(teacherComments).append("'\n");
        sb.append("---------------------------\n");
        return sb.toString();
    }
}