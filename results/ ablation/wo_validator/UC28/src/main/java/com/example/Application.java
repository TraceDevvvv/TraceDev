package com.example;

import com.example.model.AgencyOperator;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import java.util.Arrays;
import java.util.List;

/**
 * Main application class to demonstrate the flow.
 * This simulates the sequence diagram interactions.
 */
public class Application {
    public static void main(String[] args) {
        // Setup repository with some sample data
        TagRepository repo = new TagRepository();
        repo.save(new Tag("T1", "Adventure", "Adventure trips"));
        repo.save(new Tag("T2", "Beach", "Beach vacations"));
        repo.save(new Tag("T3", "Cultural", "Cultural tours"));

        // Create serv
        com.example.service.NotificationService notifier = new com.example.service.NotificationService();
        com.example.service.ETOURConnectionService etour = new com.example.service.ETOURConnectionService();

        // Create controller with dependencies
        com.example.controller.RemoveTagController controller = 
            new com.example.controller.RemoveTagController(repo, notifier, etour);

        // Create agency operator (actor)
        AgencyOperator operator = new AgencyOperator("john_doe");
        operator.login();

        // Step 1: Get all tags (first interaction in sequence)
        System.out.println("=== Step 1: Getting all tags ===");
        List<Tag> allTags = controller.getAllTags();
        System.out.println("Total tags: " + allTags.size());
        for (Tag tag : allTags) {
            System.out.println(" - " + tag.getId() + ": " + tag.getName());
        }

        // Step 2: Delete selected tags (second interaction in sequence)
        System.out.println("\n=== Step 2: Deleting selected tags ===");
        List<String> tagIdsToDelete = Arrays.asList("T1", "T3");
        controller.deleteTags(tagIdsToDelete);

        // Verify deletion
        System.out.println("\n=== Verification: Remaining tags ===");
        List<Tag> remainingTags = controller.getAllTags();
        System.out.println("Remaining tags: " + remainingTags.size());
        for (Tag tag : remainingTags) {
            System.out.println(" - " + tag.getId() + ": " + tag.getName());
        }
    }
}