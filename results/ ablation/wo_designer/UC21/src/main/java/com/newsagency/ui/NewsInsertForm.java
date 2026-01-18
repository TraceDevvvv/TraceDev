package com.newsagency.ui;

import com.newsagency.model.News;
import com.newsagency.service.NewsService;
import com.newsagency.service.NewsService.InsertResult;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Simulates the UI form for inserting a news item.
 * This is a console-based simulation for demonstration.
 */
public class NewsInsertForm {
    private Scanner scanner = new Scanner(System.in);
    private NewsService newsService = new NewsService();

    /**
     * Displays the form and handles the interaction flow.
     * Assumes the Agency Operator HAS logged in (Entry Condition).
     */
    public void displayForm() {
        System.out.println("=== News Insertion Form ===");

        // Step 1 & 2 are implicit: activating the feature and displaying the form.

        // Step 3: Agency Operator fills out the form.
        News news = new News();

        System.out.print("Enter news title: ");
        news.setTitle(scanner.nextLine());

        System.out.print("Enter news content: ");
        news.setContent(scanner.nextLine());

        System.out.print("Enter author: ");
        news.setAuthor(scanner.nextLine());

        System.out.print("Enter category: ");
        news.setCategory(scanner.nextLine());

        // Publication date is optional; if left empty, current time will be used.
        System.out.print("Enter publication date (YYYY-MM-DDTHH:MM:SS) or press Enter for now: ");
        String dateInput = scanner.nextLine();
        if (!dateInput.trim().isEmpty()) {
            try {
                LocalDateTime pubDate = LocalDateTime.parse(dateInput);
                news.setPublicationDate(pubDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Using current time.");
            }
        }

        // Step 4: Agency Operator submits the form.
        System.out.println("\nSubmitting the form...");

        // Call the service to process insertion.
        InsertResult result = newsService.insertNews(news);

        // Display result.
        System.out.println("\n" + result.getMessage());
        if (result.isSuccess() && result.getInsertedNews() != null) {
            System.out.println("Inserted News Details: " + result.getInsertedNews());
        }

        scanner.close();
    }

    public static void main(String[] args) {
        NewsInsertForm form = new NewsInsertForm();
        form.displayForm();
    }
}