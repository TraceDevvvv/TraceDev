package com.news.management.system.cli;

import com.news.management.system.exception.DatabaseOperationException;
import com.news.management.system.exception.InvalidNewsDataException;
import com.news.management.system.model.NewsArticle;
import com.news.management.system.service.NewsArticleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Command-line interface for inserting news articles.
 * This class handles user interaction, gathers input, displays messages,
 * and interacts with the NewsArticleService to perform news insertion.
 */
public class NewsInsertionCLI {

    private final NewsArticleService newsArticleService;
    private final Scanner scanner;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructs a NewsInsertionCLI with the given NewsArticleService.
     *
     * @param newsArticleService The service responsible for news article business logic.
     */
    public NewsInsertionCLI(NewsArticleService newsArticleService) {
        this.newsArticleService = newsArticleService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the news insertion process.
     * This method guides the user through activating the feature, filling the form,
     * confirming the data, and storing the news.
     */
    public void start() {
        displayMessage("--- Activate Feature: Insert New News Article ---");

        while (true) {
            try {
                // 2. Displays the corresponding form.
                // 3. Fill out the form and submit.
                NewsArticle newArticle = getNewsArticleFromUserInput();

                // 4. Verify the data entered and asks for confirmation of the transaction.
                // Where the data is invalid or insufficient, the system activates the use case Errored.
                // Validation is done within the service layer, but we can pre-validate for immediate feedback.
                try {
                    newsArticleService.validateNewsArticle(newArticle);
                    displayMessage("\n--- Data Verification ---");
                    displayMessage("Please review the news article details:");
                    displayMessage(newArticle.toString());

                    // 5. Confirm the operation of insertion.
                    if (confirmOperation()) {
                        // 6. Stores the data of the new news.
                        newsArticleService.insertNews(newArticle);
                        displayMessage("\nSUCCESS: News article '" + newArticle.getTitle() + "' has been successfully inserted.");
                        break; // Exit after successful insertion
                    } else {
                        displayMessage("Operation cancelled by Agency Operator. Returning to main menu.");
                        break; // Exit or return to a main menu
                    }
                } catch (InvalidNewsDataException e) {
                    displayError("Validation Error: " + e.getMessage());
                    displayMessage("Please correct the input and try again.");
                    // Loop back to get input again
                }

            } catch (DatabaseOperationException e) {
                displayError("System Error: A database operation failed. Please try again later or contact support.");
                displayError("Details: " + e.getMessage());
                // Interruption of the connection to the server ETOUR.
                // In a real system, this might trigger a retry mechanism or more robust error handling.
                break; // Exit on critical system error
            } catch (Exception e) {
                displayError("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                break; // Exit on unexpected error
            }
        }
        scanner.close();
    }

    /**
     * Gathers news article details from user input via the command line.
     *
     * @return A NewsArticle object populated with user-provided data.
     */
    public NewsArticle getNewsArticleFromUserInput() {
        displayMessage("\n--- Enter News Article Details ---");

        String title = promptForInput("Title (required, 5-255 chars): ");
        String content = promptForInput("Content (required, 20-65535 chars): ");
        String author = promptForInput("Author (required, 3-100 chars): ");

        Date publicationDate = null;
        while (publicationDate == null) {
            String dateString = promptForInput("Publication Date (yyyy-MM-dd, e.g., 2023-10-27): ");
            try {
                publicationDate = DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                displayError("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        String categoriesInput = promptForInput("Categories (comma-separated, e.g., Politics,Economy - optional): ");
        List<String> categories = Arrays.stream(categoriesInput.split(","))
                                       .map(String::trim)
                                       .filter(s -> !s.isEmpty())
                                       .collect(Collectors.toList());

        String tagsInput = promptForInput("Tags (comma-separated, e.g., election,budget - optional): ");
        List<String> tags = Arrays.stream(tagsInput.split(","))
                                   .map(String::trim)
                                   .filter(s -> !s.isEmpty())
                                   .collect(Collectors.toList());

        // ID will be generated by the NewsArticle constructor
        return new NewsArticle(title, content, author, publicationDate, categories, tags);
    }

    /**
     * Prompts the user for confirmation of an operation.
     *
     * @return true if the user confirms, false otherwise.
     */
    public boolean confirmOperation() {
        String confirmation = promptForInput("Confirm insertion? (yes/no): ");
        return confirmation.equalsIgnoreCase("yes");
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to display.
     */
    public void displayError(String error) {
        System.err.println("ERROR: " + error);
    }

    /**
     * Helper method to prompt for input and read a line from the console.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The user's input string.
     */
    private String promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}