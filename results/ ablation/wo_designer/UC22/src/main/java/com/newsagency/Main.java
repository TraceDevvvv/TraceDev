package com.newsagency;

import com.newsagency.service.NewsService;
import java.util.Scanner;

/**
 * Main application class for News Agency System.
 * Demonstrates the deletion use case.
 */
public class Main {
    public static void main(String[] args) {
        NewsService newsService = new NewsService();
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("=== News Agency System - Delete News Use Case ===\n");

        // Entry Condition: Agency Operator IS logged in.
        newsService.login();

        // If not logged in, exit.
        if (!newsService.isLoggedIn()) {
            System.out.println("Cannot proceed without login.");
            return;
        }

        // Main loop for the use case.
        boolean exit = false;
        while (!exit) {
            System.out.println("\nOptions:");
            System.out.println("1 - Delete a news");
            System.out.println("2 - Simulate server interruption");
            System.out.println("3 - Exit");
            System.out.print("Choose an option: ");

            String input = mainScanner.nextLine();
            switch (input) {
                case "1":
                    // Step 1: Agency Operator activates the function.
                    newsService.activateDeleteNews();
                    break;
                case "2":
                    // Simulate interruption exit condition.
                    newsService.simulateServerInterruption();
                    break;
                case "3":
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        // Clean up.
        newsService.close();
        mainScanner.close();
    }
}