package com.newsagency.service;

import com.newsagency.model.News;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Service class handling business logic for News operations.
 */
public class NewsService {
    private List<News> newsList;
    private Scanner scanner;
    private boolean isLoggedIn;

    public NewsService() {
        newsList = new ArrayList<>();
        scanner = new Scanner(System.in);
        isLoggedIn = false;
        // Initialize with some dummy data for demonstration
        initializeDummyData();
    }

    private void initializeDummyData() {
        newsList.add(new News(1, "Java 21 Released", "New features in Java 21...", LocalDateTime.now().minusDays(5), true));
        newsList.add(new News(2, "AI Advances", "Recent breakthroughs in AI...", LocalDateTime.now().minusDays(3), true));
        newsList.add(new News(3, "Cybersecurity Alert", "New vulnerabilities discovered...", LocalDateTime.now().minusDays(1), true));
    }

    /**
     * Simulates login. In a real application, this would involve authentication.
     */
    public void login() {
        System.out.println("Logging in as Agency Operator...");
        isLoggedIn = true;
        System.out.println("Login successful.\n");
    }

    /**
     * Checks if the user is logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Activates the delete function - main flow for deleting a news.
     */
    public void activateDeleteNews() {
        if (!isLoggedIn) {
            System.out.println("Error: Agency Operator must be logged in.");
            return;
        }

        // Step 2: System views all news in a form.
        viewAllNews();

        // Step 3: Agency Operator selects a news from the list.
        News selectedNews = selectNews();
        if (selectedNews == null) {
            System.out.println("No news selected. Operation cancelled.");
            return;
        }

        // Step 4: Agency Operator submits the form (implicit in selection).
        // Step 5: System asks for confirmation.
        boolean confirmed = askForConfirmation(selectedNews);
        if (!confirmed) {
            System.out.println("Deletion cancelled by operator.");
            return;
        }

        // Step 6: Operator confirms (handled above).
        // Step 7: System deletes the data news.
        boolean success = deleteNews(selectedNews.getId());
        if (success) {
            System.out.println("Success: News deleted successfully.");
        } else {
            System.out.println("Error: Could not delete news.");
        }
    }

    /**
     * Displays all active news (Step 2).
     */
    private void viewAllNews() {
        System.out.println("===== LIST OF ALL NEWS =====");
        if (newsList.isEmpty()) {
            System.out.println("No news available.");
        } else {
            for (News news : newsList) {
                if (news.isActive()) {
                    System.out.println(news);
                }
            }
        }
        System.out.println("============================\n");
    }

    /**
     * Prompts user to select a news by ID (Step 3).
     * @return selected News object, or null if invalid/cancelled.
     */
    private News selectNews() {
        System.out.print("Enter the ID of the news you want to delete (or 0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return null; // Cancelled
            }
            for (News news : newsList) {
                if (news.getId() == choice && news.isActive()) {
                    return news;
                }
            }
            System.out.println("Invalid ID or news not found.");
            return selectNews(); // Retry
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectNews();
        }
    }

    /**
     * Asks for confirmation before deletion (Step 5).
     * @param news the news to be deleted.
     * @return true if confirmed, false otherwise.
     */
    private boolean askForConfirmation(News news) {
        System.out.println("You are about to delete the following news:");
        System.out.println(news);
        System.out.print("Are you sure? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Deletes a news by setting its active flag to false (soft delete).
     * Alternatively could remove from list.
     * @param newsId ID of the news to delete.
     * @return true if deletion successful, false otherwise.
     */
    private boolean deleteNews(int newsId) {
        for (News news : newsList) {
            if (news.getId() == newsId) {
                news.setActive(false); // Soft delete
                return true;
            }
        }
        return false;
    }

    /**
     * Simulates server connection interruption.
     */
    public void simulateServerInterruption() {
        System.out.println("Connection to server ETOUR interrupted.");
    }

    /**
     * Closes resources.
     */
    public void close() {
        scanner.close();
    }
}