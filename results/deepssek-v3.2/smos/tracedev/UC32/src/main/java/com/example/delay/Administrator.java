package com.example.delay;

import java.util.Date;

/**
 * Represents an Administrator actor that interacts with the system to manage delays.
 */
public class Administrator {
    /**
     * Simulates login process. In a real system, this would authenticate credentials.
     * @return true if login is successful.
     */
    public boolean login() {
        // In a real implementation, authentication logic would go here.
        return true;
    }

    /**
     * Selects a date for viewing/editing delays.
     * @param date The date to select.
     */
    public void selectDate(Date date) {
        // This method triggers the sequence. In practice, this would be called by a UI event.
        System.out.println("Administrator selected date: " + date);
    }

    /**
     * Edits a delay DTO with new delay value.
     * @param delayDTO The DTO containing date and new delay value.
     */
    public void editDelay(DelayDTO delayDTO) {
        // In a real system, this would capture user input.
        System.out.println("Administrator editing delay to: " + delayDTO.getDelay());
    }

    /**
     * Simulates the administrator clicking save to persist changes.
     */
    public void clickSave() {
        System.out.println("Administrator clicked save.");
    }

    /**
     * Simulates the administrator interrupting the operation.
     */
    public void interrupt() {
        System.out.println("Administrator interrupted the operation.");
    }
}