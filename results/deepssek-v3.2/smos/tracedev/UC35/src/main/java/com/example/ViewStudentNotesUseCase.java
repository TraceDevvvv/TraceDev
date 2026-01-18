package com.example;

/**
 * Interface for the View Student Notes use case.
 */
public interface ViewStudentNotesUseCase {
    /**
     * Executes the use case to retrieve notes for a student.
     *
     * @param studentId the ID of the student whose notes are to be viewed
     * @return the view model containing the student's notes
     */
    StudentNotesViewModel execute(String studentId);
}