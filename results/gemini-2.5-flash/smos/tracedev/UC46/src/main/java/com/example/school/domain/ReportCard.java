package com.example.school.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a student's report card for a specific academic term and year.
 */
public class ReportCard {
    public String id;
    public String studentId;
    public String academicYearId;
    public String term;
    private List<Grade> grades; // Composition: ReportCard contains Grades

    /**
     * Constructs a new ReportCard.
     * @param id The unique identifier for the report card.
     * @param studentId The ID of the student this report card belongs to.
     * @param academicYearId The ID of the academic year this report card is for.
     * @param term The academic term (e.g., "Fall", "Spring", "Term 1").
     */
    public ReportCard(String id, String studentId, String academicYearId, String term) {
        this.id = id;
        this.studentId = studentId;
        this.academicYearId = academicYearId;
        this.term = term;
        this.grades = new ArrayList<>();
    }

    /**
     * Adds a grade to this report card.
     * @param grade The grade to add.
     */
    public void addGrade(Grade grade) {
        if (grade != null) {
            this.grades.add(grade);
        }
    }

    /**
     * Retrieves an unmodifiable list of grades in this report card.
     * @return An unmodifiable list of Grade objects.
     */
    public List<Grade> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public String getTerm() {
        return term;
    }

    /**
     * Validates if the report card is consistent and its grades are valid.
     * @return true if the report card is valid, false otherwise.
     */
    public boolean isValid() {
        if (id == null || id.isEmpty() || studentId == null || studentId.isEmpty() ||
            academicYearId == null || academicYearId.isEmpty() || term == null || term.isEmpty()) {
            System.err.println("ReportCard validation failed: Missing basic information.");
            return false;
        }

        for (Grade grade : grades) {
            if (!grade.isValid()) {
                System.err.println("ReportCard validation failed: Invalid grade found for subjectId " + grade.getSubjectId());
                return false;
            }
        }
        return true;
    }
}