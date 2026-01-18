package com.example.interfaceadapters.controllers;

import com.example.domain.Teaching;
import java.util.List;

/**
 * View controller responsible for updating the teaching list view.
 */
public class ListViewController {
    /**
     * Displays the updated list of teachings.
     */
    public void displayUpdatedTeachingList(List<Teaching> teachingList) {
        // In a real implementation, this would update the UI.
        // For now, we just print to console.
        System.out.println("Teaching list updated. Total teachings: " + teachingList.size());
    }

    /**
     * Displays an error message.
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }
}