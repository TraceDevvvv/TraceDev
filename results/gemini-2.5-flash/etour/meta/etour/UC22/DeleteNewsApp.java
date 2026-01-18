package com.example.newsapp;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class to simulate the "Delete News" use case.
 * This class acts as the Agency Operator, interacting with the NewsService.
 */
public class DeleteNewsApp {

    private final NewsService newsService;
    private final Scanner scanner;

    /**
     * Constructor to initialize the application with a NewsService and Scanner.
     */
    public DeleteNewsApp() {
        this.newsService = new NewsService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the login process for an Agency Operator.
     * In a real application, this would involve authentication.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean agencyLogin() {
        System.out.println("--- Agency Operator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // For demonstration, any non-empty username/password is considered a successful login.
        // In a real system, this would involve checking against a user database.
        if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }

    /**
     * Displays all available news articles to the operator.
     *
     * @return true if there are news articles to display, false otherwise.
     */
    private boolean viewAllNews() {
        System.out.println("\n--- All News Articles ---");
        List<News> allNews = newsService.getAllNews();

        if (allNews.isEmpty()) {
            System.out.println("No news articles available in the system.");
            return false;
        } else {
            for (int i = 0; i < allNews.size(); i++) {
                System.out.println((i + 1) + ". " + allNews.get(i));
            }
            return true;
        }
    }

    /**
     * Prompts the operator to select a news article for deletion and confirms the action.
     *
     * @param newsList The list of news articles currently displayed.
     * @return The ID of the news to be deleted, or null if the operation is cancelled or invalid input.
     */
    private String selectNewsForDeletion(List<News> newsList) {
        System.out.print("Enter the number of the news to delete (or 'c' to cancel): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("c")) {
            System.out.println("Operation cancelled by Agency Operator.");
            return null;
        }

        try {
            int selection = Integer.parseInt(input);
            if (selection > 0 && selection <= newsList.size()) {
                News selectedNews = newsList.get(selection - 1);
                System.out.println("You selected: " + selectedNews);
                System.out.print("Are you sure you want to delete this news? (yes/no): ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("yes")) {
                    return selectedNews.getId();
                } else {
                    System.out.println("Deletion cancelled by Agency Operator.");
                    return null;
                }
            } else {
                System.out.println("Invalid selection. Please enter a number within the range.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'c' to cancel.");
            return null;
        }
    }

    /**
     * Executes the "Delete News" use case flow.
     */
    public void run() {
        // Entry condition: The agency has logged in.
        if (!agencyLogin()) {
            System.out.println("Exiting application due to login failure.");
            return;
        }

        System.out.println("\n--- Activating News Deletion Function ---");

        // Loop to allow multiple deletions or cancellation
        while (true) {
            // 2. View all news in a form.
            List<News> currentNews = newsService.getAllNews();
            if (!viewAllNews()) {
                System.out.println("No news to delete. Exiting deletion function.");
                break; // Exit if no news available
            }

            // 3. Select a news from the list and submit the form.
            // 4. Asks for confirmation of the transaction.
            String newsIdToDelete = selectNewsForDeletion(currentNews);

            if (newsIdToDelete == null) {
                // Operator cancelled or invalid input, ask if they want to try again or exit
                System.out.print("Do you want to try deleting another news item? (yes/no): ");
                String tryAgain = scanner.nextLine();
                if (!tryAgain.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting news deletion function.");
                    break;
                }
                continue; // Continue to show news again
            }

            // 5. Confirm the deletion of the news. (Already handled in selectNewsForDeletion)
            // 6. Delete the data news.
            boolean deleted = newsService.deleteNews(newsIdToDelete);

            // Exit conditions: The system shall notify the successful elimination of the news.
            if (deleted) {
                System.out.println("SUCCESS: News with ID '" + newsIdToDelete + "' has been successfully deleted.");
            } else {
                // This case should ideally not happen if newsIdToDelete came from currentNews,
                // but good for robustness against concurrent modifications or bugs.
                System.out.println("ERROR: Could not delete news with ID '" + newsIdToDelete + "'. It might have been already deleted or never existed.");
            }

            // Ask if the operator wants to delete more news
            System.out.print("Do you want to delete another news item? (yes/no): ");
            String deleteMore = scanner.nextLine();
            if (!deleteMore.equalsIgnoreCase("yes")) {
                System.out.println("Exiting news deletion function.");
                break;
            }
        }
        scanner.close(); // Close the scanner when done
        System.out.println("Application session ended.");
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Simulate potential connection interruption (Quality requirement)
        // For this simple console app, we'll just print a message.
        // In a real app, this would involve network error handling.
        // System.out.println("Checking server connection...");
        // if (Math.random() < 0.1) { // 10% chance of connection interruption
        //     System.err.println("ERROR: Interruption of the connection to the server ETOUR. Please try again later.");
        //     return;
        // }

        DeleteNewsApp app = new DeleteNewsApp();
        app.run();
    }
}