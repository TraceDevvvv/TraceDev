package com.school.ui;

/**
 * Response DTO for note creation.
 */
public class CreateNoteResponse {
    private String noteId;
    private boolean success;
    private String message;

    public CreateNoteResponse(String noteId, boolean success, String message) {
        this.noteId = noteId;
        this.success = success;
        this.message = message;
    }

    public String getNoteId() {
        return noteId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}