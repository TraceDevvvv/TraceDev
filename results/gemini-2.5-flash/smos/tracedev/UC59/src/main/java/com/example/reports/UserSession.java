package com.example.reports;

/**
 * Represents the current user's session, holding information like the logged-in student's ID.
 */
public class UserSession {
    private String currentStudentId;

    /**
     * Constructs a new UserSession for a specific student.
     *
     * @param studentId The ID of the student currently logged in.
     */
    public UserSession(String studentId) {
        this.currentStudentId = studentId;
    }

    /**
     * Gets the ID of the student associated with the current session.
     * @return The current student's ID.
     */
    public String getCurrentStudentId() {
        return currentStudentId;
    }
}