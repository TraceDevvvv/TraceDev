package com.school.monitoring.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an academic note (grade) given to a student for a specific subject
 * within a particular school year.
 */
public class Note {
    private String studentId;
    private String subject;
    private double value; // The actual grade/note value
    private LocalDate date;
    private SchoolYear schoolYear;

    /**
     * Constructs a new Note record.
     *
     * @param studentId    The ID of the student who received the note.
     * @param subject      The subject for which the note was given.
     * @param value        The numerical value of the note/grade.
     * @param date         The date the note was recorded.
     * @param schoolYear   The school year during which the note was given.
     * @throws IllegalArgumentException if studentId, subject, date, or schoolYear is null or empty,
     *                                  or if the note value is negative.
     */
    public Note(String studentId, String subject, double value, LocalDate date, SchoolYear schoolYear) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty for a note record.");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty for a note record.");
        }
        if (value < 0) { // Assuming notes/grades are non-negative
            throw new IllegalArgumentException("Note value cannot be negative.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }
        if (schoolYear == null) {
            throw new IllegalArgumentException("School year cannot be null for a note record.");
        }

        this.studentId = studentId;
        this.subject = subject.trim();
        this.value = value;
        this.date = date;
        this.schoolYear = schoolYear;
    }

    /**
     * Returns the ID of the student associated with this note.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student associated with this note.
     *
     * @param studentId The new student ID.
     * @throws IllegalArgumentException if studentId is null or empty.
     */
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the subject for which this note was given.
     *
     * @return The subject name.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject for which this note was given.
     *
     * @param subject The new subject name.
     * @throws IllegalArgumentException if subject is null or empty.
     */
    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        this.subject = subject.trim();
    }

    /**
     * Returns the numerical value of the note/grade.
     *
     * @return The note value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the numerical value of the note/grade.
     *
     * @param value The new note value.
     * @throws IllegalArgumentException if the note value is negative.
     */
    public void setValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Note value cannot be negative.");
        }
        this.value = value;
    }

    /**
     * Returns the date when this note was recorded.
     *
     * @return The note date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when this note was recorded.
     *
     * @param date The new note date.
     * @throws IllegalArgumentException if date is null.
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }
        this.date = date;
    }

    /**
     * Returns the school year associated with this note.
     *
     * @return The school year.
     */
    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the school year associated with this note.
     *
     * @param schoolYear The new school year.
     * @throws IllegalArgumentException if schoolYear is null.
     */
    public void setSchoolYear(SchoolYear schoolYear) {
        if (schoolYear == null) {
            throw new IllegalArgumentException("School year cannot be null.");
        }
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Note{" +
               "studentId='" + studentId + '\'' +
               ", subject='" + subject + '\'' +
               ", value=" + value +
               ", date=" + date +
               ", schoolYear=" + schoolYear.getYearIdentifier() +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Double.compare(note.value, value) == 0 &&
               Objects.equals(studentId, note.studentId) &&
               Objects.equals(subject, note.subject) &&
               Objects.equals(date, note.date) &&
               Objects.equals(schoolYear, note.schoolYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subject, value, date, schoolYear);
    }
}