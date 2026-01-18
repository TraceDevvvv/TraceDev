package com.agency.modifycomment.controller;

import com.agency.modifycomment.model.Comment;
import com.agency.modifycomment.model.Feedback;
import com.agency.modifycomment.model.Site;
import com.agency.modifycomment.service.CommentService;
import com.agency.modifycomment.service.FeedbackService;
import com.agency.modifycomment.service.SiteService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Controller class for handling user interactions related to modifying comments.
 * This class orchestrates the flow of the ModifyComment use case,
 * interacting with serv and handling user input/output.
 */
public class ModifyCommentController {
    private final SiteService siteService;
    private final FeedbackService feedbackService;
    private final CommentService commentService;
    private boolean loggedIn = true; // Simulate logged-in state for the agency operator

    /**
     * Constructs a new ModifyCommentController with the given serv.
     *
     * @param siteService     The service for site-related operations.
     * @param feedbackService The service for feedback-related operations.
     * @param commentService  The service for comment-related operations.
     */
    public ModifyCommentController(SiteService siteService, FeedbackService feedbackService, CommentService commentService) {
        this.siteService = siteService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
    }

    /**
     * Starts the ModifyComment application flow.
     * This method guides the user through selecting a site, feedback, and then modifying a comment.
     *
     * @param scanner The Scanner object for reading user input.
     */
    public void start(Scanner scanner) {
        if (!loggedIn) {
            System.out.println("Error: Agency operator not logged in. Please log in to proceed.");
            return;
        }

        System.out.println("--- Modify Comment Use Case ---");

        // Step 1: View the list of sites and select one
        Optional<Site> selectedSite = selectSite(scanner);
        if (selectedSite.isEmpty()) {
            System.out.println("Operation cancelled or no site selected.");
            return;
        }
        Site site = selectedSite.get();
        System.out.println("Selected Site: " + site.getName() + " (ID: " + site.getId() + ")");

        // Step 2 & 3: Upload feedback issued to that site, display them, and select one
        Optional<Feedback> selectedFeedback = selectFeedback(scanner, site.getId());
        if (selectedFeedback.isEmpty()) {
            System.out.println("Operation cancelled or no feedback selected.");
            return;
        }
        Feedback feedback = selectedFeedback.get();
        System.out.println("Selected Feedback: " + feedback.getTitle() + " (ID: " + feedback.getId() + ")");
        System.out.println("Current Comment: " + feedback.getComment());

        // Step 4 & 5: Display form for editing comment, edit and submit
        editAndSubmitComment(scanner, feedback);
    }

    /**
     * Allows the user to select a site from a displayed list.
     *
     * @param scanner The Scanner object for reading user input.
     * @return An Optional containing the selected Site, or empty if cancelled or invalid input.
     */
    private Optional<Site> selectSite(Scanner scanner) {
        List<Site> sites = siteService.getAllSites();
        if (sites.isEmpty()) {
            System.out.println("No sites available.");
            return Optional.empty();
        }

        System.out.println("\nAvailable Sites:");
        for (int i = 0; i < sites.size(); i++) {
            System.out.println((i + 1) + ". " + sites.get(i).getName() + " (ID: " + sites.get(i).getId() + ")");
        }
        System.out.println("Enter the number of the site to select, or 'c' to cancel:");

        while (true) {
            System.out.print("Your choice: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("c")) {
                return Optional.empty(); // Operator cancels
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= sites.size()) {
                    return Optional.of(sites.get(choice - 1));
                } else {
                    System.out.println("Invalid site number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'c'.");
            }
        }
    }

    /**
     * Allows the user to select a feedback from a displayed list for a given site.
     *
     * @param scanner The Scanner object for reading user input.
     * @param siteId  The ID of the site to retrieve feedback for.
     * @return An Optional containing the selected Feedback, or empty if cancelled or invalid input.
     */
    private Optional<Feedback> selectFeedback(Scanner scanner, String siteId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksBySiteId(siteId);
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback available for this site.");
            return Optional.empty();
        }

        System.out.println("\nFeedback for Site ID " + siteId + ":");
        for (int i = 0; i < feedbacks.size(); i++) {
            Feedback fb = feedbacks.get(i);
            System.out.println((i + 1) + ". Title: " + fb.getTitle() + " (ID: " + fb.getId() + ")");
            System.out.println("   Comment: " + fb.getComment());
        }
        System.out.println("Enter the number of the feedback to select, or 'c' to cancel:");

        while (true) {
            System.out.print("Your choice: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("c")) {
                return Optional.empty(); // Operator cancels
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= feedbacks.size()) {
                    return Optional.of(feedbacks.get(choice - 1));
                } else {
                    System.out.println("Invalid feedback number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'c'.");
            }
        }
    }

    /**
     * Handles the process of editing and submitting a comment for a selected feedback.
     * Includes data validation and confirmation steps.
     *
     * @param scanner  The Scanner object for reading user input.
     * @param feedback The Feedback object whose comment is to be modified.
     */
    private void editAndSubmitComment(Scanner scanner, Feedback feedback) {
        System.out.println("\n--- Editing Comment for Feedback ID: " + feedback.getId() + " ---");
        System.out.println("Current Comment: " + feedback.getComment());
        System.out.println("Enter new comment text (or 'c' to cancel):");

        String newCommentText;
        while (true) {
            System.out.print("New Comment: ");
            newCommentText = scanner.nextLine();

            if (newCommentText.equalsIgnoreCase("c")) {
                System.out.println("Comment modification cancelled.");
                return; // Operator cancels
            }

            // Step 6: Verify the data entered
            if (!commentService.isValidCommentText(newCommentText)) {
                System.out.println("Error: Comment cannot be empty. Please enter valid text.");
                // Activate use case Errored (simulated by printing error message)
            } else {
                break; // Valid input, proceed
            }
        }

        System.out.println("\n--- Confirm Change ---");
        System.out.println("Original Comment: " + feedback.getComment());
        System.out.println("New Comment:      " + newCommentText);
        System.out.print("Confirm change? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            // Step 7: Confirm the operation
            // Step 8: Remember the comment changed
            try {
                // In this design, Feedback object holds the comment directly.
                // We update the Feedback object's comment and then save it.
                // If comments were separate entities, we'd fetch the Comment object, update its text, and save it.
                // For this use case, we assume a 1:1 relationship where Feedback.comment is the target.
                // However, the system design also includes a Comment model and CommentService.
                // Let's use the CommentService to update the comment associated with the feedback.

                // Find the comment associated with this feedback
                List<Comment> commentsForFeedback = commentService.getCommentsByFeedbackId(feedback.getId());
                if (commentsForFeedback.isEmpty()) {
                    System.out.println("Error: No comment found for feedback ID " + feedback.getId() + ". Cannot modify.");
                    return;
                }
                // Assuming one comment per feedback for simplicity in this use case
                Comment commentToModify = commentsForFeedback.get(0);

                // Create a new Comment object with the updated text for the service layer
                Comment updatedComment = new Comment(commentToModify.getId(), commentToModify.getFeedbackId(), newCommentText);

                commentService.updateComment(updatedComment);

                // Also update the feedback object in the feedback service to reflect the change
                // This ensures consistency if Feedback objects are also cached or used elsewhere
                feedback.setComment(newCommentText);
                feedbackService.updateFeedback(feedback); // Update the feedback in its repository

                System.out.println("Comment successfully updated!");
                System.out.println("Notification system has been modified for the comment feedback selected.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error during comment update: " + e.getMessage());
                // Activate use case Errored
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                // Simulate interruption of connection to the server or other system errors
            }
        } else {
            System.out.println("Comment modification cancelled by operator.");
        }
        System.out.println("--- End Modify Comment Use Case ---");
    }
}