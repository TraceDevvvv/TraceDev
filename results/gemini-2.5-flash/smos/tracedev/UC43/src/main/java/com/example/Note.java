package com.example;

/**
 * Represents a disciplinary note.
 * Contains information about the note's ID, student, content, and parent email.
 */
public class Note {
    private String id;
    private String studentId;
    private String content;
    private String parentEmail;

    /**
     * Constructor for the Note class.
     *
     * @param id          The unique ID of the note.
     * @param studentId   The ID of the student associated with the note.
     * @param content     The content/description of the note.
     * @param parentEmail The email address of the student's parent.
     */
    public Note(String id, String studentId, String content, String parentEmail) {
        this.id = id;
        this.studentId = studentId;
        this.content = content;
        this.parentEmail = parentEmail;
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getContent() {
        return content;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", content='" + content + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                '}';
    }
}