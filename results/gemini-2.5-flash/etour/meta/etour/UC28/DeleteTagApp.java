package DeleteTag_1765892478;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main application to simulate user interaction for the DeleteTag use case.
 * This class demonstrates the flow of events as described in the use case:
 * 1. Access the functionality to delete a tag.
 * 2. Research in the existing system, the tags and displays them in a form.
 * 3. Select one or more tags from the list and sends the request for deletion.
 * 4. Delete the selected search tag.
 * It also simulates an "Agency Operator" being logged in.
 */
public class DeleteTagApp {

    private final TagManager tagManager;
    private final Scanner scanner;
    private boolean isLoggedIn; // Simulates the "Entry Operator conditions: The agency has logged."

    /**
     * Constructs a new DeleteTagApp.
     * Initializes the TagManager and a Scanner for user input.
     */
    public DeleteTagApp() {
        this.tagManager = new TagManager();
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false; // Initially not logged in
        initializeTags(); // Populate some initial tags for demonstration
    }

    /**
     * Populates the TagManager with some predefined tags for demonstration purposes.
     */
    private void initializeTags() {
        tagManager.addTag(new Tag("1", "Travel"));
        tagManager.addTag(new Tag("2", "Adventure"));
        tagManager.addTag(new Tag("3", "Beach"));
        tagManager.addTag(new Tag("4", "Mountain"));
        tagManager.addTag(new Tag("5", "City Break"));
        tagManager.addTag(new Tag("6", "Culture"));
        tagManager.addTag(new Tag("7", "Food Tour"));
        System.out.println("Initial tags loaded.");
    }

    /**
     * Simulates the login process for an agency operator.
     * For this simulation, any input is considered a successful login.
     */
    public void login() {
        System.out.println("\n--- Agency Operator Login ---");
        System.out.print("Enter username: ");
        scanner.nextLine(); // Consume the username input
        System.out.print("Enter password: ");
        scanner.nextLine(); // Consume the password input
        this.isLoggedIn = true;
        System.out.println("Login successful. Welcome, Agency Operator!");
    }

    /**
     * Displays the main menu for the Delete Tag functionality.
     */
    public void displayMenu() {
        if (!isLoggedIn) {
            System.out.println("Please log in first to access this functionality.");
            return;
        }

        System.out.println("\n--- Delete Tag Functionality ---");
        System.out.println("1. Access Delete Tag functionality");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Handles the user's choice from the main menu.
     *
     * @param choice The integer choice made by the user.
     * @return true if the application should continue running, false to exit.
     */
    public boolean handleMenuChoice(int choice) {
        if (!isLoggedIn && choice != 2) { // Allow exit even if not logged in
            System.out.println("You must be logged in to perform this action.");
            return true;
        }

        switch (choice) {
            case 1:
                accessDeleteTagFunctionality();
                break;
            case 2:
                System.out.println("Exiting application. Goodbye!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    /**
     * Implements the core logic for accessing and performing tag deletion.
     * This method follows the flow of events described in the use case.
     */
    private void accessDeleteTagFunctionality() {
        System.out.println("\n--- Accessing Delete Tag Functionality ---");

        // Step 2: Research in the existing system, the tags and displays them in a form.
        List<Tag> availableTags = tagManager.getAllTags();
        if (availableTags.isEmpty()) {
            System.out.println("No tags found in the system to delete.");
            return;
        }

        System.out.println("\nAvailable Tags:");
        availableTags.forEach(tag -> System.out.println("ID: " + tag.getId() + ", Name: " + tag.getName()));

        // Simulate search functionality (optional, but good for "Research")
        System.out.print("\nEnter a search term to filter tags (or press Enter to show all): ");
        String searchTerm = scanner.nextLine();
        List<Tag> filteredTags = tagManager.searchTags(searchTerm);

        if (filteredTags.isEmpty()) {
            System.out.println("No tags match your search term: '" + searchTerm + "'");
            return;
        }

        System.out.println("\nTags matching your search:");
        filteredTags.forEach(tag -> System.out.println("ID: " + tag.getId() + ", Name: " + tag.getName()));


        // Step 3: Select one or more tags from the list and sends the request for deletion.
        System.out.print("\nEnter the IDs of the tags to delete, separated by commas (e.g., 1,3,5): ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            System.out.println("No tags selected for deletion.");
            return;
        }

        // Parse the input IDs
        List<String> tagIdsToDelete = Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // Validate if selected IDs actually exist in the filtered list
        Set<String> validFilteredTagIds = filteredTags.stream()
                                                    .map(Tag::getId)
                                                    .collect(Collectors.toSet());

        List<String> actualIdsForDeletion = tagIdsToDelete.stream()
                                                        .filter(validFilteredTagIds::contains)
                                                        .collect(Collectors.toList());

        if (actualIdsForDeletion.isEmpty()) {
            System.out.println("None of the entered IDs correspond to available or filtered tags. No tags deleted.");
            return;
        }

        // Step 4: Delete the selected search tag.
        try {
            // Simulate potential server interruption (Quality Requirement)
            if (Math.random() < 0.1) { // 10% chance of interruption
                throw new RuntimeException("Simulated ETOUR server connection interruption.");
            }

            List<Tag> deletedTags = tagManager.deleteTags(actualIdsForDeletion);

            // Exit conditions: The system shall notify the successful elimination of selected tags.
            if (!deletedTags.isEmpty()) {
                System.out.println("\nSuccessfully deleted the following tags:");
                deletedTags.forEach(tag -> System.out.println(" - ID: " + tag.getId() + ", Name: " + tag.getName()));
            } else {
                System.out.println("No tags were deleted. They might not have existed or were already removed.");
            }

            // Display remaining tags
            System.out.println("\nRemaining Tags in the system:");
            List<Tag> currentTags = tagManager.getAllTags();
            if (currentTags.isEmpty()) {
                System.out.println("No tags remaining.");
            } else {
                currentTags.forEach(tag -> System.out.println("ID: " + tag.getId() + ", Name: " + tag.getName()));
            }

        } catch (RuntimeException e) {
            System.err.println("Error during tag deletion: " + e.getMessage());
            System.err.println("Deletion failed due to server interruption. Please try again later.");
        }
    }

    /**
     * The main method to run the DeleteTag application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DeleteTagApp app = new DeleteTagApp();
        app.login(); // Simulate agency operator login

        boolean running = true;
        while (running) {
            app.displayMenu();
            try {
                int choice = Integer.parseInt(app.scanner.nextLine());
                running = app.handleMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        app.scanner.close(); // Close the scanner when the application exits
    }
}