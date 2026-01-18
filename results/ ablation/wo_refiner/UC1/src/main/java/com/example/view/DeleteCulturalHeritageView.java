package com.example.view;

import com.example.model.CulturalHeritage;
import java.util.List;

/**
 * View for deleting cultural heritage.
 * Added to satisfy requirement REQ-005.
 */
public class DeleteCulturalHeritageView {
    public void displayList(List<CulturalHeritage> culturalHeritageList) {
        System.out.println("Displaying cultural heritage list:");
        for (CulturalHeritage ch : culturalHeritageList) {
            System.out.println("ID: " + ch.getId() + ", Name: " + ch.getName());
        }
    }

    public void showDeleteConfirmation(String token) {
        System.out.println("Delete confirmation token: " + token);
        System.out.println("Please confirm the deletion by entering the token.");
    }

    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void showErrorMessage(int errorCode, String message) {
        System.out.println("ERROR [" + errorCode + "]: " + message);
    }

    public void selectCulturalHeritage(int culturalHeritageId) {
        System.out.println("Selected CulturalHeritage with ID: " + culturalHeritageId + " for deletion.");
    }

    public void activateDeleteFunction() {
        System.out.println("Delete function activated.");
    }

    public void displayConfirmationDialog(String token) {
        System.out.println("Display confirmation dialog with token: " + token);
    }

    public void submitConfirmation(String token) {
        System.out.println("Submitted confirmation with token: " + token);
    }

    public void displaySuccessNotification(String message) {
        System.out.println("SUCCESS NOTIFICATION: " + message);
    }

    public void cancelOperation() {
        System.out.println("Operation cancelled.");
    }

    public void displayOperationCancelled() {
        System.out.println("Operation cancelled. No action taken.");
    }
}