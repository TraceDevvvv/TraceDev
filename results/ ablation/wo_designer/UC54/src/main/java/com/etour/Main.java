package com.etour;

import com.etour.entity.Comment;
import com.etour.exception.InvalidDataException;
import com.etour.exception.ServerConnectionException;
import com.etour.repository.InMemoryCommentRepository;
import com.etour.service.CommentService;
import com.etour.service.NotificationService;
import com.etour.service.ValidationService;
import java.util.Scanner;

/**
 * Main class that simulates the Tourist editing a comment.
 * This is a console‑based simulation of the use case steps.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        InMemoryCommentRepository repository = new InMemoryCommentRepository();
        ValidationService validationService = new ValidationService();
        NotificationService notificationService = new NotificationService();
        CommentService commentService = new CommentService(repository, validationService, notificationService);

        Scanner scanner = new Scanner(System.in);

        // Simulate: Tourist IS in the details of a particular site.
        System.out.println("Tourist is viewing site details.");
        System.out.println("A previous comment exists (ID: 1, Text: 'Original comment').");

        // Step 1: Tourist chooses to change the comment.
        System.out.print("\nEnter new comment text: ");
        String newText = scanner.nextLine();

        // Step 2: Tourist uses the appropriate functionality.
        // (Here we directly call the service with a confirmation step.)

        // Step 5: Tourist confirms the change.
        System.out.print("Confirm change? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        boolean confirm = confirmation.equals("yes");

        try {
            // The service call encompasses steps 3, 4, 6 and handles exceptions.
            Comment updated = commentService.editComment(1L, newText, confirm);

            System.out.println("\nComment updated successfully!");
            System.out.println("Updated text: " + updated.getText());
            System.out.println("Updated at: " + updated.getUpdatedAt());

        } catch (InvalidDataException e) {
            System.err.println("Invalid data: " + e.getMessage());
        } catch (ServerConnectionException e) {
            System.err.println("Server error: " + e.getMessage());
        }

        scanner.close();
    }
}