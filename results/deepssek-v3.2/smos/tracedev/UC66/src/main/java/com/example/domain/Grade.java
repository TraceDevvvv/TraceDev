package com.example.domain;

import java.util.Objects;

/**
 * Represents a grade for a specific subject.
 */
public class Grade {
    private final String gradeId;
    private final String subject;
    private final double value;
    private final Semester semester;
    private final Student student;

    public Grade(String gradeId, String subject, double value, Semester semester, Student student) {
        this.gradeId = Objects.requireNonNull(gradeId);
        this.subject = Objects.requireNonNull(subject);
        this.value = value;
        this.semester = Objects.requireNonNull(semester);
        this.student = Objects.requireNonNull(student);
    }

    public String getGradeId() {
        return gradeId;
    }

    public String getSubject() {
        return subject;
    }

    public double getValue() {
        return value;
    }

    public Semester getSemester() {
        return semester;
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Returns a formatted string representation of the grade,
     * e.g., "A" or "85.5".
     * For simplicity, we just convert the numeric value to a string.
     * @return formatted grade
     */
    public String getFormattedGrade() {
        return String.format("%.1f", value);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", value=" + value +
                '}';
    }
}