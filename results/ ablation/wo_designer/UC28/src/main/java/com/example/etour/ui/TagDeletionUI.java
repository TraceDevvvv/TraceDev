package com.example.etour.ui;

import com.example.etour.model.Tag;
import com.example.etour.service.TagService;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console UI for the Agency Operator to delete tags.
 */
public class TagDeletionUI {
    private TagService tagService;
    private Scanner scanner;

    public TagDeletionUI() {
        this.tagService = new TagService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main interaction loop.
     */
    public void start() {
        System.out.println("=== Tag Deletion System ===");
        System.out.println("Agency Operator logged in.");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    listTags();
                    break;
                case 2:
                    deleteTags();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. View all tags");
        System.out.println("2. Delete tags");
        System.out.println("3. Exit");
    }

    private void listTags() {
        List<Tag> tags = tagService.getAllTags();
        if (tags.isEmpty()) {
            System.out.println("No tags available.");
        } else {
            System.out.println("Available Tags:");
            for (Tag tag : tags) {
                System.out.println("  " + tag.getId() + ": " + tag.getName());
            }
        }
    }

    private void deleteTags() {
        // Step 1: Display tags
        List<Tag> tags = tagService.getAllTags();
        if (tags.isEmpty()) {
            System.out.println("No tags to delete.");
            return;
        }
        System.out.println("Current Tags:");
        for (Tag tag : tags) {
            System.out.println("  " + tag.getId() + ": " + tag.getName());
        }

        // Step 2: Agency Operator selects tags
        System.out.println("\nEnter the IDs of tags you want to delete, separated by commas (e.g., 1,3,5):");
        scanner.nextLine(); // clear buffer
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("No tags selected.");
            return;
        }

        List<Integer> tagIds = new ArrayList<>();
        try {
            String[] parts = input.split(",");
            for (String part : parts) {
                tagIds.add(Integer.parseInt(part.trim()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric IDs separated by commas.");
            return;
        }

        // Step 3: Send request for deletion
        System.out.println("Sending deletion request...");
        try {
            List<Tag> deleted = tagService.deleteTags(tagIds);
            if (deleted.isEmpty()) {
                System.out.println("No tags were deleted (IDs may not exist).");
            } else {
                System.out.println("Successfully deleted the following tags:");
                for (Tag tag : deleted) {
                    System.out.println("  - " + tag.getName());
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Deletion failed due to server connection interruption.");
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input. Please enter a number: ");
        }
        int value = scanner.nextInt();
        return value;
    }
}