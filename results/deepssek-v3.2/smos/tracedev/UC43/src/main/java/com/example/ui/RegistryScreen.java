package com.example.ui;

import com.example.application.query.NoteDetailsQuery;
import com.example.application.dto.NoteDetailsDTO;

public class RegistryScreen {
    private String screenId;

    public RegistryScreen(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    // Display details of a note
    public void displayNoteDetails(String noteId) {
        NoteDetailsQuery query = new NoteDetailsQuery();
        NoteDetailsDTO details = query.execute();
        System.out.println("Displaying note details: " + details);
    }

    // Return to the view (registry screen)
    public void returnToView() {
        System.out.println("Returning to registry screen view.");
    }
}