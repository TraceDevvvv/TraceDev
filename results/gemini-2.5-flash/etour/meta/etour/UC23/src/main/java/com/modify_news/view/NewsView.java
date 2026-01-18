package com.modify_news.view;

import com.modify_news.model.News;
import com.modify_news.util.InputValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Handles user interaction and displays information related to news management.
 * This class acts as the presentation layer, responsible for taking user input
 * and displaying output messages.
 */
public class NewsView {
    private final Scanner scanner;

    /**
     * Constructs a NewsView with a new Scanner for input.
     */
    public NewsView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void displayWelcomeMessage() {
        System.out.println("=======================================");
        System.out.println("   Welcome to the News Management System");
        System.out.println("=======================================");
    }

    /**
     * Displays a message indicating the start of the news editing functionality.
     */
    public void displayActivateEditingMessage() {
        System.out.println("\n--- Activating News Editing Functionality ---");
    }

    /**
     * Displays a list of news articles to the user.
     *
     * @param newsList The list of News objects to display.
     */
    public void displayNewsList(List<News> newsList) {
        if (newsList.isEmpty()) {
            System.out.println("No news articles available.");
            return;
        }
        System.out.println("\n--- Available News Articles ---");
        for (int i = 0; i < newsList.size(); i++) {
            News news = newsList.get(i);
            System.out.printf("%d. ID: %s, Title: %s, Author: %s, Publication Date: %s%n",
                    (i + 1), news.getId(), news.getTitle(), news.getAuthor(), news.getPublicationDate());
        }
        System.out.println("-------------------------------");
    }

    /**
     * Prompts the user to select a news article by its ID.
     *
     * @return The ID of the selected news article, or null if the user cancels.
     */
    public String getNewsIdSelection() {
        System.out.print("Enter the ID of the news to edit (or 'cancel' to abort): ");
        String input = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(input.trim())) {
            return null;
        }
        return input.trim();
    }

    /**
     * Displays the details of a specific news article in an editable form.
     *
     * @param news The News object whose details are to be displayed.
     */
    public void displayNewsForEditing(News news) {
        System.out.println("\n--- Editing News Article (ID: " + news.getId() + ") ---");
        System.out.println("Current Title: " + news.getTitle());
        System.out.println("Current Content: " + news.getContent());
        System.out.println("Current Author: " + news.getAuthor());
        System.out.println("Current Publication Date: " + news.getPublicationDate());
        System.out.println("---------------------------------------");
        System.out.println("Enter new values (leave blank to keep current value):");
    }

    /**
     * Prompts the user for new news data and returns it as an array of strings.
     * The array elements are: [title, content, author, publicationDate].
     *
     * @param currentNews The current News object to provide default values.
     * @return A String array containing the new or unchanged news data.
     */
    public String[] getModifiedNewsData(News currentNews) {
        String[] modifiedData = new String[4]; // title, content, author, publicationDate

        System.out.print("New Title (current: '" + currentNews.getTitle() + "'): ");
        String title = scanner.nextLine().trim();
        modifiedData[0] = title.isEmpty() ? currentNews.getTitle() : title;

        System.out.print("New Content (current: '" + currentNews.getContent() + "'): ");
        String content = scanner.nextLine().trim();
        modifiedData[1] = content.isEmpty() ? currentNews.getContent() : content;

        System.out.print("New Author (current: '" + currentNews.getAuthor() + "'): ");
        String author = scanner.nextLine().trim();
        modifiedData[2] = author.isEmpty() ? currentNews.getAuthor() : author;

        System.out.print("New Publication Date (yyyy-MM-dd HH:mm, current: '" + currentNews.getPublicationDate() + "'): ");
        String pubDateStr = scanner.nextLine().trim();
        modifiedData[3] = pubDateStr.isEmpty() ? currentNews.getPublicationDate().toString() : pubDateStr;

        return modifiedData;
    }

    /**
     * Displays the modified news information and asks for confirmation.
     *
     * @param news The News object with modified data to confirm.
     * @return true if the user confirms, false otherwise.
     */
    public boolean confirmModification(News news) {
        System.out.println("\n--- Please Confirm Modified News Information ---");
        System.out.println("ID: " + news.getId());
        System.out.println("Title: " + news.getTitle());
        System.out.println("Content: " + news.getContent());
        System.out.println("Author: " + news.getAuthor());
        System.out.println("Publication Date: " + news.getPublicationDate());
        System.out.print("Confirm changes? (yes/no): ");
        String confirmation = scanner.nextLine().trim();
        return "yes".equalsIgnoreCase(confirmation);
    }

    /**
     * Displays a success message after news modification.
     *
     * @param newsId The ID of the news that was successfully modified.
     */
    public void displayModificationSuccess(String newsId) {
        System.out.println("\nSuccess: News article with ID '" + newsId + "' has been successfully updated.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\nError: " + message);
    }

    /**
     * Displays a message when the operation is cancelled by the user.
     */
    public void displayOperationCancelled() {
        System.out.println("\nOperation cancelled by user.");
    }

    /**
     * Displays a generic goodbye message.
     */
    public void displayGoodbyeMessage() {
        System.out.println("\nThank you for using the News Management System. Goodbye!");
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param dateTimeString The string to parse, expected in "yyyy-MM-dd HH:mm" format.
     * @return An Optional containing the LocalDateTime if parsing is successful, or an empty Optional otherwise.
     */
    public Optional<LocalDateTime> parseDateTime(String dateTimeString) {
        try {
            // Assuming format "yyyy-MM-dd HH:mm"
            return Optional.of(LocalDateTime.parse(dateTimeString, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (DateTimeParseException e) {
            displayErrorMessage("Invalid date/time format. Please use yyyy-MM-dd HH:mm.");
            return Optional.empty();
        }
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}