package com.news.view;

import com.news.controller.NewsController;
import com.news.entity.News;
import java.util.List;
import java.util.Scanner;

/**
 * View class that interacts with the user (Agency Operator).
 * Implements rendering, confirmation dialog, and message display.
 * Includes authentication check as per sequence diagram.
 */
public class NewsListView {
    private NewsController controller;
    private Scanner scanner;

    public NewsListView(NewsController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates authentication check as per entry condition in sequence diagram.
     */
    public boolean verifyAuthentication() {
        // Simplified: always successful for this example.
        // In a real system, this would check login status.
        System.out.println("Authentication confirmed.");
        return true;
    }

    public void renderNewsList(List<News> newsList) {
        System.out.println("=== News List ===");
        for (News news : newsList) {
            System.out.println("ID: " + news.getId() + ", Title: " + news.getTitle());
        }
        System.out.println("=================");
    }

    public boolean showConfirmationDialog(Long newsId) {
        System.out.print("Are you sure you want to delete news with ID " + newsId + "? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void listAllNews() {
        List<News> newsList = controller.listAllNews();
        renderNewsList(newsList);
    }

    /**
     * Main interaction method simulating the sequence diagram flow.
     * This is a simplified simulation; in a real UI this would be event-driven.
     */
    public void simulateUserInteraction() {
        // Entry condition authentication
        if (!verifyAuthentication()) {
            displayMessage("Please log in first");
            return;
        }

        // Step 1-3: List news
        listAllNews();

        // Step 4: Assume user selects a news (here we pick the first for demonstration)
        List<News> newsList = controller.listAllNews();
        if (!newsList.isEmpty()) {
            Long selectedId = newsList.get(0).getId();
            boolean confirmed = showConfirmationDialog(selectedId);
            if (confirmed) {
                // Step 7-8: Confirm and delete
                String result = controller.confirmAndDelete(selectedId);
                displayMessage(result);
            } else {
                displayMessage("Operation cancelled");
            }
        } else {
            displayMessage("No news available to delete.");
        }
    }
}