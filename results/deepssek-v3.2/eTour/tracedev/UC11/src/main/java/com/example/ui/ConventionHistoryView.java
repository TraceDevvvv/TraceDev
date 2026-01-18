package com.example.ui;

import com.example.domain.Convention;
import java.util.List;

/**
 * Passive view in MVC pattern. Displays convention history or errors.
 */
public class ConventionHistoryView {

    private final ConventionHistoryController controller;

    public ConventionHistoryView(ConventionHistoryController controller) {
        this.controller = controller;
    }

    public void showHistory(List<Convention> conventions) {
        System.out.println("=== Convention History ===");
        for (Convention c : conventions) {
            System.out.println("ID: " + c.getId() + ", Details: " + c.getDetails() +
                    ", Point: " + c.getPointOfRest().getName() + ", Time: " + c.getTimestamp());
        }
    }

    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}