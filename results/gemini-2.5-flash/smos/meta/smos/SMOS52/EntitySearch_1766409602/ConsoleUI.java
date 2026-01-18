package EntitySearch_1766409602;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the command-line user interface for the Administrator Entity Search application.
 * It prompts the administrator for search keywords, displays the search results,
 * and manages the interaction loop until the user decides to stop.
 */
public class ConsoleUI {
    private final SearchService searchService;
    private final Scanner scanner;

    /**
     * Constructs a new ConsoleUI instance.
     *
     * @param searchService The SearchService instance to use for performing searches.
     *                      Must not be null.
     */
    public ConsoleUI(SearchService searchService) {
        if (searchService == null) {
            throw new IllegalArgumentException("SearchService cannot be null.");
        }
        this.searchService = searchService;
        this.scanner = new Scanner(System.in); // Initialize scanner for user input
    }

    /**
     * Starts the interactive console loop, allowing the administrator to
     * repeatedly search for entities until they choose to exit.
     * This method simulates the "User is logged in as administrator" precondition
     * and handles the "User inserts the text to search for and click on the 'Search' button"
     * event sequence.
     */
    public void start() {
        System.out.println("\n--- Administrator Entity Search Console ---");
        System.out.println("Enter keywords to search for entities (e.g., 'CS101 Smith').");
        System.out.println("Type 'exit' or 'quit' to stop the operation.");

        String input;
        do {
            System.out.print("\nEnter search keywords: ");
            input = scanner.nextLine().trim(); // Read user input

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Stopping search operation as requested.");
                break; // Exit the loop
            }

            if (input.isEmpty()) {
                System.out.println("No keywords entered. Please enter some keywords or 'exit' to quit.");
                continue; // Prompt again
            }

            // Perform the search using the SearchService
            List<Entity> results = searchService.searchEntities(input);

            // Display the search results
            displayResults(results, input);

        } while (true); // Loop indefinitely until 'exit' or 'quit' is entered

        scanner.close(); // Close the scanner to release system resources
        System.out.println("ConsoleUI session ended.");
        // This simulates the "User stops the operation" postcondition.
    }

    /**
     * Displays the list of entities found based on the search keywords.
     * This method simulates the "Displays the list of entities related to the searched keywords"
     * event and the "User is displaying an active list of entities" postcondition.
     *
     * @param entities The list of entities to display.
     * @param keywords The keywords used for the search.
     */
    private void displayResults(List<Entity> entities, String keywords) {
        System.out.println("\n--- Search Results for '" + keywords + "' ---");
        if (entities.isEmpty()) {
            System.out.println("No entities found matching your keywords.");
        } else {
            System.out.println("Found " + entities.size() + " entities:");
            // Group entities by type for a more organized display
            entities.stream()
                    .collect(java.util.stream.Collectors.groupingBy(Entity::getType))
                    .forEach((type, list) -> {
                        System.out.println("\n  " + type + "s:");
                        list.forEach(entity ->
                            System.out.printf("    - [%s] ID: %s, Name: %s%n",
                                    entity.getType(), entity.getId(), entity.getDisplayName())
                        );
                    });
        }
        System.out.println("------------------------------------------");
    }
}