package com.example;

/**
 * UI Button component for triggering details view.
 * Traceability: System HAS Details button associated with one register clicked.
 */
public class DetailsButton {
    private String registerId;

    public DetailsButton(String registerId) {
        this.registerId = registerId;
    }

    public void onClick() {
        // This method is called when the button is clicked.
        // In a real UI framework, this would trigger an event.
    }
}