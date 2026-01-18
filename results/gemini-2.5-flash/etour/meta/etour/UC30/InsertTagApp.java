package com.example.inserttag;

import java.util.Scanner;

/**
 * Main application class to demonstrate the 'InsertTag' use case.
 * This class simulates the user interaction flow, including accessing functionality,
 * showing a form, filling data, and handling various outcomes (success, existing tag, invalid data, server error).
 */
public class InsertTagApp {

    private final TagService tagService;
    private final Scanner scanner;

    /**
     * Constructs the InsertTagApp with a TagService dependency.
     *
     * @param tagService The service layer responsible for tag operations.
     */
    public InsertTagApp(TagService tagService) {
        this.tagService = tagService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the login process for an agency operator.
     * In a real application, this would involve authentication.
     *
     * @return true if the operator is "logged in", false otherwise.
     */
    private boolean agencyLogin() {
        System.out.println("--- Agency Operator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple simulation: any non-empty username/password is considered logged in.
        // In a real system, this would involve actual authentication logic.
        if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }

    /**
     * Displays the form for entering a new tag and collects user input.
     *
     * @return A TagInput object containing the collected tag name and description.
     */
    private TagInput showTagForm() {
        System.out.println("\n--- Insert New Tag Search ---");
        System.out.println("Please fill out the form with the required information.");

        System.out.print("Tag Name (required): ");
        String tagName = scanner.nextLine();

        System.out.print("Tag Description (optional): ");
        String tagDescription = scanner.nextLine();

        return new TagInput(tagName, tagDescription);
    }

    /**
     * Represents the input collected from the tag form.
     */
    private static class TagInput {
        final String name;
        final String description;

        TagInput(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    /**
     * Runs the 'InsertTag' use case flow.
     * This method orchestrates the steps as described in the use case.
     */
    public void run() {
        // Entry Operator conditions: The agency has logged.
        if (!agencyLogin()) {
            System.out.println("Cannot proceed with tag insertion. Operator not logged in.");
            return;
        }

        // 1. Access the functionality of inserting new tag search.
        System.out.println("\nAccessing 'Insert New Tag Search' functionality...");

        // Loop to allow multiple tag insertions or retry after errors
        while (true) {
            // 2. Show the form for entering a tag.
            // 3. Fill out the form with the required information and submit.
            TagInput tagInput = showTagForm();

            try {
                // 4. Verify the data entered and check if the tag is already present.
                // This logic is encapsulated within the TagService.
                Tag insertedTag = tagService.insertTag(tagInput.name, tagInput.description);

                // Exit conditions: The notification about the inclusion of the tag.
                System.out.println("\nSUCCESS: Tag '" + insertedTag.getName() + "' inserted successfully!");
                System.out.println("Details: " + insertedTag);

            } catch (InvalidTagDataException e) {
                // Activates the use case Errored.
                System.err.println("\nERROR: Invalid or insufficient tag data. (Errored use case)");
                System.err.println("Reason: " + e.getMessage());
            } catch (TagAlreadyExistsException e) {
                // Activates the use case ExistingErrorTag.
                System.err.println("\nERROR: Tag already exists in the system. (ExistingErrorTag use case)");
                System.err.println("Reason: " + e.getMessage());
            } catch (ServerConnectionException e) {
                // Interruption of the connection to the server ETOUR.
                System.err.println("\nCRITICAL ERROR: Server connection interrupted! (ETOUR)");
                System.err.println("Reason: " + e.getMessage());
                System.out.println("Exiting application due to server connection loss.");
                break; // Exit the application loop on critical server error
            } catch (Exception e) {
                // Catch any other unexpected errors
                System.err.println("\nAN UNEXPECTED ERROR OCCURRED: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.print("\nDo you want to insert another tag? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\n--- Application Finished ---");
        scanner.close();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize dependencies
        TagRepository tagRepository = new TagRepository();
        TagService tagService = new TagService(tagRepository);

        // Create and run the application instance
        InsertTagApp app = new InsertTagApp(tagService);
        app.run();
    }
}