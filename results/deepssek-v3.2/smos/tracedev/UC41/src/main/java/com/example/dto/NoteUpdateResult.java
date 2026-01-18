package com.example.dto;

/**
 * Result object for note update operations.
 */
public class NoteUpdateResult {
    private boolean success;
    private String message;
    private int errorCode;
    private String errorDetails;
    private NoteDTO updatedNote;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public NoteDTO getUpdatedNote() {
        return updatedNote;
    }

    public void setUpdatedNote(NoteDTO updatedNote) {
        this.updatedNote = updatedNote;
    }
}