
package com.example.commentediting;

import com.example.commentediting.adapter.CommentUIAdapter;
import com.example.commentediting.usecase.EditCommentUseCase;
import com.example.commentediting.repo.CommentRepositoryImpl;
import com.example.commentediting.view.CommentView;
import com.example.commentediting.domain.Comment; // For initial setup inspection
import com.example.commentediting.repo.ICommentRepository;
import com.example.commentediting.usecase.IEditCommentUseCase;
import com.example.commentediting.output.IEditCommentOutputPort;
import com.example.commentediting.view.ICommentView;
import com.example.commentediting.exception.TechnicalServiceException;


import java.util.Scanner;
import java.util.Optional;

/**
 * Main application class to demonstrate the Comment Editing use case.
 * This acts as the entry point and sets up the dependency injection for the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup Dependencies
        CommentRepositoryImpl commentRepository = new CommentRepositoryImpl();
        ICommentView commentView = new CommentView();
        
        // The UIAdapter also acts as the IEditCommentOutputPort
        // So, we'll create the adapter first, then pass it as the output port to the use case.
        // We pass the commentRepository to the adapter for the selectCommentToEdit method (R4)
        CommentUIAdapter commentUIAdapter = new CommentUIAdapter(null, commentView, commentRepository);
        
        IEditCommentUseCase editCommentUseCase = new EditCommentUseCase(commentRepository, commentUIAdapter);

        // Now inject the useCase into the adapter
        // (This demonstrates a common pattern where dependencies are sometimes created in a specific order
        // or a setter injection might be used if the graph was more complex and circular dependencies existed)
        // For simplicity in this example, we can re-create with the correct useCase or make the useCase field mutable.
        // Let's just create it correctly now that useCase is instantiated.
        commentUIAdapter = new CommentUIAdapter(editCommentUseCase, commentView, commentRepository);


        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to Comment Editor ---");

        // Initial state of comments
        System.out.println("\n--- Current Comments ---");
        try {
            commentRepository.findById("c1").ifPresent(c -> System.out.println("c1: " + c.getText() + " by " + c.getTouristId()));
        } catch (TechnicalServiceException e) {
            System.err.println("Error displaying comment c1: " + e.getMessage());
        }
        try {
            commentRepository.findById("c2").ifPresent(c -> System.out.println("c2: " + c.getText() + " by " + c.getTouristId()));
        } catch (TechnicalServiceException e) {
            System.err.println("Error displaying comment c2: " + e.getMessage());
        }
        try {
            commentRepository.findById("c3").ifPresent(c -> System.out.println("c3: " + c.getText() + " by " + c.getTouristId()));
        } catch (TechnicalServiceException e) {
            System.err.println("Error displaying comment c3: " + e.getMessage());
        }
        System.out.println("------------------------\n");


        // --- Scenario 1: Successful Edit (Tourist t1 edits c1) ---
        System.out.println("--- SCENARIO 1: Successful Edit (Tourist t1 edits c1) ---");
        String commentId1 = "c1";
        String touristId1 = "t1";
        String siteId1 = "s1";
        String newText1 = "This is a truly magnificent site! Highly recommended.";

        System.out.println("\nTourist t1 wants to edit comment c1.");
        // Assuming selectCommentToEdit handles its own TechnicalServiceException or doesn't throw it directly to main
        commentUIAdapter.selectCommentToEdit(commentId1); // R4

        System.out.println("\nTourist t1 enters new text for c1: \"" + newText1 + "\"");
        commentUIAdapter.handleEditRequest(commentId1, newText1, touristId1, siteId1);

        System.out.println("Simulating Tourist t1 confirming the change (type 'yes'):");
        String confirmation1 = scanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation1)) {
            // Assuming confirmChange handles its own TechnicalServiceException or doesn't throw it directly to main
            commentUIAdapter.confirmChange(commentId1, newText1, touristId1, siteId1);
        } else {
            System.out.println("Edit cancelled by user.");
        }
        String c1TextAfterEdit;
        try {
            c1TextAfterEdit = commentRepository.findById("c1").map(Comment::getText).orElse("Not found");
        } catch (TechnicalServiceException e) {
            c1TextAfterEdit = "Error retrieving comment c1 after edit: " + e.getMessage();
            System.err.println(c1TextAfterEdit);
        }
        System.out.println("Comment c1 after edit: " + c1TextAfterEdit);
        System.out.println("--------------------------------------------------\n");


        // --- Scenario 2: Permission Denied (Tourist t3 tries to edit c2) ---
        System.out.println("--- SCENARIO 2: Permission Denied (Tourist t3 tries to edit c2) ---");
        String commentId2 = "c2";
        String touristId2 = "t3"; // T3 is not the owner of c2 (t2 is)
        String siteId2 = "s1";
        String newText2 = "This comment should not be changed by t3.";

        System.out.println("\nTourist t3 wants to edit comment c2.");
        commentUIAdapter.selectCommentToEdit(commentId2); // R4

        System.out.println("\nTourist t3 enters new text for c2: \"" + newText2 + "\"");
        commentUIAdapter.handleEditRequest(commentId2, newText2, touristId2, siteId2);

        System.out.println("Simulating Tourist t3 confirming the change (type 'yes'):");
        String confirmation2 = scanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation2)) {
            commentUIAdapter.confirmChange(commentId2, newText2, touristId2, siteId2);
        } else {
            System.out.println("Edit cancelled by user.");
        }
        String c2TextAfterAttemptedEdit;
        try {
            c2TextAfterAttemptedEdit = commentRepository.findById("c2").map(Comment::getText).orElse("Not found");
        } catch (TechnicalServiceException e) {
            c2TextAfterAttemptedEdit = "Error retrieving comment c2 after attempted edit: " + e.getMessage();
            System.err.println(c2TextAfterAttemptedEdit);
        }
        System.out.println("Comment c2 after attempted edit: " + c2TextAfterAttemptedEdit);
        System.out.println("----------------------------------------------------------\n");


        // --- Scenario 3: Validation Error (Empty new text) ---
        System.out.println("--- SCENARIO 3: Validation Error (Empty new text) ---");
        String commentId3 = "c3";
        String touristId3 = "t1";
        String siteId3 = "s2";
        String newText3 = ""; // Invalid new text

        System.out.println("\nTourist t1 wants to edit comment c3 with empty text.");
        commentUIAdapter.selectCommentToEdit(commentId3); // R4

        System.out.println("\nTourist t1 enters empty text for c3.");
        commentUIAdapter.handleEditRequest(commentId3, newText3, touristId3, siteId3);
        String c3TextAfterAttemptedEdit;
        try {
            c3TextAfterAttemptedEdit = commentRepository.findById("c3").map(Comment::getText).orElse("Not found");
        } catch (TechnicalServiceException e) {
            c3TextAfterAttemptedEdit = "Error retrieving comment c3 after attempted edit: " + e.getMessage();
            System.err.println(c3TextAfterAttemptedEdit);
        }
        System.out.println("Comment c3 after attempted edit: " + c3TextAfterAttemptedEdit);
        System.out.println("--------------------------------------------------\n");

        // --- Scenario 4: Technical Service Exception (R12) ---
        System.out.println("--- SCENARIO 4: Technical Service Exception (R12) ---");
        commentRepository.setSimulateTechnicalError(true); // Enable simulation of technical errors
        String commentId4 = "c1";
        String touristId4 = "t1";
        String siteId4 = "s1";
        String newText4 = "This text should cause a technical error.";

        System.out.println("\nTourist t1 tries to edit comment c1, but a technical error occurs during repo access.");
        // The selectCommentToEdit call itself might trigger an error if findById fails.
        // Assuming CommentUIAdapter methods handle or wrap TechnicalServiceException if it's a checked exception,
        // or it's an unchecked exception. The current 'try-catch' blocks are invalid if the adapter
        // methods do not declare 'throws TechnicalServiceException'.
        commentUIAdapter.selectCommentToEdit(commentId4);

        System.out.println("\nAttempting handleEditRequest (this might also fail if findById is called):");
        commentUIAdapter.handleEditRequest(commentId4, newText4, touristId4, siteId4);

        System.out.println("Simulating confirmation, which will lead to a save operation and trigger another error:");
        String confirmation4 = scanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation4)) {
            commentUIAdapter.confirmChange(commentId4, newText4, touristId4, siteId4);
        } else {
            System.out.println("Edit cancelled by user.");
        }
        String c1TextAfterTechnicalError;
        try {
            c1TextAfterTechnicalError = commentRepository.findById("c1").map(Comment::getText).orElse("Not found (error occurred)");
        } catch (TechnicalServiceException e) {
            c1TextAfterTechnicalError = "Error retrieving comment c1 after technical error simulation: " + e.getMessage();
            System.err.println(c1TextAfterTechnicalError);
        }
        System.out.println("Comment c1 after attempted edit with technical error: " + c1TextAfterTechnicalError);
        commentRepository.setSimulateTechnicalError(false); // Disable simulation for subsequent operations
        System.out.println("--------------------------------------------------\n");


        scanner.close();
        System.out.println("--- End of Demonstration ---");
    }
}
