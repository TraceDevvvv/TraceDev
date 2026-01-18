package com.example.application.command;

public class DeleteNoteCommand {
    private String noteId;
    private String administratorId;

    public DeleteNoteCommand(String noteId, String administratorId) {
        this.noteId = noteId;
        this.administratorId = administratorId;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getAdministratorId() {
        return administratorId;
    }
}