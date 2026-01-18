package com.example.news.ui;

import com.example.news.entity.News;
import com.example.news.service.NewsService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Console-based UI for Agency Operator to edit news.
 * Implements the flow of events from the use case.
 */
public class NewsEditorUI {
    private final NewsService newsService;
    private final Scanner scanner;
    private boolean exitRequested = false;

    public NewsEditorUI(NewsService newsService) {
        this.newsService = newsService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main UI loop.
     */
    public void start() {
        System.out.println("=== News Management System ===");
        
        // Entry Condition: Agency Operator must be logged in
        System.out.print("Please log in (press Enter to login as Agency Operator): ");
        scanner.nextLine();
        newsService.login();
        System.out.println("Login successful.\n");

        while (!exitRequested) {
            displayMainMenu();
        }
        
        scanner.close();
        System.out.println("System closed.");
    }

    private void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View and Edit News");
        System.out.println("2. Exit");
        System.out.print("Select option: ");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                editNewsFlow();
                break;
            case "2":
                exitRequested = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Implements the full flow of events from the use case.
     */
    private void editNewsFlow() {
        // Step 1-2: Activate editing and view all news
        System.out.println("\n--- Available News ---");
        List<News> allNews = newsService.getAllNews();
        if (allNews.isEmpty()) {
            System.out.println("No news articles found.");
            return;
        }
        
        for (News news : allNews) {
            System.out.println(news);
        }

        // Step 3: Agency Operator selects a news
        System.out.print("\nEnter News ID to edit (or 0 to cancel): ");
        Long selectedId;
        try {
            selectedId = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Operation cancelled.");
            return;
        }
        
        if (selectedId == 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        // Step 5-6: System loads and displays data for editing
        Optional<News> newsOpt = newsService.loadNews(selectedId);
        if (!newsOpt.isPresent()) {
            System.out.println("News not found with ID: " + selectedId);
            return;
        }
        
        News news = newsOpt.get();
        System.out.println("\n--- Editing News ---");
        displayNewsForm(news);

        // Step 7: Agency Operator changes data
        News updatedNews = getUpdatedNewsFromUser(news);
        
        // Step 8: Agency Operator submits the form (implicit in our flow)
        // Step 9-10: System validates and asks for confirmation
        System.out.println("\n--- Review Changes ---");
        System.out.println("Original Title: " + news.getTitle());
        System.out.println("Updated Title: " + updatedNews.getTitle());
        System.out.println("Original Content: " + (news.getContent().length() > 50 ? news.getContent().substring(0, 50) + "..." : news.getContent()));
        System.out.println("Updated Content: " + (updatedNews.getContent().length() > 50 ? updatedNews.getContent().substring(0, 50) + "..." : updatedNews.getContent()));
        
        System.out.print("\nConfirm update? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        boolean confirmed = confirmation.equals("yes") || confirmation.equals("y");
        
        // Step 11-12: Process update based on confirmation
        String result = newsService.updateNews(news, updatedNews, confirmed);
        System.out.println("\nResult: " + result);
        
        // Exit Conditions handled in updateNews method
    }

    private void displayNewsForm(News news) {
        System.out.println("ID: " + news.getId());
        System.out.println("Current Title: " + news.getTitle());
        System.out.println("Current Content: " + news.getContent());
        System.out.println("Current Author: " + news.getAuthor());
        System.out.println("Published: " + news.getPublishDate());
        System.out.println("Last Modified: " + news.getLastModified());
    }

    private News getUpdatedNewsFromUser(News originalNews) {
        News updated = new News();
        updated.setId(originalNews.getId());
        
        System.out.println("\nEnter new values (press Enter to keep current):");
        
        System.out.print("Title [" + originalNews.getTitle() + "]: ");
        String titleInput = scanner.nextLine();
        updated.setTitle(titleInput.isEmpty() ? originalNews.getTitle() : titleInput);
        
        System.out.print("Content [" + (originalNews.getContent().length() > 20 ? originalNews.getContent().substring(0, 20) + "..." : originalNews.getContent()) + "]: ");
        String contentInput = scanner.nextLine();
        updated.setContent(contentInput.isEmpty() ? originalNews.getContent() : contentInput);
        
        System.out.print("Author [" + originalNews.getAuthor() + "]: ");
        String authorInput = scanner.nextLine();
        updated.setAuthor(authorInput.isEmpty() ? originalNews.getAuthor() : authorInput);
        
        // Keep original publish date
        updated.setPublishDate(originalNews.getPublishDate());
        
        return updated;
    }
}