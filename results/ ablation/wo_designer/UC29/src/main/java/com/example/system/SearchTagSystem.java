package com.example.system;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * System that manages search tags and handles duplicate entry scenarios.
 */
public class SearchTagSystem {
    private Set<String> existingTags;
    private Scanner scanner;
    private String previousState;

    public SearchTagSystem() {
        existingTags = new HashSet<>();
        scanner = new Scanner(System.in);
        // Initialize with some existing tags for demonstration
        existingTags.add("java");
        existingTags.add("python");
        existingTags.add("database");
        previousState = "Ready to accept a search tag.";
    }

    /**
     * Main method to run the tag entry flow.
     */
    public void startTagEntry() {
        System.out.println("System: " + previousState);
        System.out.print("Please enter a search tag: ");
        String inputTag = scanner.nextLine().trim();

        handleTagEntry(inputTag);
    }

    /**
     * Handles the tag entry logic.
     * @param tag The tag entered by the user.
     */
    private void handleTagEntry(String tag) {
        if (existingTags.contains(tag)) {
            displayErrorMessage(tag);
        } else {
            existingTags.add(tag);
            System.out.println("Tag '" + tag + "' added successfully.");
            previousState = "Tag added successfully.";
        }
    }

    /**
     * Displays error message for duplicate tag and manages recovery.
     * @param duplicateTag The duplicate tag entered.
     */
    private void displayErrorMessage(String duplicateTag) {
        System.out.println("\nError: The tag '" + duplicateTag + "' is already in the system.");
        System.out.println("Please choose a unique tag.");
        askForConfirmation();
    }

    /**
     * Asks user for confirmation that they have read the error message.
     */
    private void askForConfirmation() {
        System.out.print("Have you read the error message? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            recoverPreviousState();
        } else {
            System.out.println("Please read the error message before proceeding.");
            askForConfirmation(); // Recursively ask until confirmed
        }
    }

    /**
     * Recovers the system to the previous state immediately.
     */
    private void recoverPreviousState() {
        System.out.println("\nRecovering previous state...");
        System.out.println("System: " + previousState);
        System.out.println("Control returned to user interaction.\n");
        // In a real application, you might reset UI or re‑prompt
        startTagEntry();
    }

    public static void main(String[] args) {
        SearchTagSystem system = new SearchTagSystem();
        system.startTagEntry();
    }
}